package com.cihatakyol.vibrationmeter.domain.model

/**
 * Represents a single vibration measurement point.
 *
 * @property timestamp Time when the measurement was taken (milliseconds since epoch)
 * @property magnitude Vibration magnitude in m/s²
 */
data class VibrationPoint(
    val timestamp: Long,
    val magnitude: Float
)
