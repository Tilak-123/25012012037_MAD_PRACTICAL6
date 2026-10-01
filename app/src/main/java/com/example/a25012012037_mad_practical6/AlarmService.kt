package com.example.a25012012037_mad_practical6

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

class AlarmService : Service() {
    var mp: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.getStringExtra("Service1")
        
        if (action == "Start") {
            mp = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)
            mp?.isLooping = true
            mp?.start()
        } else if (action == "Stop") {
            mp?.stop()
            mp?.release()
        }
        
        return START_STICKY
    }

    override fun onDestroy() {
        mp?.stop()
        mp?.release()
        super.onDestroy()
    }
}

