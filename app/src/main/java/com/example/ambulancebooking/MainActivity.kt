package com.example.ambulancebooking


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.libraries.places.api.Places

class MainActivity : AppCompatActivity() {

    var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var checkAnim = findViewById<LottieAnimationView>(R.id.lottieAmbulance)
        checkAnim.setOnClickListener{
            val intent = Intent(this, Map::class.java)
            startActivity(intent)
        }
    }

}