package com.leodemo.taipei_tour.activity.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import com.leodemo.taipei_tour.R
import com.leodemo.taipei_tour.activity.base.BaseActivity
import com.leodemo.taipei_tour.databinding.ActivityMainBinding
import com.leodemo.taipei_tour.viewModel.main.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()
    override val layoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
}