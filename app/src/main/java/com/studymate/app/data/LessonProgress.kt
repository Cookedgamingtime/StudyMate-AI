package com.studymate.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson_progress")
data class LessonProgress(
    @PrimaryKey val lessonId: String,
    val subjectId: String,
    val completed: Boolean,
    val score: Int,
    val completedAt: Long,
    val timeSpentSeconds: Long
)
