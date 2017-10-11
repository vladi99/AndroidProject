package vladi.youtubeconverter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

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

    private List<Video> videos = getAllVideo();
    private VideoAdapter adapter;
    private GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_video);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new VideoAdapter(this, videos);
        gridview = findViewById(R.id.grid);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent playVideoIntent = new Intent(getApplicationContext(), PlayVideo.class);
                playVideoIntent.putExtra("VIDEO_PATH", videos.get(position).getPath());
                startActivity(playVideoIntent);
            }
        });

        registerForContextMenu(gridview);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.grid) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_grid, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete:
                File file = new File(videos.get(info.position).getPath());
                boolean deleted = file.delete();
                Toast.makeText(this, R.string.deleted, Toast.LENGTH_SHORT).show();
                videos = getAllVideo();
                adapter = new VideoAdapter(this, videos);
                gridview.invalidateViews();
                gridview.setAdapter(adapter);
                return deleted;
            default:
                return super.onContextItemSelected(item);
        }
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
