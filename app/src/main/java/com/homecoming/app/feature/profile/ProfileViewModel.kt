package com.homecoming.app.feature.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onDisplayNameChange(value: String) {
        _uiState.update { it.copy(displayName = value) }
    }

    fun onGraduationYearChange(raw: String) {
        _uiState.update { it.copy(graduationYear = raw.filter(Char::isDigit).take(4)) }
    }
}
