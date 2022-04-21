package com.example.ambulancebooking.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ambulancebooking.MainActivity
import com.example.ambulancebooking.databinding.ActivitySettingsBinding
import com.example.ambulancebooking.user.SignInActivity
import com.google.firebase.auth.FirebaseAuth

class Settings : AppCompatActivity() {

    private lateinit var binding : ActivitySettingsBinding
    private lateinit var fAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fAuth = FirebaseAuth.getInstance()
        setListeners()
    }

    private fun setListeners(){
        binding.logoutButton.setOnClickListener {
            fAuth.signOut()
            startActivity(Intent(applicationContext, SignInActivity::class.java))
            finishAffinity()
        }
        binding.backButton.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

}