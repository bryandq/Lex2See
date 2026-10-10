package com.example.lex2see.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.lex2see.data.local.entity.DetectionEntity
import com.example.lex2see.data.local.entity.DetectionResultEntity

@Dao
interface DetectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetection(detection: DetectionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResults(results: List<DetectionResultEntity>)

    @Transaction
    suspend fun saveDetection(detection: DetectionEntity, results: List<DetectionResultEntity>) {
        insertDetection(detection)
        insertResults(results)
    }

    @Query("SELECT * FROM detection_results WHERE detection_id = :detectionId ORDER BY confidence DESC")
    suspend fun getResults(detectionId: String): List<DetectionResultEntity>
}
