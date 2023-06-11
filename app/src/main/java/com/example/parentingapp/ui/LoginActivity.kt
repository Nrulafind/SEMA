package com.example.parentingapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.parentingapp.R
import com.example.parentingapp.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest.EXTRA_TOKEN
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            val mAuth = FirebaseAuth.getInstance()

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Otentikasi berhasil, dapatkan token Firebase
                        val user = mAuth.currentUser
                        user?.getIdToken(true)
                            ?.addOnCompleteListener { tokenTask ->
                                if (tokenTask.isSuccessful) {
                                    val firebaseToken = tokenTask.result?.token
                                    updateUI(user, firebaseToken)
                                } else {
                                    // Penanganan kesalahan saat mendapatkan token Firebase
                                }
                            }
                    } else {
                        // Penanganan kesalahan saat signInWithEmailAndPassword
                    }
                }

//            auth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "signInWithEmail:success")
//                        val user = auth.currentUser
//                        updateUI(user)
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "signInWithEmail:failure", task.exception)
//                        Toast.makeText(
//                            baseContext,
//                            "Authentication failed.",
//                            Toast.LENGTH_SHORT,
//                        ).show()
//                        updateUI(null)
//                    }
//                }
        }
    }

    private fun updateUI(currentUser: FirebaseUser?, token: String?) {
        if (currentUser != null){
            val bundle = Bundle()
            bundle.putString(HomeFragment.EXTRA_TOKEN, token)
            //startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//            val intent = Intent(this, HomeFragment::class.java).apply {
//                putExtra(EXTRA_TOKEN, token)
//            }
//            startActivity(intent)
//            finish()

            val homeFragment = HomeFragment()

            val fragmentManager =supportFragmentManager
            val fragment = fragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

            if (fragment !is HomeFragment){
                fragmentManager.beginTransaction().replace(
                    R.id.frame_container, homeFragment, HomeFragment::class.java.simpleName
                ).commit()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser, EXTRA_TOKEN)
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}