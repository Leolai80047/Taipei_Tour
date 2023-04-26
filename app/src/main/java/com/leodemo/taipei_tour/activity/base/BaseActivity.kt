package com.leodemo.taipei_tour.activity.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.leodemo.taipei_tour.di.HiltBaseActivity

abstract class BaseActivity<T: ViewBinding, V: ViewModel>: HiltBaseActivity() {
    var binding: T? = null
        private set

    abstract val viewModel: V
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }
}