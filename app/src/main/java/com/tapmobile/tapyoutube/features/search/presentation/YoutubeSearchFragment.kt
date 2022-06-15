package com.tapmobile.tapyoutube.features.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.akocharyan.ultimatearch.databinding.FragmentYoutubeVideoSearchBinding
import com.tapmobile.tapyoutube.core.platorm.BaseFragment

class YoutubeSearchFragment : BaseFragment() {

    private var _binding: FragmentYoutubeVideoSearchBinding? = null

    private val binding: FragmentYoutubeVideoSearchBinding
        get() = _binding!!

    private val viewModel by viewModels<YoutubeSearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) return

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentYoutubeVideoSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = YoutubeVideoAdapter()
        binding.recyclerView.adapter = adapter
        setupSearchView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchVideo(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    companion object {
        fun newInstance() = YoutubeSearchFragment()
    }

}
