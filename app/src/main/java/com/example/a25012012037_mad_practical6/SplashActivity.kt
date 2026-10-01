package com.example.a25012012037_mad_practical6

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    lateinit var logoAnimation: AnimationDrawable
    lateinit var tweenAnimation: Animation
    lateinit var logoImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        logoImage = findViewById(R.id.imglogo)
        logoImage.setBackgroundResource(R.drawable.uvpce_animation_list)
        
        logoAnimation = logoImage.background as AnimationDrawable
        tweenAnimation = AnimationUtils.loadAnimation(this, R.anim.twinanimation)

        logoImage.startAnimation(tweenAnimation)

        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 2500)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            logoAnimation.start()
        } else {
            logoAnimation.stop()
        }
    }
}


