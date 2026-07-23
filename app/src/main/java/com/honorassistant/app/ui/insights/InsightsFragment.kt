package com.honorassistant.app.ui.insights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.honorassistant.app.databinding.FragmentInsightsBinding

class InsightsFragment : Fragment() {

    private var _binding: FragmentInsightsBinding? = null
    private val binding get() = _binding!!

    // Mock meta data
    private val mockStats = listOf(
        Triple("李白", "胜率 52.3%", "选取率 18.1%"),
        Triple("花木兰", "胜率 51.0%", "选取率 14.6%"),
        Triple("诸葛亮", "胜率 53.8%", "选取率 12.4%"),
        Triple("吕布", "胜率 49.7%", "选取率 16.2%"),
        Triple("狄仁杰", "胜率 50.5%", "选取率 11.9%")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInsightsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mockStats.forEach { (name, winRate, pickRate) ->
            val row = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(24, 16, 24, 16)
            }
            val nameView = TextView(requireContext()).apply {
                text = name
                textSize = 16f
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }
            val statsView = TextView(requireContext()).apply {
                text = "$winRate  |  $pickRate"
                textSize = 13f
            }
            row.addView(nameView)
            row.addView(statsView)
            binding.llStatsList.addView(row)

            // Divider
            val divider = View(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 1
                ).also { it.setMargins(24, 0, 24, 0) }
                setBackgroundColor(0xFFDDDDDD.toInt())
            }
            binding.llStatsList.addView(divider)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
