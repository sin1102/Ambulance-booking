package com.example.ambulancebooking.Menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ambulancebooking.R
import kotlinx.android.synthetic.main.activity_about.*

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        backButton.setOnClickListener(){
            onBackPressed()
        }
    }
}