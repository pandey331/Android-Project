package com.example.dailyhunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val time:Long=2000
        Handler().postDelayed(Runnable {
            val intent = Intent(SplashScreen.this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },time)
    }
}