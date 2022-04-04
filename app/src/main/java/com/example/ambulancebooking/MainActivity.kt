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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        aboutButton.setOnClickListener{
            val intent = Intent(this, About::class.java).also {
                startActivity(it)
            }
        }

        contactButton.setOnClickListener{
            val intent = Intent(this, Contact::class.java).also {
                startActivity(it)
            }
        }

//        bottom_nav.setOnItemReselectedListener() {
//            when(it.itemId){
//                R.id.ic_settings -> {
//                    intent = Intent(this, Settings::class.java).also {
//                        startActivity(it)
//                    }
//                }
//                R.id.ic_payment -> {
//                    intent = Intent(this, Payment::class.java).also {
//                        startActivity(it)
//                    }
//                }
//            }
//        }

        bottom_nav.setOnItemSelectedListener() {
            when(it.itemId) {
                R.id.ic_payment -> {
                    intent = Intent(this, Payment::class.java).also {
                        startActivity(it)
                    }
                    true
                }
                R.id.ic_settings -> {
                    intent = Intent(this, Settings::class.java).also {
                        startActivity(it)
                    }
                    true
                }
                else -> false
            }
        }
            var checkAnim = findViewById<LottieAnimationView>(R.id.lottieAmbulance)
            checkAnim.setOnClickListener {
                val intent = Intent(this, Map::class.java).also {
                    startActivity(it)
                }

            }
        }
    }