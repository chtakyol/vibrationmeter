# Visual Assets Guide for Google Play Store

This guide explains how to create all required visual assets for the Vibration Meter Play Store listing.

**Last Updated:** December 3, 2025

---

## Required Assets Checklist

- [ ] App Icon (512x512 PNG) - **REQUIRED**
- [ ] Feature Graphic (1024x500 PNG/JPG) - **REQUIRED**
- [ ] Phone Screenshots (minimum 2, recommended 4-6) - **REQUIRED**
- [ ] Tablet Screenshots (optional)
- [ ] Promo Video (optional)

---

## 1. App Icon (512x512)

### Requirements

- **Size:** 512 x 512 pixels (EXACTLY)
- **Format:** 32-bit PNG with alpha channel
- **Max File Size:** 1024 KB (1 MB)
- **Color Space:** sRGB
- **Design:** Follow Material Design guidelines

### Design Guidelines

**Do:**
✓ Use simple, recognizable shapes
✓ Use vibrant, contrasting colors
✓ Make it unique and memorable
✓ Ensure it looks good at small sizes (24x24 to 512x512)
✓ Use consistent branding with app
✓ Test on various backgrounds (light/dark)

**Don't:**
✗ Use small text (won't be readable)
✗ Include screenshots or UI elements
✗ Use too many details
✗ Copy other app icons
✗ Use Android system icons

### Design Concepts for Vibration Meter

**Concept 1: Wave Pattern**
- Central element: Sine wave or vibration wave
- Color scheme: Blue gradient (#1976D2 to #64B5F6)
- Background: Subtle circular gradient
- Style: Modern, clean, minimal

**Concept 2: Seismograph Style**
- Central element: Classic seismograph line pattern
- Color scheme: Red/Orange (#F44336) for activity
- Background: Dark or white depending on theme
- Style: Technical, professional

**Concept 3: Acceleration Meter**
- Central element: Circular meter/gauge
- Needle pointing to measurement
- Color scheme: Green (#4CAF50) for active measurement
- Style: Instrument-like, technical

**Concept 4: Three-Axis Representation**
- Three arrows or lines representing X, Y, Z axes
- Color scheme: Multi-color (Red X, Green Y, Blue Z)
- Style: Technical, geometric

### Current Icon Location

Check if there's an existing icon:
```
app/src/main/res/drawable/app_icon.xml (or .png)
```

### Tools for Creating Icons

**Free Options:**
1. **Figma** (https://figma.com)
   - Professional design tool
   - Free tier available
   - Export to PNG directly

2. **Canva** (https://canva.com)
   - Easy to use
   - Templates available
   - Free tier available

3. **Android Asset Studio** (https://romannurik.github.io/AndroidAssetStudio/)
   - Specialized for Android icons
   - Free and browser-based

4. **GIMP** (https://gimp.org)
   - Free Photoshop alternative
   - Full control over design

**Paid Options:**
- Adobe Photoshop/Illustrator
- Sketch (Mac only)
- Affinity Designer

**Hire a Designer:**
- Fiverr: $5-50
- Upwork: $50-200
- 99designs: Contest-based

### Export Settings

If creating in design tool:
1. Canvas size: 512x512 px
2. Export as PNG
3. Include transparency (alpha channel)
4. 32-bit color depth
5. Verify file size < 1 MB

### Save Location

```
assets/store-listing/app-icon-512.png
```

---

## 2. Feature Graphic (1024x500)

### Requirements

- **Size:** 1024 x 500 pixels (EXACTLY)
- **Format:** 24-bit PNG (no alpha) or JPG
- **Max File Size:** No official limit, keep under 1 MB for faster loading
- **Color Space:** sRGB
- **Safe Zone:** Keep important elements in center 924x400 area

### Design Guidelines

**Purpose:**
- Displayed at top of Play Store listing
- First visual impression for many users
- Should convey app purpose quickly

**Do:**
✓ Feature app icon prominently
✓ Include app name in large, readable text
✓ Use high-quality graphics
✓ Keep design simple and clean
✓ Use brand colors
✓ Consider adding a tagline

**Don't:**
✗ Use screenshots (dedicated section for those)
✗ Include small text (won't be readable on mobile)
✗ Clutter with too many elements
✗ Use low-resolution images
✗ Violate Google's policies (no misleading claims)

### Design Concepts for Vibration Meter

**Concept 1: App Name + Icon + Waves**
```
┌──────────────────────────────────────────┐
│                                          │
│  [ICON]  VIBRATION METER                │
│          Precise Measurement             │
│  ≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈              │
│                                          │
└──────────────────────────────────────────┘
```
- Left: App icon (128-150px size)
- Center/Right: App name in large text
- Below: Short tagline
- Background: Gradient or wave pattern

**Concept 2: Graph Visualization**
```
┌──────────────────────────────────────────┐
│  VIBRATION METER                         │
│  [Wave graph visualization]              │
│           [ICON]                         │
└──────────────────────────────────────────┘
```
- Top: App name
- Center: Stylized graph/wave
- Bottom right: App icon
- Background: Clean gradient

**Concept 3: Minimal & Professional**
```
┌──────────────────────────────────────────┐
│                                          │
│    [Large Icon]                          │
│    Vibration Meter                       │
│    Real-Time Vibration Measurement       │
│                                          │
└──────────────────────────────────────────┘
```
- Centered design
- Large icon as focal point
- App name below
- Tagline for context
- Solid color or subtle gradient background

### Recommended Approach

**Quick & Professional:**
1. Background: Blue gradient (#1976D2 to #0D47A1)
2. Left side: App icon (140px)
3. Right side: "VIBRATION METER" in bold white text (60-70px font)
4. Below name: "Precise Vibration Measurement" (24-30px font)
5. Bottom: Subtle wave pattern decoration

### Tools

Same as icon creation:
- Figma, Canva, Photoshop, GIMP
- Size: 1024x500 px canvas

### Save Location

```
assets/store-listing/feature-graphic-1024x500.png
```

---

## 3. Screenshots (Phone)

### Requirements

- **Minimum:** 2 screenshots
- **Recommended:** 4-6 screenshots
- **Maximum:** 8 screenshots
- **Min Dimension:** 320 px
- **Max Dimension:** 3840 px
- **Format:** JPG or 24-bit PNG
- **Recommended Size:** 1080 x 1920 px (9:16 aspect ratio)
- **Orientation:** Portrait

### What to Capture

**Screenshot 1: Main Measurement Screen**
- Show real-time vibration measurement in action
- Display with active/realistic vibration values
- Show clean, clear UI
- **Purpose:** Show core functionality

**Screenshot 2: Session Recording**
- Show recording in progress
- Timer visible
- Session name displayed
- Active vibration graph
- **Purpose:** Demonstrate recording feature

**Screenshot 3: Session History**
- List of saved sessions
- Multiple entries with dates/times
- Show organized data
- **Purpose:** Show data management

**Screenshot 4: Session Detail/Graph**
- Detailed view of completed session
- Graph with vibration data
- Statistics visible (peak, average, duration)
- **Purpose:** Show analytics capabilities

**Screenshot 5: Export/Share (Optional)**
- Show export or share functionality
- Android share sheet if applicable
- **Purpose:** Demonstrate data portability

**Screenshot 6: Settings/About (Optional)**
- App settings or information screen
- Show configuration options
- **Purpose:** Show additional features

### How to Capture Screenshots

**Method 1: Emulator** (Recommended for consistency)
1. Open Android Studio
2. Launch emulator (Pixel 6 or similar, 1080x2400 resolution)
3. Run app on emulator
4. Use emulator's screenshot button
5. Images saved to desktop

**Method 2: Physical Device**
1. Run app on your Android device
2. Navigate to screen you want
3. Press Volume Down + Power simultaneously
4. Find screenshots in Photos/Gallery
5. Transfer to computer

**Method 3: ADB**
```bash
adb shell screencap -p /sdcard/screenshot.png
adb pull /sdcard/screenshot.png
```

### Screenshot Enhancement (Optional but Recommended)

**Basic Enhancement:**
- Use actual device frames around screenshots
- Add consistent status bar styling
- Ensure no embarrassing notifications in status bar

**Professional Enhancement:**
- Add text overlays highlighting features
- "Real-Time Measurement" on screenshot 1
- "Record & Save Sessions" on screenshot 2
- Use consistent typography and colors
- Add subtle drop shadows or backgrounds

**Tools for Enhancement:**
1. **Device Art Generator** (https://developer.android.com/distribute/marketing-tools/device-art-generator)
   - Free, official Google tool
   - Adds device frames

2. **Figma/Canva**
   - Import screenshots
   - Add device frames and text overlays
   - Export as PNG

3. **Screenshot Maker Apps**
   - "Screenshot Maker" on Play Store
   - "Previewed" (online tool)

### Preparation Checklist

Before capturing:
- [ ] Clean up status bar (turn off notifications, set battery to 100%, good signal)
- [ ] Use consistent time (e.g., always show 10:30 AM)
- [ ] Populate app with realistic data
  - Create 3-5 sample sessions
  - Use realistic session names ("Washing Machine Test", "Floor Vibration", etc.)
  - Include various timestamps
- [ ] Test in both light and dark mode (choose one for consistency)
- [ ] Ensure no debug UI elements visible

### Save Location

```
assets/store-listing/screenshots/phone-01.png
assets/store-listing/screenshots/phone-02.png
assets/store-listing/screenshots/phone-03.png
assets/store-listing/screenshots/phone-04.png
assets/store-listing/screenshots/phone-05.png (optional)
assets/store-listing/screenshots/phone-06.png (optional)
```

---

## 4. Tablet Screenshots (Optional)

### When to Include

- If app is optimized for tablets
- If app uses tablet-specific layouts
- Recommended if targeting productivity users

### Requirements

**7-inch Tablets:**
- Recommended: 1200 x 1920 px or similar

**10-inch Tablets:**
- Recommended: 2560 x 1800 px (landscape) or 1800 x 2560 px (portrait)

### Skip for Now?

For v1.0 initial release, **tablet screenshots are optional** unless:
- You have tablet-specific UI
- Significant tablet user base expected

---

## 5. Promo Video (Optional)

### Requirements

- **Platform:** YouTube
- **Length:** 30 seconds to 2 minutes
- **Format:** YouTube video URL
- **Content:** Demonstrate app functionality

### When to Create

- **Not for v1.0:** Focus on core assets first
- **Future update:** Create after launch with user feedback
- **If needed:** After you have established user base

### If You Want to Create One

**Content Ideas:**
1. Quick app overview (5-10 seconds)
2. Real-time measurement demo (10-15 seconds)
3. Recording session (10-15 seconds)
4. Viewing saved data (10 seconds)
5. Export/share demo (5 seconds)
6. Call to action (5 seconds)

**Tools:**
- Screen recording on device
- Video editing: iMovie, DaVinci Resolve (free), Adobe Premiere
- Voiceover or text overlays

---

## Quick Start: Minimum Viable Assets

To launch v1.0, you MUST have:

1. **App Icon (512x512)** - Even a simple one works
2. **Feature Graphic (1024x500)** - Can be basic
3. **At Least 2 Screenshots** - Real app screenshots, no enhancement needed

**Time Estimate:**
- Basic icon (using template): 30 minutes
- Basic feature graphic: 30 minutes
- 4 screenshots: 15 minutes
- **Total:** ~75 minutes for minimum viable assets

---

## Professional Assets (Recommended)

For better conversion and professionalism:

1. **Custom designed icon** - Hire designer or spend time in Figma
2. **Polished feature graphic** - Include branding and tagline
3. **4-6 enhanced screenshots** - With device frames and text overlays

**Time Estimate:**
- Custom icon: 2-4 hours (or $20-50 outsourced)
- Professional feature graphic: 1-2 hours
- Enhanced screenshots: 1-2 hours
- **Total:** ~5-8 hours or $50-100 outsourced

---

## Next Steps

1. **Decide on approach:** Quick launch vs. polished assets
2. **Check existing icon:** See if `app/src/main/res/drawable/app_icon.*` is usable
3. **Create or export assets** using guides above
4. **Save to correct locations** in `assets/store-listing/`
5. **Mark tasks complete** in task list

---

## Resources

**Design Inspiration:**
- Google Play Store: Browse similar apps (tools, utilities, sensors)
- Dribbble: Search "app icon android"
- Behance: Search "play store screenshots"

**Stock Assets (Free):**
- Unsplash: Free stock photos
- Flaticon: Free icons
- Google Fonts: Free typography

**Learning Resources:**
- Material Design Guidelines: https://m3.material.io
- Google Play Console Help: Asset specifications
- Android Developers: Icon design guide

---

**End of Visual Assets Guide**
