package com.example.hansorifront.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.hansorifront.R
import com.example.hansorifront.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    // 바인딩을 해줌
    private lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        //로그인 버튼을 클릭할 경우
        binding.loginBtn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

// 회원가입 버튼을 클릭할 경우
        binding.joinBtn.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }


    }
}