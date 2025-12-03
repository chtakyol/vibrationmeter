package com.cihatakyol.vibrationmeter.util

/**
 * Utility functions for formatting session data.
 */

/**
 * Format duration in milliseconds to readable string.
 * Examples: "5m 23s", "1h 5m 23s", "45s"
 */
fun Long.formatDuration(): String {
    val seconds = (this / 1000) % 60
    val minutes = (this / (1000 * 60)) % 60
    val hours = (this / (1000 * 60 * 60))

    return when {
        hours > 0 -> "${hours}h ${minutes}m ${seconds}s"
        minutes > 0 -> "${minutes}m ${seconds}s"
        else -> "${seconds}s"
    }
}

/**
 * Format duration in milliseconds to short format.
 * Examples: "5:23", "1:05:23", "0:45"
 */
fun Long.formatDurationShort(): String {
    val seconds = (this / 1000) % 60
    val minutes = (this / (1000 * 60)) % 60
    val hours = (this / (1000 * 60 * 60))

    return when {
        hours > 0 -> String.format("%d:%02d:%02d", hours, minutes, seconds)
        else -> String.format("%d:%02d", minutes, seconds)
    }
}
