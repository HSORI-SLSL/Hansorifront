package com.example.hansorifront.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hansorifront.R
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.ktx.Firebase
//import com.google.firebase.auth.ktx.auth

class JoinActivity : AppCompatActivity() {

    //private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

//        auth = Firebase.auth
//
//        auth.createUserWithEmailAndPassword("abc@abc.com", "abcdabcd")
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) { // 성공했을 때
//                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()
//
//                } else { // 실패했을 때
//                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
//
//                }
//            }

    }
}