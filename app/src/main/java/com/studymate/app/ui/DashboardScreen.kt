package com.studymate.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studymate.app.data.ContentRepository
import com.studymate.app.data.Subject

@Composable
fun DashboardScreen(onDebugTap: () -> Unit = {}) {
    var tapCount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9F0))
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color(0xFF7EB8E8), CircleShape)
                    .clickable {
                        tapCount++
                        if (tapCount >= 7) {
                            tapCount = 0
                            onDebugTap()
                        }
                    }
            )
            Spacer(Modifier.size(12.dp))
            Column {
                Text(
                    text = "Namaste!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4A3F35)
                )
                Text(
                    text = "Aaj kya padhna hai?",
                    fontSize = 14.sp,
                    color = Color(0xFF4A3F35).copy(alpha = 0.7f)
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Subjects",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A3F35)
        )

        Spacer(Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(ContentRepository.subjects) { subject ->
                SubjectTile(subject)
            }
        }
    }
}

@Composable
private fun SubjectTile(subject: Subject) {
    Card(
        colors = CardDefaults.cardColors(containerColor = subject.color),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = subject.emoji, fontSize = 32.sp)
            Text(
                text = subject.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
