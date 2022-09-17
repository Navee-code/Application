package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
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
        binding.progressCircular2.visibility= View.INVISIBLE
        auth= FirebaseAuth.getInstance()
        binding.signUp.setOnClickListener {
            binding.progressCircular2.visibility= View.VISIBLE
            createUser()
        }
    }

    private fun createUser() {
        val email=binding.signUpEmail.text.toString()
        val password=binding.signUpPassword.text.toString()

        if(email.isEmpty()){
            binding.signUpEmail.error = "FILL"
            binding.signUpPassword.requestFocus()
            binding.progressCircular2.visibility= View.INVISIBLE
        }else if(password.isEmpty()){
            var toast=Toast.makeText(applicationContext,"Fill password", Toast.LENGTH_LONG)
            toast?.setGravity(Gravity.TOP,0,0)
            toast?.show()
            binding.progressCircular2.visibility= View.INVISIBLE
        }else{

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {

                        intent= Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        binding.progressCircular2.visibility= View.INVISIBLE
                        Toast.makeText(applicationContext,"registered", Toast.LENGTH_LONG).show()
                    } else {
                        binding.progressCircular2.visibility= View.INVISIBLE
                        Toast.makeText(applicationContext,"Enter your emailId and password" , Toast.LENGTH_SHORT).show()
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