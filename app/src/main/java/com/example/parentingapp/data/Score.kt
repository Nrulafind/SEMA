package com.example.parentingapp.data

data class Score(
    val name: String,
    val score: String,
    val kkm: String
)

val dummyScore = listOf(
    Score("Ulangan Harian 1", "90", "kkm = 70"),
    Score("Ulangan Harian 2", "100", "kkm = 70"),
    Score("Ulangan Harian 3", "80", "kkm = 70"),
    Score("Ujian Tengan Semester", "80", "kkm = 75"),
    Score("Ulangan Harian 4", "85", "kkm = 70"),
    Score("Ulangan Harian 5", "70", "kkm = 70"),
    Score("Ulangan Harian 6", "80", "kkm = 70"),
    Score("Ujian Akhir Semester", "90", "kkm = 75"),
)

val dummyScoreMenu = dummyScore.shuffled()

//val dummyCourse = listOf(
//    Course(R.drawable.matematika, "Matematika"),
//    Course(R.drawable.bd, "Bahasa Daerah"),
//    Course(R.drawable.agama, "Agama"),
//    Course(R.drawable.bing, "Bahasa Inggris"),
//    Course(R.drawable.bindo, "Bahasa Indonesia"),
//    Course(R.drawable.biologi, "Biologi"),
//    Course(R.drawable.ekonomi, "Ekonomi"),
//    Course(R.drawable.fisika, "Fisika"),
//    Course(R.drawable.kimia, "Kimia"),
//    Course(R.drawable.pkn, "Kewarganegaraan"),
//    Course(R.drawable.prakarya, "Prakarya"),
//    Course(R.drawable.sejarah, "Sejarah")
//)
//
//val dummyCourseMenu = dummyCourse.shuffled()
