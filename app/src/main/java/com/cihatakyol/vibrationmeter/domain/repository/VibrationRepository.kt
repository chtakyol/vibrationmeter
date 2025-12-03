package com.cihatakyol.vibrationmeter.domain.repository

import com.cihatakyol.vibrationmeter.domain.model.MaxVibrationRecord
import com.cihatakyol.vibrationmeter.domain.model.VibrationPoint
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for vibration data operations.
 * Provides abstraction over data sources.
 */
interface VibrationRepository {
    /**
     * Get real-time vibration measurements as a Flow.
     * Emits new VibrationPoint whenever sensor data changes.
     */
    fun getVibrationMeasurements(): Flow<VibrationPoint>

    /**
     * Get the maximum vibration record from persistent storage.
     *
     * @return Flow of MaxVibrationRecord or null if no record exists
     */
    fun getMaxVibrationRecord(): Flow<MaxVibrationRecord?>

    /**
     * Save a new maximum vibration record to persistent storage.
     *
     * @param record The max vibration record to save
     */
    suspend fun saveMaxVibrationRecord(record: MaxVibrationRecord)

    /**
     * Reset the maximum vibration record.
     */
    suspend fun resetMaxVibrationRecord()

    /**
     * Start listening to sensor data.
     */
    fun startSensorListening()

    /**
     * Stop listening to sensor data.
     */
    fun stopSensorListening()
}
