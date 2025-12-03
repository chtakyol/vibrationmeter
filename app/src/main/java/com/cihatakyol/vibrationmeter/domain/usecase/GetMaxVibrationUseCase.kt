package com.cihatakyol.vibrationmeter.domain.usecase

import com.cihatakyol.vibrationmeter.domain.model.MaxVibrationRecord
import com.cihatakyol.vibrationmeter.domain.repository.VibrationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for getting the maximum vibration record.
 * Provides access to the persisted max vibration data.
 */
class GetMaxVibrationUseCase @Inject constructor(
    private val repository: VibrationRepository
) {
    /**
     * Get the maximum vibration record as a Flow.
     *
     * @return Flow of MaxVibrationRecord or null if no record exists
     */
    operator fun invoke(): Flow<MaxVibrationRecord?> {
        return repository.getMaxVibrationRecord()
    }
}
