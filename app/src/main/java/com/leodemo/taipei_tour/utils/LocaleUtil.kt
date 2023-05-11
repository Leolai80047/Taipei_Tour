package com.leodemo.taipei_tour.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import androidx.core.os.ConfigurationCompat
import androidx.preference.PreferenceManager
import com.leodemo.taipei_tour.BuildConfig
import com.leodemo.taipei_tour.data.local.sharePreference.SharePreferenceDataSource
import java.util.*

class LocaleUtil {

    companion object {
        private fun equal(left: Locale, right: Locale): Boolean {
            return (left.language.lowercase() == right.language.lowercase()) &&
                    (left.country.lowercase() == right.country.lowercase())
        }

        private fun getLocaleByLast(context: Context): Locale {
            val lastLanguage = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(SharePreferenceDataSource.LAST_LANGUAGE, BuildConfig.DEFAULT_LANGUAGE) ?: ""
            val lastLocale = SupportLanguage.getLocale(lastLanguage)
            val currentSystemLocale =
                ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0) ?: lastLocale
            return if (equal(lastLocale, currentSystemLocale)) {
                currentSystemLocale
            } else {
                lastLocale
            }
        }

        fun getLocalizeConfiguration(context: Context): Configuration {
            val locale = getLocaleByLast(context)
            return getLocalizeConfiguration(locale)
        }

        private fun getLocalizeConfiguration(locale: Locale): Configuration {
            val config = Configuration()
            return config.apply {
                setLayoutDirection(locale)
                setLocale(locale)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val localeList = LocaleList(locale)
                    LocaleList.setDefault(localeList)
                    setLocales(localeList)
                }
            }
        }

        fun getLocalizeContext(baseContext: Context): Context {
            val lastLocale = getLocaleByLast(baseContext)
            val baseLocale = getLocaleFromConfiguration(baseContext.resources.configuration)
            Locale.setDefault(lastLocale)
            return if (equal(lastLocale, baseLocale)) {
                baseContext
            } else {
                val config = getLocalizeConfiguration(lastLocale)
                baseContext.createConfigurationContext(config)
            }
        }

        private fun getLocaleFromConfiguration(configuration: Configuration): Locale {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                configuration.locales.get(0)
            } else {
                configuration.locale
            }
        }
    }
}