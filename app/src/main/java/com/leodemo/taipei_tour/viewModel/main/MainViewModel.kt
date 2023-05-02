package com.leodemo.taipei_tour.viewModel.main

import androidx.lifecycle.MutableLiveData
import com.leodemo.taipei_tour.data.api.AttractionResponse
import com.leodemo.taipei_tour.domain.GetLastLanguageUseCase
import com.leodemo.taipei_tour.domain.SetLastLanguageUseCase
import com.leodemo.taipei_tour.utils.Event
import com.leodemo.taipei_tour.viewModel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val setLastLanguageUseCase: SetLastLanguageUseCase,
    getLastLanguageUseCase: GetLastLanguageUseCase
) : BaseViewModel() {

    val selectAttractionData = MutableLiveData<AttractionResponse.Data>()

    val restartActivity = MutableLiveData<Event<Boolean>>()

    var lastLanguage: String = getLastLanguageUseCase()
        set(value) {
            setLastLanguageUseCase(value)
            field = value
        }

    val apiHandler = CoroutineExceptionHandler { _, e ->
        _alertDialog.postValue(Event(e.message ?: e.stackTrace.toString()))
    }
}