package com.leodemo.taipei_tour.dialog

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

abstract class BaseDialogFragment<T : ViewDataBinding>(
    protected val fragmentActivity: FragmentActivity
) : DialogFragment() {

    private var _binding: T? = null
    val binding: T
        get() = _binding ?: throw Exception("View Not Bind!")

    protected var layoutId: Int = 0

    protected var onDialogViewCreateCallback: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (layoutId > 0) {
            _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        } else {
            throw Exception("Layout ID not set!")
        }
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onDialogViewCreateCallback?.invoke()
    }

    fun show() {
        show(fragmentActivity.supportFragmentManager, "")
    }

    open fun setPercentWidth(widthPercent: Float) {
        val dm = Resources.getSystem().displayMetrics
        val rect = Rect(0,0, dm.widthPixels, dm.heightPixels)
        val width = rect.width() * widthPercent
        dialog?.window?.setLayout(width.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}