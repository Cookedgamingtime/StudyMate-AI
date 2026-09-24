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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studymate.app.logic.DebugHelper

@Composable
fun DebugConsoleScreen(onBack: () -> Unit) {
    val logs by DebugHelper.logs.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
            .padding(16.dp)
    ) {
        Text(
            text = "Debug Console",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Logs: ${logs.size}",
                color = Color.White,
                modifier = Modifier.padding(12.dp)
            )
        }

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth()
        ) {
            items(logs.reversed()) { entry ->
                val color = when (entry.level) {
                    "ERROR" -> Color(0xFFFF6B6B)
                    "WARN" -> Color(0xFFFFD93D)
                    else -> Color(0xFFA8E6CF)
                }
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                ) {
                    Column(Modifier.padding(10.dp)) {
                        Text(
                            text = "${entry.time} [${entry.level}]",
                            color = color,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = entry.message,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = { DebugHelper.clear() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4D4D)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear Logs")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = onBack,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7EB8E8)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}
