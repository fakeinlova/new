package com.example.alpha2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class SplashSceene : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val timer = object : CountDownTimer(3000 , 1000){
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                startActivity(Intent(this@SplashSceene, Scroll::class.java))
            }

        }.start()
    }
}