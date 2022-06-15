package com.tapmobile.tapyoutube.features

import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.tapmobile.youtube.databinding.ActivityMainBinding
import com.tapmobile.tapyoutube.core.platorm.BaseActivity
import com.tapmobile.tapyoutube.features.search.presentation.YoutubeSearchFragment

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
