package com.cihatakyol.vibrationmeter.domain.usecase

import com.cihatakyol.vibrationmeter.domain.model.SessionSummary
import com.cihatakyol.vibrationmeter.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for getting all sessions.
 */
class GetAllSessionsUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    operator fun invoke(): Flow<List<SessionSummary>> {
        return repository.getAllSessions()
    }
}
