package com.example.lex2see.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.lex2see.data.local.entity.DailyProgressEntity
import com.example.lex2see.data.local.entity.NotificationSettingEntity
import com.example.lex2see.data.local.entity.UserGoalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    @Upsert
    suspend fun upsertDailyProgress(progress: DailyProgressEntity)

    @Upsert
    suspend fun upsertGoal(goal: UserGoalEntity)

    @Upsert
    suspend fun upsertNotificationSetting(setting: NotificationSettingEntity)

    @Query("SELECT * FROM daily_progress WHERE user_id = :userId AND date = :date LIMIT 1")
    fun observeDailyProgress(userId: String, date: String): Flow<DailyProgressEntity?>

    @Query("SELECT * FROM user_goals WHERE user_id = :userId LIMIT 1")
    fun observeGoal(userId: String): Flow<UserGoalEntity?>
}
