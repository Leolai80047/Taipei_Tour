package com.leodemo.taipei_tour.ui.fragment.attraction

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.leodemo.taipei_tour.R
import com.leodemo.taipei_tour.databinding.FragmentAttractionBinding
import com.leodemo.taipei_tour.ui.dialog.AlertDialog
import com.leodemo.taipei_tour.ui.dialog.TranslateOptionDialog
import com.leodemo.taipei_tour.ui.fragment.attraction.adapter.AttractionAdapter
import com.leodemo.taipei_tour.ui.fragment.attraction.adapter.AttractionLoadMoreAdapter
import com.leodemo.taipei_tour.ui.fragment.base.BaseFragment
import com.leodemo.taipei_tour.utils.Event
import com.leodemo.taipei_tour.utils.EventObserver
import com.leodemo.taipei_tour.viewModel.attraction.AttractionViewModel
import com.leodemo.taipei_tour.viewModel.main.MainViewModel
import kotlinx.coroutines.launch

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
                    activityViewModel.lastLanguage = language
                    activityViewModel.restartActivity.value = Event(true)
                }
                translateOptionDialog?.show()
            }
        }
        binding.toolbar.tvTitle.text = localizeContext.getString(R.string.app_name)
    }

    private fun initView() {

        attractionAdapter?.apply {
            addLoadStateListener { loadState ->
                handlePagingError(loadState)
                if (itemCount != 0 && loadState.refresh is LoadState.NotLoading) {
                    stopShimmer()
                }
            }
            setOnItemClick { data ->
                activityViewModel.selectAttractionData.value = data
                findNavController().navigate(R.id.action_attractionFragment_to_attractionDetailFragment)
            }
        }

        binding.rvAttraction.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(10)
            adapter = attractionAdapter?.withLoadStateFooter(AttractionLoadMoreAdapter())
            layoutManager = LinearLayoutManager(requireActivity())
        }

        binding.srlAttraction.setOnRefreshListener {
            attractionAdapter?.refresh()
        }
    }

    private fun initObserver() {
        activityViewModel.alertDialog.observe(viewLifecycleOwner, EventObserver { message ->
            alertDialog = AlertDialog(requireActivity()) {
                alertDialog
                    ?.setDescription(message)
                    ?.setConfirmClick {
                        stopShimmer()
                        alertDialog?.dismiss()
                    }
            }
            alertDialog?.show()
        })

        lifecycleScope.launch(activityViewModel.apiHandler) {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.attractionPagingData
                    .collect {
                        attractionAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
                    }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                attractionAdapter?.loadStateFlow?.collect {
                    binding.srlAttraction.isRefreshing =
                        it.refresh is LoadState.Loading && binding.shimmerAttraction.isGone
                }
            }
        }

    }

    private fun stopShimmer() {
        binding.shimmerAttraction.stopShimmer()
        binding.shimmerAttraction.isVisible = false
    }

    private fun handlePagingError(loadState: CombinedLoadStates) {
        val errorState = when {
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            else -> null
        }

        errorState?.let {
            val error = errorState.error
            activityViewModel.showAlert(error.message ?: error.stackTraceToString())
        }
    }
}