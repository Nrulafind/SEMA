package com.example.parentingapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parentingapp.R
import com.example.parentingapp.data.Contact
import com.example.parentingapp.databinding.ActivityChatDetailBinding
import com.example.parentingapp.model.Message
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions

@Suppress("DEPRECATION")
class ChatDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatDetailBinding
    private var rootRef: FirebaseFirestore? = null
    private var fromUid: String? = ""
    private var adapter: MessageAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rootRef = FirebaseFirestore.getInstance()

        supportActionBar?.hide()

        val fromUser = intent.extras?.get("fromUser") as Contact
        fromUid = fromUser.UID
        var fromRooms = fromUser.rooms
        val toUser = intent.extras!!.get("toUser") as Contact
        val toUid = toUser.UID
        var toRooms = toUser.rooms

        var roomId = intent.extras!!.get("roomId") as String

        if (roomId == "noRoomId") {
            roomId = rootRef!!.collection("messages").document().id
            if (fromRooms != null) {
                for ((key, _) in fromRooms) {
                    if (toRooms != null){
                        if (toRooms.contains(key)){
                            roomId = key
                        }
                    }
                }
            }
        }
        binding.send.setOnClickListener {
            if (fromRooms == null) {
                fromRooms = mutableMapOf()
            }
            fromRooms!![roomId] = true
            fromUser.rooms = fromRooms
            rootRef!!.collection("teacher").document(fromUid!!).set(fromUser, SetOptions.merge())
            rootRef!!.collection("contact").document(fromUid!!).collection("userContact").document(fromUid!!).set(toUser, SetOptions.merge())
            rootRef!!.collection("rooms").document(fromUid!!).collection("userRooms").document(roomId).set(fromUser, SetOptions.merge())

            if (toRooms == null) {
                toRooms = mutableMapOf()
            }
            toRooms!![roomId] = true
            toUser.rooms = toRooms
            rootRef!!.collection("teacher").document(fromUid!!).set(toUser, SetOptions.merge())
            rootRef!!.collection("contact").document(fromUid!!).collection("userContact").document(fromUid!!).set(toUser, SetOptions.merge())
            rootRef!!.collection("rooms").document(fromUid!!).collection("userRooms").document(roomId).set(toUser, SetOptions.merge())

            val messageText = binding.editText.text.toString()
            val message = Message(messageText, fromUid!!)
            rootRef!!.collection("messages").document(roomId).collection("roomMessages").add(message)
            binding.editText.text.clear()
        }

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        val query = rootRef!!.collection("messages").document(roomId).collection("roomMessages").orderBy("sentAt", Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<Message>().setQuery(query, Message::class.java).build()
        adapter = MessageAdapter(options)
        binding.recyclerView.adapter = adapter

        title = toUser.name

        binding.backIcon.setOnClickListener {
            onBackPressed()
        }
    }

    inner class MessageViewHolder internal constructor(private val view: View) : RecyclerView.ViewHolder(view){
        internal fun setMessage(message: Message) {
            val textView = view.findViewById<TextView>(R.id.send_message)
            textView.text = message.messageText
//            binding.textView4.text = message.messageText
        }
    }

    inner class MessageAdapter internal constructor(options: FirestoreRecyclerOptions<Message>) : FirestoreRecyclerAdapter<Message, MessageViewHolder>(options){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
            return if (viewType == R.layout.item_message_to) {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_to, parent, false)
                MessageViewHolder(view)
            } else {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_from, parent, false)
                MessageViewHolder(view)
            }
        }

        override fun onBindViewHolder(holder: MessageViewHolder, position: Int, model: Message) {
            holder.setMessage(model)
        }

        override fun getItemViewType(position: Int): Int {
            return if (fromUid!! != getItem(position).fromUid) {
                R.layout.item_message_to
            } else {
                R.layout.item_message_from
            }
        }

        override fun onDataChanged() {
            binding.recyclerView.layoutManager?.scrollToPosition(itemCount - 1)
        }
    }

    override fun onStart() {
        super.onStart()

        if (adapter != null) {
            adapter!!.startListening()
        }
    }

    override fun onStop() {
        super.onStop()

        if (adapter != null) {
            adapter!!.stopListening()
        }
    }
}