package com.example.parentingapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parentingapp.adapter.CourseAdapter
import com.example.parentingapp.adapter.ScoreAdapter
import com.example.parentingapp.data.Input
import com.example.parentingapp.data.Score
import com.example.parentingapp.databinding.ActivityScoreDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.http.Headers

@Suppress("DEPRECATION")
class ScoreDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreDetailBinding
    private lateinit var auth: FirebaseAuth
    private val viewModel: ScoreViewModel by viewModels()
    var db = Firebase.firestore
    var listInput: ArrayList<Any> = ArrayList(4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val input = Input(listInput)
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
                    getData(userId, course)
//                    viewModel.getPredict(listInput)
                } else {
                    Log.d("failed", "Data doesn't exist")
                }
            }
        Log.d("TAG", listInput.toString())

//        viewModel.predict.observe(this) { data->
//            binding.status.text = data.results.toString()
//        }
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

    private fun getData(userId: String, course: String?) {
        var listScore: ArrayList<Score> = ArrayList()
//        var input1: ArrayList<Int> = ArrayList()
//        var input2: ArrayList<Int> = ArrayList()
//        var avg1: Double
//        var avg2: Double
        db.collection("student").document(userId).collection("Score").document(userId).collection(course.toString()).document(userId)
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
//                            if (name == "Ulangan Harian 1" || name == "Ulangan Harian 2" || name == "Ulangan Harian 3"){
//                                input1.add(nilai.toString().toInt())
//                            }
//                            if (name == "Ulangan Harian 4" || name == "Ulangan Harian 5" || name == "Ulangan Harian 6"){
//                                input2.add(nilai.toString().toInt())
//                            }
                            if (name == "UTS" || name == "UAS") listInput.add(nilai.toString().toDouble())
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
//                    avg1 = input1.average()
//                    avg2 = input2.average()
//                    listInput.add(avg1.toInt())
//                    listInput.add(avg2.toInt())
//                    binding.daftarNilai.text = listInput[0].toString()
//                    listInput.add(95)
//                    listInput.add(100)
                    listInput.add(1)
                    listInput.add(0)
                    Log.d("TAG", listInput.toString())
                    val client = AsyncHttpClient()
                    val url = "https://backendsema-x6blq7wjaa-et.a.run.app/api/predict"

//                    val params = RequestParams(listInput)
//                    params.put("input_data", listInput)

                    val array = JSONArray(listInput)
                    val jsonString = array.toString()
                    val params = StringEntity(jsonString)

//                    val params = RequestParams()
//                    params.put("input_data", listInput)

//                    client.addHeader("Content-Type", "application/json")

                    params.setContentType("application/json")
                    client.post(applicationContext, url, params, "application/json", object : AsyncHttpResponseHandler() {
                        override fun getUseSynchronousMode(): Boolean {
                            return false
                        }

                        override fun onSuccess(
                            statusCode: Int,
                            headers: Array<out Header>,
                            responseBody: ByteArray
                        ) {
                            val result = String(responseBody)
                            Log.d("TAG", result)
                            try {
                                val responseObject = JSONObject(result)
                                val quote = responseObject.getDouble("results")
                                binding.status.text = quote.toString()
                            } catch (e: Exception) {
                                Toast.makeText(this@ScoreDetailActivity, e.message, Toast.LENGTH_SHORT).show()
                                e.printStackTrace()
                            }
                        }
                        override fun onFailure(
                            statusCode: Int,
                            headers: Array<out Header>,
                            responseBody: ByteArray?,
                            error: Throwable?
                        ) {
                            headers?.let {
                                val errorMessage = when (statusCode) {
                                    400 -> "$statusCode : Bad Request"
                                    401 -> "$statusCode : Unauthorized"
                                    403 -> "$statusCode : Forbidden"
                                    404 -> "$statusCode : Not Found"
                                    else -> "$statusCode : ${error?.message}"
                                }
                                Toast.makeText(this@ScoreDetailActivity, errorMessage, Toast.LENGTH_SHORT).show()
                                Log.d("TAG", errorMessage)
                            }
                        }
                    })
                    val adapter = ScoreAdapter(listScore)
                    binding.rvScore.adapter = adapter
                }
            }
//        Log.d("TAG", listInput.toString())

//        viewModel.getPredict(listInput)
//        viewModel.predict.observe(this) { data->
//            binding.status.text = data.results.toString()
//        }
    }

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }
}