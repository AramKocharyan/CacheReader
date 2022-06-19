package com.akocharyan.ultimatearch.features

import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.akocharyan.core.platorm.BaseActivity
import com.akocharyan.ultimatearch.databinding.ActivityMainBinding
import com.akocharyan.ultimatearch.features.search.presentation.YoutubeSearchFragment

class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding: ActivityMainBinding
        get() = _binding!!

    override val fragmentContainerView: FragmentContainerView
        get() = binding.fragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment(savedInstanceState, YoutubeSearchFragment.newInstance())
    }

}
