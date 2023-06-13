package com.example.parentingapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parentingapp.adapter.AttendanceAdapter
import com.example.parentingapp.data.Attendance
import com.example.parentingapp.databinding.ActivityDetailAttendanceBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetailAttendanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAttendanceBinding
    private lateinit var auth: FirebaseAuth
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backIcon.setOnClickListener {
            onBackPressed()
        }

        binding.rvAttendance.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.rvAttendance.layoutManager = layoutManager

        auth = Firebase.auth
        val userId = auth.currentUser?.uid.toString()

        val score =
            db.collection("student").document(userId).collection("userData").document(userId)
        score.get()
            .addOnSuccessListener {
                if (it != null) {
                    Log.d("success", "DocumentSnapshot data: ${it.data}")
                    binding.studentName.text = it.data?.get("name") as String
                    binding.studentGrade.text = it.data?.get("class") as String
                    binding.studentSemester.text = it.data?.get("angkatan") as String
                    getDataAttendance(userId)
                } else {
                    Log.d("failed", "Data doesn't exist")
                }
            }
    }

    private fun getDataAttendance(userId : String) {
        var listAttendance: ArrayList<Attendance> = ArrayList()
        db.collection("student").document(userId)
            .collection("attendance").document(userId)
            .get()
            .addOnCompleteListener {
                var attendance: Attendance
                if (it.isSuccessful) {
                    val data = it.result.data
                    data?.let {
                        for ((_, value) in data) {
                            val v = value as Map<*, *>
                            val nama = v["Nama"]
                            val tanggal = v["Tanggal"]
                            val keterangan = v["Keterangan"]
                            attendance = Attendance(
                                "$nama",
                                "$tanggal",
                                "$keterangan"
                            )
                            listAttendance.add(attendance)
                        }
                    }
                    val adapter = AttendanceAdapter(listAttendance)
                    binding.rvAttendance.adapter = adapter
                }
            }
    }
}