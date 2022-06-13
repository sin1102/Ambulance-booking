package com.example.ambulancebooking.menu

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ambulancebooking.databinding.ActivityHospitalDetailBinding
import com.squareup.picasso.Picasso

class HospitalDetail : AppCompatActivity() {

    private lateinit var binding : ActivityHospitalDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHospitalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        getHospitalData()
    }

    private fun setListeners(){
        binding.btnBook.setOnClickListener {

        }
        binding.btnMap.setOnClickListener {
            displayTrack()
        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, Hospitals::class.java))
            finish()
        }
    }

    private fun getHospitalData(){
        binding.tvID.text = intent.getStringExtra("id")
        binding.tvName.text = intent.getStringExtra("name")
        binding.tvAddress.text = intent.getStringExtra("address")
        binding.tvPhone.text = intent.getStringExtra("phone")
        Picasso.get().load(intent.getStringExtra("image")).fit().centerCrop().into(binding.imgHospital)
    }

    private fun displayTrack(){
        try{
            val uri : Uri = Uri.parse("https://www.google.com.co.in/maps/dir/${intent.getStringExtra("address")}/${intent.getStringExtra("address")}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }catch (e : Exception){
            val uri : Uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}