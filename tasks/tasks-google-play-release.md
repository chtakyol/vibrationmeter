## Relevant Files

### Build Configuration
- `app/build.gradle.kts` - Main app build configuration; needs release build type, signing config, ProGuard/R8 settings, and bundle configuration
- `build.gradle.kts` - Root project build configuration (may need minimal updates)
- `settings.gradle.kts` - Project settings (likely no changes needed)
- `app/proguard-rules.pro` - ProGuard rules for release builds; needs rules for Room, Hilt, ViewModels, and data classes
- `.gitignore` - Must exclude keystore files and credential files

### App Configuration
- `app/src/main/AndroidManifest.xml` - App permissions and metadata; verify all permissions are justified

### Keystore and Signing (NOT committed to git)
- `vibrationmeter-release.jks` - Release keystore file (to be created, stored securely outside repo)
- `local.properties` - Keystore credentials (excluded from git, for local builds)
- `keystore-info.md` - Documentation of keystore details without passwords (to be created in `/docs/`)

### Privacy and Compliance Documents (to be created)
- `docs/privacy-policy.md` - Privacy policy content (to be hosted publicly)
- `docs/data-safety-disclosure.md` - Data safety form answers for Play Console reference
- `docs/content-rating-responses.md` - Content rating questionnaire responses

### Store Listing Assets (to be created)
- `assets/store-listing/app-icon-512.png` - 512x512 app icon for Play Store
- `assets/store-listing/feature-graphic-1024x500.png` - Feature graphic banner
- `assets/store-listing/screenshots/phone-*.png` - Phone screenshots (minimum 2, recommended 4-6)
- `assets/store-listing/store-description.md` - Full store listing text content

### Release Documentation (to be created)
- `docs/release-process.md` - Complete release process documentation
- `docs/version-management.md` - Version code/name strategy
- `docs/release-checklist.md` - Checklist for future releases
- `CHANGELOG.md` - Version history and release notes

### Notes

- Keystore files must NEVER be committed to version control
- Use environment variables or `local.properties` for sensitive credentials
- Backup keystore file in multiple secure locations (encrypted cloud storage, USB drive)
- Test release AAB locally with bundletool before uploading to Play Console
- All compliance documents should be reviewed before finalizing

## Instructions for Completing Tasks

**IMPORTANT:** As you complete each task, you must check it off in this markdown file by changing `- [ ]` to `- [x]`. This helps track progress and ensures you don't skip any steps.

Example:
- `- [ ] 1.1 Read file` → `- [x] 1.1 Read file` (after completing)

Update the file after completing each sub-task, not just after completing an entire parent task.

## Tasks

- [x] 0.0 Create feature branch
  - [x] 0.1 Create and checkout a new branch for this feature (e.g., `git checkout -b release/v1.0-play-store-prep`)

- [x] 1.0 Configure release build and app signing
  - [x] 1.1 Read current `app/build.gradle.kts` to understand existing configuration
  - [x] 1.2 Generate release keystore using keytool command (store outside repository)
  - [x] 1.3 Document keystore information (alias, validity, fingerprints) in `docs/keystore-info.md` (without passwords)
  - [x] 1.4 Create backup copies of keystore in 2-3 secure locations
  - [x] 1.5 Update `.gitignore` to exclude `*.jks`, `*.keystore`, `keystore.properties`, and ensure `local.properties` is excluded
  - [x] 1.6 Create `local.properties` with keystore credentials (keystoreFile, keystorePassword, keyAlias, keyPassword)
  - [x] 1.7 Update `app/build.gradle.kts` to add release signingConfig that reads from local.properties or environment variables
  - [x] 1.8 Update `app/build.gradle.kts` release buildType to enable minification (isMinifyEnabled = true, isShrinkResources = true)
  - [x] 1.9 Update `app/build.gradle.kts` release buildType to reference release signingConfig
  - [x] 1.10 Configure bundle block in `app/build.gradle.kts` for density and ABI splits
  - [x] 1.11 Read current `app/proguard-rules.pro` to review existing rules
  - [x] 1.12 Update `app/proguard-rules.pro` with rules for Room entities, Hilt/Dagger, ViewModels, and data classes
  - [x] 1.13 Verify version code and version name in `app/build.gradle.kts` (versionCode = 1, versionName = "1.0")
  - [x] 1.14 Verify debuggable is false for release build type

