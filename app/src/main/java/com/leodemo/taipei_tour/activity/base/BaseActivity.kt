package com.leodemo.taipei_tour.activity.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.leodemo.taipei_tour.di.HiltBaseActivity

abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : HiltBaseActivity() {
    var binding: T? = null
        private set

    abstract val viewModel: V
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }
}