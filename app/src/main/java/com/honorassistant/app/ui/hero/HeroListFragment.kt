package com.honorassistant.app.ui.hero

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.honorassistant.app.R
import com.honorassistant.app.databinding.FragmentHeroListBinding
import com.honorassistant.app.data.models.Hero
import com.honorassistant.app.ui.hero.adapters.HeroAdapter

class HeroListFragment : Fragment() {
    private var _binding: FragmentHeroListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HeroListViewModel
    private lateinit var adapter: HeroAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHeroListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(HeroListViewModel::class.java)
        adapter = HeroAdapter { hero -> navigateToDetail(hero) }
        binding.rvHeroes.adapter = adapter
        binding.rvHeroes.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        viewModel.heroes.observe(viewLifecycleOwner) { adapter.submitList(it) }
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) { viewModel.search(s?.toString() ?: "") }
        })
        viewModel.loadHeroes()
    }

    private fun navigateToDetail(hero: Hero) {
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment, HeroDetailFragment::class.java, Bundle().apply { putString("hero_id", hero.id) })
            addToBackStack(null)
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
