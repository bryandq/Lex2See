package com.example.lex2see.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "topics",
    indices = [Index(value = ["owner_id", "name"])]
)
data class TopicEntity(
    @PrimaryKey @ColumnInfo(name = "topic_id") val topicId: String,
    val name: String,
    @ColumnInfo(name = "icon_url") val iconUrl: String? = null,
    @ColumnInfo(name = "owner_id") val ownerId: String? = null,
    val type: String = "SYSTEM",
    @ColumnInfo(name = "created_at") val createdAt: Long
)