- [x] 2.0 Create and host privacy policy
  - [x] 2.1 Write privacy policy content in `docs/privacy-policy.md` covering: data collection, usage, storage, user rights, contact info
  - [x] 2.2 Include sections: Introduction, Information Collected (accelerometer/sensor data), How Data is Used, Data Storage (local only), User Rights (delete/export), Third-Party Services (none), Children's Privacy, Changes to Policy, Contact Information
  - [x] 2.3 Add "Last Updated" date to privacy policy
  - [x] 2.4 Choose hosting solution (GitHub Pages, personal website, Google Sites, or other)
  - [x] 2.5 Publish privacy policy to chosen hosting platform
  - [x] 2.6 Verify privacy policy is publicly accessible via URL
  - [x] 2.7 Document privacy policy URL for Play Console submission
  - [ ] 2.8 (Optional) Add privacy policy link to app Settings screen

- [x] 3.0 Complete Google Play Store compliance requirements
  - [x] 3.1 Create `docs/data-safety-disclosure.md` to document answers for Play Console data safety form
  - [x] 3.2 Document data collection: App activity - sensor data (accelerometer readings)
  - [x] 3.3 Document data usage purpose: App functionality only (vibration measurement)
  - [x] 3.4 Document data sharing: Not shared with third parties
  - [x] 3.5 Document data security: Encrypted at rest via device encryption, no network transmission
  - [x] 3.6 Document user controls: Can delete data manually via app
  - [x] 3.7 Confirm no collection of Device IDs, Personal Info, or other sensitive data types
  - [x] 3.8 Create `docs/content-rating-responses.md` to document content rating questionnaire responses
  - [x] 3.9 Document expected rating: Everyone (E) - no violence, mature content, or user interaction
  - [x] 3.10 Read `app/src/main/AndroidManifest.xml` to review all declared permissions
  - [x] 3.11 Document permission justifications: VIBRATE (haptic feedback if used), accelerometer hardware (required for core functionality)
  - [x] 3.12 Review app against Google Play Developer Program Policies checklist (no deceptive behavior, malicious code, unauthorized collection)
  - [x] 3.13 Verify target API level meets requirements (currently API 36 - compliant)

- [x] 4.0 Create store listing content and visual assets
  - [x] 4.1 Create `assets/store-listing/` directory for all store assets
  - [x] 4.2 Create `assets/store-listing/store-description.md` for text content
  - [x] 4.3 Write app title (max 50 chars): "Vibration Meter" or "Vibration Meter - Seismic Monitor"
  - [x] 4.4 Write short description (max 80 chars): "Measure device vibrations in real-time with precision accelerometer data"
  - [x] 4.5 Write full description (max 4000 chars) including: overview, key features (real-time monitoring, session recording, data export, graphs), use cases (washing machine testing, earthquake detection), technical details, privacy highlights
  - [x] 4.6 Format full description with bullet points, bold text, and clear sections
  - [x] 4.7 Define keywords/tags: vibration, seismometer, accelerometer, vibration meter, earthquake, sensor, measurement, tools
  - [x] 4.8 Set app category: Tools
  - [x] 4.9 Create or finalize app icon at 512x512 px (32-bit PNG with alpha) following Material Design guidelines
  - [x] 4.10 Save app icon as `assets/store-listing/app-icon-512.png`
  - [x] 4.11 Create feature graphic 1024x500 px (JPG or 24-bit PNG) with app branding, name, and key visual
  - [x] 4.12 Save feature graphic as `assets/store-listing/feature-graphic-1024x500.png`
  - [x] 4.13 Capture screenshot 1: Main vibration measurement screen with live data
  - [x] 4.14 Capture screenshot 2: Session recording in progress with timer
  - [x] 4.15 Capture screenshot 3: Session history list with multiple entries
  - [x] 4.16 Capture screenshot 4: Session detail view with graph and statistics
  - [x] 4.17 (Optional) Capture screenshot 5: Export/share functionality
  - [x] 4.18 (Optional) Capture screenshot 6: Settings or additional features
  - [x] 4.19 Process screenshots to meet requirements: min 320px, max 3840px, JPG or 24-bit PNG, recommended 1080x1920 or higher
  - [x] 4.20 Save screenshots in `assets/store-listing/screenshots/` as phone-01.png through phone-06.png
  - [x] 4.21 (Optional) Add professional styling to screenshots: device frames, consistent status bar, text overlays
  - [x] 4.22 Prepare developer contact information: support email address (valid and monitored)
  - [x] 4.23 (Optional) Prepare developer website URL if available

