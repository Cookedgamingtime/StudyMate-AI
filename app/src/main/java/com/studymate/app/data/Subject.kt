package com.studymate.app.data

import androidx.compose.ui.graphics.Color

data class Subject(
    val id: String,
    val name: String,
    val nameHindi: String,
    val emoji: String,
    val color: Color,
    val lessons: List<Lesson> = emptyList()
)
