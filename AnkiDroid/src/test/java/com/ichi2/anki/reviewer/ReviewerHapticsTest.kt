// SPDX-License-Identifier: GPL-3.0-or-later
package com.ichi2.anki.reviewer

import android.content.Context
import android.view.View
import androidx.test.core.app.ApplicationProvider
import com.ichi2.anki.RobolectricTest
import com.ichi2.anki.settings.Prefs
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ReviewerHapticsTest : RobolectricTest() {
    @Test
    fun testPerformReviewHapticDoesNotThrow() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val view = View(context)

        // Null-safety check
        val nullView: View? = null
        nullView.performReviewHaptic()

        // Enabled check
        Prefs.hapticFeedbackEnabled = true
        view.performReviewHaptic()

        // Disabled check
        Prefs.hapticFeedbackEnabled = false
        view.performReviewHaptic()

        assertNotNull(view)
    }
}
