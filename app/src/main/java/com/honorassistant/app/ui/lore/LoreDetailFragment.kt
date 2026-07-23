package com.honorassistant.app.ui.lore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.honorassistant.app.data.datasource.LoreDataSource
import com.honorassistant.app.databinding.FragmentLoreDetailBinding

class LoreDetailFragment : Fragment() {

    private var _binding: FragmentLoreDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoreDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loreId = arguments?.getString("loreId") ?: return
        val location = LoreDataSource.provideLore().find { it.id == loreId } ?: return

        binding.tvLoreTitle.text = location.name
        val content = buildString {
            appendLine("【涉及英雄】")
            appendLine(location.associatedHeroes.joinToString("、"))
            appendLine()
            location.chapters.forEachIndexed { i, chapter ->
                appendLine("第${i + 1}章")
                appendLine(chapter)
                appendLine()
            }
        }
        binding.tvLoreContent.text = content
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
