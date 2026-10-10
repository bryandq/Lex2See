package com.example.lex2see.data.local.model

data class RecognizedVocabulary(
    val vocabularyId: String,
    val headword: String,
    val ipa: String?,
    val partOfSpeech: String?,
    val meaningVi: String,
    val exampleEn: String?,
    val exampleVi: String?,
    val audioUrl: String?,
    val modelLabel: String
)
