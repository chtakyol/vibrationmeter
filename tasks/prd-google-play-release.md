# PRD: Google Play Store Release Preparation

## Introduction/Overview

This PRD outlines the comprehensive process for preparing the Vibration Meter app for release on Google Play Store. The app is feature-complete and ready for internal testing track deployment. This involves configuring the release build, implementing Play Store requirements (data safety, privacy policy, content rating), creating all store listing assets, and setting up proper app signing with Google Play App Signing.

**Problem it solves:** The app is currently in development mode and needs production-ready configuration, compliance with Google Play policies, and professional store listing materials to launch on the Play Store for internal testing.

## Goals

1. Configure production-ready release build with proper signing
2. Implement all mandatory Google Play Store requirements
3. Create complete store listing assets (screenshots, graphics, descriptions)
4. Set up Google Play App Signing for secure key management
5. Prepare data safety disclosure and privacy policy
6. Obtain content rating certification
7. Successfully deploy to internal testing track
8. Document the release process for future updates

## User Stories

**As a developer:**
- I want to generate a signed release APK/AAB that can be uploaded to Play Store
- I want Google to manage my app signing keys securely
- I want to understand what data disclosures are required
- I want to create professional store listing materials that attract users

**As a Play Store reviewer:**
- I need to see a clear privacy policy explaining data handling
- I need accurate data safety disclosures
- I need appropriate content rating information
- I need compliance with all Play Store policies

**As a potential user:**
- I want to see attractive screenshots showing app functionality
- I want to read a clear description of what the app does
- I want to understand what permissions the app requires and why
- I want confidence that the app is safe and trustworthy

## Functional Requirements

### FR-001: Release Build Configuration

**FR-001.1:** The app MUST be configured to build a release variant with proper optimization

**FR-001.2:** ProGuard/R8 MUST be enabled for code shrinking and obfuscation in release builds

**FR-001.3:** Version code MUST be set appropriately (currently 1 for initial release)

**FR-001.4:** Version name MUST follow semantic versioning (currently "1.0")

**FR-001.5:** Release build MUST be configured to generate Android App Bundle (.aab) format

**FR-001.6:** Debuggable flag MUST be set to false in release builds

**FR-001.7:** Application ID MUST remain consistent: "com.cihatakyol.vibrationmeter"

### FR-002: App Signing Setup

**FR-002.1:** A keystore MUST be created for signing the release build

**FR-002.2:** Keystore credentials MUST include:
- Key alias
- Key password
- Store password
- Keystore file location

**FR-002.3:** Keystore credentials MUST be stored securely (NOT in version control)

**FR-002.4:** Build configuration MUST reference keystore for signing release builds

**FR-002.5:** Upload key MUST be generated for Google Play App Signing

**FR-002.6:** Keystore backup MUST be created and stored in secure location

**FR-002.7:** Documentation MUST be created with keystore details (excluding passwords)

### FR-003: Google Play App Signing Enrollment

**FR-003.1:** The app MUST be enrolled in Google Play App Signing during first release setup

**FR-003.2:** Upload key certificate MUST be provided to Google Play Console

**FR-003.3:** Google Play App Signing terms MUST be accepted

**FR-003.4:** App signing key MUST be managed by Google for production releases

**FR-003.5:** Documentation MUST include steps to use Play App Signing for future updates

### FR-004: Data Safety Disclosure

**FR-004.1:** Data safety form in Play Console MUST be completed accurately

**FR-004.2:** All data types collected or shared MUST be declared:
- Device or other IDs (if any analytics)
- App activity data (sensor data from accelerometer)
- App info and performance data

**FR-004.3:** Data usage purposes MUST be clearly stated:
- App functionality (vibration measurement)
- Analytics (if implemented)

**FR-004.4:** Data handling practices MUST specify:
- Whether data is collected (Yes/No for each type)
- Whether data is shared with third parties
- Whether data collection is optional or required
- Data encryption status (in transit and at rest)
- Whether users can request data deletion

