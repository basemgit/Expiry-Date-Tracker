package com.basemibrahim.expirydatetracker.receiver

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import com.basemibrahim.expirydatetracker.utils.Constants
import com.basemibrahim.expirydatetracker.utils.NotificationUtils
import com.basemibrahim.expirydatetracker.utils.Utils
import dagger.hilt.android.internal.Contexts.getApplication

class RebootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("ExpiryDate", "RECEIVED")
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            AlarmManagerCompat.setExactAndAllowWhileIdle(
                context.getSystemService(Context.ALARM_SERVICE) as AlarmManager,
                AlarmManager.RTC_WAKEUP,
                Utils.getAlarmTime(context),
                PendingIntent.getBroadcast(
                    context,
                    Constants.REQUEST_CODE,
                    Intent(context, AlarmReceiver::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )

        }
    }
}

