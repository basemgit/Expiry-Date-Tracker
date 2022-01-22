
package com.basemibrahim.expirydatetracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.basemibrahim.expirydatetracker.utils.Converters


@Database(entities = arrayOf(Product::class,ExpiredProduct::class), version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun expiredProductDao(): ExpiredProductDao
}
