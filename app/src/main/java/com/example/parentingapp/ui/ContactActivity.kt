package com.example.parentingapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.parentingapp.adapter.ContactAdapter
import com.example.parentingapp.data.Contact
import com.example.parentingapp.data.ContactUser
import com.example.parentingapp.databinding.ActivityContactBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val firebaseUser = FirebaseAuth.getInstance().currentUser

        val uid = firebaseUser?.uid

        val contact = hashMapOf(
            "UID" to firebaseUser?.uid,
            "email" to "Fadela",
            "name" to "0021210023@gmail.com"
        )

        db.collection("teacher").document(uid.toString())
            .set(contact)
            .addOnSuccessListener { Log.d("TAG", "Berhasil") }
            .addOnFailureListener { Log.d("TAG", "Gagal") }

        if (firebaseUser != null) {
            val fromUid = firebaseUser.uid
            val rootRef = FirebaseFirestore.getInstance()
            val uidRef = rootRef.collection("teacher").document(fromUid)
            uidRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document.exists()) {
                        val fromUser = document.toObject(Contact::class.java)
                        val userContactsRef = rootRef.collection("contact").document(fromUid).collection("userContact")
                        userContactsRef.get().addOnCompleteListener {
                            if (it.isSuccessful) {
//                                val data = it.result.data
//                                val contact: Contact
                                val listOfToUserNames = ArrayList<String>()
                                val listOfToUsers = ArrayList<Contact>()
                                val listOfRooms = ArrayList<String>()
//                                data?.let {
//                                    for ((_, value) in data){
//                                        val v = value as Map<*, *>
//                                        val name = v["Nama"]
//                                        contact = Contact()
//                                    }
//                                }
                                for (d in it.result) {
                                    val toUser = d.toObject(Contact::class.java)
                                    listOfToUserNames.add(toUser.name)
                                    listOfToUsers.add(toUser)
                                    listOfRooms.add(d.id)
//                                    val toUser2 = user.MdtSsy6ihHTt8A7Ojlh9tkUC6Pn2
//                                    listOfToUserNames.add(toUser2?.name ?: "")
//                                    listOfToUsers.add(toUser2!!)
//                                    listOfRooms.add(d.id)
                                }

                                Log.d("TAG", listOfToUserNames.toString())

                                val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfToUserNames)
                                binding.listView.adapter = arrayAdapter
                                binding.listView.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, _ ->
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