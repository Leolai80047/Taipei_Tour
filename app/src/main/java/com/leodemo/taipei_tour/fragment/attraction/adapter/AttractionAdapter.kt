package com.leodemo.taipei_tour.fragment.attraction.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leodemo.taipei_tour.R
import com.leodemo.taipei_tour.data.api.AttractionResponse
import com.leodemo.taipei_tour.databinding.ItemAttractionBinding

class AttractionAdapter : RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder>() {

    private var dataList = emptyList<AttractionResponse.Data>()
    private var onItemClick: ((data: AttractionResponse.Data) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAttractionBinding.inflate(inflater, parent, false)
        return AttractionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    @SuppressLint("NotifyDataSetChanged")
    fun submit(list: List<AttractionResponse.Data>) {
        dataList = list
        notifyDataSetChanged()
    }

    fun setOnItemClick(onClick: (data: AttractionResponse.Data) -> Unit) {
        onItemClick = onClick
    }


    inner class AttractionViewHolder(private val binding: ItemAttractionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.clAttraction.setOnClickListener {
                onItemClick?.invoke(dataList[adapterPosition])
            }
        }

        fun bind(data: AttractionResponse.Data) {
            binding.apply {
                Glide.with(root)
                    .load(data.getImage())
                    .error(R.drawable.ic_launcher_background)
                    .into(ivAttraction)
                tvTitle.text = data.name
                tvOverview.text = data.introduction
            }

        }
    }
}