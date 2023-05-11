package com.leodemo.taipei_tour.ui.activity.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.leodemo.taipei_tour.R
import com.leodemo.taipei_tour.databinding.ActivityMainBinding
import com.leodemo.taipei_tour.ui.activity.base.BaseActivity
import com.leodemo.taipei_tour.utils.EventObserver
import com.leodemo.taipei_tour.viewModel.main.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()
    override val layoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    private fun recreateFragment() {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.popBackStack(R.id.attractionFragment, true)
        navController.navigate(R.id.attractionFragment)
    }

    private fun initObserver() {
        viewModel.restartActivity.observe(this, EventObserver {
            recreateFragment()
        })
    }
}