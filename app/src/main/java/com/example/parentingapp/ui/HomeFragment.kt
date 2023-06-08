package com.example.parentingapp.ui

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.*
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.parentingapp.R
import com.example.parentingapp.adapter.SliderAdapter
import com.example.parentingapp.databinding.FragmentHomeBinding
import com.example.parentingapp.model.SliderData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchView: SearchView
    private val images = ArrayList<SliderData>()
    private lateinit var dots: ArrayList<TextView>
    private lateinit var adapter: SliderAdapter
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        images.add(
            SliderData(
                R.drawable.slider,
                "Belajar itu keren!"
            )
        )
        images.add(
            SliderData(
                R.drawable.slider2,
                "Tetap Semangat!"
            )
        )
        images.add(
            SliderData(
                R.drawable.slider3,
                "Don't Give Up!"
            )
        )

        adapter = SliderAdapter(images)
        binding.viewpager.adapter = adapter
        dots = ArrayList()
        setupIndicator()

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })

        binding.fbAddStory.setOnClickListener(this)

        requireActivity().run{
            binding.textView2.text = intent.getParcelableExtra(EXTRA_TOKEN)
        }

        auth = Firebase.auth
        val user = auth.currentUser
        binding.textView2.text = user?.uid.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                // Tindakan yang dilakukan saat item-menu "Search" dipilih
                performSearch()
                true
            }
            R.id.notification -> {
                // Tindakan yang dilakukan saat item-menu "Settings" dipilih
                openSettings()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun performSearch() {
        // Logika untuk menjalankan pencarian
    }

    private fun openSettings() {
        // Logika untuk membuka pengaturan
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
//        if (v?.id == R.id.fb_addStory){
//            startActivity(Intent(requireContext(), ChatDetailActivity::class.java))
//        }
    }

    private fun setupIndicator() {
        for (i in 0 until images.size) {
            dots.add(TextView(requireContext()))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                dots[i].text = Html.fromHtml("&#9679")
            }
            dots[i].textSize = 18f
            binding.indicator.addView(dots[i])
        }
    }

    private fun selectedDot(position: Int) {
        for (i in 0 until images.size) {
            if (i == position) {
                dots[i].setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        com.google.android.material.R.color.design_default_color_primary
                    )
                )
            } else {
                dots[i].setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        com.google.android.material.R.color.design_default_color_secondary
                    )
                )
            }
        }
    }

    companion object{
        const val EXTRA_TOKEN = "extra_token"
    }
}