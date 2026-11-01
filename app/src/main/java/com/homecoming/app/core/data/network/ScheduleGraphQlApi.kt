package com.homecoming.app.core.data.network

import com.homecoming.app.core.model.Event
import com.homecoming.app.core.model.HomeHighlight
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

@Singleton
internal class ScheduleGraphQlApi @Inject constructor(
    private val client: OkHttpClient,
    private val json: Json,
    @Named("graphqlUrl") private val graphQlUrl: String,
) {

    suspend fun fetchEvents(): Result<List<Event>> = withContext(Dispatchers.IO) {
        runCatching {
            val body = json.encodeToString(
                GraphQlRequest.serializer(),
                GraphQlRequest(EVENTS_QUERY),
            )
            val response = post(body)
            val envelope = json.decodeFromString<EventsEnvelope>(response)
            envelope.data?.events?.map { it.toDomain() }.orEmpty()
        }
    }

    suspend fun fetchHomeHighlight(): Result<HomeHighlight?> = withContext(Dispatchers.IO) {
        runCatching {
            val body = json.encodeToString(
                GraphQlRequest.serializer(),
                GraphQlRequest(HOME_HIGHLIGHT_QUERY),
            )
            val response = post(body)
            val envelope = json.decodeFromString<HomeHighlightEnvelope>(response)
            if (envelope.errors?.isNotEmpty() == true) {
                return@runCatching null
            }
            envelope.data?.homeHighlight?.toDomain()
        }
    }

    private fun post(jsonBody: String): String {
        val request = Request.Builder()
            .url(graphQlUrl)
            .post(jsonBody.toRequestBody(JSON_MEDIA_TYPE))
            .build()
        client.newCall(request).execute().use { response ->
            val payload = response.body?.string().orEmpty()
            if (!response.isSuccessful) {
                error("GraphQL HTTP ${response.code}: $payload")
            }
            return payload
        }
    }

    @Serializable
    private data class GraphQlRequest(val query: String)

    @Serializable
    private data class EventsEnvelope(val data: EventsData? = null)

    @Serializable
    private data class EventsData(val events: List<EventDto> = emptyList())

    @Serializable
    private data class HomeHighlightEnvelope(
        val data: HomeHighlightData? = null,
        val errors: List<GraphQlError>? = null,
    )

    @Serializable
    private data class GraphQlError(val message: String? = null)

    @Serializable
    private data class HomeHighlightData(val homeHighlight: HomeHighlightDto? = null)

    @Serializable
    private data class EventDto(
        val id: String,
        val title: String,
        @SerialName("scheduleLabel") val scheduleLabel: String,
        val location: String,
    ) {
        fun toDomain() = Event(id, title, scheduleLabel, location)
    }

    @Serializable
    private data class HomeHighlightDto(
        val title: String,
        val dates: String,
    ) {
        fun toDomain() = HomeHighlight(title, dates)
    }

    private companion object {
        val JSON_MEDIA_TYPE = "application/json".toMediaType()

        const val EVENTS_QUERY = """
            query Events {
              events { id title scheduleLabel location }
            }
        """

        const val HOME_HIGHLIGHT_QUERY = """
            query HomeHighlight {
              homeHighlight { title dates }
            }
        """
    }
}
