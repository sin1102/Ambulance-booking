package com.example.ambulancebooking.Menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about.*
import com.example.ambulancebooking.R

class Contact : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        backButton.setOnClickListener() {
            onBackPressed()
        }
    }
}