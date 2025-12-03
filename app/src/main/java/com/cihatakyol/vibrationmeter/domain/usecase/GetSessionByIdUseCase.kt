package com.cihatakyol.vibrationmeter.domain.usecase

import com.cihatakyol.vibrationmeter.domain.model.Session
import com.cihatakyol.vibrationmeter.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for getting a session by ID with full data.
 */
class GetSessionByIdUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    operator fun invoke(sessionId: String): Flow<Session?> {
        return repository.getSessionById(sessionId)
    }
}
