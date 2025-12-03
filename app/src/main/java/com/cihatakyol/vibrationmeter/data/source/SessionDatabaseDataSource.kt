package com.cihatakyol.vibrationmeter.data.source

import com.cihatakyol.vibrationmeter.data.model.SessionDataPointEntity
import com.cihatakyol.vibrationmeter.data.model.SessionEntity
import com.cihatakyol.vibrationmeter.data.model.SessionWithDataPoints
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Data source for session database operations.
 */
@Singleton
class SessionDatabaseDataSource @Inject constructor(
    private val sessionDao: SessionDao
) {
    fun getAllSessions(): Flow<List<SessionEntity>> {
        return sessionDao.getAllSessions()
    }

    fun getSessionWithDataPoints(sessionId: String): Flow<SessionWithDataPoints?> {
        return sessionDao.getSessionWithDataPoints(sessionId)
    }

    suspend fun getSessionById(sessionId: String): SessionEntity? {
        return sessionDao.getSessionById(sessionId)
    }

    suspend fun insertSession(session: SessionEntity) {
        sessionDao.insertSession(session)
    }

    suspend fun insertDataPoints(dataPoints: List<SessionDataPointEntity>) {
        sessionDao.insertDataPoints(dataPoints)
    }

    suspend fun deleteSession(sessionId: String) {
        sessionDao.deleteSessionById(sessionId)
    }

    suspend fun deleteAllSessions() {
        sessionDao.deleteAllSessions()
    }

    fun getSessionCount(): Flow<Int> {
        return sessionDao.getSessionCount()
    }
}
