package com.example.ambulancebooking.menu

import android.content.Intent
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

        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this@HospitalDetail, Hospitals::class.java))
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
}