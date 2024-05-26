package com.example.cs473

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.cs473.databinding.ActivityRoleBinding
import com.example.cs473.databinding.ActivityPreferenceBinding

class PreferenceActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityPreferenceBinding
    lateinit var role: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        role = intent.getStringExtra("role").toString()

        val btn_daily: Button = findViewById(R.id.btn_daily)
        val btn_money: Button = findViewById(R.id.btn_money)
        val btn_job: Button = findViewById(R.id.btn_job)
        val btn_sport: Button = findViewById(R.id.btn_sport)
        val btn_study: Button = findViewById(R.id.btn_study)
        val btn_travel: Button = findViewById(R.id.btn_travel)
        val btn_back: Button = findViewById(R.id.btn_back)

        btn_daily.setOnClickListener(this)
        btn_money.setOnClickListener(this)
        btn_job.setOnClickListener(this)
        btn_sport.setOnClickListener(this)
        btn_study.setOnClickListener(this)
        btn_travel.setOnClickListener(this)
        btn_back.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

        fun send_intent(cls: Class<*>, topic: String) {
            val intent = Intent(this@PreferenceActivity, cls)
            if (cls != RoleActivity::class.java) {
                intent.putExtra("topic", topic)
                intent.putExtra("role", role)
            }
            startActivity(intent)
        }
        if (view != null) {
            when (view.id) {
                R.id.btn_daily -> {
                    //Toast.makeText(this, "You chose daily life topic!", Toast.LENGTH_SHORT).show()
                    send_intent(MatchActivity::class.java, "daily life")
                }
                R.id.btn_money -> {
                    //Toast.makeText(this, "You chose money topic!", Toast.LENGTH_SHORT).show()
                    send_intent(MatchActivity::class.java, "money")
                }
                R.id.btn_job -> {
                    //Toast.makeText(this, "You chose job topic!", Toast.LENGTH_SHORT).show()
                    send_intent(MatchActivity::class.java, "job")
                }
                R.id.btn_sport -> {
                    //Toast.makeText(this, "You chose sport topic!", Toast.LENGTH_SHORT).show()
                    send_intent(MatchActivity::class.java, "sports")
                }
                R.id.btn_study -> {
                    //Toast.makeText(this, "You chose study topic!", Toast.LENGTH_SHORT).show()
                    send_intent(MatchActivity::class.java, "study")
                }
                R.id.btn_travel -> {
                    //Toast.makeText(this, "You chose travel topic!", Toast.LENGTH_SHORT).show()
                    send_intent(MatchActivity::class.java, "travel")
                }
                R.id.btn_back -> {
                    //Toast.makeText(this, "Going back.", Toast.LENGTH_SHORT).show()
                    send_intent(RoleActivity::class.java, "")
                }
            }
        }
    }
}