**FR-004.5:** Security practices MUST be documented:
- Data encryption
- Secure data handling procedures

**FR-004.6:** For sensor data (accelerometer), the disclosure MUST clarify:
- Data is processed locally on device
- Data is stored locally (Room database)
- No data is transmitted to external servers
- Users have full control over their data (can delete sessions)

### FR-005: Privacy Policy

**FR-005.1:** A privacy policy MUST be created covering:
- What data the app collects (sensor data, measurement sessions)
- How data is used (vibration analysis, session recording)
- Where data is stored (locally on device)
- User rights (data deletion, export)
- Contact information for privacy inquiries

**FR-005.2:** Privacy policy MUST be hosted on a publicly accessible URL

**FR-005.3:** Privacy policy URL MUST be provided in Play Console

**FR-005.4:** Privacy policy MUST comply with GDPR, CCPA, and other applicable regulations

**FR-005.5:** Privacy policy MUST be written in clear, understandable language

**FR-005.6:** Privacy policy MUST be dated and versioned

**FR-005.7:** OPTIONAL: Privacy policy link should be accessible from within the app (Settings screen)

### FR-006: Content Rating

**FR-006.1:** Content rating questionnaire MUST be completed in Play Console

**FR-006.2:** All questionnaire answers MUST accurately reflect app content

**FR-006.3:** Expected rating: Everyone (E) - as app contains no objectionable content

**FR-006.4:** Content rating certificates MUST be obtained from IARC system

**FR-006.5:** All regional rating certifications MUST be displayed (ESRB, PEGI, USK, etc.)

### FR-007: Store Listing - Text Content

**FR-007.1:** App title MUST be clear and descriptive:
- Primary title: "Vibration Meter" or "Vibration Meter - Seismic Monitor"
- Maximum 50 characters

**FR-007.2:** Short description MUST be compelling and concise:
- Maximum 80 characters
- Example: "Measure device vibrations in real-time with precision accelerometer data"

**FR-007.3:** Full description MUST include:
- App overview and purpose (what it does)
- Key features list (real-time monitoring, session recording, data export, graphs)
- Use cases (washing machine testing, earthquake detection, general vibration monitoring)
- Technical details (uses accelerometer, displays m/s² units)
- Privacy highlights (all data stored locally)
- Maximum 4000 characters

**FR-007.4:** Full description MUST use formatting:
- Bullet points for features
- Bold text for emphasis
- Clear sections with headers

**FR-007.5:** Keywords/tags MUST be relevant and optimized:
- vibration, seismometer, accelerometer, vibration meter, earthquake, sensor, measurement

**FR-007.6:** App category MUST be set to: Tools

**FR-007.7:** Tags MUST include relevant options from Play Store taxonomy

### FR-008: Store Listing - Visual Assets

**FR-008.1:** App icon MUST meet Google Play requirements:
- 512 x 512 px
- 32-bit PNG (with alpha)
- Maximum 1024 KB
- Professional quality
- Follows Material Design guidelines

**FR-008.2:** Feature graphic MUST be created:
- 1024 x 500 px
- JPG or 24-bit PNG (no alpha)
- Showcases app key visual or branding

**FR-008.3:** Phone screenshots MUST be provided (minimum 2, maximum 8):
- Minimum dimension: 320 px
- Maximum dimension: 3840 px
- JPG or 24-bit PNG
- Must show actual app interface
- Recommended: 1080 x 1920 px or higher

**FR-008.4:** Phone screenshots MUST showcase:
- Screenshot 1: Main vibration measurement screen (live view)
- Screenshot 2: Session recording in progress
- Screenshot 3: Session history list
- Screenshot 4: Session detail view with graph
- Screenshot 5 (optional): Export/share functionality
- Screenshot 6 (optional): Settings or about screen

**FR-008.5:** OPTIONAL: 7-inch tablet screenshots (if app optimized for tablets)
- Same requirements as phone screenshots
- Different resolution: 1200 x 1920 px or similar

**FR-008.6:** OPTIONAL: 10-inch tablet screenshots

