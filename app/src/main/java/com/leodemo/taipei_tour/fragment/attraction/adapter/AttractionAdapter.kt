package com.leodemo.taipei_tour.fragment.attraction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leodemo.taipei_tour.R
import com.leodemo.taipei_tour.data.api.AttractionResponse
import com.leodemo.taipei_tour.databinding.ItemAttractionBinding

class AttractionAdapter : PagingDataAdapter<AttractionResponse.Data, AttractionAdapter.AttractionViewHolder>(
    INDIFFERENCE
) {

    companion object {
        val INDIFFERENCE = object : DiffUtil.ItemCallback<AttractionResponse.Data>() {
            override fun areItemsTheSame(oldItem: AttractionResponse.Data, newItem: AttractionResponse.Data): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: AttractionResponse.Data,
                newItem: AttractionResponse.Data
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    private var onItemClick: ((data: AttractionResponse.Data) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAttractionBinding.inflate(inflater, parent, false)
        return AttractionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    fun setOnItemClick(onClick: (data: AttractionResponse.Data) -> Unit) {
        onItemClick = onClick
    }


    inner class AttractionViewHolder(private val binding: ItemAttractionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.clAttraction.setOnClickListener {
                onItemClick?.invoke(getItem(layoutPosition) ?: return@setOnClickListener)
            }
        }

        fun bind(data: AttractionResponse.Data) {
            binding.apply {
                Glide.with(root)
                    .load(data.getImage())
                    .error(R.drawable.img_not_found)
                    .placeholder(R.drawable.img_placeholder)
                    .into(ivAttraction)
                tvTitle.text = data.name
                tvOverview.text = data.introduction
            }

        }
    }
}