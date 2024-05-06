package com.example.cs473

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs473.databinding.ReceiveBinding
import com.google.firebase.auth.FirebaseAuth

class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val receiveMessage: TextView = itemView.findViewById(R.id.receive_message_text)
}

class SendViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val sendMessage: TextView = itemView.findViewById(R.id.send_message_text)
}

class MessageAdapter(private val context: Context, private val messageList: ArrayList<Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val receive = 1
    private val send = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val view: View = LayoutInflater.from(context).inflate(R.layout.receive, parent, false)
            ReceiveViewHolder(view)
        } else {
            val view: View = LayoutInflater.from(context).inflate(R.layout.send, parent, false)
            SendViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]

        if (holder.javaClass == SendViewHolder::class.java) {
            val viewHolder = holder as SendViewHolder
            viewHolder.sendMessage.text = currentMessage.message
        } else {
            val viewHolder = holder as ReceiveViewHolder
            viewHolder.receiveMessage.text = currentMessage.message
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]

        return send

        // 아래 함수는 FirebaseAuth를 이용하여 로그인하였을 때 사용
        //return if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.sendId)) {
            //send
        //} else {
            //receive
        //}
    }

    }