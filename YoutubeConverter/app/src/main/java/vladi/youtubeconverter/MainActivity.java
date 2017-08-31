package vladi.youtubeconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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

}
