package com.example.parentingapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.parentingapp.databinding.ActivityDetailAttendanceBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetailAttendance : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAttendanceBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val db = Firebase.firestore
        auth = Firebase.auth

        val user = auth.currentUser
        val docRef = db.collection("userData").document(user!!.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.studentName.text = document.data!!["name"].toString()
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

//        val postListener = object : ValueEventListener(postPreference: DatabaseReference) {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // Get Post object and use the values to update the UI
//                val post = dataSnapshot.getValue<User>()
//                binding.studentName.text = post?.name
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//            }
//        }
//        postReference.addValueEventListener(postListener)
    }

    companion object {
        const val TAG = "Detail Attendance"
    }
}