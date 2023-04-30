package com.leodemo.taipei_tour.dialog

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.leodemo.taipei_tour.R
import com.leodemo.taipei_tour.databinding.LayoutAlertDialogBinding

class AlertDialog(
    fragmentActivity: FragmentActivity,
    viewCreatedCallback: () -> Unit
) : BaseDialogFragment<LayoutAlertDialogBinding>(fragmentActivity) {

    init {
        layoutId = R.layout.layout_alert_dialog
        onDialogViewCreateCallback = viewCreatedCallback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun setDescription(message: String): AlertDialog {
        binding.tvDescription.text = message
        return this
    }

    fun setConfirmClick(onClick: () -> Unit): AlertDialog {
        binding.tvConfirm.setOnClickListener {
            onClick()
        }
        return this
    }

    private fun initView() {
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.background_round)
        setPercentWidth(0.8f)
    }

    override fun setPercentWidth(widthPercent: Float) {
        val dm = Resources.getSystem().displayMetrics
        val rect = Rect(0, 0, dm.widthPixels, dm.heightPixels)
        val width = rect.width() * widthPercent
        dialog?.window?.setLayout(width.toInt(), fragmentActivity.resources.getDimensionPixelSize(R.dimen.dimen_250))
    }
}