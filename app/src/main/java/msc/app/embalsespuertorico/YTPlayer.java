package msc.app.embalsespuertorico;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;

public class YTPlayer extends YouTubeBaseActivity implements OnInitializedListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.player);
        YouTubePlayerView youTubeView = findViewById(R.id.youtube_view);
        youTubeView.initialize("key_here",  this) ;
        // TODO Auto-generated method stub
    }
    public void onInitializationSuccess(Provider arg0, YouTubePlayer arg1, boolean arg2) {
        Bundle extras = getIntent().getExtras();
        String value = "";
        if(extras !=null) {
            value = extras.getString("video");
        }
        if (!arg2) {
            arg1.loadVideo(value);
        }
    }

    public void onInitializationFailure(Provider arg0, YouTubeInitializationResult arg1) {

    }
}
