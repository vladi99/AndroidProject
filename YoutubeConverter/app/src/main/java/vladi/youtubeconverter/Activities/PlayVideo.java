package vladi.youtubeconverter.Activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import vladi.youtubeconverter.R;

public class PlayVideo extends AppCompatActivity {

    private VideoView myVideoView;
    private int position = 0;
    private MediaController mediaControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        LinearLayout mediaLayout = findViewById(R.id.video_layout);
        String path = getIntent().getStringExtra("VIDEO_PATH");
        if (mediaControls == null) {
            mediaControls = new MediaController(PlayVideo.this);
        }

        myVideoView = findViewById(R.id.video_view);

        try {
            myVideoView.setMediaController(mediaControls);
            mediaControls.setAnchorView(mediaLayout);
            myVideoView.setVideoURI(Uri.parse(path));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        myVideoView.requestFocus();
        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                myVideoView.seekTo(position);
                if (position == 0) {
                    myVideoView.start();
                } else {
                    myVideoView.pause();
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
        myVideoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("Position");
        myVideoView.seekTo(position);
    }

}
