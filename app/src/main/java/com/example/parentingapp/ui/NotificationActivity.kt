package com.example.parentingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parentingapp.R
import com.example.parentingapp.adapter.NotificationAdapter
import com.example.parentingapp.data.Notification
import com.example.parentingapp.databinding.ActivityNotificationBinding

@Suppress("DEPRECATION")
class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.backIcon.setOnClickListener {
            onBackPressed()
        }

        binding.rvNotification.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.rvNotification.layoutManager = layoutManager

        val listNotif = getListNotif()

        getData(listNotif)
    }

    private fun getData(listNotif: java.util.ArrayList<Notification>) {
        val adapter = NotificationAdapter(listNotif)
        binding.rvNotification.adapter = adapter
    }

    private fun getListNotif(): ArrayList<Notification> {
        val list = ArrayList<Notification>()
        list.add(
            Notification(
                R.drawable.img_news1,
                "Yeay! Study Tour",
                "Sesuai dengan program sekolah dan hasil sosialisasi pada tanggal 15 Oktober 2018, dengan ini diberitahukan bahwa Study Tour (Wisata Siswa) yang akan dilaksanakan pada bulan Desember 2018 (sekitar tanggal 9 Desember 2018/ minggu ke-2). Salah satu pertimbangannya adalah mengantisipasi rencana kenaikan BBM, sehingga beban biaya study tour bisa ditekan lebih rendah (yaitu Rp. 875.000)."
            )
        )
        list.add(
            Notification(
                R.drawable.img_news2,
                "Ujian Akhir Semester",
                "Sehubungan dengan akan dilaksanakan Ujian Akhir Semester Tahun Akademik Ganjil 2022/2023 sesuai dengan Surat Kepala Biro Administrasi Akademik Tanggal 21 Desember 2022 Perihal Tentang Pemberitahuan Ujian Akhir Semester, maka dengan ini kami memberitahukan bahwa :\n" +
                        "1. Ujian Akhir Semester Ganjil 2022/2023 dilaksanakan pada Tanggal 16 s/d 21 Januari 2023.\n" +
                        "2.Ujian dapat dilaksanakan oleh Dosen apabila perkuliahan sudah mencapai Minimal 85%.\n" +
                        "3. Mahasiswa dapat mengikuti ujian apabila sudah melunasi SPP Tahap II dan memiliki kartu ujian yang dicetak oleh Akademik Fakultas (SBAK)."
            )
        )
        list.add(
            Notification(
                R.drawable.img_news3,
                "Pembagian Rapot",
                "Berkenaan dengan Pandemi Covid-19, maka pembagian Raport Semester Genap Tahun 2020/2021 akan dilaksanakan secara online pada Hari Senin, 28 Juni 2021 melalui website resmi SMK Negeri 21 Jakarta. \n" +
                        "\n" +
                        "Pembagian Raport Semester Genap Tahun 2020/2021 melalui online ini bukan merupakan pengganti Raport Cetak. Raport asli akan diberikan secara offline dengan menunggu pemberitahuan resmi dari Pemerintah terkait Pembatasan Sosial Berskala Besar. "
            )
        )
        return list
    }
}