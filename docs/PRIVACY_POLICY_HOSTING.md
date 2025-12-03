# Privacy Policy Hosting Instructions

This document explains how to host the Vibration Meter privacy policy publicly for Google Play Store submission.

## Files Created

- `docs/privacy-policy.md` - Markdown version (for documentation)
- `docs/privacy-policy-html/index.html` - HTML version (for public hosting)

## Hosting Options

### Option 1: GitHub Pages (Recommended - Free & Easy)

GitHub Pages is the easiest and most common solution for hosting privacy policies.

**Steps:**

1. **Push this repository to GitHub** (if not already done)
   ```bash
   # If you don't have a remote yet
   git remote add origin https://github.com/YOUR_USERNAME/vibrationmeter.git

   # Commit the privacy policy files
   git add docs/
   git commit -m "Add privacy policy for Play Store"
   git push -u origin main
   ```

2. **Enable GitHub Pages:**
   - Go to your repository on GitHub
   - Navigate to **Settings** > **Pages**
   - Under **Source**, select **Deploy from a branch**
   - Choose branch: `main` (or your default branch)
   - Choose folder: `/docs`
   - Click **Save**

3. **Access your privacy policy:**
   - After a few minutes, your privacy policy will be available at:
   - `https://YOUR_USERNAME.github.io/vibrationmeter/privacy-policy-html/`

4. **Use this URL in Play Console:**
   - Copy the full URL and paste it into the Google Play Console Privacy Policy field

**Advantages:**
- ✅ Free
- ✅ Easy to setup
- ✅ Automatic updates when you push changes
- ✅ Reliable hosting
- ✅ HTTPS by default

### Option 2: Google Sites (Free, No Coding Required)

**Steps:**

1. Go to https://sites.google.com
2. Click **Create new site**
3. Choose a template or start blank
4. Copy the content from `docs/privacy-policy-html/index.html` or `docs/privacy-policy.md`
5. Paste and format the content in Google Sites
6. Click **Publish**
7. Choose a custom URL (e.g., vibration-meter-privacy)
8. Use the published URL in Play Console

**Advantages:**
- ✅ Free
- ✅ Very easy, no technical knowledge required
- ✅ Google ecosystem integration
- ✅ Can edit directly in browser

### Option 3: Custom Domain / Personal Website

If you have your own website or domain:

**Steps:**

1. Upload `docs/privacy-policy-html/index.html` to your web server
2. Make it accessible at a URL like: `https://yourdomain.com/vibration-meter/privacy-policy.html`
3. Use this URL in Play Console

**Advantages:**
- ✅ Professional appearance
- ✅ Complete control
- ✅ Can match your branding

**Disadvantages:**
- ❌ Requires owning a domain and hosting
- ❌ Additional cost

### Option 4: Firebase Hosting (Free Tier Available)

**Steps:**

1. Install Firebase CLI: `npm install -g firebase-tools`
2. Login: `firebase login`
3. Initialize: `firebase init hosting`
4. Copy `index.html` to the `public/` directory
5. Deploy: `firebase deploy --only hosting`
6. Use the provided URL in Play Console

**Advantages:**
- ✅ Free tier available
- ✅ Fast CDN delivery
- ✅ Easy integration with other Firebase services if you add them later

## Recommended Approach

**For this project, we recommend GitHub Pages** because:
1. The code is already in a git repository
2. It's completely free
3. Setup takes less than 5 minutes
4. Automatic updates when you update the policy
5. Professional and reliable

## After Publishing

1. **Test the URL:**
   - Visit the URL in a browser
   - Verify the privacy policy displays correctly
   - Test on mobile to ensure responsive design works

2. **Add to Play Console:**
   - Copy the full public URL
   - Go to Google Play Console > Your App > Policy > Privacy Policy
   - Paste the URL
   - Save

3. **Document the URL:**
   - Add the URL to `docs/keystore-info.md` or create a separate reference document
   - Example: "Privacy Policy URL: https://username.github.io/vibrationmeter/privacy-policy-html/"

4. **(Optional) Add to App:**
   - Consider adding a link to the privacy policy in your app's Settings screen
   - This is good practice and appreciated by users

## Updating the Privacy Policy

When you need to update the privacy policy:

1. Update `docs/privacy-policy.md` (markdown version)
2. Update `docs/privacy-policy-html/index.html` (HTML version)
3. Update the "Last Updated" date in both files
4. Commit and push to GitHub (if using GitHub Pages)
5. The changes will be live automatically within a few minutes

## Contact Email

**IMPORTANT:** Before publishing, replace `[TODO: Add support email address]` in both files with your actual support email address.

Options:
- Personal email: `your.email@gmail.com`
- Dedicated support email: `support@yourdomain.com`
- Create a new email specifically for the app: `vibrationmeter.support@gmail.com`

## Verification Checklist

Before submitting to Play Store:

- [ ] Privacy policy is publicly accessible via URL
- [ ] URL uses HTTPS (secure connection)
- [ ] Content displays correctly on desktop browsers
- [ ] Content displays correctly on mobile browsers
- [ ] Contact email is filled in (not TODO)
- [ ] "Last Updated" date is accurate
- [ ] URL is saved/documented for future reference
- [ ] URL is added to Google Play Console

## Troubleshooting

**GitHub Pages not showing:**
- Wait 5-10 minutes after enabling (initial setup takes time)
- Check repository settings to ensure Pages is enabled
- Verify the branch and folder are correct
- Ensure the files are in the correct location

**404 Error:**
- Double-check the full path in the URL
- Ensure `index.html` filename is lowercase
- Verify the file was committed and pushed to the correct branch

**Content doesn't display correctly:**
- Check browser console for errors
- Verify HTML file is valid
- Test in multiple browsers
