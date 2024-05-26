package com.example.cs473

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs473.databinding.ActivityMatchBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MatchActivity : AppCompatActivity() {

    lateinit var binding: ActivityMatchBinding
    lateinit var adapter: UserAdapter

    lateinit var role: String
    lateinit var topic: String

    var search = 1

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var userList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //mAuth = Firebase.auth
        //mDbRef = Firebase.database.reference
        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().reference

        userList = ArrayList()
        adapter = UserAdapter(this, userList)

        binding.userRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.userRecyclerView.adapter = adapter

        val Uid = mAuth.currentUser?.uid!!
        val name = mAuth.currentUser?.displayName!!
        val email = mAuth.currentUser?.email!!

        val user = User(name, email, Uid)

        role = intent.getStringExtra("role").toString()
        topic = intent.getStringExtra("topic").toString()

        val MatchObject = Match(user, role, topic)

        mDbRef.child("match").child(Uid).setValue(MatchObject)

        mDbRef.child("match").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.child("uid").getValue(User::class.java)
                    val currentUser_role = postSnapshot.child("role").getValue().toString()
                    val currentUser_topic = postSnapshot.child("topic").getValue().toString()

                    val uid = currentUser?.uid
                    // val uid = postSnapshot.child("uid").child("uid").getValue().toString()

                    if (mAuth.currentUser?.uid != uid && role != currentUser_role) {
                        if (search == 2) {
                            userList.add(currentUser!!)
                        } else {
                            if (topic == currentUser_topic) {
                                userList.add(currentUser!!)
                            }
                        }
                    }

                    //val currentUser = postSnapshot.getValue(User::class.java)
                    //if (mAuth.currentUser?.uid != currentUser?.uid) {
                        //userList.add(currentUser!!)
                    //}
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.see_all) {
            search = 2
        } else if (item.itemId == R.id.see_topic) {
            search = 1
        }
        userList.clear()
        mDbRef.child("match").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.child("uid").getValue(User::class.java)
                    val currentUser_role = postSnapshot.child("role").getValue().toString()
                    val currentUser_topic = postSnapshot.child("topic").getValue().toString()

                    val uid = currentUser?.uid
                    // val uid = postSnapshot.child("uid").child("uid").getValue().toString()

                    if (mAuth.currentUser?.uid != uid && role != currentUser_role) {
                        if (search == 2) {
                            userList.add(currentUser!!)
                        } else {
                            if (topic == currentUser_topic) {
                                userList.add(currentUser!!)
                            }
                        }
                    }

                    //val currentUser = postSnapshot.getValue(User::class.java)
                    //if (mAuth.currentUser?.uid != currentUser?.uid) {
                    //userList.add(currentUser!!)
                    //}
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return true
    }
}