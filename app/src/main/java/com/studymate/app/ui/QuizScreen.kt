package com.studymate.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studymate.app.data.QuizQuestion

@Composable
fun QuizScreen(
    question: QuizQuestion,
    language: String,
    onAnswer: (Boolean) -> Unit
) {
    var selected by remember { mutableStateOf(-1) }

    val options = if (language == "hi") question.optionsHindi else question.options
    val questionText = if (language == "hi") question.questionHindi else question.question

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9F0))
            .padding(20.dp)
    ) {
        Spacer(Modifier.height(20.dp))

        Text(
            text = questionText,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A3F35)
        )

        Spacer(Modifier.height(24.dp))

        options.forEachIndexed { index, option ->
            val isCorrect = index == question.correctIndex
            val bg = when {
                selected == -1 -> Color.White
                index == selected && isCorrect -> Color(0xFFA8E6CF)
                index == selected -> Color(0xFFFFB3B3)
                else -> Color.White
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = bg),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        if (selected == -1) {
                            selected = index
                            onAnswer(isCorrect)
                        }
                    }
            ) {
                Text(
                    text = option,
                    fontSize = 16.sp,
                    color = Color(0xFF4A3F35),
                    modifier = Modifier.padding(18.dp)
                )
            }
        }
    }
}
