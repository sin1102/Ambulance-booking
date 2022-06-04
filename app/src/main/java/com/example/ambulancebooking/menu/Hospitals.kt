package com.example.ambulancebooking.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ambulancebooking.adapter.HospitalAdapter
import com.example.ambulancebooking.databinding.ActivityHospitalsBinding
import com.example.ambulancebooking.model.Hospital
import com.google.firebase.database.*

class Hospitals : AppCompatActivity() {

    private lateinit var binding : ActivityHospitalsBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var hospitalRecyclerView : RecyclerView
    private lateinit var hospitalArrayList : ArrayList<Hospital>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHospitalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewHospital.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewHospital.setHasFixedSize(true)

        hospitalArrayList = arrayListOf<Hospital>()
        getHospitalData()
    }

    private fun getHospitalData(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Hospitals")

        databaseReference.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(hospitalSnapshot in snapshot.children){
                        val hospital = hospitalSnapshot.getValue(Hospital::class.java)
                        hospitalArrayList.add(hospital!!)
                    }
                    hospitalRecyclerView.adapter = HospitalAdapter(hospitalArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}