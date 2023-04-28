package com.leodemo.taipei_tour.fragment.attraction

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leodemo.taipei_tour.R
import com.leodemo.taipei_tour.databinding.FragmentAttractionBinding
import com.leodemo.taipei_tour.fragment.attraction.adapter.AttractionAdapter
import com.leodemo.taipei_tour.fragment.base.BaseFragment
import com.leodemo.taipei_tour.viewModel.attraction.AttractionViewModel
import com.leodemo.taipei_tour.viewModel.main.MainViewModel

class AttractionFragment : BaseFragment<FragmentAttractionBinding, AttractionViewModel>() {
    override val viewModel: AttractionViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()
    override val layoutId = R.layout.fragment_attraction

    private var attractionAdapter: AttractionAdapter? = AttractionAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        attractionAdapter = null
    }

    override fun initToolbar() {
        binding.toolbar.tvTitle.text = getString(R.string.app_name)
    }

    private fun initView() {
        binding.apply {
            rvAttraction.adapter = attractionAdapter
            rvAttraction.layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun initObserver() {
        activityViewModel.attractionList.observe(viewLifecycleOwner) { list ->
            attractionAdapter?.apply {
                submit(list)
                setOnItemClick { data ->
                    activityViewModel.selectAttractionData.value = data
                    findNavController().navigate(R.id.attractionDetailFragment)
                }
            }
        }
    }
}