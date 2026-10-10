package com.example.lex2see.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    @Volatile
    private var instance: Lex2SeeDatabase? = null

    fun get(context: Context): Lex2SeeDatabase =
        instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                Lex2SeeDatabase::class.java,
                "lex2see.db"
            ).build().also { instance = it }
        }
}
