package com.cihatakyol.vibrationmeter.presentation.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cihatakyol.vibrationmeter.util.toVibrationValue

/**
 * Component to display current vibration magnitude.
 * Uses large, readable typography and color coding for vibration levels.
 */
@Composable
fun VibrationDisplay(
    vibration: Float,
    modifier: Modifier = Modifier
) {
    val animatedVibration by animateFloatAsState(
        targetValue = vibration,
        animationSpec = tween(durationMillis = 100),
        label = "vibration_animation"
    )

    val vibrationColor = getVibrationColor(animatedVibration)

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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Current Vibration",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Text(
                text = animatedVibration.toVibrationValue(),
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Text(
                text = "m/s²",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

/**
 * Get color based on vibration magnitude.
 * Green: 0-5 m/s² (low)
 * Orange: 5-10 m/s² (medium)
 * Red: 10+ m/s² (high)
 */
@Composable
private fun getVibrationColor(magnitude: Float): Color {
    return when {
        magnitude < 5f -> MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.3f)
        magnitude < 10f -> MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
        else -> MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 1f)
    }
}
