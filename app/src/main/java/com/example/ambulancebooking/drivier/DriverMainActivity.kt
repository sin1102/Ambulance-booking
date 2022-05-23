package com.example.ambulancebooking.drivier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ambulancebooking.R
import com.google.android.material.navigation.NavigationView

class DriverMainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_main)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> Toast.makeText(applicationContext,"Home",Toast.LENGTH_SHORT).show()
                R.id.nav_history -> Toast.makeText(applicationContext,"History",Toast.LENGTH_SHORT).show()
                R.id.nav_notification -> Toast.makeText(applicationContext,"Notification",Toast.LENGTH_SHORT).show()
                R.id.nav_Support -> Toast.makeText(applicationContext,"Support",Toast.LENGTH_SHORT).show()
                R.id.nav_Profile -> Toast.makeText(applicationContext,"Profile",Toast.LENGTH_SHORT).show()
                R.id.nav_Change_Password -> Toast.makeText(applicationContext,"Change Password",Toast.LENGTH_SHORT).show()
                R.id.nav_Log_out -> Toast.makeText(applicationContext,"Log out",Toast.LENGTH_SHORT).show()
            }

            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}