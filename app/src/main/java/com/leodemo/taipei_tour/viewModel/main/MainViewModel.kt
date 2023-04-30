package com.leodemo.taipei_tour.viewModel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leodemo.taipei_tour.data.api.AttractionResponse
import com.leodemo.taipei_tour.domain.FetchAttractionsUseCase
import com.leodemo.taipei_tour.domain.GetLastLanguageUseCase
import com.leodemo.taipei_tour.domain.SetLastLanguageUseCase
import com.leodemo.taipei_tour.utils.Event
import com.leodemo.taipei_tour.viewModel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchAttractionsUseCase: FetchAttractionsUseCase,
    private val setLastLanguageUseCase: SetLastLanguageUseCase,
    getLastLanguageUseCase: GetLastLanguageUseCase
) : BaseViewModel() {

    val selectAttractionData = MutableLiveData<AttractionResponse.Data>()

    private var _attractionList = MutableLiveData<List<AttractionResponse.Data>>()
    val attractionList: LiveData<List<AttractionResponse.Data>> = _attractionList

    val restartActivity = MutableLiveData<Event<Boolean>>()

    var lastLanguage: String = getLastLanguageUseCase()
        private set(value) {
            setLastLanguageUseCase(value)
            field = value
        }

    init {
        fetchAttraction(lastLanguage)
    }

    fun fetchAttraction(language: String) {
        lastLanguage = language
        viewModelScope.launch {
            try {
                val dataList = fetchAttractionsUseCase(language)
                _attractionList.value = dataList
            } catch (e: Throwable) {
                _alertDialog.postValue(Event(e.message ?: e.stackTrace.toString()))
            }
        }
    }

}