package com.basemibrahim.expirydatetracker.data

import android.content.ClipData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Query("SELECT * from product ORDER BY expiry_date ASC")
    fun getProducts(): Flow<List<Product>>

    @Delete
    suspend fun delete(product: Product)

}