package com.basemibrahim.expirydatetracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val productName: String,
    @ColumnInfo(name = "type")
    val productType: String,
    @ColumnInfo(name = "expiry_date")
    val expiryDate: Date
)

