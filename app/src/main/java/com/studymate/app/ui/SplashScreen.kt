package com.studymate.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNext: () -> Unit) {
    var showContent by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(500)
        showContent = true
        delay(1000)
        showButton = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.tertiary
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = showContent,
                enter = fadeIn() + slideInVertically(initialOffsetY = { -40 })
            ) {
                Text(
                    text = "StudyMate AI",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            AnimatedVisibility(
                visible = showContent,
                enter = fadeIn()
            ) {
                Text(
                    text = "🤖 Learn Smart, Grow Fast",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            AnimatedVisibility(
                visible = showButton,
                enter = fadeIn()
            ) {
                Button(
                    onClick = onNext,
                    modifier = Modifier.padding(top = 48.dp)
                ) {
                    Text("Get Started", fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun LanguageScreen(onLanguageSelected: (String) -> Unit) {
    var showButtons by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        showButtons = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.background
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            AnimatedVisibility(
                visible = showButtons,
                enter = fadeIn()
            ) {
                Text(
                    "Choose Language",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }

            AnimatedVisibility(
                visible = showButtons,
                enter = slideInVertically(initialOffsetY = { 40 })
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { onLanguageSelected("en") },
                        modifier = Modifier.fillMaxSize(1f).padding(horizontal = 16.dp)
                    ) {
                        Text("English 🇬🇧", fontSize = 18.sp)
                    }

                    Button(
                        onClick = { onLanguageSelected("hi") },
                        modifier = Modifier.fillMaxSize(1f).padding(horizontal = 16.dp)
                    ) {
                        Text("हिंदी 🇮🇳", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}
