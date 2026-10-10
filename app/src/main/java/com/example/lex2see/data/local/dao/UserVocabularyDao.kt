package com.example.lex2see.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.lex2see.data.local.entity.TopicEntity
import com.example.lex2see.data.local.entity.UserVocabularyEntity
import com.example.lex2see.data.local.entity.UserVocabularyTopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserVocabularyDao {
    @Upsert
    suspend fun upsert(word: UserVocabularyEntity)

    @Upsert
    suspend fun upsertTopics(topics: List<TopicEntity>)

    @Upsert
    suspend fun linkTopic(link: UserVocabularyTopicEntity)

    @Query("SELECT * FROM user_vocabularies WHERE user_id = :userId ORDER BY updated_at DESC")
    fun observeForUser(userId: String): Flow<List<UserVocabularyEntity>>

    @Query(
        """
        SELECT * FROM user_vocabularies
        WHERE user_id = :userId
          AND normalized_word = :normalizedWord
        LIMIT 1
        """
    )
    suspend fun findDuplicate(userId: String, normalizedWord: String): UserVocabularyEntity?

    @Query(
        """
        SELECT * FROM user_vocabularies
        WHERE user_id = :userId
          AND next_review_at IS NOT NULL
          AND next_review_at <= :now
          AND status != 'ARCHIVED'
        ORDER BY next_review_at ASC, forget_count DESC
        LIMIT :limit
        """
    )
    suspend fun getDueForReview(userId: String, now: Long, limit: Int): List<UserVocabularyEntity>
}
