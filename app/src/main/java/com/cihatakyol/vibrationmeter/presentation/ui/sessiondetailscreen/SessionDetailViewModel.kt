package com.cihatakyol.vibrationmeter.presentation.ui.sessiondetailscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cihatakyol.vibrationmeter.domain.model.Session
import com.cihatakyol.vibrationmeter.domain.usecase.DeleteSessionUseCase
import com.cihatakyol.vibrationmeter.domain.usecase.ExportSessionUseCase
import com.cihatakyol.vibrationmeter.domain.usecase.GetSessionByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Session Detail screen.
 */
@HiltViewModel
class SessionDetailViewModel @Inject constructor(
    private val getSessionByIdUseCase: GetSessionByIdUseCase,
    private val deleteSessionUseCase: DeleteSessionUseCase,
    private val exportSessionUseCase: ExportSessionUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val sessionId: String = checkNotNull(savedStateHandle["sessionId"])

    private val _uiState = MutableStateFlow<SessionDetailUiState>(SessionDetailUiState.Loading)
    val uiState: StateFlow<SessionDetailUiState> = _uiState.asStateFlow()

    init {
        loadSession()
    }

    /**
     * Load session details.
     */
    private fun loadSession() {
        viewModelScope.launch {
            getSessionByIdUseCase(sessionId)
                .catch { error ->
                    _uiState.value = SessionDetailUiState.Error(
                        error.message ?: "Failed to load session"
                    )
                }
                .collect { session ->
                    _uiState.value = if (session != null) {
                        SessionDetailUiState.Success(session)
                    } else {
                        SessionDetailUiState.Error("Session not found")
                    }
                }
        }
    }

    /**
     * Delete the current session.
     */
    fun deleteSession(onDeleted: () -> Unit) {
        viewModelScope.launch {
            try {
                deleteSessionUseCase(sessionId)
                onDeleted()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    /**
     * Export session as CSV.
     */
    fun exportAsCsv(): String? {
        val currentState = _uiState.value
        return if (currentState is SessionDetailUiState.Success) {
            exportSessionUseCase.exportAsCsv(currentState.session)
        } else {
            null
        }
    }

    /**
     * Export session as JSON.
     */
    fun exportAsJson(): String? {
        val currentState = _uiState.value
        return if (currentState is SessionDetailUiState.Success) {
            exportSessionUseCase.exportAsJson(currentState.session)
        } else {
            null
        }
    }
}

/**
 * UI state for Session Detail screen.
 */
sealed interface SessionDetailUiState {
    data object Loading : SessionDetailUiState
    data class Success(val session: Session) : SessionDetailUiState
    data class Error(val message: String) : SessionDetailUiState
}
