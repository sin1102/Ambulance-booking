package com.example.ambulancebooking.Menu

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
                    dialog, i -> call() }
            builder.setNegativeButton("No") {
                    dialog, i -> }
            builder.show()
        }

        binding.tvEmail.setOnClickListener {
            val email = binding.tvEmail.text.toString().trim()
            val intent = Intent(this, SendEmail::class.java)
            intent.putExtra("email", email)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun call(){
        var phone : String = binding.tvPhone.text.toString().trim()
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$phone")
        startActivity(intent)
    }
}