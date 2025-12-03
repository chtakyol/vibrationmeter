package com.cihatakyol.vibrationmeter.util

import androidx.compose.ui.Modifier

/**
 * Utility functions for Compose UI.
 * These functions provide plug-and-play Compose functionality.
 */

/**
 * Apply a modifier conditionally based on a boolean condition.
 *
 * @param condition Boolean condition to check
 * @param modifier Lambda that returns the Modifier to apply when condition is true
 * @return Modified Modifier if condition is true, original Modifier otherwise
 *
 * Example:
 * ```
 * Modifier
 *     .fillMaxWidth()
 *     .conditional(isSelected) {
 *         background(Color.Blue)
 *     }
 * ```
 */
fun Modifier.conditional(
    condition: Boolean,
    modifier: Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

/**
 * Apply a modifier conditionally with else branch.
 *
 * @param condition Boolean condition to check
 * @param ifTrue Lambda that returns the Modifier to apply when condition is true
 * @param ifFalse Lambda that returns the Modifier to apply when condition is false
 * @return Modified Modifier based on condition
 *
 * Example:
 * ```
 * Modifier
 *     .fillMaxWidth()
 *     .conditionalElse(
 *         condition = isSelected,
 *         ifTrue = { background(Color.Blue) },
 *         ifFalse = { background(Color.Gray) }
 *     )
 * ```
 */
fun Modifier.conditionalElse(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        then(ifTrue(Modifier))
    } else {
        then(ifFalse(Modifier))
    }
}
