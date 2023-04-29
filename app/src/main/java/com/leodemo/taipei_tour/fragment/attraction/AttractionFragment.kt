package com.leodemo.taipei_tour.fragment.attraction

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.leodemo.taipei_tour.R
import com.leodemo.taipei_tour.databinding.FragmentAttractionBinding
import com.leodemo.taipei_tour.dialog.AlertDialog
import com.leodemo.taipei_tour.dialog.TranslateOptionDialog
import com.leodemo.taipei_tour.fragment.attraction.adapter.AttractionAdapter
import com.leodemo.taipei_tour.fragment.base.BaseFragment
import com.leodemo.taipei_tour.utils.EventObserver
import com.leodemo.taipei_tour.viewModel.attraction.AttractionViewModel
import com.leodemo.taipei_tour.viewModel.main.MainViewModel

class AttractionFragment : BaseFragment<FragmentAttractionBinding, AttractionViewModel>() {
    override val viewModel: AttractionViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()
    override val layoutId = R.layout.fragment_attraction

    private var attractionAdapter: AttractionAdapter? = AttractionAdapter()
    private var translateOptionDialog: TranslateOptionDialog? = null
    private var alertDialog: AlertDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        attractionAdapter = null
        translateOptionDialog = null
        alertDialog = null
    }

    override fun initToolbar() {
        binding.toolbar.tvTitle.text = getString(R.string.app_name)
        binding.toolbar.ivRight.apply {
            setImageResource(R.drawable.ic_translate)
            isVisible = true
            setOnClickListener {
                translateOptionDialog = TranslateOptionDialog(requireActivity()) { language ->
                    if (language == activityViewModel.lastLanguage) return@TranslateOptionDialog
                    fetchAttraction(language)
                    binding.rvAttraction.scrollToPosition(0)
                    binding.rvAttraction.isVisible = false
                }
                translateOptionDialog?.show()
            }
        }
    }

    private fun initView() {
        binding.rvAttraction.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(10)
            adapter = attractionAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            addItemDecoration(
                DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL)
            )
        }
    }

    private fun initObserver() {
        activityViewModel.attractionList.observe(viewLifecycleOwner) { list ->
            attractionAdapter?.apply {
                stopShimmer()
                submit(list)
                setOnItemClick { data ->
                    activityViewModel.selectAttractionData.value = data
                    findNavController().navigate(R.id.attractionDetailFragment)
                }
            }
            binding.rvAttraction.isVisible = true
        }

        activityViewModel.alertDialog.observe(viewLifecycleOwner, EventObserver { message ->
            alertDialog = AlertDialog(requireActivity()) {
                alertDialog
                    ?.setDescription(message)
                    ?.setConfirmClick {
                        binding.rvAttraction.isVisible = true
                        stopShimmer()
                        alertDialog?.dismiss()
                    }
            }
            alertDialog?.show()
        })
    }

    private fun startShimmer() {
        binding.shimmerAttraction.startShimmer()
        binding.shimmerAttraction.isVisible = true
    }

    private fun stopShimmer() {
        binding.shimmerAttraction.stopShimmer()
        binding.shimmerAttraction.isVisible = false
    }

    private fun fetchAttraction(language: String) {
        startShimmer()
        activityViewModel.fetchAttraction(language)
    }
}