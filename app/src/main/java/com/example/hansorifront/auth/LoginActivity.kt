package com.example.hansorifront.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.hansorifront.MainActivity
import com.example.hansorifront.R
import com.example.hansorifront.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityLoginBinding

    private final var signInButton: SignInButton? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val TAG = "mainTag"
    private var mAuth: FirebaseAuth? = null
    private val RC_SIGN_IN = 123

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(com.example.hansorifront.R.layout.activity_login);

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, com.example.hansorifront.R.layout.activity_login)

        binding.loginBtn.setOnClickListener{

            val email = binding.emailArea.text.toString()
            val password = binding.passwordArea.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()

                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()

                    }
                }
        }

        signInButton = findViewById(R.id.SignIn_Button);

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(com.example.hansorifront.R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        mAuth = FirebaseAuth.getInstance()

        signInButton?.setOnClickListener(View.OnClickListener { signIn() })
    }


    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        //updateUI(currentUser);
    }
    // [END on_start_check_user]

    // [END on_start_check_user]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                Toast.makeText(applicationContext, "Google sign in Failed", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id)
        // [START_EXCLUDE silent]
        //showProgressDialog();
        // [END_EXCLUDE]
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = mAuth!!.currentUser
                    //updateUI(user);
                    Toast.makeText(applicationContext, "Complete", Toast.LENGTH_LONG).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    // Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                    Toast.makeText(applicationContext, "Authentication Failed", Toast.LENGTH_LONG)
                        .show()

                    // updateUI(null);
                }

                // [START_EXCLUDE]
                // hideProgressDialog();
                // [END_EXCLUDE]
            }
    }
    // [END auth_with_google]

    // [END auth_with_google]
    // [START signin]
    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    // [END signin]

    // [END signin]
    private fun signOut() {
        // Firebase sign out
        mAuth!!.signOut()

        // Google sign out
        mGoogleSignInClient!!.signOut().addOnCompleteListener(
            this
        ) { Toast.makeText(applicationContext, "Complete", Toast.LENGTH_LONG).show() }
    }

    private fun revokeAccess() {
        // Firebase sign out
        mAuth!!.signOut()

        // Google revoke access
        mGoogleSignInClient!!.revokeAccess().addOnCompleteListener(
            this
        ) { Toast.makeText(applicationContext, "Complete", Toast.LENGTH_LONG).show() }
    }


}