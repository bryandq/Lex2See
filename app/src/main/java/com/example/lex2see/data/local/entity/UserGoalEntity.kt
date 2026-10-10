package com.example.lex2see.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_goals",
    indices = [Index(value = ["user_id"], unique = true)]
)
data class UserGoalEntity(
    @PrimaryKey @ColumnInfo(name = "goal_id") val goalId: String,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "daily_new_words") val dailyNewWords: Int = 15,
    @ColumnInfo(name = "daily_review_words") val dailyReviewWords: Int = 30,
    @ColumnInfo(name = "morning_reminder") val morningReminder: String? = null,
    @ColumnInfo(name = "evening_reminder") val eveningReminder: String? = null,
    val timezone: String = "Asia/Ho_Chi_Minh",
    @ColumnInfo(name = "updated_at") val updatedAt: Long,
    @ColumnInfo(name = "sync_status") val syncStatus: String = "PENDING_UPDATE"
)
