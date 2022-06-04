package com.example.ambulancebooking.menu

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            val to = binding.edtTo.setText(intent.getStringExtra("email"))
            val intent = Intent(Intent.ACTION_VIEW,
            Uri.parse("mailto:$to"))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(intent)
        }
    }
}