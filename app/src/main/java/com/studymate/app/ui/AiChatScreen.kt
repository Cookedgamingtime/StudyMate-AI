package com.studymate.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ChatMessage(val text: String, val fromUser: Boolean)

@Composable
fun AiChatScreen(messages: List<ChatMessage>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9F0))
            .padding(16.dp),
        reverseLayout = true
    ) {
        items(messages.reversed()) { msg ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (msg.fromUser) Color(0xFFFF8A80) else Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(vertical = 4.dp)
                    .align(if (msg.fromUser) Alignment.End else Alignment.Start)
            ) {
                Text(
                    text = msg.text,
                    fontSize = 15.sp,
                    color = if (msg.fromUser) Color.White else Color(0xFF4A3F35),
                    modifier = Modifier.padding(14.dp)
                )
            }
        }
    }
}
