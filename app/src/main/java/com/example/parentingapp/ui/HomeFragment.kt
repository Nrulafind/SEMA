package com.example.parentingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.parentingapp.R
import com.example.parentingapp.adapter.NewsAdapter
import com.example.parentingapp.adapter.PostAdapter
import com.example.parentingapp.adapter.SliderAdapter
import com.example.parentingapp.data.Course
import com.example.parentingapp.data.News
import com.example.parentingapp.data.Post
import com.example.parentingapp.databinding.FragmentHomeBinding
import com.example.parentingapp.model.SliderData
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest.EXTRA_TOKEN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val images = ArrayList<SliderData>()
    private lateinit var dots: ArrayList<TextView>
    private lateinit var adapter: SliderAdapter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var postAdapter: PostAdapter
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
        binding.rvNews.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPost.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvNews.adapter = newsAdapter
        binding.rvPost.adapter = postAdapter



        binding.searchView.queryHint = getString(R.string.search_hint)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Metode ini dipanggil saat pengguna menekan tombol Submit pada keyboard
                if (!query.isNullOrEmpty()) {
                    performSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Metode ini dipanggil saat teks pencarian berubah
                if (!newText.isNullOrEmpty()) {
                    performSearch(newText)
                } else {
                    // Jika teks pencarian kosong, lakukan sesuatu
                }
                return true
            }
        })

        binding.imageView.setOnClickListener {
            val intent = Intent(requireContext(), NotificationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addListNews() {
        listNews.add(News(R.drawable.img_news1, "STUDY TOUR KELAS 12", "Dalam rangka perpisahan kelas.."))
        listNews.add(News(R.drawable.img_news2, "UJIAN AKHIR SEMESTER", "Dengan ini kami mengumumkan."))
        listNews.add(News(R.drawable.img_news3, "PEMBAGIAN RAPOT", "Pembagian rapot akan dilakukan"))
        listNews.add(News(R.drawable.img_news4, "LIBUR SEMESTER", "Libur semester genap akan di..."))
    }

    private fun addListPost() {
        listPost.add(Post(R.drawable.img_post1, R.drawable.img_teacher1, "Pak Jung", "Baru Saja"))
        listPost.add(Post(R.drawable.img_post2, R.drawable.img_teacher2, "Bu Kim", "2 Jam yang lalu"))
    }

    private fun performSearch(query: String) {
        // Lakukan pencarian berdasarkan query yang diberikan
        // Anda dapat mengganti logika ini dengan logika pencarian yang sesuai dengan aplikasi Anda
        Log.d("Search", "Query: $query")
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
                        R.color.light_green
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