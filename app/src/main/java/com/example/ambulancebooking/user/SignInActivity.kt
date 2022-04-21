package com.example.ambulancebooking.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.ambulancebooking.MainActivity
import com.example.ambulancebooking.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignInActivity : AppCompatActivity(){

    private lateinit var binding : ActivitySignInBinding
    private lateinit var fAuth : FirebaseAuth
    private lateinit var userAuth : FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
        signIn()
        //checkUser()
    }

    private fun setListener(){
        binding.btnSignIn.setOnClickListener {
            signIn()
        }

        binding.txtSignUp.setOnClickListener{
            startActivity(Intent(applicationContext, SignUpActivity::class.java))
        }
    }

    private fun showToast(message : String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun checkUser(){
        if(userAuth != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finishAffinity()
        }else{
            startActivity(Intent(applicationContext, SignInActivity::class.java))
        }
    }

    private fun signIn(){

        val edtEmail = binding.edtEmail.text.toString().trim()
        val edtPassword = binding.edtPassword.text.toString().trim()

        if(edtEmail.isEmpty()){
            binding.edtEmail.error = "Please enter your email"
            binding.edtEmail.requestFocus()
            return
        }else if(!Patterns.EMAIL_ADDRESS.matcher(edtEmail).matches()){
            binding.edtEmail.error = "Invalid email"
            binding.edtEmail.requestFocus()
            return
        }else if(edtPassword.isEmpty()){
            binding.edtPassword.error = "Please enter your password"
            binding.edtPassword.requestFocus()
            return
        }else{
            fAuth = FirebaseAuth.getInstance()
            userAuth = fAuth.currentUser!!
            fAuth.signInWithEmailAndPassword(edtEmail, edtPassword).addOnSuccessListener {
                if(userAuth.isEmailVerified){
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finishAffinity()
                }else{
                    showToast("Please verify your email")
                    return@addOnSuccessListener
                }
            }.addOnFailureListener {
                showToast("Wrong password or email")
                return@addOnFailureListener
            }
        }


    }

}