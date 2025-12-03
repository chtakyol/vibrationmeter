package com.cihatakyol.vibrationmeter.domain.usecase

import com.cihatakyol.vibrationmeter.domain.model.VibrationPoint
import com.cihatakyol.vibrationmeter.domain.repository.VibrationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for measuring real-time vibration.
 * Provides a clean interface to the vibration measurement functionality.
 */
class MeasureVibrationUseCase @Inject constructor(
    private val repository: VibrationRepository
) {
    /**
     * Get real-time vibration measurements.
     *
     * @return Flow of VibrationPoint containing timestamp and magnitude
     */
    operator fun invoke(): Flow<VibrationPoint> {
        return repository.getVibrationMeasurements()
    }
}
