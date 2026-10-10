package com.example.lex2see.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "study_session_items",
    foreignKeys = [
        ForeignKey(
            entity = StudySessionEntity::class,
            parentColumns = ["session_id"],
            childColumns = ["session_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserVocabularyEntity::class,
            parentColumns = ["user_vocabulary_id"],
            childColumns = ["user_vocabulary_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["session_id"]), Index(value = ["user_vocabulary_id"])]
)
data class StudySessionItemEntity(
    @PrimaryKey @ColumnInfo(name = "item_id") val itemId: String,
    @ColumnInfo(name = "session_id") val sessionId: String,
    @ColumnInfo(name = "user_vocabulary_id") val userVocabularyId: String,
    val round: Int,
    @ColumnInfo(name = "question_type") val questionType: String,
    @ColumnInfo(name = "swipe_result") val swipeResult: String? = null,
    @ColumnInfo(name = "first_try_correct") val firstTryCorrect: Boolean? = null,
    @ColumnInfo(name = "attempt_count") val attemptCount: Int = 0,
    val score: Double? = null,
    @ColumnInfo(name = "payload_json") val payloadJson: String? = null,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "sync_status") val syncStatus: String = "PENDING_CREATE"
)
