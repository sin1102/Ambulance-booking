package com.example.ambulancebooking.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ambulancebooking.databinding.ActivitySendEmailBinding

class SendEmail : AppCompatActivity() {

    private lateinit var binding : ActivitySendEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            val subject = binding.edtSubject.text.toString().trim()
            val message = binding.edtMessage.text.toString().trim()
            val to = binding.edtTo.setText(intent.getStringExtra("emailContact"))

            sendEmail(to.toString(), subject, message)
        }
    }

    private fun sendEmail(to : String, subject : String, message : String){
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)
        try{
            startActivity(Intent.createChooser(intent, "Choose App To Send"))
        }catch(e : Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}