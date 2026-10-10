package com.example.lex2see.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "daily_progress",
    indices = [Index(value = ["user_id", "date"], unique = true)]
)
data class DailyProgressEntity(
    @PrimaryKey @ColumnInfo(name = "progress_id") val progressId: String,
    @ColumnInfo(name = "user_id") val userId: String,
    val date: String,
    @ColumnInfo(name = "new_target") val newTarget: Int,
    @ColumnInfo(name = "new_learned") val newLearned: Int = 0,
    @ColumnInfo(name = "review_due") val reviewDue: Int = 0,
    @ColumnInfo(name = "review_completed") val reviewCompleted: Int = 0,
    @ColumnInfo(name = "xp_gained") val xpGained: Int = 0,
    val streak: Int = 0,
    @ColumnInfo(name = "detections_count") val detectionsCount: Int = 0,
    @ColumnInfo(name = "community_count") val communityCount: Int = 0,
    @ColumnInfo(name = "updated_at") val updatedAt: Long,
    @ColumnInfo(name = "sync_status") val syncStatus: String = "PENDING_UPDATE"
)
