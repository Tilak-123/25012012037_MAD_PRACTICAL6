package com.example.a25012012037_mad_practical6

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    lateinit var alarmAnimation: AnimationDrawable
    lateinit var imgAlarm: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgAlarm = findViewById(R.id.img_alarm)

        imgAlarm.setBackgroundResource(R.drawable.alarm_animation_list)
        alarmAnimation = imgAlarm.background as AnimationDrawable

        val btnCreate = findViewById<MaterialButton>(R.id.btn_create)
        val btnCancel = findViewById<MaterialButton>(R.id.btn_cancel)

        btnCreate.setOnClickListener {
            setAlarm(System.currentTimeMillis() + 5000)
            Toast.makeText(this, "Alarm set for 5 seconds", Toast.LENGTH_SHORT).show()
        }

        btnCancel.setOnClickListener {
            cancelAlarm()
            Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            alarmAnimation.start()
        } else {
            alarmAnimation.stop()
        }
    }

    fun setAlarm(time: Long) {
        val am = getSystemService(ALARM_SERVICE) as AlarmManager
        val i = Intent(this, AlarmBroadcastReceiver::class.java)
        i.putExtra("Service1", "Start")
        val pi = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_IMMUTABLE)

        if (am.canScheduleExactAlarms()) {
            am.setExact(AlarmManager.RTC_WAKEUP, time, pi)
        } else {
            am.set(AlarmManager.RTC_WAKEUP, time, pi)
        }
    }

    fun cancelAlarm() {
        val am = getSystemService(ALARM_SERVICE) as AlarmManager
        val i = Intent(this, AlarmBroadcastReceiver::class.java)
        i.putExtra("Service1", "Stop")
        val pi = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_IMMUTABLE)

        am.cancel(pi)
        sendBroadcast(i)
    }
}

