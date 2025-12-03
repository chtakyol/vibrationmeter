package com.cihatakyol.vibrationmeter.presentation.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cihatakyol.vibrationmeter.domain.model.VibrationPoint
import com.cihatakyol.vibrationmeter.util.maxOrDefault

/**
 * Component to display vibration graph.
 * Shows a real-time line graph of vibration over the last 30 seconds.
 */
@Composable
fun VibrationGraph(
    vibrationHistory: List<VibrationPoint>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Vibration Graph (Last 30s)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.SemiBold
            )

            if (vibrationHistory.isEmpty()) {
                Text(
                    text = "Waiting for data...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f),
                    modifier = Modifier.padding(vertical = 40.dp)
                )
            } else {
                VibrationGraphCanvas(
                    vibrationHistory = vibrationHistory,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
            }
        }
    }
}

@Composable
private fun VibrationGraphCanvas(
    vibrationHistory: List<VibrationPoint>,
    modifier: Modifier = Modifier
) {
    val lineColor = MaterialTheme.colorScheme.secondary
    val gridColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.2f)

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val padding = 40f

        if (vibrationHistory.isEmpty()) return@Canvas

        // Calculate scales
        val timeRange = vibrationHistory.maxOf { it.timestamp } - vibrationHistory.minOf { it.timestamp }
        val maxMagnitude = vibrationHistory.map { it.magnitude }.maxOrDefault(10f)
        val magnitudeRange = maxMagnitude

        // Draw grid lines (horizontal)
        val gridLines = 5
        for (i in 0..gridLines) {
            val y = padding + (height - 2 * padding) * i / gridLines
            drawLine(
                color = gridColor,
                start = Offset(padding, y),
                end = Offset(width - padding, y),
                strokeWidth = 1f
            )
        }

        // Draw graph line
        if (vibrationHistory.size > 1) {
            val path = Path()
            val sortedHistory = vibrationHistory.sortedBy { it.timestamp }

            sortedHistory.forEachIndexed { index, point ->
                val x = if (timeRange > 0) {
                    padding + (width - 2 * padding) * (point.timestamp - sortedHistory.first().timestamp) / timeRange
                } else {
                    padding + (width - 2 * padding) * index / sortedHistory.size
                }

                val y = if (magnitudeRange > 0) {
                    height - padding - (height - 2 * padding) * (point.magnitude / magnitudeRange)
                } else {
                    height - padding
                }

                if (index == 0) {
                    path.moveTo(x, y)
                } else {
                    path.lineTo(x, y)
                }
            }

            drawPath(
                path = path,
                color = lineColor,
                style = Stroke(width = 3f)
            )
        }

        // Draw data points
        vibrationHistory.forEach { point ->
            val x = if (timeRange > 0) {
                padding + (width - 2 * padding) * (point.timestamp - vibrationHistory.minOf { it.timestamp }) / timeRange
            } else {
                width / 2
            }

            val y = if (magnitudeRange > 0) {
                height - padding - (height - 2 * padding) * (point.magnitude / magnitudeRange)
            } else {
                height - padding
            }

            drawCircle(
                color = lineColor,
                radius = 3f,
                center = Offset(x, y)
            )
        }
    }
}
