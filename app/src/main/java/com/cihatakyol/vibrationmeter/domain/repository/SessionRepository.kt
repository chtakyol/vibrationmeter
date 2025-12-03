package com.cihatakyol.vibrationmeter.domain.repository

import com.cihatakyol.vibrationmeter.domain.model.Session
import com.cihatakyol.vibrationmeter.domain.model.SessionSummary
import com.cihatakyol.vibrationmeter.domain.model.VibrationPoint
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for session operations.
 */
interface SessionRepository {
    /**
     * Get all sessions as summaries.
     */
    fun getAllSessions(): Flow<List<SessionSummary>>

    /**
     * Get a full session with data points by ID.
     */
    fun getSessionById(sessionId: String): Flow<Session?>

    /**
     * Save a new session with data points.
     */
    suspend fun saveSession(session: Session)

    /**
     * Delete a session.
     */
    suspend fun deleteSession(sessionId: String)

    /**
     * Delete all sessions.
     */
    suspend fun deleteAllSessions()

    /**
     * Get total session count.
     */
    fun getSessionCount(): Flow<Int>
}
