package com.studymate.app.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val ZigBlue = Color(0xFF7EB8E8)
private val ZigBlueLight = Color(0xFFA9D3F5)
private val ZigBlueDark = Color(0xFF5A9BD4)
private val Jacket = Color(0xFFFF4D4D)
private val JacketDark = Color(0xFFD93636)
private val JacketLight = Color(0xFFFF7A7A)
private val Ink = Color(0xFF3A2E28)
private val Pink = Color(0xFFFF8A80)

/** Zig the blue cat in his red puffer jacket. Bobs gently and blinks. */
@Composable
fun ZigMascot(
    modifier: Modifier = Modifier,
    mascotSize: Dp = 120.dp,
    animated: Boolean = true
) {
    val transition = rememberInfiniteTransition(label = "zig")
    val bob by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bob"
    )
    val blink by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3600
                1f at 0
                1f at 3300
                0.08f at 3420
                1f at 3540
            }
        ),
        label = "blink"
    )

    Canvas(
        modifier = modifier
            .size(mascotSize)
            .graphicsLayer { translationY = -(if (animated) bob else 0f) * 8f }
    ) {
        val s = this.size.minDimension / 100f
        val eyeOpen = if (animated) blink else 1f
        fun o(x: Float, y: Float) = Offset(x * s, y * s)

        // Puffer jacket body
        drawRoundRect(
            brush = Brush.verticalGradient(
                colors = listOf(JacketLight, Jacket, JacketDark),
                startY = 64f * s,
                endY = 100f * s
            ),
            topLeft = o(14f, 66f),
            size = Size(72f * s, 34f * s),
            cornerRadius = CornerRadius(20f * s, 20f * s)
        )
        for (y in listOf(80f, 90f)) {
            val puff = Path().apply {
                moveTo(20f * s, y * s)
                quadraticBezierTo(50f * s, (y + 5f) * s, 80f * s, y * s)
            }
            drawPath(puff, JacketDark, style = Stroke(width = 1.8f * s, cap = StrokeCap.Round))
        }

        // Ears
        val leftEar = Path().apply {
            moveTo(22f * s, 38f * s); lineTo(26f * s, 8f * s); lineTo(46f * s, 22f * s); close()
        }
        val rightEar = Path().apply {
            moveTo(78f * s, 38f * s); lineTo(74f * s, 8f * s); lineTo(54f * s, 22f * s); close()
        }
        drawPath(leftEar, ZigBlueDark)
        drawPath(rightEar, ZigBlueDark)
        drawPath(Path().apply {
            moveTo(29f * s, 33f * s); lineTo(31f * s, 16f * s); lineTo(41f * s, 24f * s); close()
        }, Color(0xFFFFB3B3))
        drawPath(Path().apply {
            moveTo(71f * s, 33f * s); lineTo(69f * s, 16f * s); lineTo(59f * s, 24f * s); close()
        }, Color(0xFFFFB3B3))

        // Head
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(ZigBlueLight, ZigBlue, ZigBlueDark),
                center = o(42f, 32f),
                radius = 56f * s
            ),
            radius = 31f * s,
            center = o(50f, 46f)
        )
        drawOval(color = Color(0xFFE8F4FD), topLeft = o(38f, 50f), size = Size(24f * s, 17f * s))

        // Eyes (blink by squashing height)
        fun eye(cx: Float) {
            val cy = 43f
            val w = 17f * s
            val h = 18f * s * eyeOpen
            drawOval(color = Color.White, topLeft = Offset(cx * s - w / 2f, cy * s - h / 2f), size = Size(w, h))
            val pw = 10f * s
            val ph = 11f * s * eyeOpen
            drawOval(
                color = Ink,
                topLeft = Offset((cx + 0.8f) * s - pw / 2f, (cy + 0.8f) * s - ph / 2f),
                size = Size(pw, ph)
            )
            if (eyeOpen > 0.5f) drawCircle(Color.White, 2.1f * s, o(cx + 2.2f, cy - 1.8f))
        }
        eye(38f)
        eye(62f)

        // Cheeks
        drawCircle(Pink.copy(alpha = 0.45f), 5f * s, o(28f, 55f))
        drawCircle(Pink.copy(alpha = 0.45f), 5f * s, o(72f, 55f))

        // Nose and mouth
        drawPath(Path().apply {
            moveTo(46.5f * s, 52f * s); lineTo(53.5f * s, 52f * s); lineTo(50f * s, 56f * s); close()
        }, Pink)
        drawPath(Path().apply {
            moveTo(50f * s, 56f * s)
            lineTo(50f * s, 58.5f * s)
            moveTo(50f * s, 58.5f * s)
            quadraticBezierTo(46f * s, 63.5f * s, 42f * s, 58.5f * s)
            moveTo(50f * s, 58.5f * s)
            quadraticBezierTo(54f * s, 63.5f * s, 58f * s, 58.5f * s)
        }, Ink, style = Stroke(width = 1.5f * s, cap = StrokeCap.Round))

        // Whiskers
        val whisker = Ink.copy(alpha = 0.35f)
        drawLine(whisker, o(12f, 50f), o(27f, 52f), strokeWidth = 1.2f * s, cap = StrokeCap.Round)
        drawLine(whisker, o(12f, 58f), o(27f, 57f), strokeWidth = 1.2f * s, cap = StrokeCap.Round)
        drawLine(whisker, o(88f, 50f), o(73f, 52f), strokeWidth = 1.2f * s, cap = StrokeCap.Round)
        drawLine(whisker, o(88f, 58f), o(73f, 57f), strokeWidth = 1.2f * s, cap = StrokeCap.Round)

        // Jacket collar, zipper, badge
        drawRoundRect(JacketDark, o(28f, 70f), Size(44f * s, 11f * s), CornerRadius(5.5f * s, 5.5f * s))
        drawRoundRect(Jacket, o(28f, 69f), Size(44f * s, 9f * s), CornerRadius(4.5f * s, 4.5f * s))
        drawLine(JacketDark, o(50f, 79f), o(50f, 100f), strokeWidth = 1.8f * s, cap = StrokeCap.Round)
        drawCircle(Color(0xFFFFD93D), 3.4f * s, o(68f, 88f))
    }
}
