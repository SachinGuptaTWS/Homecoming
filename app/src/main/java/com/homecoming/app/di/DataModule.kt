package com.homecoming.app.di

import com.homecoming.app.core.data.EventRepository
import com.homecoming.app.core.data.GraphqlEventRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindEventRepository(impl: GraphqlEventRepository): EventRepository
}
