// SPDX-License-Identifier: GPL-3.0-or-later
package com.ichi2.anki.reviewer

import android.view.HapticFeedbackConstants
import android.view.View
import com.ichi2.anki.compat.CompatHelper
import com.ichi2.anki.compat.USAGE_TOUCH
import com.ichi2.anki.settings.Prefs
import kotlin.time.Duration.Companion.milliseconds

/**
 * Triggers crisp haptic feedback for card flip and answer events.
 * Respects [Prefs.hapticFeedbackEnabled].
 */
fun View?.performReviewHaptic() {
    if (this == null || !Prefs.hapticFeedbackEnabled) return
    try {
        val performed =
            performHapticFeedback(
                HapticFeedbackConstants.KEYBOARD_TAP,
                HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING,
            )
        if (!performed) {
            val fallback = performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            if (!fallback) {
                CompatHelper.compat.vibrate(context, 15.milliseconds, USAGE_TOUCH)
            }
        }
    } catch (_: Exception) {
        try {
            CompatHelper.compat.vibrate(context, 15.milliseconds, USAGE_TOUCH)
        } catch (_: Exception) {
            // Ignored
        }
    }
}
