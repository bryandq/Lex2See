package com.example.lex2see.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "study_sessions",
    indices = [Index(value = ["user_id", "started_at"])]
)
data class StudySessionEntity(
    @PrimaryKey @ColumnInfo(name = "session_id") val sessionId: String,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "session_type") val sessionType: String,
    @ColumnInfo(name = "planned_count") val plannedCount: Int,
    @ColumnInfo(name = "completed_count") val completedCount: Int = 0,
    @ColumnInfo(name = "first_try_correct") val firstTryCorrect: Int = 0,
    @ColumnInfo(name = "first_try_accuracy") val firstTryAccuracy: Double? = null,
    val status: String = "IN_PROGRESS",
    @ColumnInfo(name = "started_at") val startedAt: Long,
    @ColumnInfo(name = "completed_at") val completedAt: Long? = null,
    @ColumnInfo(name = "sync_status") val syncStatus: String = "PENDING_CREATE"
)
