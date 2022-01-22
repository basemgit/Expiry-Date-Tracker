package com.basemibrahim.expirydatetracker.utils

import android.util.Log
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

object Utils {
     fun stringToDate(aDate: String?, aFormat: String): Date? {
        if (aDate.isNullOrEmpty()) return null
        val simpledateformat = SimpleDateFormat(aFormat)
         return simpledateformat.parse(aDate)
    }

     fun dateToString(aDate: Date?, aFormat: String): String? {
        if (aDate == null) return null
       // val aDate = Date()
        val simpledateformat = SimpleDateFormat(aFormat)
        return simpledateformat.format(aDate)
    }
}