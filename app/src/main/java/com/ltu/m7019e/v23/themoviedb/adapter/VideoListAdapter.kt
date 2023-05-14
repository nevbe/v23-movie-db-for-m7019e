package com.ltu.m7019e.v23.themoviedb.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ltu.m7019e.v23.themoviedb.R
import com.ltu.m7019e.v23.themoviedb.databinding.VideoListItemBinding
import com.ltu.m7019e.v23.themoviedb.model.Video
import com.ltu.m7019e.v23.themoviedb.utils.Constants

class VideoListAdapter :  ListAdapter<Video, VideoListAdapter.ViewHolder>(VideoListDiffCallback()){
    class ViewHolder(private var binding: VideoListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val videoView = binding.movieVid

        fun bind(video: Video) {
            binding.video = video
            binding.executePendingBindings()
        }

        @SuppressLint("SetJavaScriptEnabled")
        fun start(video: Video) {
            val url = if (video.site == "YouTube") {
                Constants.YOUTUBE_VID_BASE_URL + video.key + Constants.YOUTUBE_TAIL_URL
            } else {
                Constants.VIMEO_VID_BASE_URL + video.key
            }

            val dataUrl = Constants.IFRAME_HEAD + url + Constants.IFRAME_TAIL

            videoView.webChromeClient = object : WebChromeClient(){}

            val webSettings = videoView.settings

            webSettings.javaScriptEnabled = true
            videoView.loadData(dataUrl, "text/html", "utf-8")

        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = VideoListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.start(getItem(position))
    }



}

class VideoListDiffCallback : DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem == newItem
    }

}
