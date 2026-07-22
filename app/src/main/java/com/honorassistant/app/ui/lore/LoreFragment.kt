package com.honorassistant.app.ui.lore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.honorassistant.app.R
import com.honorassistant.app.databinding.FragmentLoreBinding
import com.honorassistant.app.data.datasource.LoreDataSource
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
            parentFragmentManager.commit {
                replace(R.id.nav_host_fragment, LoreDetailFragment::class.java, Bundle().apply { putString("lore_id", location.id) })
                addToBackStack(null)
            }
        }
        binding.rvLore.adapter = adapter
        adapter.submitList(LoreDataSource.provideLore())
    }
    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
