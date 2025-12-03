package com.cihatakyol.vibrationmeter.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for MathUtils functions.
 */
class MathUtilsTest {

    @Test
    fun `movingAverage returns correct values`() {
        val values = listOf(1.0f, 2.0f, 3.0f, 4.0f, 5.0f)
        val result = values.movingAverage(3)

        assertEquals(3, result.size)
        assertEquals(2.0f, result[0], 0.001f) // average of [1,2,3]
        assertEquals(3.0f, result[1], 0.001f) // average of [2,3,4]
        assertEquals(4.0f, result[2], 0.001f) // average of [3,4,5]
    }

    @Test
    fun `movingAverage returns empty list for empty input`() {
        val values = emptyList<Float>()
        val result = values.movingAverage(3)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `movingAverage returns empty list when window size is larger than list`() {
        val values = listOf(1.0f, 2.0f)
        val result = values.movingAverage(5)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `normalize returns correct value`() {
        val value = 5.0f
        val result = value.normalize(0.0f, 10.0f)

        assertEquals(0.5f, result, 0.001f)
    }

    @Test
    fun `normalize returns 0 when value equals min`() {
        val value = 0.0f
        val result = value.normalize(0.0f, 10.0f)

        assertEquals(0.0f, result, 0.001f)
    }

    @Test
    fun `normalize returns 1 when value equals max`() {
        val value = 10.0f
        val result = value.normalize(0.0f, 10.0f)

        assertEquals(1.0f, result, 0.001f)
    }

    @Test
    fun `normalize clamps values outside range`() {
        val valueTooHigh = 15.0f
        val resultHigh = valueTooHigh.normalize(0.0f, 10.0f)

        assertEquals(1.0f, resultHigh, 0.001f)

        val valueTooLow = -5.0f
        val resultLow = valueTooLow.normalize(0.0f, 10.0f)

        assertEquals(0.0f, resultLow, 0.001f)
    }

    @Test
    fun `denormalize returns correct value`() {
        val normalized = 0.5f
        val result = normalized.denormalize(0.0f, 10.0f)

        assertEquals(5.0f, result, 0.001f)
    }

    @Test
    fun `maxOrDefault returns max value`() {
        val values = listOf(1.0f, 5.0f, 3.0f, 2.0f)
        val result = values.maxOrDefault(0.0f)

        assertEquals(5.0f, result, 0.001f)
    }

    @Test
    fun `maxOrDefault returns default for empty list`() {
        val values = emptyList<Float>()
        val result = values.maxOrDefault(10.0f)

        assertEquals(10.0f, result, 0.001f)
    }

    @Test
    fun `minOrDefault returns min value`() {
        val values = listOf(5.0f, 1.0f, 3.0f, 2.0f)
        val result = values.minOrDefault(0.0f)

        assertEquals(1.0f, result, 0.001f)
    }

    @Test
    fun `minOrDefault returns default for empty list`() {
        val values = emptyList<Float>()
        val result = values.minOrDefault(10.0f)

        assertEquals(10.0f, result, 0.001f)
    }

    @Test
    fun `roundTo rounds correctly`() {
        val value = 3.14159f
        val result = value.roundTo(2)

        assertEquals(3.14f, result, 0.001f)
    }

    @Test
    fun `clamp returns value within range`() {
        val value = 5.0f
        val result = value.clamp(0.0f, 10.0f)

        assertEquals(5.0f, result, 0.001f)
    }

    @Test
    fun `clamp returns min when value is too low`() {
        val value = -5.0f
        val result = value.clamp(0.0f, 10.0f)

        assertEquals(0.0f, result, 0.001f)
    }

    @Test
    fun `clamp returns max when value is too high`() {
        val value = 15.0f
        val result = value.clamp(0.0f, 10.0f)

        assertEquals(10.0f, result, 0.001f)
    }
}
