package com.cihatakyol.vibrationmeter.domain.model

/**
 * Domain model representing a measurement session.
 */
data class Session(
    val id: String,
    val startTimestamp: Long,
    val endTimestamp: Long,
    val duration: Long,
    val maxVibration: Float,
    val avgVibration: Float,
    val minVibration: Float,
    val dataPoints: List<VibrationPoint> = emptyList()
)
