package com.cihatakyol.vibrationmeter.presentation.ui.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cihatakyol.vibrationmeter.R
import com.cihatakyol.vibrationmeter.presentation.ui.component.MaxVibrationCard
import com.cihatakyol.vibrationmeter.presentation.ui.component.VibrationDisplay
import com.cihatakyol.vibrationmeter.presentation.ui.component.VibrationGraph
import com.cihatakyol.vibrationmeter.util.formatDuration

/**
 * Main screen for the Vibration Meter app.
 * Displays current vibration, max vibration, and vibration graph.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VibrationScreen(
    viewModel: VibrationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Vibration Meter",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                actions = {
                    IconButton(onClick = { /* TODO: Show info dialog */ }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = uiState) {
                is VibrationUiState.Initial -> {
                    LoadingContent()
                }

                is VibrationUiState.Loading -> {
                    LoadingContent()
                }

                is VibrationUiState.Success -> {
                    val isRecording by viewModel.isRecording.collectAsState()
                    val recordingDuration by viewModel.recordingDuration.collectAsState()

                    VibrationContent(
                        state = state,
                        isRecording = isRecording,
                        recordingDuration = recordingDuration,
                        onResetMax = viewModel::resetMaxVibration,
                        onStartRecording = viewModel::startRecording,
                        onStopRecording = viewModel::stopRecording
                    )
                }

                is VibrationUiState.Error -> {
                    ErrorContent(message = state.message)
                }
            }
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircularProgressIndicator()
            Text(
                text = "Initializing sensor...",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun ErrorContent(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Error",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.error
            )
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun VibrationContent(
    state: VibrationUiState.Success,
    isRecording: Boolean,
    recordingDuration: Long,
    onResetMax: () -> Unit,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .padding(bottom = 80.dp), // Space for FAB
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (isRecording) {
                RecordingBanner(recordingDuration)
            }

            VibrationDisplay(
                vibration = state.currentVibration,
                modifier = Modifier.fillMaxWidth()
            )

            MaxVibrationCard(
                maxVibration = state.maxVibration,
                timestamp = state.maxVibrationTimestamp,
                onReset = onResetMax,
                modifier = Modifier.fillMaxWidth()
            )

            VibrationGraph(
                vibrationHistory = state.vibrationHistory,
                modifier = Modifier.fillMaxWidth()
            )
        }

        FloatingActionButton(
            onClick = {
                if (isRecording) {
                    onStopRecording()
                } else {
                    onStartRecording()
                }
            },
            containerColor = if (isRecording)
                MaterialTheme.colorScheme.error
            else
                MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            if (isRecording) {
                Icon(
                    painter = painterResource(R.drawable.stop_24),
                    contentDescription = "Stop Recording"
                )
            } else {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Start Recording"
                )
            }
        }
    }
}

@Composable
fun RecordingBanner(
    recordingDuration: Long
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.errorContainer,
                shape = MaterialTheme.shapes.medium
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Recording - ${recordingDuration.formatDuration()}",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onErrorContainer
        )
    }
}
