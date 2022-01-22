package com.basemibrahim.expirydatetracker.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class Converters {

    @TypeConverter
    fun fromDate(value: Date): String {
        val gson = Gson()
        val type = object : TypeToken<Date>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toDate(value: String): Date {
        val gson = Gson()
        val type = object : TypeToken<Date>() {}.type
        return gson.fromJson(value, type)
    }

}