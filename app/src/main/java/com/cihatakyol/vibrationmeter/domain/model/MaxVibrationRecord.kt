package com.cihatakyol.vibrationmeter.domain.model

/**
 * Represents the maximum vibration record stored persistently.
 *
 * @property magnitude Maximum vibration magnitude in m/s²
 * @property timestamp Timestamp when the max vibration occurred (milliseconds since epoch)
 */
data class MaxVibrationRecord(
    val magnitude: Float,
    val timestamp: Long
)
