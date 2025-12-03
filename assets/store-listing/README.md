# Store Listing Assets - Action Required

## Current Status

✅ **Text Content:** Complete
- App title, descriptions, keywords all written in `store-description.md`

⏳ **Visual Assets:** Need to be created/exported

---

## Visual Assets Checklist

### 1. App Icon (512x512) - REQUIRED

**Current Status:** App has an existing vector icon at `app/src/main/res/drawable/app_icon.xml`

**Design:** Black background with white wave pattern (seismograph style)

**Action Needed:**

You need to export this icon as a 512x512 PNG. You have two options:

**Option A: Use Android Studio's Image Asset Studio (Recommended)**

1. Open Android Studio
2. Right-click on `res` folder → New → Image Asset
3. Set Icon Type: "Launcher Icons (Legacy)"
4. In the Foreground Layer tab:
   - Asset Type: Select "Image"
   - Path: Choose the highest resolution `ic_launcher.webp` from `mipmap-xxxhdpi/`
5. Generate (this creates temporary versions)
6. Manually export the 512x512 version:
   - Take screenshot of the icon at large size
   - OR use a vector to raster converter

**Option B: Export Manually Using Online Tool**

1. Copy the XML content from `app/src/main/res/drawable/app_icon.xml`
2. Go to: https://svg2png.com or similar SVG to PNG converter
3. Convert to 512x512 PNG
4. Save as: `assets/store-listing/app-icon-512.png`

**Option C: Create New Icon**

If you want a different design:
- Follow the guide in `VISUAL_ASSETS_GUIDE.md`
- Use Figma, Canva, or hire a designer
- Must be 512x512 PNG with alpha channel

**Save Location:**
```
assets/store-listing/app-icon-512.png
```

---

### 2. Feature Graphic (1024x500) - REQUIRED

**Current Status:** Not created yet

**Action Needed:**

Create a 1024x500 banner image for the Play Store listing.

**Quick Method (Recommended for v1.0):**

1. Use Canva (https://canva.com)
   - Sign up free
   - Create custom size: 1024 x 500 px
   - Add elements:
     - Background: Blue gradient or solid color
     - Left side: Upload and place app icon
     - Right side: Text "VIBRATION METER" (large font)
     - Below: "Precise Vibration Measurement" (smaller font)
   - Download as PNG

2. **Or** use Figma (https://figma.com)
   - Create frame: 1024 x 500
   - Design following concepts in VISUAL_ASSETS_GUIDE.md
   - Export as PNG

**Save Location:**
```
assets/store-listing/feature-graphic-1024x500.png
```

---

### 3. Screenshots (Minimum 2, Recommended 4-6) - REQUIRED

**Current Status:** Not captured yet

**Action Needed:**

Capture screenshots of the app in action.

**Steps:**

1. **Build and run the app** on an emulator or device
   ```bash
   ./gradlew installDebug
   # OR run from Android Studio
   ```

2. **Prepare the app with sample data:**
   - Create 3-4 sample recording sessions
   - Use realistic names: "Washing Machine Test", "Floor Vibration", etc.
   - Make sure sessions have different dates/times

3. **Capture screenshots:**

   **Screenshot 1: Main Measurement Screen**
   - Open app to main screen
   - Show real-time measurement (simulate vibration if possible)
   - Take screenshot

   **Screenshot 2: Recording Session**
   - Start a recording
   - Show timer and active session
   - Take screenshot

   **Screenshot 3: Session History**
   - Navigate to session history/list
   - Show multiple saved sessions
   - Take screenshot

   **Screenshot 4: Session Detail**
   - Open a completed session
   - Show graph and statistics
   - Take screenshot

4. **How to take screenshots:**
   - **Emulator:** Click camera icon in emulator toolbar
   - **Physical Device:** Volume Down + Power button
   - **ADB Command:** `adb shell screencap -p /sdcard/screen.png && adb pull /sdcard/screen.png`

5. **Save screenshots:**
   ```
   assets/store-listing/screenshots/phone-01.png  (Main screen)
   assets/store-listing/screenshots/phone-02.png  (Recording)
   assets/store-listing/screenshots/phone-03.png  (History)
   assets/store-listing/screenshots/phone-04.png  (Detail)
   ```

**Optional Enhancement:**
- Add device frames using https://developer.android.com/distribute/marketing-tools/device-art-generator
- Clean up status bar (set time to 10:30, battery 100%, etc.)

---

## Quick Summary - What You Need to Do

**Minimum to launch (estimated time: 1 hour):**

1. ✅ Text content - Already done!
2. ⏳ Export app icon to 512x512 PNG - 15 minutes
3. ⏳ Create basic feature graphic in Canva - 20 minutes
4. ⏳ Capture 4 app screenshots - 15 minutes
5. ⏳ Save all files in correct locations - 5 minutes

**Then you can proceed to build the release AAB and upload to Play Console!**

---

## Files to Create

```
assets/store-listing/
├── app-icon-512.png                    ← REQUIRED
├── feature-graphic-1024x500.png        ← REQUIRED
├── screenshots/
│   ├── phone-01.png                    ← REQUIRED
│   ├── phone-02.png                    ← REQUIRED
│   ├── phone-03.png                    ← Recommended
│   └── phone-04.png                    ← Recommended
├── store-description.md                ✅ Done
├── VISUAL_ASSETS_GUIDE.md              ✅ Done
└── README.md                           ✅ Done (this file)
```

---

## After Creating Assets

Once you've created all the visual assets:

1. Verify file sizes and formats:
   ```bash
   ls -lh assets/store-listing/
   ```

2. Update the task list to mark visual assets as complete

3. Proceed to Task 5.0: Build and test release AAB

---

## Need Help?

- Refer to `VISUAL_ASSETS_GUIDE.md` for detailed instructions
- Check existing icon: `app/src/main/res/drawable/app_icon.xml`
- Use the recommended tools: Canva (feature graphic), Android Studio (screenshots)

---

## Alternative: Skip Visual Polish for Now

If you want to launch quickly to internal testing:

- You can use basic/placeholder graphics for internal testing
- Polish them later before promoting to production
- Internal testers won't mind basic assets
- Focus on functionality first, polish second

**However**, you still MUST have:
- One app icon (even if simple)
- One feature graphic (even if basic)
- At least 2 screenshots (actual app screenshots)

These are mandatory Play Store requirements.
