package com.cihatakyol.vibrationmeter.domain.usecase

import com.cihatakyol.vibrationmeter.domain.repository.SessionRepository
import javax.inject.Inject

/**
 * Use case for deleting a session.
 */
class DeleteSessionUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    suspend operator fun invoke(sessionId: String) {
        repository.deleteSession(sessionId)
    }
}
