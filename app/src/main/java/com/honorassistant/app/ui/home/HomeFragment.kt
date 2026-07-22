package com.honorassistant.app.ui.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                requireContext().startService(Intent(requireContext(), OverlayService::class.java))
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            } else {
                requireContext().stopService(Intent(requireContext(), OverlayService::class.java))
            }
        }
    }
    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
