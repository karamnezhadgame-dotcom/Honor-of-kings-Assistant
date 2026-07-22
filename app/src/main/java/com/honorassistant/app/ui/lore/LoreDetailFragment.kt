package com.honorassistant.app.ui.lore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.honorassistant.app.databinding.FragmentLoreDetailBinding
import com.honorassistant.app.data.datasource.LoreDataSource

class LoreDetailFragment : Fragment() {
    private var _binding: FragmentLoreDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoreDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString("lore_id") ?: return
        LoreDataSource.provideLore().firstOrNull { it.id == id }?.let {
            binding.tvLoreTitle.text = it.name
            binding.tvLoreContent.text = it.loreChapters.joinToString("\n\n")
        }
    }
    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
