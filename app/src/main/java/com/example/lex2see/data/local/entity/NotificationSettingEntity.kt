package com.example.lex2see.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification_settings")
data class NotificationSettingEntity(
    @PrimaryKey @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "push_enabled") val pushEnabled: Boolean = true,
    @ColumnInfo(name = "morning_enabled") val morningEnabled: Boolean = true,
    @ColumnInfo(name = "morning_time") val morningTime: String? = "08:00",
    @ColumnInfo(name = "evening_enabled") val eveningEnabled: Boolean = true,
    @ColumnInfo(name = "evening_time") val eveningTime: String? = "19:00",
    @ColumnInfo(name = "review_reminder") val reviewReminder: Boolean = true,
    @ColumnInfo(name = "streak_reminder") val streakReminder: Boolean = true,
    @ColumnInfo(name = "community_notification") val communityNotification: Boolean = true,
    @ColumnInfo(name = "group_notification") val groupNotification: Boolean = true,
    @ColumnInfo(name = "updated_at") val updatedAt: Long,
    @ColumnInfo(name = "sync_status") val syncStatus: String = "PENDING_UPDATE"
)
