package com.leodemo.taipei_tour.di

import android.app.Application
import android.content.Context
import com.leodemo.taipei_tour.utils.LocaleUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaipeiTourApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleUtil.getLocalizeContext(base))
    }
}