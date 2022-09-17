package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.example.myapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= FirebaseAuth.getInstance()

        if(auth.currentUser!=null) {
            intent= Intent(this,MainActivity::class.java)
            startActivity(intent)

        }else {
            binding= ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.progressCircular2.visibility= View.INVISIBLE
            binding.signup.setOnClickListener {
                intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
            }
            binding.login.setOnClickListener {
                binding.progressCircular2.visibility= View.VISIBLE
                loginUser()
            }

        }

    }

    override fun onStart() {
        auth=FirebaseAuth.getInstance()

        super.onStart()
    }
    private fun loginUser() {
        val email=binding.logUser.text.toString()
        val password=binding.logPassword.text.toString()

        if(email.isEmpty()){
            binding.logUser.error = "FILL"
            binding.logUser.requestFocus()
            binding.progressCircular2.visibility= View.INVISIBLE
        }else if(password.isEmpty()){
            var toast=Toast.makeText(applicationContext,"Fill password", Toast.LENGTH_LONG)
            toast?.setGravity(Gravity.TOP,0,0)
            toast?.show()
            binding.progressCircular2.visibility= View.INVISIBLE
        }else{
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        intent= Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        binding.progressCircular2.visibility= View.INVISIBLE
                        Toast.makeText(applicationContext,"registered", Toast.LENGTH_LONG).show()

                    } else {
                        binding.progressCircular2.visibility= View.INVISIBLE
                        Toast.makeText(applicationContext,"Enter the valid credentials", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        finish()
        startActivity(a)
        super.onBackPressed()
    }

}
