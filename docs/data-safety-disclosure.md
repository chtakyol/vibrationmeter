# Data Safety Disclosure for Google Play Console

This document contains the answers for the Google Play Console Data Safety form. Use these responses when filling out the Data Safety section in the Play Console.

**Last Updated:** December 3, 2025

---

## Overview

The Data Safety form helps users understand what data your app collects, shares, and how it handles that data. Answer honestly and accurately.

---

## Section 1: Data Collection and Security

### Does your app collect or share any of the required user data types?

**Answer: YES**

**Reason:** The app collects sensor data (accelerometer readings) for its core functionality.

---

## Section 2: Data Types

### Select all data types collected or shared by your app:

#### Location
- [ ] Approximate location
- [ ] Precise location

**Not collected**

#### Personal Info
- [ ] Name
- [ ] Email address
- [ ] User IDs
- [ ] Address
- [ ] Phone number
- [ ] Race and ethnicity
- [ ] Political or religious beliefs
- [ ] Sexual orientation
- [ ] Other info

**Not collected**

#### Financial Info
- [ ] User payment info
- [ ] Purchase history
- [ ] Credit score
- [ ] Other financial info

**Not collected**

#### Health and Fitness
- [ ] Health info
- [ ] Fitness info

**Not collected**

#### Messages
- [ ] Emails
- [ ] SMS or MMS
- [ ] Other in-app messages

**Not collected**

#### Photos and Videos
- [ ] Photos
- [ ] Videos

**Not collected**

#### Audio Files
- [ ] Voice or sound recordings
- [ ] Music files
- [ ] Other audio files

**Not collected**

#### Files and Docs
- [ ] Files and docs

**Not collected**

#### Calendar
- [ ] Calendar events

**Not collected**

#### Contacts
- [ ] Contacts

**Not collected**

#### App Activity
- [x] **App interactions** ← SELECT THIS
- [ ] In-app search history
- [ ] Installed apps
- [ ] Other user-generated content
- [ ] Other actions

**Collected: App interactions (sensor data from accelerometer)**

**Details for "App interactions":**
- Data collected: Accelerometer sensor readings (X, Y, Z axis values and magnitude)
- Purpose: App functionality - vibration measurement and recording
- Collected: Required for app functionality
- Shared: Not shared with third parties
- Processing: Data processed locally on device only

#### Web Browsing
- [ ] Web browsing history

**Not collected**

#### App Info and Performance
- [ ] Crash logs
- [ ] Diagnostics
- [ ] Other app performance data

**Not collected** (no crash reporting or analytics integrated)

#### Device or Other IDs
- [ ] Device or other IDs

**Not collected** (no analytics, no advertising IDs, no device identifiers collected)

---

## Section 3: Data Usage and Handling (for "App interactions")

For the selected data type "App interactions" (accelerometer data), answer these questions:

### Is this data collected, shared, or both?

**Answer: Collected**

- [x] Collected
- [ ] Shared

**Explanation:** Data is only collected and stored locally. It is never shared with third parties.

---

### Is this data processed ephemerally?

**Answer: NO**

**Explanation:** Data is stored persistently in the device's local database (Room) until the user manually deletes it.

---

### Is collection of this data required for your app, or can users choose whether it's collected?

**Answer: Required**

