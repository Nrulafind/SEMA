package com.example.parentingapp.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    private var pref: SharedPreferences = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val editor = pref.edit()

    fun setStringPreference(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    fun clearPreferences() {
        editor.clear().apply()
    }

    val nilai = pref.getString(NILAI, "")

    companion object{
        const val PREFS_NAME = "user_pref"
        const val NILAI = "key.nilai"
    }
}