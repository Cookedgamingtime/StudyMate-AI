package com.studymate.app.data

data class Lesson(
    val id: String,
    val subjectId: String,
    val title: String,
    val titleHindi: String,
    val content: String,
    val contentHindi: String,
    val order: Int,
    val xpReward: Int = 10
)
