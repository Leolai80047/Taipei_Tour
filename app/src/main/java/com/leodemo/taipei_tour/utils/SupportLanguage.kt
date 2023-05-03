package com.leodemo.taipei_tour.utils

import java.util.*

enum class SupportLanguage {

    ZH_TW,
    ZH_CN,
    EN,
    JA,
    KO,
    ES,
    ID,
    TH,
    VI;

    companion object {
        private fun getLanguage(language: SupportLanguage): String {
            return when (language) {
                ZH_TW -> "zh-tw"
                ZH_CN -> "zh-cn"
                EN -> "en"
                JA -> "ja"
                KO -> "ko"
                ES -> "es"
                ID -> "id"
                TH -> "th"
                VI -> "vi"
            }
        }

        fun getLocale(language: String): Locale {
            return when (language) {
                "zh-tw" -> Locale.TAIWAN
                "zh-cn" -> Locale.CHINA
                "en" -> Locale.US
                "ja" -> Locale.JAPAN
                "ko" -> Locale.KOREA
                "es" -> Locale("es", "ES")
                "id" -> Locale("in", "ID")
                "th" -> Locale("th", "TH")
                "vi" -> Locale("vi", "VN")
                else -> Locale.TAIWAN
            }
        }

        fun getSupportLanguageList(): List<String> {
            return values().map {
                getLanguage(it)
            }
        }
    }
}