package com.cihatakyol.vibrationmeter.data.repository

import com.cihatakyol.vibrationmeter.data.source.AccelerometerDataSource
import com.cihatakyol.vibrationmeter.data.source.VibrationPreferencesDataSource
import com.cihatakyol.vibrationmeter.domain.model.MaxVibrationRecord
import com.cihatakyol.vibrationmeter.domain.model.VibrationPoint
import com.cihatakyol.vibrationmeter.domain.repository.VibrationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of VibrationRepository.
 * Coordinates between accelerometer data source and preferences data source.
 */
@Singleton
class VibrationRepositoryImpl @Inject constructor(
    private val accelerometerDataSource: AccelerometerDataSource,
    private val preferencesDataSource: VibrationPreferencesDataSource
) : VibrationRepository {

    override fun getVibrationMeasurements(): Flow<VibrationPoint> {
        return accelerometerDataSource.getVibrationFlow()
    }

    override fun getMaxVibrationRecord(): Flow<MaxVibrationRecord?> {
        return preferencesDataSource.getMaxVibrationRecord()
    }

    override suspend fun saveMaxVibrationRecord(record: MaxVibrationRecord) {
        preferencesDataSource.saveMaxVibrationRecord(record)
    }

    override suspend fun resetMaxVibrationRecord() {
        preferencesDataSource.resetMaxVibrationRecord()
    }

    override fun startSensorListening() {
        // Flow-based approach handles this automatically
        // This method is kept for potential future manual control
    }

    override fun stopSensorListening() {
        // Flow-based approach handles this automatically
        // This method is kept for potential future manual control
    }
}