**FR-008.7:** OPTIONAL: Promotional video (YouTube URL)
- 30 seconds to 2 minutes
- Demonstrates app functionality

**FR-008.8:** Screenshots MUST have consistent styling:
- Same status bar style across screenshots
- Professional presentation
- Optional: Add text overlays explaining features
- Optional: Use device frames for polish

### FR-009: Store Listing - Additional Information

**FR-009.1:** Developer contact information MUST be provided:
- Email address (publicly visible)
- Website URL (optional but recommended)
- Physical address (required for paid apps, optional for free)

**FR-009.2:** Application type MUST be set: Application (not Game)

**FR-009.3:** Target audience MUST be declared:
- Target age: Everyone
- Ads policy: Declare if app contains ads (No for this app)

**FR-009.4:** Support email MUST be valid and monitored

**FR-009.5:** OPTIONAL: Developer website URL should explain app features

### FR-010: Build Generation and Upload

**FR-010.1:** Release build MUST be generated successfully without errors

**FR-010.2:** Generated AAB file MUST be signed with upload key

**FR-010.3:** AAB file MUST be tested locally before upload:
- Install using bundletool
- Verify all functionality works
- Test on multiple devices/Android versions if possible

**FR-010.4:** AAB MUST be uploaded to Internal Testing track in Play Console

**FR-010.5:** Upload MUST pass all Play Console validation checks:
- No policy violations
- No security vulnerabilities detected
- API level compliance
- 64-bit library support (if required)

**FR-010.6:** Release notes MUST be provided for internal testing:
- Brief description of build
- What to test
- Known issues (if any)

### FR-011: Internal Testing Setup

**FR-011.1:** Internal testing track MUST be configured in Play Console

**FR-011.2:** Test user email addresses MUST be added to testers list

**FR-011.3:** Internal testing release MUST be reviewed and approved

**FR-011.4:** Testers MUST be notified with opt-in link

**FR-011.5:** Testing feedback mechanism MUST be established

**FR-011.6:** Test period MUST be defined (recommended: 1-2 weeks minimum)

### FR-012: Compliance and Policy Review

**FR-012.1:** App MUST comply with all Google Play Developer Program Policies

**FR-012.2:** App permissions MUST be justified:
- VIBRATE permission: For haptic feedback (if used)
- ACCELEROMETER hardware feature: For vibration measurement (required)

**FR-012.3:** App MUST NOT contain:
- Deceptive behavior
- Malicious code
- Unauthorized data collection
- Policy-violating content

**FR-012.4:** Target API level MUST meet Play Store requirements:
- Currently API 36 (Android 14) - compliant
- Must target API level within 1 year of latest release

**FR-012.5:** App MUST include proper crash reporting and error handling

### FR-013: Release Documentation

**FR-013.1:** Release checklist document MUST be created including:
- All steps performed
- Configuration values used
- Asset creation process
- Upload procedure

**FR-013.2:** Keystore information document MUST be created (password-free):
- Keystore location
- Key alias
- Certificate fingerprints (SHA-1, SHA-256)
- Validity period

**FR-013.3:** Version management process MUST be documented:
- How to increment versionCode
- Semantic versioning strategy for versionName
- Release notes template

**FR-013.4:** Future release process MUST be documented:
- Building signed AAB
- Testing procedure
- Upload process
- Promotion from track to track

## Non-Goals (Out of Scope)

1. **Paid app features** - Initial release will be free, no in-app purchases or subscriptions
2. **Multiple language support** - English only for v1.0
3. **A/B testing** - No store listing experiments in initial release
4. **Pre-registration campaign** - Direct release without pre-registration
5. **Staged rollout** - Full rollout to internal testing, no percentage-based rollout
6. **Custom device catalog** - No device exclusions, available on all compatible devices
7. **Country restrictions** - No geographic restrictions (available worldwide)
8. **License testing** - Not applicable for free app
9. **Analytics integration** - No analytics SDK in initial release (Firebase, etc.)
10. **Crash reporting integration** - No third-party crash reporting (Crashlytics, etc.) in v1.0

