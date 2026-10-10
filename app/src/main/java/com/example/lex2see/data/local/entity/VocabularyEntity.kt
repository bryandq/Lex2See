package com.example.lex2see.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vocabularies",
    indices = [Index(value = ["normalized_word"])]
)
data class VocabularyEntity(
    @PrimaryKey @ColumnInfo(name = "vocabulary_id") val vocabularyId: String,
    val headword: String,
    @ColumnInfo(name = "normalized_word") val normalizedWord: String,
    val ipa: String? = null,
    @ColumnInfo(name = "part_of_speech") val partOfSpeech: String? = null,
    @ColumnInfo(name = "meaning_vi") val meaningVi: String,
    @ColumnInfo(name = "example_en") val exampleEn: String? = null,
    @ColumnInfo(name = "example_vi") val exampleVi: String? = null,
    @ColumnInfo(name = "audio_url") val audioUrl: String? = null,
    @ColumnInfo(name = "image_url") val imageUrl: String? = null,
    @ColumnInfo(name = "difficulty_level") val difficultyLevel: Int? = null,
    val source: String = "SYSTEM",
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "updated_at") val updatedAt: Long
)
