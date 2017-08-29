package vladi.youtubeconverter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import vladi.youtubeconverter.Adapters.VideoAdapter;
import vladi.youtubeconverter.Models.Video;
import vladi.youtubeconverter.R;

public class MyVideo extends AppCompatActivity {

    List<Video> videos = getAllVideo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_video);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GridView gridview = findViewById(R.id.grid);
            gridview.setAdapter(new VideoAdapter(this, videos));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent playVideoIntent = new Intent(getApplicationContext(), PlayVideo.class);
                playVideoIntent.putExtra("VIDEO_PATH", videos.get(position).getPath());
                startActivity(playVideoIntent);
            }
        });
    }

    private List<Video> getAllVideo() {
        List<Video> videos = new ArrayList<>();

        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Camera").getAbsolutePath());
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.getName().endsWith(".mp4")) {
                Date lastModified = new Date(file.lastModified());
                String str = new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(lastModified);
                Video video = new Video(file.getAbsolutePath(), str);
                videos.add(video);
            }
        }

        return videos;
    }
}
