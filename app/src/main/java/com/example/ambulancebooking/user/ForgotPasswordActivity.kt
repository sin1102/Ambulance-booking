package com.example.ambulancebooking.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ambulancebooking.R
import com.example.ambulancebooking.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding : ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}