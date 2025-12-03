package com.cihatakyol.vibrationmeter.presentation.ui.sessionsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cihatakyol.vibrationmeter.domain.model.SessionSummary
import com.cihatakyol.vibrationmeter.domain.usecase.DeleteSessionUseCase
import com.cihatakyol.vibrationmeter.domain.usecase.GetAllSessionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Session List screen.
 */
@HiltViewModel
class SessionListViewModel @Inject constructor(
    private val getAllSessionsUseCase: GetAllSessionsUseCase,
    private val deleteSessionUseCase: DeleteSessionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SessionListUiState>(SessionListUiState.Loading)
    val uiState: StateFlow<SessionListUiState> = _uiState.asStateFlow()

    init {
        loadSessions()
    }

    /**
     * Load all sessions.
     */
    private fun loadSessions() {
        viewModelScope.launch {
            getAllSessionsUseCase()
                .catch { error ->
                    _uiState.value = SessionListUiState.Error(
                        error.message ?: "Failed to load sessions"
                    )
                }
                .collect { sessions ->
                    _uiState.value = if (sessions.isEmpty()) {
                        SessionListUiState.Empty
                    } else {
                        SessionListUiState.Success(sessions)
                    }
                }
        }
    }

    /**
     * Delete a session.
     */
    fun deleteSession(sessionId: String) {
        viewModelScope.launch {
            try {
                deleteSessionUseCase(sessionId)
            } catch (e: Exception) {
                // Handle error - could emit a UI event
            }
        }
    }
}

/**
 * UI state for Session List screen.
 */
sealed interface SessionListUiState {
    data object Loading : SessionListUiState
    data object Empty : SessionListUiState
    data class Success(val sessions: List<SessionSummary>) : SessionListUiState
    data class Error(val message: String) : SessionListUiState
}
