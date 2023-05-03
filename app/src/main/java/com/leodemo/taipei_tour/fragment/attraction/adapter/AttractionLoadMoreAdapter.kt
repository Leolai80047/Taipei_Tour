package com.leodemo.taipei_tour.fragment.attraction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leodemo.taipei_tour.databinding.LayoutLoadAttractionHolderBinding

class AttractionLoadMoreAdapter : LoadStateAdapter<AttractionLoadMoreAdapter.AttractionLoadMoreViewHolder>() {

    override fun onBindViewHolder(holder: AttractionLoadMoreViewHolder, loadState: LoadState) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): AttractionLoadMoreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutLoadAttractionHolderBinding.inflate(inflater, parent, false)
        return AttractionLoadMoreViewHolder(binding)
    }

    inner class AttractionLoadMoreViewHolder(private val binding: LayoutLoadAttractionHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}