- [x] 5.0 Build and test release AAB
  - [x] 5.1 Run `./gradlew clean` to clean previous builds
  - [x] 5.2 Run `./gradlew bundleRelease` to build release AAB
  - [x] 5.3 Verify AAB is generated at `app/build/outputs/bundle/release/app-release.aab`
  - [x] 5.4 Check build output for any warnings or errors
  - [ ] 5.5 Download bundletool from https://github.com/google/bundletool/releases if not already available
  - [ ] 5.6 Generate APK set from AAB using bundletool: `bundletool build-apks --bundle=app-release.aab --output=app-release.apks --ks=vibrationmeter-release.jks --ks-pass=pass:PASSWORD --ks-key-alias=vibrationmeter --key-pass=pass:PASSWORD`
  - [ ] 5.7 Install APK set to connected test device: `bundletool install-apks --apks=app-release.apks`
  - [ ] 5.8 Launch app on test device and verify it starts without crashes
  - [ ] 5.9 Test core functionality: real-time vibration measurement, session recording, session history, session details, export
  - [ ] 5.10 Test app on multiple devices or Android versions if possible
  - [ ] 5.11 Verify no debug logs or debug UI elements are visible in release build
  - [ ] 5.12 Check AAB file size and note for reference

- [ ] 6.0 Upload to Google Play Console and configure internal testing
  - [ ] 6.1 Confirm access to Google Play Developer account
  - [ ] 6.2 Create new app in Google Play Console (if not already created)
  - [ ] 6.3 Set app name and default language in Play Console
  - [ ] 6.4 Navigate to Release > Setup > App Signing and enroll in Google Play App Signing
  - [ ] 6.5 Accept Google Play App Signing terms
  - [ ] 6.6 Upload app-release.aab to Internal Testing track
  - [ ] 6.7 Wait for Play Console to process AAB and run automated checks
  - [ ] 6.8 Review and resolve any warnings or errors from Play Console validation
  - [ ] 6.9 Navigate to Store Presence > Main Store Listing and fill in app title
  - [ ] 6.10 Fill in short description and full description from `store-description.md`
  - [ ] 6.11 Upload app icon (512x512 PNG)
  - [ ] 6.12 Upload feature graphic (1024x500)
  - [ ] 6.13 Upload phone screenshots (minimum 2, recommended 4-6)
  - [ ] 6.14 Set app category to Tools
  - [ ] 6.15 Add tags/keywords for discoverability
  - [ ] 6.16 Navigate to Policy > App Content and start Data Safety form
  - [ ] 6.17 Complete Data Safety form using answers from `docs/data-safety-disclosure.md`
  - [ ] 6.18 Navigate to Policy > Privacy Policy and enter privacy policy URL
  - [ ] 6.19 Navigate to Policy > App Content > Content Ratings and start questionnaire
  - [ ] 6.20 Complete content rating questionnaire using answers from `docs/content-rating-responses.md`
  - [ ] 6.21 Submit content rating and obtain certificates
  - [ ] 6.22 Navigate to Policy > Target Audience and set to Everyone
  - [ ] 6.23 Declare ads policy: No ads (for this app)
  - [ ] 6.24 Provide developer contact email in Store Presence > Store Settings
  - [ ] 6.25 (Optional) Add developer website URL if available
  - [ ] 6.26 Navigate to Release > Internal Testing and add tester email addresses
  - [ ] 6.27 Write release notes for internal testing (what to test, known issues if any)
  - [ ] 6.28 Review all sections in Play Console for completeness
  - [ ] 6.29 Submit internal testing release for review
  - [ ] 6.30 Copy opt-in link for testers and share with internal testing team

- [ ] 7.0 Create release documentation
  - [ ] 7.1 Create `docs/release-process.md` documenting complete release workflow
  - [ ] 7.2 Document in release-process.md: build configuration steps, keystore setup, AAB generation, bundletool testing, Play Console upload procedure
  - [ ] 7.3 Create `docs/version-management.md` with versioning strategy
  - [ ] 7.4 Document version code increment rules (increment by 1 each release)
  - [ ] 7.5 Document version name semantic versioning (MAJOR.MINOR.PATCH)
  - [ ] 7.6 Create `docs/release-checklist.md` as a quick reference checklist for future releases
  - [ ] 7.7 Create or update `CHANGELOG.md` with v1.0 release notes
  - [ ] 7.8 Document lessons learned and any issues encountered during first release
  - [ ] 7.9 Document Play Console access details (account email, app ID)
  - [ ] 7.10 Verify all documentation is clear and actionable for future releases
  - [ ] 7.11 Commit all documentation and configuration changes to feature branch (excluding keystore and credentials)
  - [ ] 7.12 Push feature branch to remote repository
  - [ ] 7.13 (Optional) Create pull request for review before merging to main
