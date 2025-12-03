package com.cihatakyol.vibrationmeter.di

import android.content.Context
import androidx.room.Room
import com.cihatakyol.vibrationmeter.data.repository.SessionRepositoryImpl
import com.cihatakyol.vibrationmeter.data.source.SessionDao
import com.cihatakyol.vibrationmeter.data.source.SessionDatabaseDataSource
import com.cihatakyol.vibrationmeter.data.source.VibrationDatabase
import com.cihatakyol.vibrationmeter.domain.repository.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for database-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideVibrationDatabase(
        @ApplicationContext context: Context
    ): VibrationDatabase {
        return Room.databaseBuilder(
            context,
            VibrationDatabase::class.java,
            VibrationDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideSessionDao(database: VibrationDatabase): SessionDao {
        return database.sessionDao()
    }

    @Provides
    @Singleton
    fun provideSessionDatabaseDataSource(sessionDao: SessionDao): SessionDatabaseDataSource {
        return SessionDatabaseDataSource(sessionDao)
    }

    @Provides
    @Singleton
    fun provideSessionRepository(
        sessionDatabaseDataSource: SessionDatabaseDataSource
    ): SessionRepository {
        return SessionRepositoryImpl(sessionDatabaseDataSource)
    }
}
