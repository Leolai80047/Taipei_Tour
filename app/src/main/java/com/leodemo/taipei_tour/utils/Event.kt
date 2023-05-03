package com.leodemo.taipei_tour.utils

import androidx.lifecycle.Observer

class Event<out T>(private val content: T) {

    private var hasHandled = false

    fun getContentIfNotHandled(): T? {
        return if (hasHandled) {
            null
        } else {
            hasHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

class EventObserver<T>(private val onEventHandle: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventHandle.invoke(it)
        }
    }
}