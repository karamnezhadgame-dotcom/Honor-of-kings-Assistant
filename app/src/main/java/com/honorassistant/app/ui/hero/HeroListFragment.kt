package com.honorassistant.app.ui.hero

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.honorassistant.app.R
import com.honorassistant.app.databinding.FragmentHeroListBinding
import com.honorassistant.app.ui.hero.adapters.HeroAdapter

class HeroListFragment : Fragment() {

    private var _binding: FragmentHeroListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HeroListViewModel by viewModels()
    private lateinit var adapter: HeroAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHeroListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HeroAdapter { hero ->
            findNavController().navigate(
                R.id.action_heroListFragment_to_heroDetailFragment,
                bundleOf("heroId" to hero.id)
            )
        }

        binding.rvHeroes.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvHeroes.adapter = adapter

        viewModel.heroes.observe(viewLifecycleOwner) { heroes ->
            adapter.submitList(heroes)
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onSearchChanged(s.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
