package com.leodemo.taipei_tour.ui.activity.base

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.leodemo.taipei_tour.di.HiltBaseActivity
import com.leodemo.taipei_tour.utils.LocaleUtil

abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : HiltBaseActivity() {
    var binding: T? = null
        private set

    abstract val viewModel: V
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    override fun attachBaseContext(newBase: Context) {
        applyOverrideConfiguration(LocaleUtil.getLocalizeConfiguration(newBase))
        super.attachBaseContext(newBase)
    }
}