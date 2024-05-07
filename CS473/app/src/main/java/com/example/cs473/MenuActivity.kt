package com.example.cs473

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu) // Change 'activity_main' to your actual layout file name that contains the menu

        // Setup listener for "Chat"
        findViewById<TextView>(R.id.menu_chat).setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        // Setup listener for "My Profile"
        findViewById<TextView>(R.id.menu_myprofile).setOnClickListener {
            val intent = Intent(this, MyProfileActivity::class.java)
            startActivity(intent)
        }
    }

}
