package com.leodemo.taipei_tour.fragment.attraction

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.leodemo.taipei_tour.R
import com.leodemo.taipei_tour.databinding.FragmentAttractionDetailBinding
import com.leodemo.taipei_tour.fragment.base.BaseFragment
import com.leodemo.taipei_tour.viewModel.attraction.AttractionDetailViewModel
import com.leodemo.taipei_tour.viewModel.main.MainViewModel

class AttractionDetailFragment : BaseFragment<FragmentAttractionDetailBinding, AttractionDetailViewModel>() {
    override val viewModel: AttractionDetailViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()
    override val layoutId = R.layout.fragment_attraction_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        activityViewModel.selectAttractionData.value = null
    }

    override fun initToolbar() {
        binding.toolbar.ivLeft.apply {
            isVisible = true
            setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}