package com.example.parentingapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.parentingapp.R
import com.example.parentingapp.adapter.MonthAdapter
import com.example.parentingapp.data.Month
import com.example.parentingapp.databinding.FragmentAttendanceBinding

class AttendanceFragment : Fragment() {

    private var _binding: FragmentAttendanceBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<Month>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAttendanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvMonthList.layoutManager = layoutManager

        list.add(Month(R.drawable.img, "Januari"))
        list.add(Month(R.drawable.img_1, "Februari"))
        list.add(Month(R.drawable.img_2, "Maret"))
        list.add(Month(R.drawable.img_3, "April"))
        list.add(Month(R.drawable.img_4, "Mei"))
        list.add(Month(R.drawable.img_5, "Juni"))
        list.add(Month(R.drawable.img_6, "Juli"))
        list.add(Month(R.drawable.img_7, "Agustus"))
        list.add(Month(R.drawable.img_8, "September"))
        list.add(Month(R.drawable.img_9, "Oktober"))
        list.add(Month(R.drawable.img_10, "November"))
        list.add(Month(R.drawable.img_11, "Desember"))

        getData()

    }

    private fun getData() {
        val adapter = MonthAdapter(list)
        binding.rvMonthList.adapter = adapter
    }

}