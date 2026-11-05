package com.example.lex2see.data.remote.firebase

data class UserProfile(
    val uid: String = "",
    val email: String = "",
    val displayName: String = "",
    val photoUrl: String? = null,

    val xp: Long = 0,
    val level: Long = 1,
    val streak: Long = 0,

    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)