package com.cihatakyol.vibrationmeter.presentation.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cihatakyol.vibrationmeter.domain.model.Session
import com.cihatakyol.vibrationmeter.domain.model.VibrationPoint
import com.cihatakyol.vibrationmeter.domain.usecase.GetMaxVibrationUseCase
import com.cihatakyol.vibrationmeter.domain.usecase.MeasureVibrationUseCase
import com.cihatakyol.vibrationmeter.domain.usecase.ResetMaxVibrationUseCase
import com.cihatakyol.vibrationmeter.domain.usecase.SaveMaxVibrationUseCase
import com.cihatakyol.vibrationmeter.domain.usecase.SaveSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

/**
 * ViewModel for the Vibration screen.
 * Manages vibration measurement state and business logic.
 */
@HiltViewModel
class VibrationViewModel @Inject constructor(
    private val measureVibrationUseCase: MeasureVibrationUseCase,
    private val saveMaxVibrationUseCase: SaveMaxVibrationUseCase,
    private val getMaxVibrationUseCase: GetMaxVibrationUseCase,
    private val resetMaxVibrationUseCase: ResetMaxVibrationUseCase,
    private val saveSessionUseCase: SaveSessionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<VibrationUiState>(VibrationUiState.Initial)
    val uiState: StateFlow<VibrationUiState> = _uiState.asStateFlow()

    private val _vibrationHistory = MutableStateFlow<List<VibrationPoint>>(emptyList())

    // Session recording state
    private val _isRecording = MutableStateFlow(false)
    val isRecording: StateFlow<Boolean> = _isRecording.asStateFlow()

    private val _recordingDuration = MutableStateFlow(0L)
    val recordingDuration: StateFlow<Long> = _recordingDuration.asStateFlow()

    private var sessionStartTime: Long = 0L
    private var sessionDataPoints = mutableListOf<VibrationPoint>()
    private var sessionMaxVibration: Float = 0f
    private var sessionMinVibration: Float = Float.MAX_VALUE
    private var sessionTotalVibration: Float = 0f
    private var sessionDataPointCount: Int = 0

    private var measurementJob: Job? = null
    private var recordingTimerJob: Job? = null

    companion object {
        private const val MAX_HISTORY_DURATION_MS = 30_000L // 30 seconds
        private const val MAX_HISTORY_POINTS = 1000 // Limit memory usage
    }

    init {
        startMeasurement()
    }

    /**
     * Start vibration measurement.
     * Listens to sensor data and updates UI state.
     */
    fun startMeasurement() {
        measurementJob?.cancel()

        _uiState.value = VibrationUiState.Loading

        measurementJob = viewModelScope.launch {
            try {
                // Combine sensor measurements with max vibration record
                combine(
                    measureVibrationUseCase(),
                    getMaxVibrationUseCase()
                ) { vibrationPoint, maxRecord ->
                    Triple(vibrationPoint, maxRecord, _vibrationHistory.value)
                }
                    .catch { error ->
                        _uiState.value = VibrationUiState.Error(
                            error.message ?: "Failed to measure vibration"
                        )
                    }
                    .collect { (vibrationPoint, maxRecord, currentHistory) ->
                        // Update vibration history
                        val updatedHistory = updateVibrationHistory(vibrationPoint, currentHistory)
                        _vibrationHistory.value = updatedHistory

                        // If recording, add to session data
                        if (_isRecording.value) {
                            sessionDataPoints.add(vibrationPoint)
                            sessionMaxVibration = maxOf(sessionMaxVibration, vibrationPoint.magnitude)
                            sessionMinVibration = minOf(sessionMinVibration, vibrationPoint.magnitude)
                            sessionTotalVibration += vibrationPoint.magnitude
                            sessionDataPointCount++
                        }

                        // Check if we have a new max vibration
                        val currentMaxMagnitude = maxRecord?.magnitude ?: 0f
                        if (vibrationPoint.magnitude > currentMaxMagnitude) {
                            saveMaxVibrationUseCase(
                                newMagnitude = vibrationPoint.magnitude,
                                timestamp = vibrationPoint.timestamp,
                                currentMaxMagnitude = currentMaxMagnitude
                            )
                        }

                        // Update UI state
                        _uiState.value = VibrationUiState.Success(
                            currentVibration = vibrationPoint.magnitude,
                            maxVibration = maxOf(vibrationPoint.magnitude, currentMaxMagnitude),
                            maxVibrationTimestamp = if (vibrationPoint.magnitude > currentMaxMagnitude) {
                                vibrationPoint.timestamp
                            } else {
                                maxRecord?.timestamp ?: 0L
                            },
                            vibrationHistory = updatedHistory
                        )
                    }
            } catch (e: Exception) {
                _uiState.value = VibrationUiState.Error(
                    e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    /**
     * Stop vibration measurement.
     */
    fun stopMeasurement() {
        measurementJob?.cancel()
        measurementJob = null
    }

    /**
     * Reset the maximum vibration record.
     */
    fun resetMaxVibration() {
        viewModelScope.launch {
            resetMaxVibrationUseCase()
        }
    }

    /**
     * Update vibration history with new point.
     * Maintains a sliding window of the last 30 seconds.
     */
    private fun updateVibrationHistory(
        newPoint: VibrationPoint,
        currentHistory: List<VibrationPoint>
    ): List<VibrationPoint> {
        val cutoffTime = newPoint.timestamp - MAX_HISTORY_DURATION_MS

        // Remove old points and add new point
        val updatedHistory = (currentHistory + newPoint)
            .filter { it.timestamp >= cutoffTime }
            .takeLast(MAX_HISTORY_POINTS) // Limit to prevent memory issues

        return updatedHistory
    }

    /**
     * Start recording a session.
     */
    fun startRecording() {
        if (_isRecording.value) return

        _isRecording.value = true
        sessionStartTime = System.currentTimeMillis()
        sessionDataPoints.clear()
        sessionMaxVibration = 0f
        sessionMinVibration = Float.MAX_VALUE
        sessionTotalVibration = 0f
        sessionDataPointCount = 0

        // Start recording duration timer
        recordingTimerJob = viewModelScope.launch {
            while (_isRecording.value) {
                _recordingDuration.value = System.currentTimeMillis() - sessionStartTime
                delay(1000) // Update every second
            }
        }
    }

    /**
     * Stop recording and save the session.
     */
    fun stopRecording() {
        if (!_isRecording.value) return

        _isRecording.value = false
        recordingTimerJob?.cancel()

        val endTime = System.currentTimeMillis()
        val duration = endTime - sessionStartTime

        // Calculate average
        val avgVibration = if (sessionDataPointCount > 0) {
            sessionTotalVibration / sessionDataPointCount
        } else {
            0f
        }

        // Create and save session
        val session = Session(
            id = UUID.randomUUID().toString(),
            startTimestamp = sessionStartTime,
            endTimestamp = endTime,
            duration = duration,
            maxVibration = sessionMaxVibration,
            avgVibration = avgVibration,
            minVibration = if (sessionMinVibration == Float.MAX_VALUE) 0f else sessionMinVibration,
            dataPoints = sessionDataPoints.toList()
        )

        viewModelScope.launch {
            saveSessionUseCase(session)
        }

        // Reset recording duration
        _recordingDuration.value = 0L
    }

    override fun onCleared() {
        super.onCleared()
        stopMeasurement()
        recordingTimerJob?.cancel()
    }
}
