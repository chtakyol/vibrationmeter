# PRD: Measurement Sessions

## Introduction/Overview

This feature adds the ability to record discrete measurement sessions in the Vibration Meter app. Users can manually start and stop recording sessions, view a history of all saved sessions, and analyze detailed session data including full vibration history graphs. This enables users to track and compare vibration measurements over time for different scenarios (e.g., different washing machine cycles, various earthquake simulations, etc.).

**Problem it solves:** Currently, the app only shows real-time vibration data and a single max vibration record. Users cannot track multiple measurement scenarios separately or review historical measurement data. This feature enables comparative analysis and historical tracking.

## Goals

1. Enable users to manually start and stop discrete measurement recording sessions
2. Persist unlimited measurement sessions with full vibration history data
3. Provide an intuitive session history view with key metrics
4. Allow users to view detailed session data including replay of vibration graphs
5. Enable users to export/share session data for external analysis
6. Implement bottom navigation for easy access between live measurements and session history
7. Use Compose Navigation for screen management

## User Stories

**As a user measuring washing machine vibration:**
- I want to start a recording session when my washing machine begins its spin cycle
- I want to stop the session when the cycle completes
- I want to view all my previous washing machine tests to compare which load size causes most vibration

**As a user testing earthquake detection:**
- I want to record multiple earthquake simulation sessions
- I want to see the maximum vibration, average vibration, and full graph for each test
- I want to export session data to share with my research team

**As a user monitoring general vibrations:**
- I want to save different measurement scenarios separately (car ride, construction site, etc.)
- I want to delete old sessions I no longer need
- I want to quickly see which session had the highest vibration

## Functional Requirements

### FR-001: Session Recording Control

**FR-001.1:** The main screen MUST display a prominent "Start Recording" button when no session is active

**FR-001.2:** When a session is started:
- The button MUST change to "Stop Recording" with different styling
- A recording indicator MUST be visible (e.g., pulsing red dot, timer)
- A session timer MUST show elapsed recording time (MM:SS format)
- All real-time vibration data MUST continue to display normally

**FR-001.3:** While recording:
- The app MUST capture all vibration data points (timestamp + magnitude)
- The app MUST track session start time
- The app MUST track maximum vibration during the session
- The app MUST calculate average vibration continuously

**FR-001.4:** When user stops recording:
- The app MUST save the complete session to persistent storage
- The app MUST show a confirmation/success message
- The app MUST reset to ready-to-record state
- The current max vibration on main screen MUST remain unchanged (separate from session max)

**FR-001.5:** The app MUST allow starting a new session while a session is not active

### FR-002: Session Data Storage

**FR-002.1:** Each session MUST store:
- Unique session ID
- Start timestamp (date and time)
- End timestamp (date and time)
- Duration (calculated from start/end)
- Maximum vibration magnitude during session
- Average vibration magnitude during session
- Complete list of vibration data points (timestamp + magnitude)

**FR-002.2:** Sessions MUST be persisted using Room database (NOT DataStore)

**FR-002.3:** Sessions MUST be stored without automatic limit - unlimited storage with manual cleanup by user

**FR-002.4:** Session data MUST persist across app restarts

**FR-002.5:** Each session's vibration history MUST maintain all data points captured during recording (no artificial limits)

### FR-003: Navigation Structure

**FR-003.1:** The app MUST implement bottom navigation with 2 tabs:
- "Live" or "Measure" - Current main vibration measurement screen
- "Sessions" or "History" - Session history list screen

**FR-003.2:** Bottom navigation MUST use Material 3 NavigationBar component

**FR-003.3:** Navigation MUST be implemented using Jetpack Compose Navigation

**FR-003.4:** The currently active tab MUST be clearly indicated

**FR-003.5:** Navigation state MUST persist during configuration changes

### FR-004: Session History Screen

**FR-004.1:** Sessions list MUST display all saved sessions in reverse chronological order (newest first)

**FR-004.2:** Each session list item MUST show:
- Date and time of session (formatted: "Dec 01, 2024 14:30")
- Duration (formatted: "5m 23s" or "1h 5m 23s")
- Maximum vibration with unit (e.g., "15.23 m/s²")
- Average vibration with unit (e.g., "8.45 m/s²")

**FR-004.3:** Session list items MUST be clickable to view detailed session view

**FR-004.4:** The screen MUST show an empty state message when no sessions exist (e.g., "No sessions recorded yet. Start measuring to create your first session!")

