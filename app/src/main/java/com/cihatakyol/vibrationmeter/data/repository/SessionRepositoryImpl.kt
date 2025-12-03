package com.cihatakyol.vibrationmeter.data.repository

import com.cihatakyol.vibrationmeter.data.model.SessionDataPointEntity
import com.cihatakyol.vibrationmeter.data.model.SessionEntity
import com.cihatakyol.vibrationmeter.data.source.SessionDatabaseDataSource
import com.cihatakyol.vibrationmeter.domain.model.Session
import com.cihatakyol.vibrationmeter.domain.model.SessionSummary
import com.cihatakyol.vibrationmeter.domain.model.VibrationPoint
import com.cihatakyol.vibrationmeter.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of SessionRepository.
 */
@Singleton
class SessionRepositoryImpl @Inject constructor(
    private val sessionDatabaseDataSource: SessionDatabaseDataSource
) : SessionRepository {

    override fun getAllSessions(): Flow<List<SessionSummary>> {
        return sessionDatabaseDataSource.getAllSessions().map { entities ->
            entities.map { it.toSessionSummary() }
        }
    }

    override fun getSessionById(sessionId: String): Flow<Session?> {
        return sessionDatabaseDataSource.getSessionWithDataPoints(sessionId).map { sessionWithData ->
            sessionWithData?.toSession()
        }
    }

    override suspend fun saveSession(session: Session) {
        // Save session entity
        val sessionEntity = session.toEntity()
        sessionDatabaseDataSource.insertSession(sessionEntity)

        // Save data points
        val dataPointEntities = session.dataPoints.map { it.toEntity(session.id) }
        sessionDatabaseDataSource.insertDataPoints(dataPointEntities)
    }

    override suspend fun deleteSession(sessionId: String) {
        sessionDatabaseDataSource.deleteSession(sessionId)
    }

    override suspend fun deleteAllSessions() {
        sessionDatabaseDataSource.deleteAllSessions()
    }

    override fun getSessionCount(): Flow<Int> {
        return sessionDatabaseDataSource.getSessionCount()
    }
}

// Extension functions for mapping between domain and data models

private fun SessionEntity.toSessionSummary() = SessionSummary(
    id = id,
    startTimestamp = startTimestamp,
    endTimestamp = endTimestamp,
    duration = duration,
    maxVibration = maxVibration,
    avgVibration = avgVibration,
    minVibration = minVibration
)

private fun com.cihatakyol.vibrationmeter.data.model.SessionWithDataPoints.toSession() = Session(
    id = session.id,
    startTimestamp = session.startTimestamp,
    endTimestamp = session.endTimestamp,
    duration = session.duration,
    maxVibration = session.maxVibration,
    avgVibration = session.avgVibration,
    minVibration = session.minVibration,
    dataPoints = dataPoints.map { it.toVibrationPoint() }
)

private fun Session.toEntity() = SessionEntity(
    id = id,
    startTimestamp = startTimestamp,
    endTimestamp = endTimestamp,
    duration = duration,
    maxVibration = maxVibration,
    avgVibration = avgVibration,
    minVibration = minVibration
)

private fun VibrationPoint.toEntity(sessionId: String) = SessionDataPointEntity(
    sessionId = sessionId,
    timestamp = timestamp,
    magnitude = magnitude
)

private fun SessionDataPointEntity.toVibrationPoint() = VibrationPoint(
    timestamp = timestamp,
    magnitude = magnitude
)
