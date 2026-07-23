package com.honorassistant.app.ui.lore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.honorassistant.app.R
import com.honorassistant.app.data.datasource.LoreDataSource
import com.honorassistant.app.databinding.FragmentLoreBinding
import com.honorassistant.app.ui.lore.adapters.LoreAdapter

class LoreFragment : Fragment() {

    private var _binding: FragmentLoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LoreAdapter { location ->
            findNavController().navigate(
                R.id.action_loreFragment_to_loreDetailFragment,
                bundleOf("loreId" to location.id)
            )
        }

        binding.rvLore.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLore.adapter = adapter
        adapter.submitList(LoreDataSource.provideLore())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
