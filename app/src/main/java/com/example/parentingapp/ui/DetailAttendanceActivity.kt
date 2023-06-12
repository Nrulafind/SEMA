package com.example.parentingapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.parentingapp.databinding.ActivityDetailAttendanceBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetailAttendanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAttendanceBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        auth = Firebase.auth
        val user = auth.currentUser

        val db = Firebase.firestore
        val docRef = db.collection("student")
            .document(user!!.uid)
            .collection("userData")
            .document(user!!.uid)

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
    }

    companion object {
        const val TAG = "Detail Attendance"
    }
}