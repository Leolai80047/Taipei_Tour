package com.leodemo.taipei_tour.ui.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.leodemo.taipei_tour.di.HiltBaseFragment
import com.leodemo.taipei_tour.utils.LocaleUtil

abstract class BaseFragment<V : ViewDataBinding, T : ViewModel> : HiltBaseFragment() {
    private var _binding: V? = null
    val binding: V
        get() = _binding ?: throw Exception("View Not Bind!")

    abstract val viewModel: T
    abstract val layoutId: Int

    protected val localizeContext
        get() = LocaleUtil.getLocalizeContext(requireActivity())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        _binding?.lifecycleOwner = this
        _binding?.executePendingBindings()
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    open fun initToolbar() {}

}