package com.tapmobile.tapyoutube.features.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tapmobile.youtube.databinding.ItemYoutubeVideoBinding
import com.tapmobile.tapyoutube.features.search.domain.models.YoutubeVideoDto

class YoutubeVideoAdapter : ListAdapter<YoutubeVideoAdapter.YoutubeVideoItem, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return YoutubeVideoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as YoutubeVideoViewHolder).bind(getItem(position))
    }

    private class YoutubeVideoViewHolder(private val binding: ItemYoutubeVideoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: YoutubeVideoItem) {

        }

        companion object {
            fun create(parent: ViewGroup) =
                YoutubeVideoViewHolder(ItemYoutubeVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

    }

    data class YoutubeVideoItem(
        val youtubeVideoModel: YoutubeVideoDto,
    )

    private companion object {
        val diffCallback = object : DiffUtil.ItemCallback<YoutubeVideoItem>() {
            override fun areItemsTheSame(oldItem: YoutubeVideoItem, newItem: YoutubeVideoItem): Boolean {
                return oldItem.youtubeVideoModel.id == newItem.youtubeVideoModel.id
            }

            override fun areContentsTheSame(oldItem: YoutubeVideoItem, newItem: YoutubeVideoItem): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }

}