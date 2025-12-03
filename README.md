# Vibration Meter

An Android app that measures and visualizes vibration intensity using the device's accelerometer sensor.

## Features

- **Real-time Vibration Measurement**: Displays current vibration magnitude in m/s²
- **Maximum Vibration Tracking**: Records and persists the highest vibration event
- **Vibration Graph**: Visualizes vibration data over the last 30 seconds
- **Material Design 3**: Modern UI with light/dark theme support
- **Clean Architecture**: MVVM pattern with separation of concerns

## Use Cases

- Check washing machine balance during spin cycle
- Test earthquake detection sensitivity
- General vibration monitoring and analysis
- Measure device stability

## Technical Stack

### Language & Framework
- **Kotlin** - Programming language
- **Jetpack Compose** - Modern Android UI toolkit
- **Minimum SDK**: 26 (Android 8.0)
- **Target SDK**: 36+

### Architecture
- **MVVM** (Model-View-ViewModel) pattern
- **Clean Architecture** with clear layer separation:
  - **Presentation Layer**: Composables, ViewModels
  - **Domain Layer**: Use Cases, Business Logic
  - **Data Layer**: Repositories, Data Sources

### Dependencies
- **Hilt** - Dependency injection
- **Kotlin Coroutines** - Asynchronous programming
- **Kotlin Flow** - Reactive streams
- **DataStore** - Data persistence
- **Material 3** - UI components and theming

### Testing
- **JUnit 4** - Unit testing framework
- **MockK** - Mocking library
- **Turbine** - Flow testing
- **Compose UI Test** - UI testing

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/cihatakyol/vibrationmeter/
│   │   │   ├── di/                    # Dependency Injection modules
│   │   │   ├── data/
│   │   │   │   ├── repository/        # Repository implementations
│   │   │   │   ├── source/            # Data sources (sensors, preferences)
│   │   │   │   └── model/             # Data models
│   │   │   ├── domain/
│   │   │   │   ├── model/             # Domain models
│   │   │   │   ├── usecase/           # Business logic use cases
│   │   │   │   └── repository/        # Repository interfaces
│   │   │   ├── presentation/
│   │   │   │   ├── ui/
│   │   │   │   │   ├── screen/        # Composable screens
│   │   │   │   │   └── component/     # Reusable UI components
│   │   │   │   └── viewmodel/         # ViewModels
│   │   │   ├── ui/theme/              # Material 3 theme setup
│   │   │   ├── util/                  # Utility functions (plug and play)
│   │   │   ├── MainActivity.kt
│   │   │   └── VibrationMeterApplication.kt
│   │   └── res/
│   └── test/                          # Unit tests
│   └── androidTest/                   # Instrumented tests
└── build.gradle.kts
```

## Getting Started

### Prerequisites
- Android Studio Ladybug or later
- JDK 11 or later
- Android device or emulator with accelerometer sensor

### Setup
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd vibrationmeter
   ```

2. Open the project in Android Studio

3. Sync Gradle files

4. Build and run on a device or emulator

### Running Tests
```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

## Architecture Details

### Data Flow
1. **Sensor Data**: Accelerometer readings are captured by `AccelerometerDataSource`
2. **Repository**: `VibrationRepository` provides abstraction over data sources
3. **Use Cases**: Business logic is encapsulated in dedicated use case classes
4. **ViewModel**: `VibrationViewModel` manages UI state using `StateFlow`
5. **UI**: Composable functions observe state and render the UI

### Key Components

#### Utility Functions
All utility functions are designed as plug-and-play extensions:
- `SensorUtils.kt` - Sensor operations (magnitude calculation, availability checks)
- `FormatUtils.kt` - Value formatting (vibration display, timestamps)
- `ComposeUtils.kt` - Compose helpers (conditional modifiers)
- `MathUtils.kt` - Mathematical operations (moving average, normalization)

#### Data Sources
- `AccelerometerDataSource` - Provides real-time sensor data via Flow
- `VibrationPreferencesDataSource` - Manages persistent max vibration records

#### Use Cases
- `MeasureVibrationUseCase` - Gets real-time vibration measurements
- `SaveMaxVibrationUseCase` - Saves maximum vibration records
- `GetMaxVibrationUseCase` - Retrieves stored max vibration
- `ResetMaxVibrationUseCase` - Clears max vibration data

#### UI Components
- `VibrationDisplay` - Shows current vibration with color coding
- `MaxVibrationCard` - Displays max vibration record with reset button
- `VibrationGraph` - Canvas-based real-time graph

## Performance Considerations

- Sensor sampling rate: 50Hz (SENSOR_DELAY_GAME)
- Graph data points limited to 1000 maximum
- Vibration history: Last 30 seconds
- Efficient state updates using Kotlin Flow
- Proper lifecycle management to prevent battery drain

## Permissions

The app requires the following permissions:
- `android.permission.VIBRATE` - For haptic feedback (optional)
- Accelerometer sensor - Required hardware feature

## Material Design 3

The app follows Material Design 3 guidelines with:
- Dynamic color theming (Android 12+)
- Proper light/dark theme support
- Material 3 typography scale
- Material 3 elevation system
- Smooth animations and transitions

## Code Quality

- **KDoc** documentation for all public functions
- **Unit tests** with 80%+ coverage for utilities
- **Kotlin conventions** followed throughout
- **No lint warnings** in production code
- **Immutable data classes** for state management

## Future Enhancements

- Export vibration data to CSV
- Configurable sampling rate
- Frequency analysis (FFT)
- Multiple vibration profiles
- Notification alerts for high vibration
- Historical data persistence with Room
- Graph zoom and pan capabilities

## License

[Add your license here]

## Contributing

[Add contribution guidelines here]

## Author

Cihat Akyol

## Acknowledgments

Built following the PRD specifications with:
- Clean Architecture principles
- MVVM pattern
- Material Design 3 guidelines
- Best practices from Google's "What's New in Android" app
