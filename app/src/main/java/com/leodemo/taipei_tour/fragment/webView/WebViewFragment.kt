package com.leodemo.taipei_tour.fragment.webView

import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.leodemo.taipei_tour.R
import com.leodemo.taipei_tour.databinding.FragmentWebviewBinding
import com.leodemo.taipei_tour.fragment.base.BaseFragment
import com.leodemo.taipei_tour.viewModel.webView.WebViewViewModel

class WebViewFragment: BaseFragment<FragmentWebviewBinding, WebViewViewModel>() {
    override val viewModel: WebViewViewModel by viewModels()
    override val layoutId = R.layout.fragment_webview

    companion object{
        const val URL = "url"
    }

    override fun initToolbar() {
        binding.toolbar.ivLeft.apply {
            isVisible = true
            setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        destroyWebView()
    }

    private fun initWebView() {
        val url = arguments?.getString(URL)?: "about:blank"

        binding.webView.settings.apply {
            useWideViewPort = true
            loadWithOverviewMode = true
            defaultTextEncodingName = "utf-8"
            cacheMode = WebSettings.LOAD_NO_CACHE
        }

        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                if (request?.url != null) {
                    view?.loadUrl(request.url.toString())
                    return true
                }
                return false
            }
        }
        binding.webView.loadUrl(url)
    }

    private fun destroyWebView() {
        binding.webView.apply {
            clearHistory()
            clearCache(true)
            loadUrl("about:blank")
            onPause()
            removeAllViews()
            pauseTimers()
            destroy()
        }
    }
}