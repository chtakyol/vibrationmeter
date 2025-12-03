package com.cihatakyol.vibrationmeter.data.source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.cihatakyol.vibrationmeter.domain.model.MaxVibrationRecord
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "vibration_preferences")

/**
 * Data source for vibration-related preferences using DataStore.
 * Provides persistent storage for max vibration records.
 */
@Singleton
class VibrationPreferencesDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private companion object {
        val MAX_VIBRATION_MAGNITUDE = floatPreferencesKey("max_vibration_magnitude")
        val MAX_VIBRATION_TIMESTAMP = longPreferencesKey("max_vibration_timestamp")
    }

    /**
     * Get the maximum vibration record as a Flow.
     * Emits null if no record has been saved.
     */
    fun getMaxVibrationRecord(): Flow<MaxVibrationRecord?> {
        return context.dataStore.data.map { preferences ->
            val magnitude = preferences[MAX_VIBRATION_MAGNITUDE]
            val timestamp = preferences[MAX_VIBRATION_TIMESTAMP]

            if (magnitude != null && timestamp != null) {
                MaxVibrationRecord(magnitude, timestamp)
            } else {
                null
            }
        }
    }

    /**
     * Save a new maximum vibration record.
     */
    suspend fun saveMaxVibrationRecord(record: MaxVibrationRecord) {
        context.dataStore.edit { preferences ->
            preferences[MAX_VIBRATION_MAGNITUDE] = record.magnitude
            preferences[MAX_VIBRATION_TIMESTAMP] = record.timestamp
        }
    }

    /**
     * Reset the maximum vibration record.
     * Clears all stored values.
     */
    suspend fun resetMaxVibrationRecord() {
        context.dataStore.edit { preferences ->
            preferences.remove(MAX_VIBRATION_MAGNITUDE)
            preferences.remove(MAX_VIBRATION_TIMESTAMP)
        }
    }
}
