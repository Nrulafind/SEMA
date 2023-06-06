package com.example.parentingapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.parentingapp.R
import com.example.parentingapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchView: SearchView

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

        binding.fbAddStory.setOnClickListener(this)
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
}