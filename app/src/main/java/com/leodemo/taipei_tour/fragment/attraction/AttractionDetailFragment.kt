package com.leodemo.taipei_tour.fragment.attraction

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.leodemo.taipei_tour.R
import com.leodemo.taipei_tour.databinding.FragmentAttractionDetailBinding
import com.leodemo.taipei_tour.ext.toHtmlText
import com.leodemo.taipei_tour.fragment.base.BaseFragment
import com.leodemo.taipei_tour.fragment.webView.WebViewFragment
import com.leodemo.taipei_tour.viewModel.attraction.AttractionDetailViewModel
import com.leodemo.taipei_tour.viewModel.main.MainViewModel

class AttractionDetailFragment : BaseFragment<FragmentAttractionDetailBinding, AttractionDetailViewModel>() {
    override val viewModel: AttractionDetailViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()
    override val layoutId = R.layout.fragment_attraction_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
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

    private fun initView() {
        activityViewModel.selectAttractionData.value?.apply{
            val concatAddress =  "${getString(R.string.address)}\n${address}"
            val concatDate = "${getString(R.string.last_update_time)}\n${modified}"
            val underlineUrl = "<u>${url}</u>"
            Glide.with(binding.root)
                .load(getImage())
                .error(R.drawable.img_not_found)
                .into(binding.ivAttraction)
            binding.tvTitle.text = name
            binding.tvDescription.text = introduction.toHtmlText()
            binding.tvAddress.text = concatAddress
            binding.tvLastUpdate.text = concatDate
            binding.tvUrl.text = underlineUrl.toHtmlText()
            binding.tvUrl.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(WebViewFragment.URL, url)
                navigateWebView(bundle)
            }
        }
    }

    private fun navigateWebView(bundle: Bundle) {
        findNavController().navigate(R.id.action_attractionDetailFragment_to_webViewFragment, bundle)
    }
}