**FR-004.5:** The screen MUST show total number of sessions (e.g., "23 Sessions")

**FR-004.6:** The list MUST be scrollable if sessions exceed screen height

**FR-004.7:** Each session list item MUST have a delete button/icon

**FR-004.8:** Delete action MUST show a confirmation dialog before deleting

### FR-005: Session Detail Screen

**FR-005.1:** Tapping a session MUST navigate to a detailed session view

**FR-005.2:** Session detail screen MUST display:
- Session date and time
- Total duration
- Maximum vibration (value + timestamp when it occurred)
- Average vibration
- Minimum vibration (value + timestamp)
- Full vibration graph showing entire session data

**FR-005.3:** The vibration graph MUST show all data points from the session

**FR-005.4:** The graph MUST have proper X-axis labels (time) and Y-axis labels (magnitude)

**FR-005.5:** The detail screen MUST have a "Share/Export" button

**FR-005.6:** The detail screen MUST have a "Delete" button

**FR-005.7:** The detail screen MUST have a back button to return to session list

**FR-005.8:** Graph SHOULD support zoom/pan capabilities for detailed analysis

### FR-006: Export/Share Functionality

**FR-006.1:** Users MUST be able to export session data in multiple formats:
- CSV format (timestamp, magnitude columns)
- JSON format (structured session data)
- Image/Screenshot of the graph

**FR-006.2:** Export action MUST trigger Android's native share sheet

**FR-006.3:** CSV export MUST include headers: "Timestamp", "Magnitude (m/s²)"

**FR-006.4:** JSON export MUST include all session metadata + data points

**FR-006.5:** Users MUST be able to export from both the session detail screen

**FR-006.6:** OPTIONAL: Bulk export multiple sessions from history screen

### FR-007: Session Management

**FR-007.1:** Users MUST be able to delete individual sessions

**FR-007.2:** Delete action MUST show confirmation dialog with session details

**FR-007.3:** Confirmation dialog MUST have "Cancel" and "Delete" options

**FR-007.4:** After deletion, the session MUST be immediately removed from the list

**FR-007.5:** OPTIONAL: "Delete All Sessions" action in settings or history screen

**FR-007.6:** OPTIONAL: Rename/add notes to sessions

## Non-Goals (Out of Scope)

1. **Automatic session detection** - Sessions must be manually started/stopped by user
2. **Cloud sync** - All data stored locally only; no cloud backup or multi-device sync
3. **Session comparison view** - No side-by-side comparison of multiple sessions (future feature)
4. **Advanced analytics** - No frequency analysis, FFT, or statistical analysis beyond max/average/min
5. **Session templates or presets** - No ability to create named session types or categories
6. **Background recording** - Sessions can only be recorded while app is in foreground
7. **Automatic session naming** - Sessions identified by timestamp only, no custom names (initially)
8. **Session search/filter** - No search or filtering capabilities in v1
9. **Session tagging** - No labels or categories for sessions

## Design Considerations

### UI/UX Requirements

**Bottom Navigation:**
- Use Material 3 NavigationBar component
- Two items: "Live" (with sensor icon) and "Sessions" (with history/list icon)
- Active tab clearly highlighted

**Main Screen Changes:**
- Add prominent "Start Recording" FAB or button
- Show recording indicator when active (pulsing dot + timer)
- "Stop Recording" button should be red/prominent when recording

**Session List Screen:**
- Use Material 3 Card components for each session
- Group sessions by date (Today, Yesterday, Last 7 days, Older)
- Swipe-to-delete gesture OPTIONAL
- Pull-to-refresh OPTIONAL

**Session Detail Screen:**
- Large graph at top (similar to main screen but showing full session)
- Stats cards below graph (max, avg, min, duration)
- Share and Delete buttons in top app bar or bottom

**Color Coding:**
- Recording state: Use attention-grabbing color (red/orange)
- Session cards: Use subtle colors to indicate intensity (based on max vibration)

### Navigation Flow

```
Main Screen (Live Tab)
  ├─ Bottom Nav: Sessions Tab → Session List Screen
  │                               ├─ Tap Session → Session Detail Screen
  │                               │                  ├─ Share → Android Share Sheet
  │                               │                  ├─ Delete → Confirmation Dialog
  │                               │                  └─ Back → Session List
  │                               └─ Delete on list item → Confirmation Dialog
  └─ Bottom Nav: Live Tab (return to main)
```

## Technical Considerations

### Database (Room)

