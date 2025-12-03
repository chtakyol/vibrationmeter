package com.cihatakyol.vibrationmeter.util

/**
 * Utility functions for mathematical operations.
 * These functions provide plug-and-play math functionality.
 */

/**
 * Calculate moving average of a list of Float values.
 * Smooths out short-term fluctuations and highlights longer-term trends.
 *
 * @param windowSize Size of the moving window
 * @return List of averaged values
 *
 * Example:
 * ```
 * val values = listOf(1.0f, 2.0f, 3.0f, 4.0f, 5.0f)
 * val smoothed = values.movingAverage(3)
 * // Result: [2.0, 3.0, 4.0] (averages of [1,2,3], [2,3,4], [3,4,5])
 * ```
 */
fun List<Float>.movingAverage(windowSize: Int): List<Float> {
    if (isEmpty() || windowSize <= 0 || windowSize > size) {
        return emptyList()
    }

    val result = mutableListOf<Float>()
    for (i in 0..(size - windowSize)) {
        val window = subList(i, i + windowSize)
        val average = window.average().toFloat()
        result.add(average)
    }
    return result
}

/**
 * Normalize a Float value to a range [0, 1].
 *
 * @param min Minimum value of the original range
 * @param max Maximum value of the original range
 * @return Normalized value between 0 and 1
 *
 * Example:
 * ```
 * val value = 5.0f
 * val normalized = value.normalize(0.0f, 10.0f)
 * // Result: 0.5f
 * ```
 */
fun Float.normalize(min: Float, max: Float): Float {
    if (max == min) return 0f
    return ((this - min) / (max - min)).coerceIn(0f, 1f)
}

/**
 * Denormalize a normalized value [0, 1] back to original range.
 *
 * @param min Minimum value of the target range
 * @param max Maximum value of the target range
 * @return Denormalized value in the original range
 *
 * Example:
 * ```
 * val normalized = 0.5f
 * val value = normalized.denormalize(0.0f, 10.0f)
 * // Result: 5.0f
 * ```
 */
fun Float.denormalize(min: Float, max: Float): Float {
    return min + this * (max - min)
}

/**
 * Clamp a Float value between min and max.
 *
 * @param min Minimum allowed value
 * @param max Maximum allowed value
 * @return Clamped value
 */
fun Float.clamp(min: Float, max: Float): Float {
    return coerceIn(min, max)
}

/**
 * Calculate the maximum value in a list, with default if empty.
 *
 * @param default Default value to return if list is empty
 * @return Maximum value or default
 */
fun List<Float>.maxOrDefault(default: Float = 0f): Float {
    return maxOrNull() ?: default
}

/**
 * Calculate the minimum value in a list, with default if empty.
 *
 * @param default Default value to return if list is empty
 * @return Minimum value or default
 */
fun List<Float>.minOrDefault(default: Float = 0f): Float {
    return minOrNull() ?: default
}

/**
 * Calculate standard deviation of a list of Float values.
 *
 * @return Standard deviation or 0 if list is empty
 */
fun List<Float>.standardDeviation(): Float {
    if (isEmpty()) return 0f

    val mean = average().toFloat()
    val variance = map { (it - mean) * (it - mean) }.average().toFloat()
    return kotlin.math.sqrt(variance)
}

/**
 * Round Float to specified number of decimal places.
 *
 * @param decimals Number of decimal places
 * @return Rounded value
 */
fun Float.roundTo(decimals: Int): Float {
    val multiplier = Math.pow(10.0, decimals.toDouble()).toFloat()
    return kotlin.math.round(this * multiplier) / multiplier
}
