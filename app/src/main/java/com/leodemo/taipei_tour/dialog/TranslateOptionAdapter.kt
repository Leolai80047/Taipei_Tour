package com.leodemo.taipei_tour.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leodemo.taipei_tour.databinding.ItemTranslateLanguageBinding

class TranslateOptionAdapter(
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<TranslateOptionAdapter.TranslateOptionViewHolder>() {

    private val languageList = listOf(
        "zh-tw",
        "zh-cn",
        "en",
        "ja",
        "ko",
        "es",
        "id",
        "th",
        "vi"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslateOptionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTranslateLanguageBinding.inflate(inflater, parent, false)
        return TranslateOptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TranslateOptionViewHolder, position: Int) {
        holder.bind(languageList[position])
    }

    override fun getItemCount() = languageList.size

    inner class TranslateOptionViewHolder(private val binding: ItemTranslateLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.clLanguage.setOnClickListener {
                onItemClick(languageList[adapterPosition])
            }
        }

        fun bind(language: String) {
            binding.tvLanguage.text = language
        }
    }
}