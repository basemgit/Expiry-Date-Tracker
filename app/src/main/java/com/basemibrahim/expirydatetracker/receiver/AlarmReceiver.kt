package com.basemibrahim.expirydatetracker.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.basemibrahim.expirydatetracker.utils.NotificationUtils

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtils.sendNotification(
            "product is expired",
            context
        )

    }

}