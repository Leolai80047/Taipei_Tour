package com.leodemo.taipei_tour.viewModel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leodemo.taipei_tour.utils.Event

abstract class BaseViewModel : ViewModel() {
    protected var _alertDialog: MutableLiveData<Event<String>> = MutableLiveData<Event<String>>()
    val alertDialog: LiveData<Event<String>> = _alertDialog
}