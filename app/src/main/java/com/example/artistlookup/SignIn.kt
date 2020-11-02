package com.example.artistlookup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignIn : AppCompatActivity() {

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        signIn()
        toSignUp()
    }

    private fun signIn() {
        signIn.setOnClickListener {
            if (signInEmail.text.isEmpty() || signInPassword.text.isEmpty()
            ) {
                Toast.makeText(this,getString(R.string.enter_all_data), Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            checkUser()
        }
    }


    private fun checkUser() {
        auth.signInWithEmailAndPassword(
            signInEmail.text.toString(),
            signInPassword.text.toString()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java))
                return@addOnCompleteListener
            }
            Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun toSignUp() {
        signUp.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }
    }
}
