package com.example.lex2see.data.repository

import com.example.lex2see.data.local.dao.VocabularyDao
import com.example.lex2see.data.local.model.RecognizedVocabulary
import java.util.Locale

class RecognitionRepository(
    private val vocabularyDao: VocabularyDao
) {
    suspend fun mapYoloLabel(
        rawLabel: String,
        modelName: String,
        modelVersion: String
    ): RecognizedVocabulary? {
        val normalized = rawLabel
            .trim()
            .lowercase(Locale.ROOT)
            .replace(Regex("\\s+"), " ")

        return vocabularyDao.mapModelLabel(
            modelName = modelName,
            modelVersion = modelVersion,
            normalizedLabel = normalized
        )
    }
}
