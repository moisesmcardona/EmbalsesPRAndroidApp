package msc.app.embalsespuertorico

import android.content.pm.ActivityInfo
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayer.Provider

class YTPlayer : YouTubeBaseActivity(), OnInitializedListener {
    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContentView(R.layout.player)
        val youTubeView = findViewById<YouTubePlayerView>(R.id.youtube_view)
        youTubeView.initialize("AIzaSyCqVJ3cBq-sqwDU2lrDTYrGjDp3AZkekEg", this)
        // TODO Auto-generated method stub
    }

    override fun onInitializationSuccess(arg0: Provider, arg1: YouTubePlayer, arg2: Boolean) {
        val extras = intent.extras
        var value: String? = ""
        if (extras != null) {
            value = extras.getString("video")
        }
        if (!arg2) {
            arg1.loadVideo(value)
        }
    }

    override fun onInitializationFailure(arg0: Provider, arg1: YouTubeInitializationResult) {

    }
}
