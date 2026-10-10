package com.example.lex2see.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lex2see.data.local.dao.DetectionDao
import com.example.lex2see.data.local.dao.ProgressDao
import com.example.lex2see.data.local.dao.StudyDao
import com.example.lex2see.data.local.dao.UserVocabularyDao
import com.example.lex2see.data.local.dao.VocabularyDao
import com.example.lex2see.data.local.entity.DailyProgressEntity
import com.example.lex2see.data.local.entity.DetectionEntity
import com.example.lex2see.data.local.entity.DetectionResultEntity
import com.example.lex2see.data.local.entity.ModelLabelEntity
import com.example.lex2see.data.local.entity.NotificationSettingEntity
import com.example.lex2see.data.local.entity.StudySessionEntity
import com.example.lex2see.data.local.entity.StudySessionItemEntity
import com.example.lex2see.data.local.entity.TopicEntity
import com.example.lex2see.data.local.entity.UserGoalEntity
import com.example.lex2see.data.local.entity.UserVocabularyEntity
import com.example.lex2see.data.local.entity.UserVocabularyTopicEntity
import com.example.lex2see.data.local.entity.VocabularyEntity

@Database(
    entities = [
        VocabularyEntity::class,
        ModelLabelEntity::class,
        TopicEntity::class,
        UserVocabularyEntity::class,
        UserVocabularyTopicEntity::class,
        DetectionEntity::class,
        DetectionResultEntity::class,
        StudySessionEntity::class,
        StudySessionItemEntity::class,
        DailyProgressEntity::class,
        UserGoalEntity::class,
        NotificationSettingEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Lex2SeeDatabase : RoomDatabase() {
    abstract fun vocabularyDao(): VocabularyDao
    abstract fun userVocabularyDao(): UserVocabularyDao
    abstract fun detectionDao(): DetectionDao
    abstract fun studyDao(): StudyDao
    abstract fun progressDao(): ProgressDao
}