## Design Considerations

### App Icon Design

**Requirements:**
- 512 x 512 px, 32-bit PNG with alpha
- Clear, recognizable at small sizes
- Follows Material Design guidelines
- Distinctive color scheme

**Design suggestions:**
- Central icon: Seismograph wave or vibration wave visual
- Color palette: Use app primary color
- Style: Modern, minimal, flat design
- Avoid: Text in icon (except small version identifier if needed)

### Feature Graphic Design

**Requirements:**
- 1024 x 500 px horizontal banner
- Professional, eye-catching design

**Design suggestions:**
- Background: Gradient or solid color matching app theme
- Central element: App icon + key visual (vibration waves, graph)
- Text: App name "Vibration Meter" in large, readable font
- Optional tagline: "Precise Vibration Measurement"
- Visual elements: Abstract wave patterns, measurement indicators

### Screenshot Design

**Style guidelines:**
- Use actual app interface (no mockups)
- Show realistic data in graphs and measurements
- Ensure readable text sizes
- Status bar: Clean, minimal notifications
- Optional enhancements:
  - Device frames around screenshots
  - Subtle drop shadows
  - Text overlays highlighting key features
  - Background color/gradient for presentation

**Content for each screenshot:**
1. **Main Screen**: Show live measurement with active vibration graph
2. **Recording Session**: Show recording indicator, timer, and active session
3. **Session History**: Show populated list with multiple sessions
4. **Session Detail**: Show detailed graph with statistics
5. **Export**: Show share sheet or export options
6. **Additional**: Any other compelling features

### Privacy Policy Hosting

**Options:**
- GitHub Pages (free, easy to update)
- Developer website
- Google Sites (free)
- Dedicated privacy policy hosting service

**Content structure:**
```
Privacy Policy for Vibration Meter
Last Updated: [Date]

1. Introduction
2. Information We Collect
   - Sensor data (accelerometer)
   - Local storage of measurement sessions
3. How We Use Information
   - App functionality only
   - No data transmission
4. Data Storage and Security
   - All data stored locally
   - User controls (export, delete)
5. Third-Party Services
   - None currently integrated
6. Your Rights
   - Data access
   - Data deletion
   - Data export
7. Children's Privacy
8. Changes to Privacy Policy
9. Contact Information
```

## Technical Considerations

### Build Configuration Updates

**build.gradle.kts (app module):**
```kotlin
android {
    // ... existing config

    buildTypes {
        release {
            isMinifyEnabled = true  // Enable R8
            isShrinkResources = true  // Shrink unused resources
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    signingConfigs {
        create("release") {
            // These will be loaded from local.properties or environment variables
            storeFile = file(System.getenv("KEYSTORE_FILE") ?: project.properties["keystoreFile"] as String)
            storePassword = System.getenv("KEYSTORE_PASSWORD") ?: project.properties["keystorePassword"] as String
            keyAlias = System.getenv("KEY_ALIAS") ?: project.properties["keyAlias"] as String
            keyPassword = System.getenv("KEY_PASSWORD") ?: project.properties["keyPassword"] as String
        }
    }

    bundle {
        language {
            enableSplit = false  // Don't split languages in initial release
        }
        density {
            enableSplit = true  // Enable density splits for smaller downloads
        }
        abi {
            enableSplit = true  // Enable ABI splits
        }
    }
}
```

**local.properties (NOT committed to git):**
```properties
keystoreFile=/path/to/keystore.jks
keystorePassword=YOUR_STORE_PASSWORD
keyAlias=YOUR_KEY_ALIAS
keyPassword=YOUR_KEY_PASSWORD
```

**.gitignore additions:**
```
# Keystore files
*.jks
*.keystore
keystore.properties
local.properties
```

### ProGuard Rules

