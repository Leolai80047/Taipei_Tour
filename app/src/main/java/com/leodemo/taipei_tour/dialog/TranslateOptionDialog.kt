package com.leodemo.taipei_tour.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.leodemo.taipei_tour.R
import com.leodemo.taipei_tour.databinding.LayoutTranslateOptionBinding

class TranslateOptionDialog(
    currentActivity: FragmentActivity,
    onItemClick: ((String) -> Unit)
): BaseDialogFragment<LayoutTranslateOptionBinding>(currentActivity) {

    private var translateOptionAdapter: TranslateOptionAdapter? = null

    init {
        this.layoutId = R.layout.layout_translate_option
        val wrappedListener = { language: String ->
            onItemClick(language)
            dismiss()
        }
        translateOptionAdapter = TranslateOptionAdapter(wrappedListener)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        translateOptionAdapter = null
    }

    private fun initView() {
        setPercentWidth(0.8f)
        binding.rvLanguage.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(10)
            adapter = translateOptionAdapter
            layoutManager = LinearLayoutManager(fragmentActivity)
            addItemDecoration(
                DividerItemDecoration(fragmentActivity, DividerItemDecoration.VERTICAL)
            )
        }
    }
}