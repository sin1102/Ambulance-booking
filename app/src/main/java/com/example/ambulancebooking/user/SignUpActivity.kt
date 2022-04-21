package com.example.ambulancebooking.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.ambulancebooking.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var database : DatabaseReference
    private lateinit var userAuth : FirebaseUser
    private lateinit var fAuth : FirebaseAuth
    private lateinit var userID : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignUp.setOnClickListener{
            signUp()
        }
        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(applicationContext, SignInActivity::class.java))
            finish()
        }
    }

    private fun showToast(message : String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun signUp() {

        val edtName = binding.edtName.text.toString().trim()
        val edtEmail = binding.edtEmail.text.toString().trim()
        val edtPassword = binding.edtPassword.text.toString().trim()
        val edtConfirmPassword = binding.edtConfirmPassword.text.toString().trim()

        if(edtEmail.isEmpty()){
            binding.edtName.error = "Please enter your email"
            binding.edtName.requestFocus()
            return
        }else if(!Patterns.EMAIL_ADDRESS.matcher(edtEmail).matches()){
            binding.edtEmail.error = "Invalid email"
            binding.edtEmail.requestFocus()
            return
        }else if(edtName.isEmpty()){
            binding.edtEmail.error = "Please enter your name"
            binding.edtEmail.requestFocus()
            return
        }else if(edtPassword.isEmpty()){
            binding.edtPassword.error = "Please enter your password"
            binding.edtPassword.requestFocus()
            return
        }else if(edtPassword.length < 6){
            binding.edtPassword.error = "Your password must be bigger than 6 characters"
            binding.edtPassword.requestFocus()
            return
        }else if(edtConfirmPassword != edtPassword){
            binding.edtConfirmPassword.error = "Password doesn't match"
            binding.edtConfirmPassword.requestFocus()
            return
        }else{
            fAuth = FirebaseAuth.getInstance()
            userAuth = FirebaseAuth.getInstance().currentUser!!
            userID = FirebaseAuth.getInstance().uid!!
            fAuth.createUserWithEmailAndPassword(edtEmail, edtPassword).addOnSuccessListener {
                if(userAuth.isEmailVerified){
                    showToast("Email has been used. Please try another one")
                }else{
                    userAuth.sendEmailVerification()
                    database = FirebaseDatabase.getInstance().getReference("Users")
                    val user = Users(edtName, edtEmail)
                    database.child(userID).setValue(user)
                    val intent = Intent(applicationContext, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                    showToast("Registered Successfully")
                }

            }.addOnFailureListener {
                showToast("Email already exists")
            }

        }
    }
}

