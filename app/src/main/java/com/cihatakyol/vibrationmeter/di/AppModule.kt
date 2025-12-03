package com.cihatakyol.vibrationmeter.di

import android.content.Context
import com.cihatakyol.vibrationmeter.data.repository.VibrationRepositoryImpl
import com.cihatakyol.vibrationmeter.data.source.AccelerometerDataSource
import com.cihatakyol.vibrationmeter.data.source.VibrationPreferencesDataSource
import com.cihatakyol.vibrationmeter.domain.repository.VibrationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for application-level dependencies.
 * Provides singleton instances for the entire app lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAccelerometerDataSource(
        @ApplicationContext context: Context
    ): AccelerometerDataSource {
        return AccelerometerDataSource(context)
    }

    @Provides
    @Singleton
    fun provideVibrationPreferencesDataSource(
        @ApplicationContext context: Context
    ): VibrationPreferencesDataSource {
        return VibrationPreferencesDataSource(context)
    }

    @Provides
    @Singleton
    fun provideVibrationRepository(
        accelerometerDataSource: AccelerometerDataSource,
        preferencesDataSource: VibrationPreferencesDataSource
    ): VibrationRepository {
        return VibrationRepositoryImpl(
            accelerometerDataSource = accelerometerDataSource,
            preferencesDataSource = preferencesDataSource
        )
    }
}
