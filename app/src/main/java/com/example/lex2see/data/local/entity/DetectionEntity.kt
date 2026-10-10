package com.example.lex2see.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "detections",
    indices = [Index(value = ["user_id", "created_at"])]
)
data class DetectionEntity(
    @PrimaryKey @ColumnInfo(name = "detection_id") val detectionId: String,
    @ColumnInfo(name = "user_id") val userId: String,
    val source: String,
    @ColumnInfo(name = "model_name") val modelName: String,
    @ColumnInfo(name = "model_version") val modelVersion: String,
    @ColumnInfo(name = "image_local_uri") val imageLocalUri: String? = null,
    @ColumnInfo(name = "shared_image_url") val sharedImageUrl: String? = null,
    @ColumnInfo(name = "sync_status") val syncStatus: String = "LOCAL_ONLY",
    @ColumnInfo(name = "created_at") val createdAt: Long
)
