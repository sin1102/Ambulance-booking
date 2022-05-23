package com.example.ambulancebooking.drivier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ambulancebooking.R
import com.example.ambulancebooking.user.PhoneSignUpActivity
import com.example.ambulancebooking.user.SignInActivity
import kotlinx.android.synthetic.main.activity_driver_sign_in.*
import kotlinx.android.synthetic.main.activity_driver_sign_up.*

class DriverSignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_sign_in)

        driverSignUp.setOnClickListener{
            val intent = Intent(this, DriverSignUpActivity::class.java)
            startActivity(intent)
        }
    }
}