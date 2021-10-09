package com.example.animesyoutube

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import kotlinx.android.synthetic.main.item_row.view.*


class AnimesRecyclerViewAdapter(private val animeVidews: Array<Array<String>>, private val player: YouTubePlayer): RecyclerView.Adapter<AnimesRecyclerViewAdapter.VideoViewHolder>(){
        class VideoViewHolder(item_row: View): RecyclerView.ViewHolder(item_row){
            val button: Button = item_row.button
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
            return VideoViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
            val animeName = animeVidews[position][0]
            val animeUrl = animeVidews[position][1]
            holder.button.text = animeName
            holder.button.setOnClickListener {
                player.loadVideo(animeUrl, 0f)
            }
        }

        override fun getItemCount() = animeVidews.size
    }


