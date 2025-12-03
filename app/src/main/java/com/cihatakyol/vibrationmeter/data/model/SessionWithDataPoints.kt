package com.cihatakyol.vibrationmeter.data.model

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Room relation representing a session with all its data points.
 */
data class SessionWithDataPoints(
    @Embedded
    val session: SessionEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "sessionId"
    )
    val dataPoints: List<SessionDataPointEntity>
)
