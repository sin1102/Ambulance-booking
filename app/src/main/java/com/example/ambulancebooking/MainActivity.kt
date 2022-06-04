package com.example.ambulancebooking

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.example.ambulancebooking.databinding.ActivityMainBinding
import com.example.ambulancebooking.map.NewMap
import com.example.ambulancebooking.menu.*
import com.example.ambulancebooking.user.ProfileActivity
import com.example.ambulancebooking.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var fAuth : FirebaseAuth
    private lateinit var fUser : FirebaseUser
    private lateinit var databaseReference : DatabaseReference
    private lateinit var binding : ActivityMainBinding
    private lateinit var userID : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fAuth = FirebaseAuth.getInstance()
        fUser = fAuth.currentUser!!
        userID = fUser.uid
        showImage()
        showProfile()
        setListeners()
    }

    private fun setListeners(){
        binding.aboutButton.setOnClickListener{
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }

        binding.contactButton.setOnClickListener{
            val intent = Intent(this, Contact::class.java)
            startActivity(intent)
        }

        bottom_nav.setOnItemSelectedListener {
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
                R.id.ic_hospital -> {
                    intent = Intent(this, Hospitals::class.java).also{
                        startActivity(it)
                    }
                    true
                }
                else -> false
            }
        }
        val checkAnim = findViewById<LottieAnimationView>(R.id.lottieAmbulance)
        checkAnim.setOnClickListener {
            val intent = Intent(this, NewMap::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun showProfile(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var user : Users? = snapshot.getValue(Users::class.java)
                for (dataSnapshot in snapshot.children) {
                    if (dataSnapshot.key == userID) {
                        user = dataSnapshot.getValue(Users::class.java)
                        binding.txtName.text = user!!.name
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun showImage(){
        val storageReference = FirebaseStorage.getInstance().getReference("images/$userID.jpg")
        val localFile = File.createTempFile("tempImage", "jpg")
        storageReference.getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            binding.imgProfile.setImageBitmap(bitmap)
        }
    }
}