package com.honorassistant.app.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.honorassistant.app.databinding.FragmentHomeBinding
import com.honorassistant.app.service.OverlayService

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toggleTracker.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(requireContext())) {
                    // Ask for permission
                    val intent = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:${requireContext().packageName}")
                    )
                    startActivity(intent)
                    binding.toggleTracker.isChecked = false
                    Toast.makeText(requireContext(), "请开启悬浮窗权限后重试", Toast.LENGTH_LONG).show()
                } else {
                    startOverlayService()
                    Toast.makeText(requireContext(), "实时追踪已开启", Toast.LENGTH_SHORT).show()
                }
            } else {
                stopOverlayService()
                Toast.makeText(requireContext(), "实时追踪已关闭", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startOverlayService() {
        val intent = Intent(requireContext(), OverlayService::class.java).apply {
            action = OverlayService.ACTION_SHOW
        }
        requireContext().startService(intent)
    }

    private fun stopOverlayService() {
        val intent = Intent(requireContext(), OverlayService::class.java).apply {
            action = OverlayService.ACTION_HIDE
        }
        requireContext().startService(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
