package com.example.parentingapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parentingapp.adapter.CourseAdapter
import com.example.parentingapp.adapter.ScoreAdapter
import com.example.parentingapp.data.Score
import com.example.parentingapp.databinding.ActivityScoreDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class ScoreDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreDetailBinding
    private lateinit var auth: FirebaseAuth
    private val viewModel: ScoreViewModel by viewModels()
    var db = Firebase.firestore
    var listInput: ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val course = intent.getStringExtra(EXTRA_COURSE)

        binding.courseName.text = course
        binding.backIcon.setOnClickListener {
            onBackPressed()
        }

        binding.rvScore.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.rvScore.layoutManager = layoutManager

        auth = Firebase.auth
        val userId = auth.currentUser?.uid.toString()

        val score =
            db.collection("student").document(userId).collection("userData").document(userId)
        score.get()
            .addOnSuccessListener {
                if (it != null) {
//                    Log.d("success", "DocumentSnapshot data: ${it.data}")
                    binding.studentName.text = it.data?.get("name") as String
                    binding.studentGrade.text = it.data?.get("class") as String
                    binding.studentSemester.text = it.data?.get("angkatan") as String
                    getData(userId)
                } else {
                    Log.d("failed", "Data doesn't exist")
                }
            }
//        val score = db.collection("student/$userId/userData").whereEqualTo("userData", userId)
//        score.get()
//            .addOnSuccessListener {
//                if (it != null) {
//                    binding.studentName.text = it.documents[0].data?.get("name") as String
//                    binding.studentGrade.text = it.documents[0].data?.get("class") as String
//                    binding.studentSemester.text = it.documents[0].data?.get("angkatan") as String
//                }
//            }
//        score.get()
//            .addOnCompleteListener{
//                if (FirebaseAuth.getInstance().currentUser != null) {
//                    binding.studentName.text = it.result.get("name").toString()
//                }
//            }

//        getData(userId)
    }

    private fun getData(userId: String) {
        var listScore: ArrayList<Score> = ArrayList()
        var input1: ArrayList<Int> = ArrayList()
        var input2: ArrayList<Int> = ArrayList()
        var avg1: Double
        var avg2: Double
        db.collection("student").document(userId).collection("score").document(userId)
            .get()
//            .addOnSuccessListener { results ->
//                var listScore: ArrayList<Score> = ArrayList()
//                if ( resul) {

//                }
//            }
            .addOnCompleteListener {
                var score: Score
                if (it.isSuccessful) {
                    val data = it.result.data
                    data?.let {
                        for ((_, value) in data) {
                            val v = value as Map<*, *>
                            val name = v["Nama"]
                            val nilai = v["Nilai"]
                            val kkm = v["KKM"]
                            score = Score(
                                "$name",
                                "$nilai",
                                "$kkm"
                            )
                            if (name == "Ulangan Harian 1" || name == "Ulangan Harian 2" || name == "Ulangan Harian 3"){
                                input1.add(nilai.toString().toInt())
                            }
                            if (name == "Ulangan Harian 4" || name == "Ulangan Harian 5" || name == "Ulangan Harian 6"){
                                input2.add(nilai.toString().toInt())
                            }
                            if (name == "UTS" || name == "UAS") listInput.add(nilai.toString().toInt())
                            listScore.add(score)
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
                    avg1 = input1.average()
                    avg2 = input2.average()
                    listInput.add(avg1.toInt())
                    listInput.add(avg2.toInt())
                    viewModel.getPredict(listInput)
                    viewModel.predict.observe(this) {
                        binding.status.text = it.toString()
                    }
//                    binding.daftarNilai.text = listInput[0].toString()
                    Log.d("TAG", "$listInput")
                    val adapter = ScoreAdapter(listScore)
                    binding.rvScore.adapter = adapter
                }
            }
    }

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }
}