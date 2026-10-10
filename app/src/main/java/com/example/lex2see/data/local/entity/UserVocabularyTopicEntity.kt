package com.example.lex2see.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "user_vocabulary_topics",
    primaryKeys = ["user_vocabulary_id", "topic_id"],
    foreignKeys = [
        ForeignKey(
            entity = UserVocabularyEntity::class,
            parentColumns = ["user_vocabulary_id"],
            childColumns = ["user_vocabulary_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TopicEntity::class,
            parentColumns = ["topic_id"],
            childColumns = ["topic_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["topic_id"])]
)
data class UserVocabularyTopicEntity(
    @ColumnInfo(name = "user_vocabulary_id") val userVocabularyId: String,
    @ColumnInfo(name = "topic_id") val topicId: String,
    @ColumnInfo(name = "is_primary") val isPrimary: Boolean = false,
    @ColumnInfo(name = "created_at") val createdAt: Long
)
