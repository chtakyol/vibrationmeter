package com.cihatakyol.vibrationmeter.util

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import kotlin.math.sqrt

/**
 * Utility functions for sensor operations.
 * These functions provide plug-and-play sensor functionality.
 */

/**
 * Calculate vibration magnitude from accelerometer readings.
 * Uses Euclidean distance formula: sqrt(x² + y² + z²)
 *
 * @param x Acceleration along X-axis (m/s²)
 * @param y Acceleration along Y-axis (m/s²)
 * @param z Acceleration along Z-axis (m/s²)
 * @return Vibration magnitude in m/s²
 */
fun calculateVibrationMagnitude(x: Float, y: Float, z: Float): Float {
    return sqrt(x * x + y * y + z * z)
}

/**
 * Check if accelerometer sensor is available on the device.
 *
 * @return true if accelerometer is available, false otherwise
 */
fun SensorManager.isAccelerometerAvailable(): Boolean {
    return getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null
}

/**
 * Get the accelerometer sensor from SensorManager.
 *
 * @return Accelerometer sensor or null if not available
 */
fun SensorManager.getAccelerometer(): Sensor? {
    return getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
}

/**
 * Extension function to check sensor availability from Context.
 *
 * @return true if accelerometer is available, false otherwise
 */
fun Context.hasAccelerometer(): Boolean {
    val sensorManager = getSystemService(Context.SENSOR_SERVICE) as? SensorManager
    return sensorManager?.isAccelerometerAvailable() ?: false
}
