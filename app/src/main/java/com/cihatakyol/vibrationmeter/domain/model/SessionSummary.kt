package com.cihatakyol.vibrationmeter.domain.model

/**
 * Domain model representing a session summary for list display.
 * Lighter weight than full Session (no data points).
 */
data class SessionSummary(
    val id: String,
    val startTimestamp: Long,
    val endTimestamp: Long,
    val duration: Long,
    val maxVibration: Float,
    val avgVibration: Float,
    val minVibration: Float
)
