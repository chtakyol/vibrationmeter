package com.cihatakyol.vibrationmeter.util

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.sqrt

/**
 * Unit tests for SensorUtils functions.
 */
class SensorUtilsTest {

    @Test
    fun `calculateVibrationMagnitude returns correct value for positive inputs`() {
        val x = 3.0f
        val y = 4.0f
        val z = 0.0f

        val result = calculateVibrationMagnitude(x, y, z)
        val expected = 5.0f // sqrt(9 + 16 + 0) = 5

        assertEquals(expected, result, 0.001f)
    }

    @Test
    fun `calculateVibrationMagnitude returns correct value for all zero inputs`() {
        val x = 0.0f
        val y = 0.0f
        val z = 0.0f

        val result = calculateVibrationMagnitude(x, y, z)
        val expected = 0.0f

        assertEquals(expected, result, 0.001f)
    }

    @Test
    fun `calculateVibrationMagnitude returns correct value for negative inputs`() {
        val x = -3.0f
        val y = -4.0f
        val z = 0.0f

        val result = calculateVibrationMagnitude(x, y, z)
        val expected = 5.0f // sqrt(9 + 16 + 0) = 5

        assertEquals(expected, result, 0.001f)
    }

    @Test
    fun `calculateVibrationMagnitude returns correct value for mixed inputs`() {
        val x = 1.0f
        val y = 2.0f
        val z = 2.0f

        val result = calculateVibrationMagnitude(x, y, z)
        val expected = sqrt(1.0f + 4.0f + 4.0f) // sqrt(9) = 3

        assertEquals(expected, result, 0.001f)
    }

    @Test
    fun `calculateVibrationMagnitude handles large values`() {
        val x = 100.0f
        val y = 100.0f
        val z = 100.0f

        val result = calculateVibrationMagnitude(x, y, z)
        val expected = sqrt(10000.0f + 10000.0f + 10000.0f)

        assertEquals(expected, result, 0.001f)
    }
}