**proguard-rules.pro additions:**
```proguard
# Keep Room entities
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keepclassmembers class * extends androidx.room.RoomDatabase {
    <init>(...);
}

# Keep Hilt generated classes
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Keep data classes used in Room
-keepclassmembers class com.cihatakyol.vibrationmeter.data.** {
    <fields>;
    <init>(...);
}

# Keep ViewModels
-keep class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

# Kotlin serialization (if used for export)
-keepclassmembers class kotlinx.serialization.** {
    <fields>;
}
```

### Keystore Generation Command

```bash
keytool -genkey -v \
  -keystore vibrationmeter-release.jks \
  -alias vibrationmeter \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000 \
  -storetype JKS
```

**Information to provide:**
- Your name or organization name
- Organizational unit (can be same as name)
- Organization (your company/name)
- City
- State/Province
- Country code (2 letter, e.g., US, TR)

### Building Release AAB

```bash
# Clean build
./gradlew clean

# Build release AAB
./gradlew bundleRelease

# Output location:
# app/build/outputs/bundle/release/app-release.aab
```

### Testing AAB Locally

```bash
# Download bundletool
# https://github.com/google/bundletool/releases

# Generate APK set from AAB
bundletool build-apks \
  --bundle=app/build/outputs/bundle/release/app-release.aab \
  --output=app-release.apks \
  --ks=vibrationmeter-release.jks \
  --ks-pass=pass:YOUR_PASSWORD \
  --ks-key-alias=vibrationmeter \
  --key-pass=pass:YOUR_PASSWORD

# Install to connected device
bundletool install-apks --apks=app-release.apks
```

### Data Safety Disclosure Template

Based on current app functionality:

**Data Collection: YES**
- App activity: Sensor data (accelerometer readings)
- Purpose: App functionality only
- Collection: Required for app to function
- Sharing: Not shared with third parties
- Encryption: Data encrypted on disk (if using encrypted Room or device encryption)
- Deletion: Users can delete data via app

**Security Practices:**
- Data is encrypted in transit: NO (no network transmission)
- Data is encrypted at rest: YES (device encryption)
- Users can request data deletion: YES (manual deletion in app)

**Data Types:**
- Device or other IDs: NO (if no analytics)
- App activity: YES (sensor measurements)
- App info and performance: NO (unless using crash reporting)

### Version Management Strategy

**Version Code:**
- Increment by 1 for each release
- Current: 1
- Next: 2, 3, 4...

**Version Name:**
- Semantic versioning: MAJOR.MINOR.PATCH
- Current: 1.0 (or 1.0.0)
- Examples:
  - 1.0.1 - Bug fix release
  - 1.1.0 - New feature (sessions v2)
  - 2.0.0 - Major redesign or breaking changes

### API Level Compliance

**Current configuration:**
- minSdk: 26 (Android 8.0) - Good coverage
- targetSdk: 36 (Android 14) - Latest
- compileSdk: 36 - Latest

**Play Store requirements:**
- New apps must target API level within 1 year of latest
- Currently compliant with API 36

**Considerations:**
- 64-bit support: Enabled by default in modern Android toolchain
- Permissions: All permissions properly declared in manifest
- Background restrictions: No background services in current app

## Success Metrics

1. **Build Success:** Release AAB builds without errors on first attempt
2. **Play Console Upload:** AAB passes all automated checks (security, policy, API level)
3. **Review Time:** App passes internal testing track review within 24 hours
4. **Installation Success:** 100% of internal testers can install and launch app
5. **Crash Rate:** Less than 1% crash rate during internal testing period
6. **Policy Compliance:** Zero policy violations detected during review
7. **Asset Quality:** All store listing assets meet quality guidelines without rejection
8. **Testing Coverage:** At least 5 internal testers provide feedback
9. **Documentation Quality:** Release process documented well enough for future updates
10. **Timeline:** Complete all requirements within 1 week of starting

## Open Questions

1. **Privacy Policy Hosting:** Where should the privacy policy be hosted? GitHub Pages, personal website, or other?

2. **Analytics Integration:** Should we integrate Firebase Analytics or other analytics before release, or defer to post-launch?

