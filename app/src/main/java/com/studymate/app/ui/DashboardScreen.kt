package com.studymate.app.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studymate.app.data.ContentRepository
import com.studymate.app.data.Subject
import com.studymate.app.ui.components.ZigMascot
import kotlinx.coroutines.delay

@Composable
fun DashboardScreen(
    onDebugTap: () -> Unit = {},
    onSubjectClick: (Subject) -> Unit = {}
) {
    var tapCount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9F0))
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        // Hero card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(28.dp))
                .background(Brush.linearGradient(listOf(Color(0xFF6FAEE4), Color(0xFF9DD0F5))))
                .padding(18.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 30.dp, y = (-30).dp)
                    .size(120.dp)
                    .background(Color.White.copy(alpha = 0.15f), CircleShape)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(76.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.35f))
                        .clickable {
                            tapCount++
                            if (tapCount >= 7) {
                                tapCount = 0
                                onDebugTap()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    ZigMascot(mascotSize = 62.dp)
                }
                Spacer(Modifier.width(14.dp))
                Column {
                    Text(text = "Namaste!", fontSize = 26.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                    Text(text = "Aaj kya padhna hai?", fontSize = 15.sp, color = Color.White.copy(alpha = 0.92f))
                }
            }
        }

        Spacer(Modifier.height(22.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Subjects", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4A3F35))
            Spacer(Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFFFD93D))
                    .padding(horizontal = 10.dp, vertical = 2.dp)
            ) {
                Text(text = "${ContentRepository.subjects.size}", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4A3F35))
            }
        }

        Spacer(Modifier.height(14.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(ContentRepository.subjects) { index, subject ->
                SubjectTile(subject = subject, index = index, onClick = { onSubjectClick(subject) })
            }
        }
    }
}

@Composable
private fun SubjectTile(subject: Subject, index: Int, onClick: () -> Unit) {
    var shown by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(index * 70L)
        shown = true
    }
    val appear by animateFloatAsState(
        targetValue = if (shown) 1f else 0f,
        animationSpec = tween(durationMillis = 450),
        label = "appear"
    )
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val pressScale by animateFloatAsState(
        targetValue = if (pressed) 0.95f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "press"
    )
    val dark = lerp(subject.color, Color.Black, 0.22f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(136.dp)
            .graphicsLayer {
                val sc = pressScale * (0.85f + 0.15f * appear)
                scaleX = sc
                scaleY = sc
                alpha = appear
            }
            .clip(RoundedCornerShape(24.dp))
            .background(Brush.linearGradient(listOf(subject.color, dark)))
            .clickable(interactionSource = interaction, indication = null, onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 24.dp, y = (-24).dp)
                .size(90.dp)
                .background(Color.White.copy(alpha = 0.14f), CircleShape)
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(Color.White.copy(alpha = 0.28f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = subject.emoji, fontSize = 24.sp)
            }
            Column {
                Text(text = subject.name, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                Text(text = subject.nameHindi, fontSize = 13.sp, color = Color.White.copy(alpha = 0.9f))
            }
        }
    }
}
