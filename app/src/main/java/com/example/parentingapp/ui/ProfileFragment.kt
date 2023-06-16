package com.example.parentingapp.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.parentingapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        auth = Firebase.auth
        val firebaseUser = auth.currentUser

        db.collection("student").document(firebaseUser?.uid.toString())
            .collection("userData").document(firebaseUser?.uid.toString())
            .get()
            .addOnSuccessListener {
                if (it != null) {
                    Log.d("success", "DocumentSnapshot data: ${it.data}")
                    binding.username.text = it.data?.get("name") as String
                    binding.email.text = it.data?.get("class") as String
                    binding.tvParent.text = it.data?.get("email") as String
                    Glide.with(this)
                        .load(it.data?.get("profile"))
                        .into(binding.avatar)
                } else {
                    Log.d("failed", "Data doesn't exist")
                }
            }

        if (firebaseUser == null) {
            requireActivity().run {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            return
        }

        binding.apply {
            clLanguage.setOnClickListener {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }
            clTheme.setOnClickListener {
                startActivity(Intent(requireActivity(), SettingActivity::class.java))
            }
            clAccessbility.setOnClickListener {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }
            btnLogout.setOnClickListener {
                signOut()
            }
        }
    }

    private fun signOut() {
        auth.signOut()
        requireActivity().run {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
