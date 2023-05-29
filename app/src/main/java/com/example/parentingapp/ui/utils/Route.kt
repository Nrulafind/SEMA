package com.example.parentingapp.ui.utils

sealed class Route(val path: String) {
    object Home : Route("home")
    object Detail : Route("home/{courseTitle}") {
        fun createRoute(courseTitle: String) = "home/$courseTitle"
    }
}
