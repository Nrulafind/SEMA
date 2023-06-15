package com.example.parentingapp.ui
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.Menu
//import android.view.MenuItem
//import android.widget.ProgressBar
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.parentingapp.R
//import com.example.parentingapp.adapter.FirebaseMessageAdapter
//import com.example.parentingapp.databinding.ActivityChatDetailBinding
//import com.example.parentingapp.model.Message
//import com.firebase.ui.database.FirebaseRecyclerOptions
//
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase
//import java.util.*
//
//class ChatDetailActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityChatDetailBinding
////    private lateinit var auth: FirebaseAuth
//
//    private lateinit var db: FirebaseDatabase
//    private lateinit var adapter: FirebaseMessageAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityChatDetailBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
////        auth = Firebase.auth
//        val firebaseUser = Message(
//            "",
//            "Dico",
//            "https://i.pinimg.com/736x/ab/40/ac/ab40acafe4e48005a64a3055f2c0796e.jpg"
//        )
////        if (firebaseUser == null) {
////            // Not signed in, launch the Login activity
////            startActivity(Intent(this, LoginActivity::class.java))
////            finish()
////            return
////        }
//
//        db = Firebase.database
//
//        val messagesRef = db.reference.child(MESSAGES_CHILD)
//
//        binding.sendButton.setOnClickListener {
//            val friendlyMessage = Message(
//                binding.messageEditText.text.toString(),
//                firebaseUser.name,
//                firebaseUser.photoUrl,
//                Date().time
//            )
//            messagesRef.push().setValue(friendlyMessage) { error, _ ->
//                if (error != null) {
//                    Toast.makeText(this, getString(R.string.send_error) + error.message, Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this, getString(R.string.send_success), Toast.LENGTH_SHORT).show()
//                }
//            }
//            binding.messageEditText.setText("")
//        }
//
//        val manager = LinearLayoutManager(this)
//        manager.stackFromEnd = true
//        binding.messageRecyclerView.layoutManager = manager
//
//        val options = FirebaseRecyclerOptions.Builder<Message>()
//            .setQuery(messagesRef, Message::class.java)
//            .build()
//        adapter = FirebaseMessageAdapter(options, firebaseUser.name)
//        binding.messageRecyclerView.adapter = adapter
//    }
//
//    public override fun onResume() {
//        super.onResume()
//        adapter.startListening()
//    }
//
//    public override fun onPause() {
//        adapter.stopListening()
//        super.onPause()
//    }
//
////    override fun onCreateOptionsMenu(menu: Menu): Boolean {
////        val inflater = menuInflater
////        inflater.inflate(R.menu.main_menu, menu)
////        return true
////    }
////
////    override fun onOptionsItemSelected(item: MenuItem): Boolean {
////        return when (item.itemId) {
////            R.id.sign_out_menu -> {
////                signOut()
////                true
////            }
////            else -> super.onOptionsItemSelected(item)
////        }
////    }
////
////    private fun signOut() {
////        auth.signOut()
////        startActivity(Intent(this, LoginActivity::class.java))
////        finish()
////    }
//
//    companion object {
//        const val MESSAGES_CHILD = "messages"
//    }
//}