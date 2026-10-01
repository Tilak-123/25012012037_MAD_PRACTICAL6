package com.example.a25012012037_mad_practical6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Toast

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val str = intent.getStringExtra("Service1")
        if (str == "Start" || str == "Stop") {
            val intentService = Intent(context, AlarmService::class.java)
            intentService.putExtra("Service1", str)
            if (str == "Start") {
                context.startService(intentService)
            } else {
                context.stopService(intentService)
            }
        }
    }
}
