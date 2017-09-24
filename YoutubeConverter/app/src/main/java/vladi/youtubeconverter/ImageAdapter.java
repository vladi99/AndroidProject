package vladi.youtubeconverter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

class ImageAdapter extends BaseAdapter {
    private ArrayList<String> list;
    private final Context context;

    ImageAdapter(Context localContext) {
        context = localContext;
        this.list = getAllMedia();
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView picturesView;

        Bitmap bitmap = null;
        if (convertView == null) {
            picturesView = new ImageView(context);
            bitmap = ThumbnailUtils.createVideoThumbnail(list.get(position), 0); //Creation of Thumbnail of video
            picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            picturesView.setPadding(8, 8, 8, 8);
            picturesView.setLayoutParams(new GridView.LayoutParams(100, 100));
        } else {
            picturesView = (ImageView) convertView;
        }
        picturesView.setImageBitmap(bitmap);
        return picturesView;
    }

    private ArrayList<String> getAllMedia() {
        ArrayList<String> lis = new ArrayList<>();
        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Camera").getAbsolutePath());
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.getName().endsWith(".mp4")) {
                lis.add(file.getAbsolutePath());
            }
        }
        return lis;
    }


    public Context getContext() {
        return context;
    }
}

