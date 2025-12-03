package com.cihatakyol.vibrationmeter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cihatakyol.vibrationmeter.presentation.navigation.VibrationMeterApp
import com.cihatakyol.vibrationmeter.ui.theme.VibrationmeterTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity for the Vibration Meter app.
 * Annotated with @AndroidEntryPoint to enable Hilt dependency injection.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Install splash screen before calling super.onCreate()
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VibrationmeterTheme {
                VibrationMeterApp()
            }
        }
    }
}