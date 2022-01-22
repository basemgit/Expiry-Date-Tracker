package com.basemibrahim.expirydatetracker.data

import android.content.ClipData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpiredProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExpiredProduct(product: ExpiredProduct)

    @Query("SELECT * from expiredproduct ORDER BY expiry_date ASC")
    fun getExpiredProducts(): Flow<List<ExpiredProduct>>



}