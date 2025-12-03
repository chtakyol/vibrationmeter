package com.cihatakyol.vibrationmeter.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.cihatakyol.vibrationmeter.data.model.SessionDataPointEntity
import com.cihatakyol.vibrationmeter.data.model.SessionEntity
import com.cihatakyol.vibrationmeter.data.model.SessionWithDataPoints
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for session operations.
 */
@Dao
interface SessionDao {

    /**
     * Insert a session.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: SessionEntity)

    /**
     * Insert multiple data points.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataPoints(dataPoints: List<SessionDataPointEntity>)

    /**
     * Get all sessions ordered by start time (newest first).
     */
    @Query("SELECT * FROM sessions ORDER BY startTimestamp DESC")
    fun getAllSessions(): Flow<List<SessionEntity>>

    /**
     * Get a session with all its data points.
     */
    @Transaction
    @Query("SELECT * FROM sessions WHERE id = :sessionId")
    fun getSessionWithDataPoints(sessionId: String): Flow<SessionWithDataPoints?>

    /**
     * Get a session by ID.
     */
    @Query("SELECT * FROM sessions WHERE id = :sessionId")
    suspend fun getSessionById(sessionId: String): SessionEntity?

    /**
     * Delete a session (data points cascade).
     */
    @Delete
    suspend fun deleteSession(session: SessionEntity)

    /**
     * Delete a session by ID.
     */
    @Query("DELETE FROM sessions WHERE id = :sessionId")
    suspend fun deleteSessionById(sessionId: String)

    /**
     * Delete all sessions.
     */
    @Query("DELETE FROM sessions")
    suspend fun deleteAllSessions()

    /**
     * Get total number of sessions.
     */
    @Query("SELECT COUNT(*) FROM sessions")
    fun getSessionCount(): Flow<Int>
}
