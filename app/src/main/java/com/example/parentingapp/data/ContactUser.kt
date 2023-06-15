package com.example.parentingapp.data

data class ContactUser(
    val DbnNyR8dDdZXUEkQugGcyQfXpHs1: Contact? = null,
    val MdtSsy6ihHTt8A7Ojlh9tkUC6Pn2: Contact? = null
) {
    constructor() : this(
        Contact(
            "DbnNyR8dDdZXUEkQugGcyQfXpHs1",
            "",
            "Guru Bahasa Indonesia"
        )
    )
}
