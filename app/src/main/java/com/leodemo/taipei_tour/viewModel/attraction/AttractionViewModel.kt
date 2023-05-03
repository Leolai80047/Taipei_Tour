package com.leodemo.taipei_tour.viewModel.attraction

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.leodemo.taipei_tour.domain.GetAttractionPagingUseCase
import com.leodemo.taipei_tour.viewModel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AttractionViewModel @Inject constructor(
    getAttractionPagingUseCase: GetAttractionPagingUseCase
) : BaseViewModel() {

    val attractionPagingData = getAttractionPagingUseCase().cachedIn(viewModelScope)
}