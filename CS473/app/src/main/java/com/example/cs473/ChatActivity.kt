package com.example.cs473

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.textclassifier.ConversationActions
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs473.databinding.ActivityChatBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {

    private lateinit var receiverName: String
    private lateinit var receiverUid: String

    private lateinit var auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference

    private lateinit var binding: ActivityChatBinding
    private lateinit var messageList: ArrayList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        messageList = ArrayList()
        val messageAdapter: MessageAdapter = MessageAdapter(this, messageList)

        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.chatRecyclerView.adapter = messageAdapter

        //receiverName = intent.getStringExtra("name").toString()
        receiverName = "Receiver"
        //receiverUid = intent.getStringExtra("uid").toString()
        receiverUid = "TestingUID"

        auth = FirebaseAuth.getInstance()
        dbref = FirebaseDatabase.getInstance().reference

        //val senderUid = auth.currentUser?.uid
        val senderUid = "Sender"

        val senderRoom = senderUid + receiverUid
        val receiverRoom = receiverUid + senderUid

        supportActionBar?.title = receiverName

        binding.sendBtn.setOnClickListener {
            val message = binding.messageEdit.text.toString()
            val messageObject = Message(message, senderUid)

            Log.d("H", message)

            dbref.child("chats").child(senderRoom).child("messages").push().setValue(messageObject).addOnSuccessListener {
                dbref.child("chats").child(receiverRoom).child("messages").push().setValue(messageObject)
            }

            binding.messageEdit.setText("")
        }

        dbref.child("chats").child(senderRoom).child("messages").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                messageList.clear()

                for (postSnapshot in snapshot.children) {
                    val message = postSnapshot.getValue(Message::class.java)
                    messageList.add(message!!)
                }

                messageAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}