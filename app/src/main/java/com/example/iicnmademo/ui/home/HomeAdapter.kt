package com.example.iicnmademo.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.iicnmademo.R
import com.example.iicnmademo.domain.model.Record
import com.example.iicnmademo.databinding.ListItemHomeBinding

class HomeAdapter(
    private val onItemClick: (Record) -> Unit,
) : PagingDataAdapter<Record, HomeItemViewHolder>(MOVIE_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder =
        HomeItemViewHolder.create(parent, onItemClick)

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Record>() {
            override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean =
                oldItem == newItem
        }
    }
}

class HomeItemViewHolder(
    private val binding: ListItemHomeBinding,
    private val onItemClick: (Record) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var _record: Record? = null

    init {
        with(binding) {
            root.setOnClickListener {
                _record?.let {
                    onItemClick.invoke(it)
                }
            }
        }
    }

    fun onBind(record: Record) = with(binding) {
        _record = record
        titleTextView.text = record.title
        Glide.with(root.context)
            .load(record.primaryimageurl)
            .placeholder(R.drawable.ic_place_holder_24dp)
            .into(posterImageView)
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClick: (Record) -> Unit
        ) =
            HomeItemViewHolder(
                ListItemHomeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                onItemClick
            )
    }
}