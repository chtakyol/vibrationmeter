package com.cihatakyol.vibrationmeter.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for FormatUtils functions.
 */
class FormatUtilsTest {

    @Test
    fun `toVibrationDisplay formats with unit`() {
        val value = 9.81f
        val result = value.toVibrationDisplay()

        assertEquals("9.81 m/s²", result)
    }

    @Test
    fun `toVibrationDisplay formats zero correctly`() {
        val value = 0.0f
        val result = value.toVibrationDisplay()

        assertEquals("0.00 m/s²", result)
    }

    @Test
    fun `toVibrationDisplay rounds to 2 decimal places`() {
        val value = 9.876543f
        val result = value.toVibrationDisplay()

        assertEquals("9.88 m/s²", result)
    }

    @Test
    fun `toVibrationValue formats without unit`() {
        val value = 9.81f
        val result = value.toVibrationValue()

        assertEquals("9.81", result)
    }

    @Test
    fun `toTimestampDisplay returns formatted date string`() {
        val timestamp = 1733097600000L // Dec 01, 2024 22:00:00 UTC
        val result = timestamp.toTimestampDisplay()

        // Result will vary based on timezone, but should contain date elements
        assertTrue(result.contains("2024") || result.contains("2023") || result.contains("2025"))
        assertTrue(result.contains("Dec") || result.contains("Nov") || result.contains("Jan"))
    }

    @Test
    fun `toTimeDisplay returns time only`() {
        val timestamp = 1733097600000L
        val result = timestamp.toTimeDisplay()

        // Result will vary based on timezone, but should be in HH:mm:ss format
        assertTrue(result.matches(Regex("\\d{2}:\\d{2}:\\d{2}")))
    }

    @Test
    fun `toShortTimeDisplay returns short time format`() {
        val timestamp = 1733097600000L
        val result = timestamp.toShortTimeDisplay()

        // Result will vary based on timezone, but should be in HH:mm format
        assertTrue(result.matches(Regex("\\d{2}:\\d{2}")))
    }

    @Test
    fun `toRelativeTimeDisplay shows current time as 0s`() {
        val timestamp = 1000000L
        val currentTime = 1000000L
        val result = timestamp.toRelativeTimeDisplay(currentTime)

        assertEquals("0s", result)
    }

    @Test
    fun `toRelativeTimeDisplay shows past time correctly`() {
        val timestamp = 1000000L
        val currentTime = 1030000L // 30 seconds later
        val result = timestamp.toRelativeTimeDisplay(currentTime)

        assertEquals("-30s", result)
    }
}
