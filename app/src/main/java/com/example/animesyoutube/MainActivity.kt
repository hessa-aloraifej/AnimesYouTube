package com.example.animesyoutube


import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MainActivity : AppCompatActivity() {

    private val animeVidews: Array<Array<String>> = arrayOf(
        arrayOf("Attack On Titan", "ZFU-fZfZ0DI"),
        arrayOf("Jujutsu Kaisen", "dwv65QSdXSU"),
        arrayOf("Black Clover", "olW99JAV_lA"),
        arrayOf("Naruto", "ejWo1KHRYTI"),
        arrayOf("Hunter X Hunter", "aO-ZaF4FJls"),
        arrayOf("Death Note", "-ZhjiihNfw4"))
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var player: YouTubePlayer
    private var currentVideo = 0
    private var timeStamp = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkInternet()
        val gridView= findViewById<GridView >(R.id.gridview)
        youTubePlayerView = findViewById(R.id.animevideo)
        youTubePlayerView.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                player = youTubePlayer
                player.loadVideo(animeVidews[currentVideo][1], timeStamp)

                gridView.adapter = AnimesGridAdapter(animeVidews, player,this@MainActivity)
            }
        })

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            youTubePlayerView.enterFullScreen()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            youTubePlayerView.exitFullScreen()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("currentVideo", currentVideo)
        outState.putFloat("timeStamp", timeStamp)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        currentVideo = savedInstanceState.getInt("currentVideo", 0)
        timeStamp = savedInstanceState.getFloat("timeStamp", 0f)
    }



    private fun checkInternet(){
        if(!connectedToInternet()){
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Internet Connection Not Found")
                .setPositiveButton("RETRY"){_, _ -> checkInternet()}
                .show()
        }
    }

    private fun connectedToInternet(): Boolean{
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}