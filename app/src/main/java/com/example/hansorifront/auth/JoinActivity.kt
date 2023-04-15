package com.example.hansorifront.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.hansorifront.MainActivity
import com.example.hansorifront.R
import com.example.hansorifront.databinding.ActivityJoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join )

        binding.joinBtn.setOnClickListener{

            var isGotoJoin = true


            val email = binding.emailArea.text.toString()
            val password1 = binding.passwordArea1.text.toString()
            val password2 = binding.passwordArea2.text.toString()

            // <회원가입에서 체크할 부분들>
            // 값이 비어있는지 확인
            if(email.isEmpty()){
                Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_LONG).show()
                isGotoJoin = false
            }
            if(password1.isEmpty()){
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                isGotoJoin = false
            }
            if(password2.isEmpty()){
                Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_LONG).show()
                isGotoJoin = false
            }
            // 비밀번호 2개가 같은지 확인
            if(!password1.equals(password2)) {
                Toast.makeText(this, "비밀번호를 똑같이 입력해주세요", Toast.LENGTH_LONG).show()
                isGotoJoin = false
            }
            // 비밀번호가 8자리 이상인지 확인
            if(password1.length < 8) {
                Toast.makeText(this, "비밀번호를 8자 이상 입력해주세요", Toast.LENGTH_LONG).show()
                isGotoJoin = false
            }

            if(isGotoJoin){
                auth.createUserWithEmailAndPassword(email, password1)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) { // 성공했을 때
                            Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()

                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(intent)

                        } else { // 실패했을 때
                            Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                        }
                    }

            }
        }



    }
}