3. **Crash Reporting:** Should we integrate Firebase Crashlytics or similar crash reporting before release, or rely on Play Console crash reports?

4. **App Icon Design:** Should we hire a designer for professional icon/graphics, use design tools (Figma, Canva), or create in-house?

5. **Beta Testing Duration:** How long should internal testing run before considering broader testing tracks?

6. **Marketing Materials:** Do we need a website or landing page for the app, or is the Play Store listing sufficient?

7. **Contact Email:** Should we create a dedicated support email (e.g., support@vibrationmeter.com) or use personal email?

8. **Future Monetization:** Even though v1 is free, should we plan for future monetization options (Pro version, ads, donations)?

9. **Localization:** What languages should we prioritize for future localized versions?

10. **Backup and Restore:** Should we implement cloud backup/restore functionality before or after initial release?

11. **Store Listing A/B Testing:** After launch, should we plan store listing experiments to optimize conversion?

12. **Age Rating Details:** Should we add any content descriptors to the age rating (none expected, but confirm)?

## Implementation Priority

### Phase 1: Build Configuration (Day 1)
- FR-001: Configure release build settings
- FR-002: Create keystore and configure signing
- FR-010.1-10.3: Generate and test release AAB locally

### Phase 2: Play Store Requirements (Day 2-3)
- FR-004: Complete data safety disclosure
- FR-005: Write and host privacy policy
- FR-006: Complete content rating questionnaire
- FR-012: Policy compliance review

### Phase 3: Store Listing Content (Day 3-4)
- FR-007: Write all text content (title, descriptions, keywords)
- FR-009: Provide contact information

### Phase 4: Visual Assets (Day 4-5)
- FR-008.1: Finalize app icon (512x512)
- FR-008.2: Create feature graphic (1024x500)
- FR-008.3-8.8: Capture and prepare screenshots

### Phase 5: Upload and Testing (Day 5-6)
- FR-003: Enroll in Google Play App Signing
- FR-010.4-10.6: Upload AAB to Play Console
- FR-011: Configure internal testing track

### Phase 6: Documentation (Day 6-7)
- FR-013: Create all release documentation
- Document lessons learned
- Prepare for future updates

## Release Checklist

- [ ] Release build configuration complete
- [ ] Keystore generated and backed up securely
- [ ] ProGuard rules optimized
- [ ] Release AAB builds successfully
- [ ] AAB tested locally with bundletool
- [ ] Google Play Developer account confirmed
- [ ] Privacy policy written and hosted
- [ ] Privacy policy URL accessible
- [ ] Data safety form completed
- [ ] Content rating questionnaire completed
- [ ] App title finalized (max 50 chars)
- [ ] Short description written (max 80 chars)
- [ ] Full description written (max 4000 chars)
- [ ] Keywords/tags selected
- [ ] App icon created (512x512 PNG)
- [ ] Feature graphic created (1024x500)
- [ ] Screenshots captured (minimum 2, recommended 4-6)
- [ ] Developer contact email provided
- [ ] App category set to Tools
- [ ] Target audience declared
- [ ] Google Play App Signing enrolled
- [ ] AAB uploaded to Internal Testing track
- [ ] Play Console validation passed
- [ ] Internal testers added
- [ ] Release notes written
- [ ] Testing period scheduled
- [ ] Release documentation completed
- [ ] Keystore backup verified
- [ ] Version management process documented

## Post-Release Activities

After internal testing track release:

1. **Monitor Feedback:** Collect and review tester feedback daily
2. **Track Metrics:** Monitor crash reports, ANRs, and performance in Play Console
3. **Fix Critical Issues:** Address any blockers or critical bugs found
4. **Iterate on Assets:** Refine store listing based on feedback
5. **Plan Next Steps:** Decide on closed testing, open testing, or production release
6. **Update Documentation:** Document any issues found and solutions applied
7. **Prepare Marketing:** If planning broader release, prepare marketing materials
8. **Community Building:** Consider creating social media presence or website
