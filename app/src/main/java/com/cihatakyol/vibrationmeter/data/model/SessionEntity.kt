package com.cihatakyol.vibrationmeter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity representing a measurement session.
 */
@Entity(tableName = "sessions")
data class SessionEntity(
    @PrimaryKey
    val id: String,
    val startTimestamp: Long,
    val endTimestamp: Long,
    val duration: Long,
    val maxVibration: Float,
    val avgVibration: Float,
    val minVibration: Float
)
