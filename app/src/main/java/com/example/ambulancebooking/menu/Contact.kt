package com.example.ambulancebooking.menu

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ambulancebooking.MainActivity
import com.example.ambulancebooking.databinding.ActivityContactBinding

class Contact : AppCompatActivity() {

    private lateinit var binding : ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners(){
        val phone = binding.tvPhone.text.toString().trim()
        binding.tvPhone.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.Theme_DeviceDefault_Dialog)
            builder.setTitle("Phone Call")
            builder.setMessage("Do you want to call $phone ?")
            builder.setPositiveButton("Yes") {
                    _, _ -> call() }
            builder.setNegativeButton("No") {
                    _, _ -> }
            builder.show()
        }

        binding.tvEmail.setOnClickListener {
            val email = binding.tvEmail.text.toString().trim()
            val intent = Intent(this, SendEmail::class.java)
            intent.putExtra("email", email)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun call(){
        val phone : String = binding.tvPhone.text.toString().trim()
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$phone")
        startActivity(intent)
    }
}