- [x] Data collection is required (users can't turn off this data collection)

**Explanation:** Accelerometer data collection is essential for the app's core functionality. The app cannot measure vibrations without accessing the accelerometer sensor.

---

### Why is this user data collected? Select all that apply:

- [x] **App functionality** ← SELECT THIS
- [ ] Analytics
- [ ] Developer communications
- [ ] Advertising or marketing
- [ ] Fraud prevention, security, and compliance
- [ ] Personalization
- [ ] Account management

**Explanation:** The data is used solely for the app's core functionality - measuring and recording vibration data.

---

## Section 4: Data Security

### Do you use encryption for user data in transit?

**Answer: NO**

**Explanation:** Data is never transmitted over the network. All data remains on the device, so encryption in transit is not applicable.

**Note for form:** If the form requires a YES/NO and penalizes NO, you can clarify: "Not applicable - no data transmission occurs."

---

### Do you use encryption for user data at rest?

**Answer: YES** (via device encryption)

**Explanation:** Data is stored in a Room database on the device. The data benefits from Android's device encryption (if enabled by the user). Modern Android devices encrypt data at rest by default.

**Note:** Select YES and explain that device-level encryption is used.

---

### Can users request that their data be deleted?

**Answer: YES**

**Explanation:** Users can delete individual measurement sessions through the app interface at any time. They can also delete all app data by:
1. Uninstalling the app
2. Clearing app data in device settings
3. Deleting individual sessions within the app

**Implementation:** Data deletion is available through the session detail screen and history screen.

---

## Section 5: Additional Information

### Will you update your privacy policy to reflect the data collection and handling practices described in this form?

**Answer: YES**

**Privacy Policy URL:** [TODO: Add your privacy policy URL here after publishing]

Example: `https://yourusername.github.io/vibrationmeter/privacy-policy-html/`

---

## Summary for Quick Reference

| Question | Answer | Notes |
|----------|--------|-------|
| Collects or shares data? | YES | Only collects, doesn't share |
| Data type | App interactions | Accelerometer sensor data |
| Collected or shared? | Collected only | Not shared with third parties |
| Ephemeral? | NO | Stored persistently until user deletes |
| Required or optional? | Required | Essential for app functionality |
| Purpose | App functionality | Vibration measurement |
| Encrypted in transit? | NO / N/A | No network transmission |
| Encrypted at rest? | YES | Device-level encryption |
| User can delete? | YES | Via app interface or uninstall |
| Privacy policy updated? | YES | Must provide URL |

---

## Important Notes

1. **Be Accurate:** Incorrect data safety disclosures can result in app suspension or removal from Play Store.

2. **No Analytics or Tracking:** This disclosure assumes no analytics, crash reporting, or third-party SDKs are integrated. If you add these later, you MUST update the data safety form.

3. **Local Only:** The key differentiator is that ALL data is local. Emphasize this in any "Additional details" fields.

4. **Privacy Policy Required:** You must have a publicly accessible privacy policy and provide the URL in the form.

5. **Update When Changing:** If you add any new data collection (analytics, ads, cloud sync, etc.), you must immediately update the data safety form.

---

## Common Questions

**Q: Does accessing the accelerometer sensor count as data collection?**
A: Yes, even though it's a hardware sensor, the data collected from it must be disclosed.

**Q: If data never leaves the device, do I need to disclose it?**
A: Yes, any data collected, stored, or processed must be disclosed, even if it stays local.

**Q: What if I add Firebase Crashlytics later?**
A: You must update the data safety form to include "App info and performance" → "Crash logs" and specify that it's shared with Google.

**Q: Should I mention Room database?**
A: You don't need to specify the technical implementation (Room), just that data is stored locally and encrypted at rest.

---

## Filling Out the Form - Step by Step

1. **Navigate to:** Play Console → Your App → Policy → App content → Data safety
2. **Click:** Start
3. **Question 1:** Does your app collect or share any of the required user data types?
   - Select: **YES**
4. **Question 2:** Select data types:
   - Under "App activity": Check **App interactions**
5. **For "App interactions":**
   - Collected or shared: **Collected**
   - Is this data processed ephemerally: **NO**
   - Is data collection required: **Required**
   - Why is it collected: **App functionality**
6. **Data security:**
   - Encrypted in transit: **NO** (or explain not applicable)
   - Encrypted at rest: **YES**
   - Users can request deletion: **YES**
7. **Privacy policy:**
   - Provide the public URL
8. **Review and submit**

---

## After Submission

- Google will review your disclosure
- It may take 1-3 days for approval
- Users will see a data safety section on your app's Play Store listing
- You can edit the form anytime, but changes require re-review

---

**End of Data Safety Disclosure Document**
