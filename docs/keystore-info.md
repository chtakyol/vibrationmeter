# Keystore Information

**IMPORTANT: This document does NOT contain passwords. Store passwords separately in a secure location.**

## Keystore Details

- **Keystore File:** `~/vibrationmeter-release.jks` (stored outside repository)
- **Keystore Type:** JKS (Java KeyStore)
- **Key Alias:** vibrationmeter
- **Key Algorithm:** RSA
- **Key Size:** 2048 bits
- **Validity:** 10,000 days (approximately 27 years)
- **Creation Date:** December 3, 2025
- **Valid Until:** April 20, 2053

## Certificate Information

**Owner/Issuer:**
- CN=Cihat Akyol
- OU=Development
- O=Vibration Meter
- L=Istanbul
- ST=Istanbul
- C=TR

**Serial Number:** 7687e6b145d89384

## Certificate Fingerprints

**SHA-1:** 48:88:98:F8:51:BB:10:3D:40:95:1F:6C:FE:F8:E1:AA:61:E9:5D:85

**SHA-256:** F1:85:9C:B5:87:97:17:5A:B3:3A:2B:B5:12:18:F5:24:01:BC:D8:BB:BB:D6:EE:14:A1:57:C4:1A:94:59:2D:B9

## Backup Locations

**IMPORTANT: Create backups in multiple secure locations:**

1. **Primary:** `~/vibrationmeter-release.jks`
2. **Backup 1:** [TODO: Specify secure backup location - encrypted USB drive]
3. **Backup 2:** [TODO: Specify secure backup location - encrypted cloud storage]
4. **Backup 3:** [TODO: Specify secure backup location - offline storage]

## Security Notes

- **NEVER** commit the keystore file to version control
- **NEVER** share the keystore passwords via email or unsecured channels
- Store passwords in a password manager (1Password, LastPass, etc.)
- Keep multiple backups in different physical locations
- If keystore is lost, you cannot update the app on Google Play Store
- Treat this keystore as critically important for the lifetime of the app

## Password Storage

Passwords are stored in:
- `local.properties` file (excluded from git) for local builds
- Environment variables for CI/CD pipelines
- Secure password manager for team access

## Required Passwords

The keystore requires two passwords:
1. **Store Password:** Used to access the keystore file
2. **Key Password:** Used to access the specific key (alias: vibrationmeter)

**Note:** For this keystore, both passwords were set to the same value during creation.

## Usage in Build

The keystore is referenced in `app/build.gradle.kts` via the signing configuration, which reads credentials from `local.properties` or environment variables.

## Google Play App Signing

When enrolling in Google Play App Signing:
- This keystore becomes the "upload key"
- Google manages the actual "app signing key" used for distribution
- You'll upload this keystore's certificate to Google Play Console
- Future updates must be signed with this upload key

## Regeneration

If the keystore is lost and you need to create a new one, you'll need to:
1. Contact Google Play support for key reset (only possible once, with strict verification)
2. OR create a new app listing with a different package name

**Prevention is critical - backup regularly!**
