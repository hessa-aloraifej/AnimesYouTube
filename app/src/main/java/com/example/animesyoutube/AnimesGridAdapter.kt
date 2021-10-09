package com.example.animesyoutube

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

class AnimesGridAdapter (private val animeVideos: Array<Array<String>>, private val player: YouTubePlayer, private val context: Context
): BaseAdapter() {

    private lateinit var layoutInflater: LayoutInflater
    lateinit var displayBtn: Button

    override fun getCount(): Int {
        return animeVideos.size
    }

    override fun getItem(position: Int): Any {
        return animeVideos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        val animeName = animeVideos[position][0]
        val animeUrl = animeVideos[position][1]

        if (layoutInflater==null)
        {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        }
        if (convertView == null)
        {
            convertView = layoutInflater!!.inflate(R.layout.item_row,null)

        }
        if (convertView != null) {
            displayBtn = convertView.findViewById(R.id.button)
            displayBtn.text = animeName
            displayBtn.setOnClickListener{
                player.loadVideo(animeUrl, 0f)
            }
        }
        return convertView
    }


}