package com.cihatakyol.vibrationmeter.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Utility functions for formatting values.
 * These functions provide plug-and-play formatting functionality.
 */

/**
 * Format vibration magnitude for display.
 * Shows value with 2 decimal places and unit (m/s²).
 *
 * @return Formatted string for display (e.g., "9.81 m/s²")
 */
fun Float.toVibrationDisplay(): String {
    return "%.2f m/s²".format(this)
}

/**
 * Format vibration magnitude for display without unit.
 * Shows value with 2 decimal places.
 *
 * @return Formatted string (e.g., "9.81")
 */
fun Float.toVibrationValue(): String {
    return "%.2f".format(this)
}

/**
 * Format timestamp to readable date/time string.
 * Uses format: "MMM dd, yyyy HH:mm:ss"
 *
 * @return Formatted timestamp string (e.g., "Dec 01, 2025 14:30:45")
 */
fun Long.toTimestampDisplay(): String {
    val date = Date(this)
    val formatter = SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.getDefault())
    return formatter.format(date)
}

/**
 * Format timestamp to time only.
 * Uses format: "HH:mm:ss"
 *
 * @return Formatted time string (e.g., "14:30:45")
 */
fun Long.toTimeDisplay(): String {
    val date = Date(this)
    val formatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return formatter.format(date)
}

/**
 * Format timestamp to short time format.
 * Uses format: "HH:mm"
 *
 * @return Formatted time string (e.g., "14:30")
 */
fun Long.toShortTimeDisplay(): String {
    val date = Date(this)
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return formatter.format(date)
}

/**
 * Format relative time for graph display.
 * Shows seconds ago from current time.
 *
 * @param currentTime Current timestamp in milliseconds
 * @return Formatted string (e.g., "-30s", "-15s", "0s")
 */
fun Long.toRelativeTimeDisplay(currentTime: Long): String {
    val diffSeconds = ((currentTime - this) / 1000).toInt()
    return if (diffSeconds == 0) "0s" else "-${diffSeconds}s"
}
