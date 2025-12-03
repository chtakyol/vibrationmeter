package com.cihatakyol.vibrationmeter.presentation.ui.mainscreen

import com.cihatakyol.vibrationmeter.domain.model.VibrationPoint

/**
 * UI state for the Vibration screen.
 * Sealed interface pattern for type-safe state management.
 */
sealed interface VibrationUiState {
    /**
     * Initial state before any measurements.
     */
    data object Initial : VibrationUiState

    /**
     * Loading state while initializing sensors.
     */
    data object Loading : VibrationUiState

    /**
     * Success state with vibration data.
     *
     * @property currentVibration Current vibration magnitude in m/s²
     * @property maxVibration Maximum vibration magnitude recorded
     * @property maxVibrationTimestamp Timestamp of max vibration event
     * @property vibrationHistory List of recent vibration points for graphing (last 30 seconds)
     */
    data class Success(
        val currentVibration: Float = 0f,
        val maxVibration: Float = 0f,
        val maxVibrationTimestamp: Long = 0L,
        val vibrationHistory: List<VibrationPoint> = emptyList()
    ) : VibrationUiState

    /**
     * Error state when sensor is unavailable or other issues occur.
     *
     * @property message Error message to display
     */
    data class Error(val message: String) : VibrationUiState
}
