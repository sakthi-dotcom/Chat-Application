package com.example.chatapplication.ui

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.chatapplication.MainActivity
import com.example.chatapplication.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var mauth:FirebaseAuth
    private var mProgressBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mauth = FirebaseAuth.getInstance()
        mProgressBar = ProgressDialog(this)

        binding.navtoLogin.setOnClickListener {
            val nav_login = Intent(this,Login::class.java)
            startActivity(nav_login)
        }

        binding.signupSubmit.setOnClickListener {
            val enter_name = binding.edittextName.text.toString()
            val email = binding.edittextMail.text.toString()
            val password = binding.edittextPassword.text.toString()

            if (enter_name.isEmpty()){
                binding.edittextName.error = "Name Required"
            }
            else if(email.isEmpty()){
                binding.edittextMail.error = "mail required"
            }
            else if(password.isEmpty()){
                binding.edittextPassword.error = "Password required"
            }
            else{
                signup(email,password)
            }
        }

    }


    private fun signup(email: String, password: String) {
        mauth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful)
                {
                    mProgressBar = ProgressDialog(this)
                    mProgressBar!!.setMessage("Register...")
                    mProgressBar!!.show()
                    Toast.makeText(this,"User Registered Successfully",Toast.LENGTH_SHORT).show()
                    val success = Intent(this,Login::class.java)
                    startActivity(success)
                }
                else
                {
                   Toast.makeText(this,"Some error occurred",Toast.LENGTH_SHORT).show()

                }
             }
    }
}