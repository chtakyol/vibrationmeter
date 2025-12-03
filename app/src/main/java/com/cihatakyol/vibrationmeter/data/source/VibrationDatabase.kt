package com.cihatakyol.vibrationmeter.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cihatakyol.vibrationmeter.data.model.SessionDataPointEntity
import com.cihatakyol.vibrationmeter.data.model.SessionEntity

/**
 * Room database for vibration measurement sessions.
 */
@Database(
    entities = [
        SessionEntity::class,
        SessionDataPointEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class VibrationDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao

    companion object {
        const val DATABASE_NAME = "vibration_database"
    }
}
