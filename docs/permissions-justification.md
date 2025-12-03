# App Permissions Justification

This document provides detailed justifications for all permissions and hardware features declared in the AndroidManifest.xml file for Vibration Meter.

**Last Updated:** December 3, 2025

---

## Summary

The app declares 2 items in its manifest:
1. **1 Permission:** VIBRATE
2. **1 Hardware Feature:** Accelerometer (required)

Both are fully justified for the app's core functionality.

---

## Declared Permissions

### 1. android.permission.VIBRATE

**Declaration:**
```xml
<uses-permission android:name="android.permission.VIBRATE" />
```

**Purpose:** Provides haptic feedback to the user during app interactions.

**Justification:**
- Used to provide tactile feedback when users interact with the app
- Enhances user experience by confirming button presses, recordings start/stop, etc.
- Common in measurement and utility apps for user confirmation
- Does not access any personal data or device identifiers
- Considered a "normal" permission that doesn't require runtime permission request

**Privacy Impact:** NONE
- No data is collected through this permission
- Simply allows the app to trigger the device vibration motor
- No privacy concerns

**Required:** Optional for app functionality, but enhances UX

**Runtime Permission Required:** NO (normal permission, auto-granted)

---

## Declared Hardware Features

### 1. android.hardware.sensor.accelerometer

**Declaration:**
```xml
<uses-feature android:name="android.hardware.sensor.accelerometer" android:required="true" />
```

**Purpose:** Access to the device's accelerometer sensor to measure vibrations.

**Justification:**
- **CORE FUNCTIONALITY:** The app's entire purpose is to measure vibrations using the accelerometer
- Without this sensor, the app cannot function at all
- Marked as `required="true"` because the app is useless without it
- Ensures the app is only installable on devices with accelerometer hardware

**How It's Used:**
- Reads X, Y, Z axis acceleration values in m/s²
- Calculates magnitude of vibration
- Records sensor data during measurement sessions
- Displays real-time vibration measurements on screen

**Privacy Impact:** LOW
- Sensor data is collected (disclosed in Data Safety form)
- All data stored locally on device only
- No data transmission to external servers
- User controls all data (can delete anytime)

**Required:** YES - absolute requirement for core functionality

