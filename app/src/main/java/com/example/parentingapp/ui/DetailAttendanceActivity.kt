package com.example.parentingapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parentingapp.adapter.AttendanceAdapter
import com.example.parentingapp.adapter.PostAdapter
import com.example.parentingapp.adapter.ScoreAdapter
import com.example.parentingapp.data.Attendance
import com.example.parentingapp.data.Score
import com.example.parentingapp.databinding.ActivityDetailAttendanceBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetailAttendanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAttendanceBinding
    private lateinit var auth: FirebaseAuth
    private var listAttendance: ArrayList<Attendance> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        auth = Firebase.auth
        val user = auth.currentUser

        binding.rvAttendance.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.rvAttendance.layoutManager = layoutManager

        val db = Firebase.firestore
        val docRef = db.collection("student")
            .document(user!!.uid)
            .collection("userData")
            .document(user!!.uid)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {

                    binding.studentName.text = document.data!!["name"].toString()
                    binding.studentGrade.text = document.data!!["class"].toString()
                    binding.studentSemester.text = document.data!!["angkatan"].toString()

                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

        val attendanceRef = db.collection("student")
            .document(user!!.uid)
            .collection("attendance")
            .document(user!!.uid)

        attendanceRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {

                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

            .addOnCompleteListener {
                var attend: Attendance
                if (it.isSuccessful) {
                    val data = it.result.data
                    data?.let {
                        for ((_, value) in data) {
                            val v = value as Map<*, *>
                            val name = v["Nama"]
                            val tanggal = v["Tanggal"]
                            val keterangan = v["Keterangan"]
                            attend = Attendance(
                                "$name",
                                "$tanggal",
                                "$keterangan"
                            )
                            listAttendance.add(attend)
//                            Log.d("TAG", "$score")
//                            v.mapValues { it ->
//                                val (name, score, kkm) = it.value as List<*>
//
//                                Score(
//                                    name as String,
//                                    score as String,
//                                    kkm as String
//                                )
//
//                                listScore.add(Score(name, score, kkm))
//                            }
                        }
                    }
                    Log.d("TAG", "$listAttendance")
                    val adapter = AttendanceAdapter(listAttendance)
                    binding.rvAttendance.adapter = adapter
                }
            }
    }

    companion object {
        const val TAG = "Detail Attendance"
    }
}