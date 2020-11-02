package com.example.artistlookup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth.signOut()
        signUp()
        toSignIn()
    }

    private fun signUp() {
        signUp.setOnClickListener {
            if (signUpEmail.text.isEmpty() || signUpPassword.text.isEmpty()
            ) {
                Toast.makeText(this, getString(R.string.enter_all_data), Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            createUserByEmail()
        }
    }

    private fun createUserByEmail() {
        auth.createUserWithEmailAndPassword(
            signUpEmail.text.toString(),
            signUpPassword.text.toString()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, getString(R.string.creadted_user), Toast.LENGTH_LONG)
                    .show()
                startActivity(Intent(this, SignIn::class.java))
                return@addOnCompleteListener
            }
            Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun toSignIn(){
        signIn.setOnClickListener{
            startActivity(Intent(this, SignIn::class.java))
        }
    }


}
