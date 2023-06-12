package com.example.parentingapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.parentingapp.R
import com.example.parentingapp.adapter.CourseAdapter
import com.example.parentingapp.data.Course
//import com.example.parentingapp.data.dummyScoreMenu
import com.example.parentingapp.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {

    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<Course>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

//        binding.rvCourseList.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCourseList.layoutManager = layoutManager
//
//        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
//        binding.rvCourseList.addItemDecoration(itemDecoration)

        list.add(Course(R.drawable.matematika, "Matematika"))
        list.add(Course(R.drawable.bd, "Bahasa Daerah"))
        list.add(Course(R.drawable.agama, "Agama"))
        list.add(Course(R.drawable.eng, "Bahasa Inggris"))
        list.add(Course(R.drawable.indo, "Bahasa Indonesia"))
        list.add(Course(R.drawable.biologi, "Biologi"))
        list.add(Course(R.drawable.ekonomi, "Ekonomi"))
        list.add(Course(R.drawable.fisika, "Fisika"))
        list.add(Course(R.drawable.kimia, "Kimia"))
        list.add(Course(R.drawable.pkn, "Kewarganegaraan"))
        list.add(Course(R.drawable.prakarya, "Prakarya"))
        list.add(Course(R.drawable.sejarah, "Sejarah"))

        getData()
    }

    private fun getData(){
        val adapter = CourseAdapter(list)
        binding.rvCourseList.adapter = adapter
    }
}