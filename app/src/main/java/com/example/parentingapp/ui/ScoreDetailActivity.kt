package com.example.parentingapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parentingapp.R
import com.example.parentingapp.adapter.ScoreAdapter
import com.example.parentingapp.data.Score
import com.example.parentingapp.databinding.ActivityScoreDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

@Suppress("DEPRECATION")
class ScoreDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreDetailBinding
    private lateinit var auth: FirebaseAuth
    var db = Firebase.firestore
    var listInput: ArrayList<Double> = ArrayList(4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

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
                    binding.studentName.text = it.data?.get("name") as String
                    binding.studentGrade.text = it.data?.get("class") as String
                    binding.studentSemester.text = it.data?.get("angkatan") as String
                    getData(userId, course)
                } else {
                    Log.d("failed", "Data doesn't exist")
                }
            }
        Log.d("TAG", listInput.toString())
    }

    private fun getData(userId: String, course: String?) {
        val listScore: ArrayList<Score> = ArrayList()
        db.collection("student").document(userId).collection("Score").document(userId)
            .collection(course.toString()).document(userId)
            .get()
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
                            if (name == "UTS" || name == "UAS") listInput.add(
                                nilai.toString().toDouble()
                            )
                            listScore.add(score)
                        }
                    }
                    listInput.add(1.0)
                    listInput.add(0.0)
                    Log.d("TAG", listInput.toString())
                    val client = AsyncHttpClient()
                    val url = "https://backendsema-x6blq7wjaa-et.a.run.app/api/predict"

                    val array = JSONArray(listInput)

                    val params = RequestParams()
                    params.put("input_data", array)

                    client.post(url, params, object : AsyncHttpResponseHandler() {
                        override fun getUseSynchronousMode(): Boolean {
                            return false
                        }

                        override fun onSuccess(
                            statusCode: Int,
                            headers: Array<out Header>,
                            responseBody: ByteArray,
                        ) {
                            val result = String(responseBody)
                            Log.d("TAG", result)
                            try {
                                val responseObject = JSONObject(result)
                                val predict = responseObject.getJSONArray("results")
                                val predict2: JSONArray = predict.get(0) as JSONArray
                                //                                predict = quote2.getDouble(0)
//                                binding.status.text = predict.toString()
                                when (predict2.get(0).toString().toDouble()) {
                                    in 80.0..200.0 -> {
                                        binding.status.text = resources.getString(R.string.status1)
                                    }
                                    in 60.0..79.9 -> {
                                        binding.status.text = resources.getString(R.string.status2)
                                    }
                                    in 40.0..59.9 -> {
                                        binding.status.text = resources.getString(R.string.status3)
                                    }
                                    else -> {
                                        binding.status.text = resources.getString(R.string.status4)
                                    }
                                }

                            } catch (e: Exception) {
                                Toast.makeText(
                                    this@ScoreDetailActivity,
                                    e.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                                e.printStackTrace()
                            }
                        }

                        override fun onFailure(
                            statusCode: Int,
                            headers: Array<out Header>,
                            responseBody: ByteArray?,
                            error: Throwable?,
                        ) {
                            headers.let {
                                val errorMessage = when (statusCode) {
                                    400 -> "$statusCode : Bad Request"
                                    401 -> "$statusCode : Unauthorized"
                                    403 -> "$statusCode : Forbidden"
                                    404 -> "$statusCode : Not Found"
                                    else -> "$statusCode : ${error?.message}"
                                }
                                Toast.makeText(
                                    this@ScoreDetailActivity,
                                    errorMessage,
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.d("TAG", errorMessage)
                            }
                        }
                    })
                    val adapter = ScoreAdapter(listScore)
                    binding.rvScore.adapter = adapter
                }
            }
    }

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }
}