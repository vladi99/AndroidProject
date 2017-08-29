package vladi.youtubeconverter.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vladi.youtubeconverter.R;
import vladi.youtubeconverter.Models.Song;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    private List<Song> songs;
    private OnSongClickListener onSongClickListener;
    public SongAdapter(ArrayList<Song> songs, OnSongClickListener onSongClickListener) {
        this.onSongClickListener = onSongClickListener;
        this.songs = songs;
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song, parent, false);
        return new SongHolder(v);
    }

    @Override
    public void onBindViewHolder(SongHolder holder, int position) {
        holder.BindSongItem(songs.get(position));
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }


    public void setSongs(List<Song> songs) {
        this.songs = songs;
        notifyDataSetChanged();
    }

    class SongHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Song song;
        TextView name;
        TextView artist;
        public SongHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = (TextView) itemView.findViewById(R.id.song_title);
            artist = (TextView) itemView.findViewById(R.id.song_artist);
        }

        public void BindSongItem(Song song) {
            this.song = song;
            name.setText(song.getTitle());
            artist.setText(song.getArtist());
        }

        @Override
        public void onClick(View view) {
            onSongClickListener.onClick(song);
        }
    }

    public interface OnSongClickListener{
        void onClick(Song song);
    }
}

