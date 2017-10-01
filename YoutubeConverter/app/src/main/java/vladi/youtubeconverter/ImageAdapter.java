package vladi.youtubeconverter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static java.lang.String.format;

class ImageAdapter extends BaseAdapter {
    private ArrayList<Video> list;
    private final Context context;

    ImageAdapter(Context localContext) {
        context = localContext;

        this.list = getAllMedia();
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridViewAndroid = inflater.inflate(R.layout.grid_video_single, null);
            TextView textViewAndroid = gridViewAndroid.findViewById(R.id.grid_text);
            ImageView imageViewAndroid = gridViewAndroid.findViewById(R.id.grid_image);
            textViewAndroid.setText(format("%s %s", context.getString(R.string.date), list.get(position).getName()));
            long startTime = System.currentTimeMillis();
            Glide.with(context)
                    .load(list.get(position).getPath())
                    .into(imageViewAndroid);
            long stopTime = System.currentTimeMillis();
            System.out.println("Elapsed time was " + (stopTime - startTime) + " milliseconds.");
        } else {
            gridViewAndroid = convertView;
        }

        return gridViewAndroid;
    }

    private ArrayList<Video> getAllMedia() {
        ArrayList<Video> videos = new ArrayList<>();

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

