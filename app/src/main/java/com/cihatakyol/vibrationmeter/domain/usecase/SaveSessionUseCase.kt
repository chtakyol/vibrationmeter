package com.cihatakyol.vibrationmeter.domain.usecase

import com.cihatakyol.vibrationmeter.domain.model.Session
import com.cihatakyol.vibrationmeter.domain.repository.SessionRepository
import javax.inject.Inject

/**
 * Use case for saving a measurement session.
 */
class SaveSessionUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    suspend operator fun invoke(session: Session) {
        repository.saveSession(session)
    }
}
