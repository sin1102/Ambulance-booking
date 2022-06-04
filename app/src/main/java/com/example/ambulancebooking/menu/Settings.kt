package com.example.ambulancebooking.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ambulancebooking.MainActivity
import com.example.ambulancebooking.databinding.ActivitySettingsBinding
import com.example.ambulancebooking.user.FingerprintActivity
import com.example.ambulancebooking.Content.LanguageSelectionActivity
import com.example.ambulancebooking.user.ChangePasswordActivity
import com.example.ambulancebooking.user.SignInOptionActivity
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
            startActivity(Intent(applicationContext, SignInOptionActivity::class.java))
            finishAffinity()
        }
        binding.backButton.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
        binding.fingerprintButton.setOnClickListener {
            startActivity(Intent(applicationContext, FingerprintActivity::class.java))
        }
        binding.languagesButton.setOnClickListener {
            startActivity(Intent(applicationContext, LanguageSelectionActivity::class.java))
        }

        binding.btnChangePassword.setOnClickListener {
            startActivity(Intent(applicationContext, ChangePasswordActivity::class.java))
        }
    }
}