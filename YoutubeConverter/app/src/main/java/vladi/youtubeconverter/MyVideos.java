package vladi.youtubeconverter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class MyVideos extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_videos_layout);
    }
}
