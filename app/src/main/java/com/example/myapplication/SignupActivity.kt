package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()
        binding.signUp.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        val email=binding.signUpEmail.text.toString()
        val password=binding.signUpPassword.text.toString()

        if(email.isEmpty()){
            binding.signUpEmail.error = "FILL"
            binding.signUpPassword.requestFocus()
        }else if(password.isEmpty()){
            binding.signUpPassword.error = "FILL"
            binding.signUpPassword.requestFocus()
        }else{

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {

                        intent= Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(applicationContext,"registered", Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(applicationContext,"Failed", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
    override fun onBackPressed() {
        intent= Intent(this,LoginActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}