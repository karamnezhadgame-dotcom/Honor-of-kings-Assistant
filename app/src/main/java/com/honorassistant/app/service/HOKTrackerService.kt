package com.honorassistant.app.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent

class HOKTrackerService : AccessibilityService() {

    private val honorOfKingsPackages = setOf(
        "com.levelinfinite.sgameGlobal",  // Global
        "com.tencent.tmgp.sgame"           // CN
    )

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString() ?: return
            val isHOKRunning = packageName in honorOfKingsPackages
            sendOverlayCommand(isHOKRunning)
        }
    }

    override fun onInterrupt() {
        sendOverlayCommand(false)
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
    }

    private fun sendOverlayCommand(show: Boolean) {
        val intent = Intent(this, OverlayService::class.java).apply {
            action = if (show) OverlayService.ACTION_SHOW else OverlayService.ACTION_HIDE
        }
        startService(intent)
    }
}
