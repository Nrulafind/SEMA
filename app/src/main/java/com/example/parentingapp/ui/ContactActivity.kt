package com.example.parentingapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parentingapp.adapter.ContactAdapter
import com.example.parentingapp.data.Contact
import com.example.parentingapp.data.ContactUser
import com.example.parentingapp.databinding.ActivityContactBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
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

        binding.listView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.listView.layoutManager = layoutManager

//        val listContact = getListContact()
//
//        getData(listContact)

        val firebaseUser = FirebaseAuth.getInstance().currentUser

//        val uid = firebaseUser?.uid

//        val contact = hashMapOf(
//            "MdtSsy6ihHTt8A7Ojlh9tkUC6Pn2" to ContactUser("MdtSsy6ihHTt8A7Ojlh9tkUC6Pn2", "Guru Sejarah")
//            "DbnNyR8dDdZXUEkQugGcyQfXpHs1" to ContactUser("DbnNyR8dDdZXUEkQugGcyQfXpHs1", "Guru Bahasa Indonesia")
//        )
//
////        mapOf("UID" to "MdtSsy6ihHTt8A7Ojlh9tkUC6Pn2", "nama" to "Guru Sejarah")
//
//        db.collection("contact").document(uid.toString()).collection("userContact").document(uid.toString())
//            .set(contact, SetOptions.merge())
//            .addOnSuccessListener { Log.d("TAG", "Berhasil") }
//            .addOnFailureListener { Log.d("TAG", "Gagal") }

        if (firebaseUser != null) {
            val fromUid = firebaseUser.uid
            val rootRef = FirebaseFirestore.getInstance()
            val uidRef = rootRef.collection("teacher").document(fromUid)
            uidRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document.exists()) {
                        val fromUser = document.toObject(Contact::class.java)
                        val listOfToUserNames = ArrayList<String>()
                        val listOfToUsers = ArrayList<Contact>()
                        val listOfRooms = ArrayList<String>()
                        val userContactsRef = rootRef.collection("contact").document(fromUid)
                            .collection("userContact").document(fromUid)
                        userContactsRef.get().addOnCompleteListener {
                            if (it.isSuccessful) {
                                val data = it.result.data
                                val d = it.result
                                var contact: Contact
//                                var contact2: Contact
//                                val listOfToUserNames = ArrayList<String>()
//                                val listOfToUsers = ArrayList<Contact>()
//                                val listOfRooms = ArrayList<String>()
                                data?.let {
                                    for ((_, _) in data){
                                        val v = it as Map<*, *>
                                        val name = v["nama"]
                                        contact = Contact(
                                            "", name.toString()
                                        )
                                        listOfToUsers.add(contact)
                                        listOfRooms.add(d.id)
                                    }

                                }
                                Log.d("TAG", listOfToUsers.toString())
                                val adapter = ContactAdapter(listOfToUsers, fromUser)
                                binding.listView.adapter = adapter
//                                for (d in it.result) {
//                                    val v = d as Map<*, *>
//                                    val name = v["Nama"]
//                                    val name2 = v["nama"]
//                                    listOfToUsers.add(
//                                        Contact(
//                                            "", name.toString()
//                                        )
//                                    )
//                                    listOfToUsers.add(
//                                        Contact(
//                                            "", name2.toString()
//                                        )
//                                    )
//                                    val toUser = d.toObject(Contact::class.java)
//                                    listOfToUserNames.add(
//                                        toUser.DbnNyR8dDdZXUEkQugGcyQfXpHs1?.get("nama")
//                                        .toString())
//                                    listOfToUserNames.add(
//                                        toUser.MdtSsy6ihHTt8A7Ojlh9tkUC6Pn2?.get("nama")
//                                            .toString())
//                                    listOfToUsers.add(toUser)
//                                    listOfRooms.add(d.id)
//                                    val toUser2 = toUser.MdtSsy6ihHTt8A7Ojlh9tkUC6Pn2
//                                    listOfToUserNames.add(toUser2)
//                                    listOfToUsers.add(toUser2!!)
//                                    listOfRooms.add(d.id)
                                }



//                                val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfToUserNames)
//                                binding.listView.adapter = arrayAdapter
//                                binding.listView.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, _ ->
//                                    val intent = Intent(this, ChatActivity::class.java)
//                                    intent.putExtra("fromUser", fromUser)
//                                    intent.putExtra("toUser", listOfToUsers[position])
//                                    intent.putExtra("roomId", "noRoomId")
//                                    startActivity(intent)
//                                    finish()
//                                }

                            }
                        }
                    }
                }
            }
        }


//    private fun getData(listContact: java.util.ArrayList<Contact>) {
//        val adapter = ContactAdapter(listContact)
//        binding.listView.adapter = adapter
//    }
//
//    private fun getListContact(): ArrayList<Contact> {
//        val list = ArrayList<Contact>()
//        list.add(
//            Contact(
//                "",
//                "ina_sema@gmail.com",
//                "Guru Bahasa Indonesia",
//                R.drawable.ic_baseline_account_circle_24
//            )
//        )
//        list.add(
//            Contact(
//                "",
//                "anin_sema@gmail.com",
//                "Guru Bahasa Indonesia",
//                R.drawable.ic_baseline_account_circle_24
//            )
//        )
//        list.add(
//            Contact(
//                "",
//                "hisam_sema@gmail.com",
//                "Guru Sejarah",
//                R.drawable.ic_baseline_account_circle_24
//            )
//        )
//        list.add(
//            Contact(
//                "",
//                "igo_sema@gmail.com",
//                "Guru Biologi",
//                R.drawable.ic_baseline_account_circle_24
//            )
//        )
//        list.add(
//            Contact(
//                "",
//                "kelvin_sema@gmail.com",
//                "Guru Matematika",
//                R.drawable.ic_baseline_account_circle_24
//            )
//        )
//        return list
//    }

}