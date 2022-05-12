package com.example.ambulancebooking.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ambulancebooking.MainActivity
import com.example.ambulancebooking.databinding.ActivityPhoneVerifyBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class PhoneVerifyActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPhoneVerifyBinding
    private lateinit var fAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fAuth = FirebaseAuth.getInstance()
        setListener()
    }

    private fun showToast(message : String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun loading(isLoading : Boolean){
        if(isLoading){
            binding.btnVerify.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.btnVerify.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setListener(){
        binding.btnVerify.setOnClickListener {
            receiveOTP()
        }
    }

    private fun receiveOTP(){
        loading(false)
        val otp = binding.pvOtp.text.toString().trim()

        if(otp.isEmpty()){
            showToast("Please enter otp code")
        }else{
            val mVerificationId = intent.getStringExtra("mVerificationId")
            val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId.toString(), otp)
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        fAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                val phone = fAuth.currentUser!!.phoneNumber
                showToast("Logged in as $phone")
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finishAffinity()
                loading(true)
            }
            .addOnFailureListener {e ->
                showToast("${e.message}")
            }
    }


}