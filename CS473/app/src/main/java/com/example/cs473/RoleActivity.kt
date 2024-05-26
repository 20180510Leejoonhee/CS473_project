package com.example.cs473

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.cs473.databinding.ActivityRoleBinding

class RoleActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityRoleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.activity_match)

        val btn_listener: Button = findViewById(R.id.btn_listener)
        val btn_talker: Button = findViewById(R.id.btn_talker)
        val btn_back: Button = findViewById(R.id.btn_back)

        btn_listener.setOnClickListener(this)
        btn_talker.setOnClickListener(this)
        btn_back.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        fun send_intent(cls: Class<*>, role: String) {
            val intent = Intent(this@RoleActivity, cls)
            if (cls != MainActivity::class.java) {
                intent.putExtra("role", role)
            }
            startActivity(intent)
        }
        if (view != null) {
            when (view.id) {
                R.id.btn_listener -> {
                    //Toast.makeText(this, "You chose listener!", Toast.LENGTH_SHORT).show()
                    send_intent(PreferenceActivity::class.java, "listener")
                }
                R.id.btn_talker -> {
                    //Toast.makeText(this, "You chose talker!", Toast.LENGTH_SHORT).show()
                    send_intent(PreferenceActivity::class.java, "talker")
                }
                R.id.btn_back -> {
                    //Toast.makeText(this, "Going back.", Toast.LENGTH_SHORT).show()
                    send_intent(MainActivity::class.java, "")
                }
            }
        }
    }
}