package com.cihatakyol.vibrationmeter.domain.usecase

import com.cihatakyol.vibrationmeter.domain.repository.VibrationRepository
import javax.inject.Inject

/**
 * Use case for resetting the maximum vibration record.
 * Provides business logic for clearing max vibration data.
 */
class ResetMaxVibrationUseCase @Inject constructor(
    private val repository: VibrationRepository
) {
    /**
     * Reset the maximum vibration record.
     * Clears all stored max vibration data.
     */
    suspend operator fun invoke() {
        repository.resetMaxVibrationRecord()
    }
}
