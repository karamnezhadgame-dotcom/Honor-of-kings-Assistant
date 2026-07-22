package com.honorassistant.app.ui.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.honorassistant.app.databinding.FragmentHeroDetailBinding
import com.honorassistant.app.data.repository.DefaultHeroRepository
import kotlinx.coroutines.launch

class HeroDetailFragment : Fragment() {
    private var _binding: FragmentHeroDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val heroId = arguments?.getString("hero_id") ?: return
        lifecycleScope.launch {
            val hero = DefaultHeroRepository(requireContext()).getHeroById(heroId)
            hero?.let {
                binding.tvDetailName.text = it.name
                binding.tvDetailTitle.text = it.title
                binding.tvDetailRole.text = it.role
                binding.tvDetailLore.text = it.lore
                binding.tvDetailDifficulty.text = "难度 ${"★".repeat(it.difficulty)}${"☆".repeat(3 - it.difficulty)}"
                Glide.with(binding.root).load(it.splashArtUrl).centerCrop().into(binding.ivDetailSplash)
                binding.llSkillsContainer.removeAllViews()
                it.skills.forEach { skill ->
                    binding.llSkillsContainer.addView(TextView(requireContext()).apply { text = "• ${skill.name}: ${skill.description}"; setPadding(0, 8, 0, 8) })
                }
                binding.llSkinsContainer.removeAllViews()
                it.skins.forEach { skin ->
                    binding.llSkinsContainer.addView(TextView(requireContext()).apply { text = "• ${skin.name} (${skin.rarity})"; setPadding(0, 8, 0, 8) })
                }
            }
        }
    }
    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