**Runtime Permission Required:** NO (hardware features don't require runtime permissions)

---

## Permissions NOT Declared

The app does **NOT** declare any of the following common permissions, which demonstrates its privacy-focused design:

### Network Permissions (NOT Used)
- ❌ `INTERNET` - No network access
- ❌ `ACCESS_NETWORK_STATE` - Doesn't check network connectivity
- ❌ `ACCESS_WIFI_STATE` - Doesn't access Wi-Fi info

**Impact:** App cannot send data to external servers (privacy benefit)

### Location Permissions (NOT Used)
- ❌ `ACCESS_FINE_LOCATION` - No location tracking
- ❌ `ACCESS_COARSE_LOCATION` - No location tracking
- ❌ `ACCESS_BACKGROUND_LOCATION` - No background location

**Impact:** No location data collected or used

### Storage Permissions (NOT Used)
- ❌ `READ_EXTERNAL_STORAGE` - Doesn't read external files
- ❌ `WRITE_EXTERNAL_STORAGE` - Uses scoped storage instead

**Note:** File exports use Android's ShareSheet API and FileProvider, which don't require storage permissions on modern Android versions.

### Camera/Microphone (NOT Used)
- ❌ `CAMERA` - No camera access
- ❌ `RECORD_AUDIO` - No audio recording

### Contacts/SMS (NOT Used)
- ❌ `READ_CONTACTS` - No contact access
- ❌ `READ_SMS` / `SEND_SMS` - No SMS functionality

### Phone State (NOT Used)
- ❌ `READ_PHONE_STATE` - No device ID collection

### Other Sensitive Permissions (NOT Used)
- ❌ `GET_ACCOUNTS` - No account access
- ❌ `BLUETOOTH` - No Bluetooth functionality
- ❌ `NFC` - No NFC functionality

---

## Play Store Policy Compliance

### Permission Usage Compliance

✅ **VIBRATE Permission:**
- Used for legitimate UI feedback
- Doesn't access sensitive data
- Fully compliant with Play Store policies

✅ **Accelerometer Feature:**
- Core app functionality
- Properly disclosed in Data Safety form
- Required feature appropriately declared
- Fully compliant with Play Store policies

### Data Access Justification

All declared permissions and features are:
1. ✓ Necessary for core app functionality
2. ✓ Clearly communicated to users
3. ✓ Disclosed in the Data Safety form
4. ✓ Documented in the Privacy Policy
5. ✓ Compliant with Google Play policies

---

## User Communication

### In Data Safety Form

**App interactions - Sensor data:**
- Disclosed as: "App interactions"
- Purpose: "App functionality"
- Shared: "Not shared with third parties"
- Collected: "Required"
- Encrypted: "At rest (device encryption)"
- Deletable: "Yes"

### In Privacy Policy

The privacy policy clearly explains:
- What sensor data is collected (accelerometer X, Y, Z values)
- Why it's collected (vibration measurement)
- How it's stored (locally on device)
- User rights (delete, export)

---

## Hardware Feature Impact

### Setting `required="true"` for Accelerometer

**Effect:**
- App will ONLY be visible and installable on devices with accelerometer
- Filters out devices without this sensor (very rare - nearly all Android devices have accelerometers)
- Prevents bad user experience on incompatible devices
- Prevents negative reviews from users on incompatible devices

**Alternative (NOT recommended):**
```xml
<!-- NOT recommended for this app -->
<uses-feature android:name="android.hardware.sensor.accelerometer" android:required="false" />
```
- Would allow installation on any device
- Would require runtime checking and graceful degradation
- Not appropriate for this app since accelerometer IS the app

---

## Best Practices Followed

✅ **Minimal Permissions:** Only 1 permission declared (VIBRATE)
✅ **Justified Features:** Hardware feature is core to app functionality
✅ **No Network:** No internet permission = no data transmission
✅ **No Tracking:** No advertising ID, analytics, or tracking permissions
✅ **Transparent:** All data collection fully disclosed
✅ **User Control:** Users can delete all data anytime

---

## Future Considerations

If you plan to add features later, you may need additional permissions:

### Analytics/Crash Reporting
If adding Firebase Analytics or Crashlytics:
- Would need `INTERNET` permission
- Must update Data Safety form
- Must update Privacy Policy

### Cloud Backup
If adding cloud sync:
- Would need `INTERNET` permission
- Would need user account system
- Must update Data Safety and Privacy Policy extensively

### Social Sharing
Current sharing uses ShareSheet (no permissions needed) ✅

---

## Testing Recommendations

### Pre-Release Testing

1. **Permission Review:**
   - ✓ Confirm only VIBRATE and accelerometer are declared
   - ✓ No unnecessary permissions added by dependencies
   - ✓ Test on devices with and without accelerometer (if possible)

2. **Functional Testing:**
   - ✓ Verify accelerometer data is collected correctly
   - ✓ Verify vibration feedback works (if implemented)
   - ✓ Confirm app cannot install on devices without accelerometer

3. **Privacy Testing:**
   - ✓ Verify no network connections are made (use network monitor)
   - ✓ Verify data stays local (check with device file explorer)
   - ✓ Confirm data deletion works correctly

---

## Play Console Declaration

When filling out the app content questionnaire in Play Console:

**Permissions Question:**
- "Does your app use precise location in the background?"
  - Answer: **NO**
- "Does your app access location in the foreground?"
  - Answer: **NO**

**Device & App History:**
- Answer: **NO** (no device ID collection)

**User Generated Content:**
- Answer: **NO** (no user interaction/sharing features)

---

## Summary Table

| Permission/Feature | Declared | Required | Purpose | Privacy Impact | Compliant |
|-------------------|----------|----------|---------|----------------|-----------|
| VIBRATE | ✅ Yes | Optional | Haptic feedback | None | ✅ Yes |
| Accelerometer | ✅ Yes | Required | Core functionality | Low (disclosed) | ✅ Yes |
| INTERNET | ❌ No | N/A | N/A | N/A | ✅ N/A |
| Location | ❌ No | N/A | N/A | N/A | ✅ N/A |
| Storage | ❌ No | N/A | N/A | N/A | ✅ N/A |
| Camera | ❌ No | N/A | N/A | N/A | ✅ N/A |
| Microphone | ❌ No | N/A | N/A | N/A | ✅ N/A |
| Contacts | ❌ No | N/A | N/A | N/A | ✅ N/A |

---

**Conclusion:** The app's permission footprint is minimal, justified, and fully compliant with Google Play policies. The privacy-first design with local-only data storage and no network access is a key differentiator and selling point.

**End of Permissions Justification Document**
