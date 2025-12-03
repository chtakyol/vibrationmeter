package com.cihatakyol.vibrationmeter.domain.usecase

import com.cihatakyol.vibrationmeter.domain.model.MaxVibrationRecord
import com.cihatakyol.vibrationmeter.domain.repository.VibrationRepository
import javax.inject.Inject

/**
 * Use case for saving maximum vibration record.
 * Provides business logic for persisting max vibration data.
 */
class SaveMaxVibrationUseCase @Inject constructor(
    private val repository: VibrationRepository
) {
    /**
     * Save a new maximum vibration record.
     * Only saves if the new magnitude is greater than the existing one.
     *
     * @param newMagnitude New vibration magnitude in m/s²
     * @param timestamp Timestamp of the measurement
     * @param currentMaxMagnitude Current maximum magnitude (0 if none exists)
     */
    suspend operator fun invoke(
        newMagnitude: Float,
        timestamp: Long,
        currentMaxMagnitude: Float = 0f
    ) {
        if (newMagnitude > currentMaxMagnitude) {
            val record = MaxVibrationRecord(
                magnitude = newMagnitude,
                timestamp = timestamp
            )
            repository.saveMaxVibrationRecord(record)
        }
    }

    /**
     * Save a maximum vibration record directly.
     *
     * @param record The max vibration record to save
     */
    suspend fun saveRecord(record: MaxVibrationRecord) {
        repository.saveMaxVibrationRecord(record)
    }
}
