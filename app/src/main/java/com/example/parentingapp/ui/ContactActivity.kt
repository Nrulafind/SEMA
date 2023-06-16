package com.example.parentingapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.parentingapp.R.*
import com.example.parentingapp.data.Contact
import com.example.parentingapp.databinding.ActivityContactBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    private lateinit var auth: FirebaseAuth
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.backIcon.setOnClickListener {
            onBackPressed()
        }

        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            val fromUid = firebaseUser.uid
            val rootRef = FirebaseFirestore.getInstance()
            val uidRef = rootRef.collection("teacher").document(fromUid)
            uidRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document.exists()) {
                        val fromUser = document.toObject(Contact::class.java)
                        val userContactsRef = rootRef.collection("contact").document(fromUid)
                            .collection("userContact")
                        userContactsRef.get().addOnCompleteListener {
                            if (it.isSuccessful) {
                                val listOfToUserNames = ArrayList<String>()
                                val listOfToUsers = ArrayList<Contact>()
                                val listOfRooms = ArrayList<String>()
                                for (d in it.result) {
                                    val toUser = d.toObject(Contact::class.java)
                                    listOfToUserNames.add(toUser.nama)
                                    listOfToUsers.add(toUser)
                                    listOfRooms.add(d.id)
                                }


                                val arrayAdapter = ArrayAdapter(
                                    this,
                                    layout.item_chat,
                                    id.tv_item_name,
                                    listOfToUserNames
                                )
                                Log.d("TAG", listOfToUserNames.toString())
                                binding.listView.adapter = arrayAdapter
                                binding.listView.onItemClickListener =
                                    AdapterView.OnItemClickListener { _, _, position, _ ->
                                        val intent = Intent(this, ChatActivity::class.java)
                                        intent.putExtra("fromUser", fromUser)
                                        intent.putExtra("toUser", listOfToUsers[position])
                                        intent.putExtra("roomId", "noRoomId")
                                        startActivity(intent)
                                        finish()
                                    }

                            }
                        }
                    }
                }
            }
        }
    }
}