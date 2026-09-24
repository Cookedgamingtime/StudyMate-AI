package com.studymate.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonProgressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(progress: LessonProgress)

    @Query("SELECT * FROM lesson_progress")
    fun all(): Flow<List<LessonProgress>>

    @Query("SELECT * FROM lesson_progress WHERE lessonId = :id")
    suspend fun getById(id: String): LessonProgress?

    @Query("SELECT COUNT(*) FROM lesson_progress WHERE completed = 1")
    suspend fun completedCount(): Int

    @Query("DELETE FROM lesson_progress")
    suspend fun clearAll()
}
