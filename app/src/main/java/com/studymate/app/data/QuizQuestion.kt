package com.studymate.app.data

data class QuizQuestion(
    val id: String,
    val lessonId: String,
    val question: String,
    val questionHindi: String,
    val options: List<String>,
    val optionsHindi: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val explanationHindi: String
)
