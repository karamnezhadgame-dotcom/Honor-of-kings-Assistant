package com.honorassistant.app.service

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.WindowManager
import com.honorassistant.app.databinding.OverlayTrackerBinding

class OverlayService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var binding: OverlayTrackerBinding
    override fun onBind(intent: Intent?): IBinder? = null
    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        binding = OverlayTrackerBinding.inflate(LayoutInflater.from(this))
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply { gravity = Gravity.TOP or Gravity.START; x = 100; y = 200 }
        binding.root.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> { params.x -= event.rawX.toInt(); params.y -= event.rawY.toInt() }
                MotionEvent.ACTION_MOVE -> { params.x += event.rawX.toInt(); params.y += event.rawY.toInt(); windowManager.updateViewLayout(binding.root, params) }
            }
            true
        }
        windowManager.addView(binding.root, params)
        HOKTrackerService.enemyCooldowns.observeForever { cooldowns ->
            binding.tvCooldowns.text = cooldowns.entries.joinToString("\n") { "${it.key}: ${it.value}s" }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        windowManager.removeView(binding.root)
    }
}
