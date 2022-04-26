package com.example.movies.ui

import android.graphics.Rect
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas

@Composable
fun Votemeter(modifier: Modifier = Modifier, vote: Double) {
    val text = vote.toString()
    val textPaint = Paint().asFrameworkPaint().apply {
        color = 0xCCFFFFFF.toInt()
        textSize = 60f
    }
    val textBounds = Rect()
    textPaint.getTextBounds(text, 0, text.length, textBounds)

    Canvas(modifier) {
        drawCircle(color = Color.Black, alpha = 0.6f, radius = 60f)
        drawArc(
            color = getVotemeterColor(vote),
            startAngle = -90f,
            sweepAngle = (360f * vote / 10).toFloat(),
            useCenter = false,
            topLeft = Offset(size.width / 2 - 55, size.height / 2 - 55),
            size = Size(110f, 110f),
            style = Stroke(width = 6f, cap = StrokeCap.Round)
        )
        drawIntoCanvas {
            it.nativeCanvas.drawText(
                text,
                size.minDimension / 2 - textBounds.exactCenterX(),
                size.minDimension / 2 - textBounds.exactCenterY(),
                textPaint
            )
        }
    }
}

fun getVotemeterColor(vote: Double): Color {
    return when {
        vote < 1.0 -> Color(0xFFFF0000)
        vote < 2.0 -> Color(0xFFFF3300)
        vote < 3.0 -> Color(0xFFFF6600)
        vote < 4.0 -> Color(0xFFFF9900)
        vote < 5.0 -> Color(0xFFFFCC00)
        vote < 6.0 -> Color(0xFFFFFF00)
        vote < 7.0 -> Color(0xFFCCFF00)
        vote < 8.0 -> Color(0xFF99FF00)
        vote < 9.0 -> Color(0xFF66FF00)
        vote < 10.0 -> Color(0xFF33FF00)
        vote == 10.0 -> Color(0xFF00FF00)
        else -> Color(0xFFCC00CC)
    }
}