**Entity: Session**
```kotlin
@Entity(tableName = "sessions")
data class SessionEntity(
    @PrimaryKey val id: String,
    val startTimestamp: Long,
    val endTimestamp: Long,
    val duration: Long,
    val maxVibration: Float,
    val avgVibration: Float,
    val minVibration: Float
)
```

**Entity: SessionDataPoint**
```kotlin
@Entity(tableName = "session_data_points")
data class SessionDataPointEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sessionId: String,
    val timestamp: Long,
    val magnitude: Float
)
```

**Relationships:**
- One-to-many relationship between Session and SessionDataPoints
- Use Room's @Relation for efficient querying

### Architecture Updates

**New Use Cases Needed:**
- `StartSessionUseCase`
- `StopSessionUseCase`
- `GetAllSessionsUseCase`
- `GetSessionByIdUseCase`
- `DeleteSessionUseCase`
- `ExportSessionUseCase`

**New Repository:**
- `SessionRepository` interface + implementation

**New Data Sources:**
- `SessionDatabaseDataSource` (Room DAO)

**New ViewModels:**
- `SessionListViewModel` - Manages session list state
- `SessionDetailViewModel` - Manages single session detail state
- Update `VibrationViewModel` - Add session recording state

**Navigation:**
- Implement Compose Navigation with NavHost
- Define navigation routes: "live", "sessions", "session_detail/{sessionId}"
- Add navigation dependency to version catalog

### Dependencies to Add

```toml
[versions]
room = "2.6.1"
navigation-compose = "2.7.7"

[libraries]
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "room" }
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }
androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigation-compose" }
```

### Performance Considerations

1. **Large Session Data:** Sessions may contain thousands of data points
   - Use paging for session list if needed (later optimization)
   - Load session detail data lazily
   - Consider data point sampling for graph rendering if > 1000 points

2. **Database Size:** Unlimited sessions could grow large
   - Monitor storage usage
   - Provide storage stats to user
   - Future: Add cleanup recommendations

3. **Recording Performance:** Continuously saving data points while recording
   - Buffer data points in memory during recording
   - Batch insert to database when session stops
   - Don't block UI thread during save

### Data Export Formats

**CSV Example:**
```csv
Timestamp,Magnitude (m/s²)
1733097600000,9.81
1733097600100,10.23
```

**JSON Example:**
```json
{
  "sessionId": "uuid",
  "startTime": 1733097600000,
  "endTime": 1733097900000,
  "duration": 300000,
  "maxVibration": 15.23,
  "avgVibration": 8.45,
  "minVibration": 2.31,
  "dataPoints": [
    {"timestamp": 1733097600000, "magnitude": 9.81},
    {"timestamp": 1733097600100, "magnitude": 10.23}
  ]
}
```

## Success Metrics

1. **Adoption:** 80% of users start at least one recording session within first 3 uses
2. **Engagement:** Average user records 5+ sessions per week
3. **Retention:** Users with saved sessions have 30% higher 7-day retention
4. **Export Usage:** 20% of sessions are exported/shared
5. **Technical:** Session save completes in < 500ms for sessions up to 5 minutes
6. **Technical:** Session list loads in < 200ms for up to 100 sessions

## Open Questions

1. **Storage Limits:** Should we implement a soft storage limit warning (e.g., "You have 500 sessions, consider cleaning up old ones")?

2. **Session Naming:** Should v1 include the ability to add custom names/notes to sessions, or defer to v2?

3. **Graph Rendering:** For very long sessions (1+ hour), should we automatically sample data points for graph performance, or render all points?

4. **Background Recording:** Future consideration - should we support background recording with foreground service notification?

5. **Data Retention:** Should we implement automatic cleanup of sessions older than X months with user consent?

6. **Compression:** Should we compress session data in database to save space?

7. **Statistics:** Should we show aggregate statistics across all sessions (e.g., "Total recording time: 3h 45m", "Highest ever vibration: 25.3 m/s²")?

8. **Import:** Should we support importing sessions from exported JSON/CSV files?

## Implementation Priority

**Phase 1 (MVP):**
- FR-001: Session recording control
- FR-002: Session data storage (Room)
- FR-003: Navigation structure
- FR-004: Session history screen (basic list)

**Phase 2:**
- FR-005: Session detail screen
- FR-007: Session management (delete)

**Phase 3:**
- FR-006: Export/share functionality
- Enhanced graph features (zoom/pan)

**Future Enhancements:**
- Session naming/notes
- Search and filter
- Comparison view
- Cloud sync
- Advanced analytics
