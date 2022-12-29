package com.example.chatapplication.ui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chatapplication.MainActivity
import com.example.chatapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth:FirebaseAuth
    private lateinit var mProgressBar: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mProgressBar = ProgressDialog(this)
        mAuth = FirebaseAuth.getInstance()


        binding.navtoSignup.setOnClickListener {
            val nav_reg = Intent(this,Signup::class.java)
            startActivity(nav_reg)
        }

        binding.loginSubmit.setOnClickListener {
            val email = binding.edittextMail.text.toString()
            val password = binding.edittextPassword.text.toString()



            if (email.isEmpty()){
               binding.edittextMail.error = "Email Required"
                return@setOnClickListener
            }
            else if (password.isEmpty()){
                binding.edittextPassword.error = "Password Required"
                return@setOnClickListener
            }
            else{
                login(email,password)
            }
        }
    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful)
                {
                    mProgressBar = ProgressDialog(this)
                    mProgressBar.setMessage("Login...")
                    mProgressBar.show()
                    val success = Intent(this,MainActivity::class.java)
                    startActivity(success)
                }
                else
                {
                    Toast.makeText(this,"User doesn't exist", Toast.LENGTH_SHORT).show()
                }
            }
    }
}