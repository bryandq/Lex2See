package com.example.lex2see.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_vocabularies",
    foreignKeys = [
        ForeignKey(
            entity = VocabularyEntity::class,
            parentColumns = ["vocabulary_id"],
            childColumns = ["vocabulary_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index(value = ["vocabulary_id"]),
        Index(value = ["user_id", "normalized_word"], unique = true),
        Index(value = ["user_id", "next_review_at"])
    ]
)
data class UserVocabularyEntity(
    @PrimaryKey @ColumnInfo(name = "user_vocabulary_id") val userVocabularyId: String,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "vocabulary_id") val vocabularyId: String? = null,
    @ColumnInfo(name = "normalized_word") val normalizedWord: String,
    @ColumnInfo(name = "custom_word") val customWord: String? = null,
    @ColumnInfo(name = "custom_ipa") val customIpa: String? = null,
    @ColumnInfo(name = "custom_meaning") val customMeaning: String? = null,
    val source: String,
    val status: String = "NEW",
    @ColumnInfo(name = "srs_stage") val srsStage: Int = 0,
    @ColumnInfo(name = "srs_ease") val srsEase: Double = 2.5,
    @ColumnInfo(name = "srs_interval_days") val srsIntervalDays: Int = 0,
    @ColumnInfo(name = "next_review_at") val nextReviewAt: Long? = null,
    val repetitions: Int = 0,
    @ColumnInfo(name = "wrong_streak") val wrongStreak: Int = 0,
    @ColumnInfo(name = "forget_count") val forgetCount: Int = 0,
    @ColumnInfo(name = "last_reviewed_at") val lastReviewedAt: Long? = null,
    @ColumnInfo(name = "sync_status") val syncStatus: String = "PENDING_CREATE",
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "updated_at") val updatedAt: Long
)
