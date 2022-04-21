package com.example.ambulancebooking


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.example.ambulancebooking.Map.Map
import com.example.ambulancebooking.Menu.About
import com.example.ambulancebooking.Menu.Contact
import com.example.ambulancebooking.Menu.Payment
import com.example.ambulancebooking.Menu.Settings
import com.example.ambulancebooking.databinding.ActivityMainBinding
import com.example.ambulancebooking.user.ProfileActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isChecked = false
    private lateinit var fAuth : FirebaseAuth
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fAuth = FirebaseAuth.getInstance()
        checkUser()

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
                R.id.ic_profile -> {
                    intent = Intent(this, ProfileActivity::class.java).also {
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

    private fun checkUser(){
        val firebaseUser = fAuth.currentUser
        if(fAuth != null){
            val email = firebaseUser?.email
            binding.txtName.text = email
        }
    }

}