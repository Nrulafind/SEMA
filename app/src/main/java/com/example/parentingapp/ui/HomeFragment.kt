package com.example.parentingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.parentingapp.R
import com.example.parentingapp.adapter.NewsAdapter
import com.example.parentingapp.adapter.PostAdapter
import com.example.parentingapp.adapter.SliderAdapter
import com.example.parentingapp.data.News
import com.example.parentingapp.data.Post
import com.example.parentingapp.databinding.FragmentHomeBinding
import com.example.parentingapp.model.SliderData
import java.util.*

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val images = ArrayList<SliderData>()
    private lateinit var dots: ArrayList<TextView>
    private lateinit var adapter: SliderAdapter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var postAdapter: PostAdapter
    private lateinit var searchView: SearchView
    private val listNews = ArrayList<News>()
    private val listPost = ArrayList<Post>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
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

        addListNews()
        addListPost()

        newsAdapter = NewsAdapter(listNews)
        postAdapter = PostAdapter(listPost)
        binding.rvNews.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPost.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvNews.adapter = newsAdapter
        binding.rvPost.adapter = postAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterList(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        binding.searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.viewpager.visibility = View.GONE
                binding.indicator.visibility = View.GONE
                binding.fbMessage.visibility = View.GONE
            } else {
                binding.viewpager.visibility = View.VISIBLE
                binding.indicator.visibility = View.VISIBLE
                binding.fbMessage.visibility = View.VISIBLE
            }
        }

        binding.imageView.setOnClickListener {
            val intent = Intent(requireContext(), NotificationActivity::class.java)
            startActivity(intent)
        }

        binding.fbMessage.setOnClickListener {
            val intent = Intent(requireContext(), ChatActivity::class.java)
            startActivity(intent)
        }
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filterList = ArrayList<News>()
            for (i in listNews) {
                if (i.title.toLowerCase(Locale.ROOT).contains(query))
                    filterList.add(i)
            }
            if (filterList.isEmpty()) {
                Toast.makeText(requireActivity(), "No Data Found", Toast.LENGTH_SHORT).show()
            } else {
                newsAdapter.setFilteredList(filterList)
            }
        }
    }

    private fun addListNews() {
        listNews.add(
            News(
                R.drawable.img_news1,
                "STUDY TOUR KELAS 12",
                getString(R.string.desc_news1)
            )
        )
        listNews.add(
            News(
                R.drawable.img_news2,
                "UJIAN AKHIR SEMESTER",
                getString(R.string.desc_news2)
            )
        )
        listNews.add(
            News(
                R.drawable.img_news3,
                "PEMBAGIAN RAPOT",
                getString(R.string.desc_news3)
            )
        )
        listNews.add(
            News(
                R.drawable.img_news4,
                "LIBUR SEMESTER",
                getString(R.string.desc_news4)
            )
        )
    }

    private fun addListPost() {
        listPost.add(
            Post(
                R.drawable.img_post1,
                R.drawable.img_teacher1,
                "Pak Jung",
                "Baru Saja",
                "deskripsi"
            )
        )
        listPost.add(
            Post(
                R.drawable.img_post2,
                R.drawable.img_teacher2,
                "Bu Kim",
                "2 Jam yang lalu",
                "deskripsi"
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
                        R.color.dark_orange
                    )
                )
            } else {
                dots[i].setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.dark_green
                    )
                )
            }
        }
    }

}