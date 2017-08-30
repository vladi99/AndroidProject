package vladi.youtubeconverter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import vladi.youtubeconverter.Models.Video;
import vladi.youtubeconverter.R;

import static java.lang.String.format;

public class VideoAdapter extends BaseAdapter {
    private List<Video> list;
    private final Context context;

    public VideoAdapter(Context localContext, List<Video> videos) {
        context = localContext;
        this.list = videos;
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
            textViewAndroid.setText(format("%s %s", context.getString(R.string.date), list.get(position).getDate()));
            Glide.with(context)
                    .load(list.get(position).getPath())
                    .into(imageViewAndroid);
        } else {
            gridViewAndroid = convertView;
        }

        return gridViewAndroid;
    }
}

