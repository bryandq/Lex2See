package com.example.lex2see.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.lex2see.data.local.entity.StudySessionEntity
import com.example.lex2see.data.local.entity.StudySessionItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyDao {
    @Upsert
    suspend fun upsertSession(session: StudySessionEntity)

    @Upsert
    suspend fun upsertItems(items: List<StudySessionItemEntity>)

    @Query("SELECT * FROM study_sessions WHERE user_id = :userId ORDER BY started_at DESC")
    fun observeSessions(userId: String): Flow<List<StudySessionEntity>>

    @Query("SELECT * FROM study_session_items WHERE session_id = :sessionId ORDER BY created_at")
    suspend fun getItems(sessionId: String): List<StudySessionItemEntity>
}
