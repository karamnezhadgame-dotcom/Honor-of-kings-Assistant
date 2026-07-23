package com.honorassistant.app.ui.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.honorassistant.app.data.repository.DefaultHeroRepository
import com.honorassistant.app.databinding.FragmentHeroDetailBinding
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

        val heroId = arguments?.getString("heroId") ?: return
        val repository = DefaultHeroRepository(requireContext())

        lifecycleScope.launch {
            val hero = repository.getHeroById(heroId) ?: return@launch

            binding.tvHeroName.text = hero.name
            binding.tvHeroTitle.text = hero.title
            binding.tvHeroRole.text = "职业：${hero.role}"
            binding.tvHeroDifficulty.text = "难度：${"★".repeat(hero.difficulty)}${"☆".repeat(3 - hero.difficulty)}"
            binding.tvHeroLore.text = hero.lore

            Glide.with(binding.ivHeroSplash)
                .load(hero.splashArtUrl)
                .placeholder(android.R.color.darker_gray)
                .centerCrop()
                .into(binding.ivHeroSplash)

            // Populate skills
            binding.llSkills.removeAllViews()
            hero.skills.forEach { skill ->
                val skillView = LayoutInflater.from(requireContext())
                    .inflate(com.honorassistant.app.R.layout.layout_skill_item, binding.llSkills, false)
                skillView.findViewById<TextView>(com.honorassistant.app.R.id.tv_skill_name).text = skill.name
                skillView.findViewById<TextView>(com.honorassistant.app.R.id.tv_skill_desc).text = skill.description
                skillView.findViewById<TextView>(com.honorassistant.app.R.id.tv_skill_cooldown).text = "冷却：${skill.cooldown}"
                binding.llSkills.addView(skillView)
            }

            // Populate skins
            binding.llSkins.removeAllViews()
            hero.skins.forEach { skin ->
                val tv = TextView(requireContext()).apply {
                    text = "• ${skin.name}（${skin.tier}）"
                    textSize = 14f
                    setPadding(8, 4, 8, 4)
                }
                binding.llSkins.addView(tv)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
