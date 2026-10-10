package com.example.lex2see.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "model_labels",
    foreignKeys = [
        ForeignKey(
            entity = VocabularyEntity::class,
            parentColumns = ["vocabulary_id"],
            childColumns = ["vocabulary_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["vocabulary_id"]),
        Index(value = ["model_name", "model_version", "normalized_label"], unique = true)
    ]
)
data class ModelLabelEntity(
    @PrimaryKey @ColumnInfo(name = "label_id") val labelId: String,
    @ColumnInfo(name = "model_name") val modelName: String,
    @ColumnInfo(name = "model_version") val modelVersion: String,
    @ColumnInfo(name = "model_label") val modelLabel: String,
    @ColumnInfo(name = "normalized_label") val normalizedLabel: String,
    @ColumnInfo(name = "vocabulary_id") val vocabularyId: String,
    val active: Boolean = true,
    @ColumnInfo(name = "created_at") val createdAt: Long
)
