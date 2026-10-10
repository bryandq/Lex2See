package com.example.lex2see.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.lex2see.data.local.entity.ModelLabelEntity
import com.example.lex2see.data.local.entity.VocabularyEntity
import com.example.lex2see.data.local.model.RecognizedVocabulary
import kotlinx.coroutines.flow.Flow

@Dao
interface VocabularyDao {
    @Upsert
    suspend fun upsertVocabulary(vocabulary: VocabularyEntity)

    @Upsert
    suspend fun upsertVocabularies(vocabularies: List<VocabularyEntity>)

    @Upsert
    suspend fun upsertModelLabels(labels: List<ModelLabelEntity>)

    @Query("SELECT * FROM vocabularies ORDER BY headword COLLATE NOCASE")
    fun observeAll(): Flow<List<VocabularyEntity>>

    @Query("SELECT * FROM vocabularies WHERE vocabulary_id = :id LIMIT 1")
    suspend fun findById(id: String): VocabularyEntity?

    @Query(
        """
        SELECT
            v.vocabulary_id AS vocabularyId,
            v.headword AS headword,
            v.ipa AS ipa,
            v.part_of_speech AS partOfSpeech,
            v.meaning_vi AS meaningVi,
            v.example_en AS exampleEn,
            v.example_vi AS exampleVi,
            v.audio_url AS audioUrl,
            ml.model_label AS modelLabel
        FROM model_labels ml
        INNER JOIN vocabularies v ON v.vocabulary_id = ml.vocabulary_id
        WHERE ml.model_name = :modelName
          AND ml.model_version = :modelVersion
          AND ml.normalized_label = :normalizedLabel
          AND ml.active = 1
        LIMIT 1
        """
    )
    suspend fun mapModelLabel(
        modelName: String,
        modelVersion: String,
        normalizedLabel: String
    ): RecognizedVocabulary?
}
