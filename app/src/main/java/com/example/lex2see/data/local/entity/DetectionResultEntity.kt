package com.example.lex2see.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "detection_results",
    foreignKeys = [
        ForeignKey(
            entity = DetectionEntity::class,
            parentColumns = ["detection_id"],
            childColumns = ["detection_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = VocabularyEntity::class,
            parentColumns = ["vocabulary_id"],
            childColumns = ["vocabulary_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index(value = ["detection_id"]), Index(value = ["vocabulary_id"])]
)
data class DetectionResultEntity(
    @PrimaryKey @ColumnInfo(name = "result_id") val resultId: String,
    @ColumnInfo(name = "detection_id") val detectionId: String,
    @ColumnInfo(name = "model_label") val modelLabel: String,
    val confidence: Double,
    val x: Double,
    val y: Double,
    val width: Double,
    val height: Double,
    @ColumnInfo(name = "vocabulary_id") val vocabularyId: String? = null,
    val selected: Boolean = false,
    @ColumnInfo(name = "created_at") val createdAt: Long
)
