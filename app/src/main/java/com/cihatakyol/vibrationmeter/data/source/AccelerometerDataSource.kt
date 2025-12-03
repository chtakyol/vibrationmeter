package com.cihatakyol.vibrationmeter.data.source

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.cihatakyol.vibrationmeter.domain.model.VibrationPoint
import com.cihatakyol.vibrationmeter.util.calculateVibrationMagnitude
import com.cihatakyol.vibrationmeter.util.getAccelerometer
import com.cihatakyol.vibrationmeter.util.isAccelerometerAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Data source for accelerometer sensor measurements.
 * Provides real-time vibration data from device sensors.
 */
@Singleton
class AccelerometerDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    /**
     * Check if accelerometer is available on the device.
     */
    fun isAccelerometerAvailable(): Boolean {
        return sensorManager.isAccelerometerAvailable()
    }

    /**
     * Get vibration measurements as a Flow.
     * Emits VibrationPoint for each sensor update.
     *
     * @param samplingPeriodUs Sampling period in microseconds (default: SENSOR_DELAY_GAME = 20000µs = 50Hz)
     */
    fun getVibrationFlow(samplingPeriodUs: Int = SensorManager.SENSOR_DELAY_GAME): Flow<VibrationPoint> = callbackFlow {
        val accelerometer = sensorManager.getAccelerometer()

        if (accelerometer == null) {
            close(IllegalStateException("Accelerometer not available"))
            return@callbackFlow
        }

        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    val x = it.values[0]
                    val y = it.values[1]
                    val z = it.values[2]

                    val magnitude = calculateVibrationMagnitude(x, y, z)
                    val timestamp = System.currentTimeMillis()

                    val vibrationPoint = VibrationPoint(
                        timestamp = timestamp,
                        magnitude = magnitude
                    )

                    trySend(vibrationPoint)
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // Not needed for vibration measurement
            }
        }

        val registered = sensorManager.registerListener(
            listener,
            accelerometer,
            samplingPeriodUs
        )

        if (!registered) {
            close(IllegalStateException("Failed to register sensor listener"))
        }

        awaitClose {
            sensorManager.unregisterListener(listener)
        }
    }

    /**
     * Start listening to sensor with a specific listener.
     * Use this for manual lifecycle management.
     */
    fun startListening(
        listener: SensorEventListener,
        samplingPeriodUs: Int = SensorManager.SENSOR_DELAY_GAME
    ): Boolean {
        val accelerometer = sensorManager.getAccelerometer() ?: return false
        return sensorManager.registerListener(listener, accelerometer, samplingPeriodUs)
    }

    /**
     * Stop listening to sensor.
     */
    fun stopListening(listener: SensorEventListener) {
        sensorManager.unregisterListener(listener)
    }
}
