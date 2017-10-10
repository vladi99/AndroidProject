package vladi.youtubeconverter.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import vladi.youtubeconverter.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GetVideo(View view) {
        Intent getVideoIntent = new Intent(this, MyVideo.class);
        startActivity(getVideoIntent);

    }

    public void GetMusic(View view) {
        Intent getMusicIntent = new Intent(this, MyMusic.class);
        startActivity(getMusicIntent);
    }
    public void Download(View view) {
        Intent downloadIntent = new Intent(this, Download.class);
        downloadIntent.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v=J30UOKAD4jw");
        startActivity(downloadIntent);
    }
}
