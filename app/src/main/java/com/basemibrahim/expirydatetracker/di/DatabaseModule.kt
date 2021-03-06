package com.basemibrahim.expirydatetracker.di

import android.content.Context
import androidx.room.Room
import com.basemibrahim.expirydatetracker.data.AppDatabase
import com.basemibrahim.expirydatetracker.data.ExpiredProductDao
import com.basemibrahim.expirydatetracker.data.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideExpiredProductDao(database: AppDatabase): ExpiredProductDao {
        return database.expiredProductDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "ExpiryDateTracker.db"
        ).build()
    }
}