package com.leodemo.taipei_tour.ui.activity.main

import android.os.Bundle
import androidx.activity.viewModels
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

    private fun recreateSmooth() {
        finish()
        startActivity(intent)
    }

    private fun initObserver() {
        viewModel.restartActivity.observe(this, EventObserver {
            recreateSmooth()
        })
    }
}