package com.example.hansorifront

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.hansorifront.auth.IntroActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //3초 있다가 introactivity로 이동
        Handler().postDelayed({
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }, 3000)
    }
}