package com.cihatakyol.vibrationmeter.domain.model

/**
 * Represents complete vibration data including current, max, and history.
 *
 * @property currentVibration Current vibration magnitude in m/s²
 * @property maxVibration Maximum vibration magnitude recorded in m/s²
 * @property maxVibrationTimestamp Timestamp of max vibration event (milliseconds since epoch)
 * @property vibrationHistory List of recent vibration points for graphing
 */
data class VibrationData(
    val currentVibration: Float = 0f,
    val maxVibration: Float = 0f,
    val maxVibrationTimestamp: Long = 0L,
    val vibrationHistory: List<VibrationPoint> = emptyList()
)
