package com.example.ambulancebooking.user

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ambulancebooking.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import kotlin.collections.HashMap

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding
    private lateinit var firebaseUser : FirebaseUser
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userID : String

    private lateinit var imageUri : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        setListeners()
        showProfile()
        showImage()
    }

    private fun setListeners(){
        binding.imgProfile.setOnClickListener {
            selectImage()
        }

        binding.btnOk.setOnClickListener {
            updateProfile()
            uploadImage()
        }
    }

    private fun showToast(message : String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showProfile(){
        firebaseUser = firebaseAuth.currentUser!!
        userID = firebaseUser.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var user : Users? = snapshot.getValue(Users::class.java)
                for (dataSnapshot in snapshot.children) {
                    if (dataSnapshot.key == userID) {
                        user = dataSnapshot.getValue(Users::class.java)
                        binding.edtEmail.setText(user!!.email)
                        binding.edtName.setText(user.name)
                        binding.edtPhone.setText(user.phone)
                        binding.edtAddress.setText(user.address)
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

    private fun updateProfile(){
        val email = binding.edtEmail.text.toString().trim()
        val name = binding.edtName.text.toString().trim()
        val phone = binding.edtPhone.text.toString().trim()
        val address = binding.edtAddress.text.toString().trim()

        firebaseUser = firebaseAuth.currentUser!!
        userID = firebaseUser.uid

        val user = HashMap<String, Any>()
        user["email"] = email
        user["name"] = name
        user["phone"] = phone
        user["address"] = address
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(userID).updateChildren(user).addOnCompleteListener {task ->
            if(task.isSuccessful){
                showToast("Updated Successful")
                startActivity(Intent(this, ProfileActivity::class.java))
                finishAffinity()
            }
        }.addOnFailureListener {
            showToast("Updated Fail")
        }
    }

    private fun selectImage(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == RESULT_OK){
            imageUri = data?.data!!
            binding.imgProfile.setImageURI(imageUri)
        }
    }

    private fun uploadImage(){
//        val formatter = SimpleDateFormat("yyyy/MM/dd/HH/mm/ss", Locale.getDefault())
//        val now = Date()
//        val fileName = formatter.format(now)

        firebaseUser = firebaseAuth.currentUser!!
        userID = firebaseUser.uid
        val storageReference = FirebaseStorage.getInstance().getReference("images/$userID")

        storageReference.putFile(imageUri).addOnSuccessListener {
            binding.imgProfile.setImageURI(imageUri)
        }.addOnFailureListener{
            showToast("Change Avatar Failed")
        }
    }
}