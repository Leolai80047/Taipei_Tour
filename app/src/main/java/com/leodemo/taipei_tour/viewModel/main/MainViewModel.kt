package com.leodemo.taipei_tour.viewModel.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leodemo.taipei_tour.data.api.AttractionApi
import com.leodemo.taipei_tour.data.api.AttractionResponse
import com.leodemo.taipei_tour.domain.FetchAttractionsUseCase
import com.leodemo.taipei_tour.viewModel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchAttractionsUseCase: FetchAttractionsUseCase
): BaseViewModel() {

    init {
        fetchList()
    }

    val list = MutableLiveData<List<AttractionResponse.Data>>()

    fun fetchList() {
        viewModelScope.launch {
            try {
                val dataList = fetchAttractionsUseCase()
                list.value = dataList
            } catch (e: Exception) {
                throw e
            }
        }
    }
}