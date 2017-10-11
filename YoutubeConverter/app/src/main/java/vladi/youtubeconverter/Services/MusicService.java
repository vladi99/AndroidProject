package vladi.youtubeconverter.Services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

import vladi.youtubeconverter.Activities.MyMusic;
import vladi.youtubeconverter.Models.Song;
import vladi.youtubeconverter.Models.SongStatus;
import vladi.youtubeconverter.R;

public class MusicService extends Service implements
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener {

    private MediaPlayer player;
    private ArrayList<Song> songs;
    private int songPosition;

    private final IBinder musicBind = new MusicBinder();

    private String songTitle = "";
    private static final int NOTIFY_ID = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        player.stop();
        player.reset();
        player.release();
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (mediaPlayer.getCurrentPosition() > 0) {
            mediaPlayer.reset();
            playNext();
        }
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        mediaPlayer.reset();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Intent notIntent = new Intent(this, MyMusic.class);
        notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendInt = PendingIntent.getActivity(this, 0,
                notIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentIntent(pendInt)
                .setSmallIcon(R.drawable.ic_play_arrow_white_24dp)
                .setTicker(songTitle)
                .setOngoing(true)
                .setContentTitle(getString(R.string.playing))
                .setContentText(songTitle);
        Notification not = builder.build();

        startForeground(NOTIFY_ID, not);
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
    }

    public void onCreate() {
        super.onCreate();
        songPosition = 0;
        player = new MediaPlayer();
        initMusicPlayer();
    }

    public void initMusicPlayer() {
        player.setWakeMode(getApplicationContext(),
                PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
    }

    public void setList(ArrayList<Song> theSongs) {
        songs = theSongs;
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    public void playSong() {
        player.reset();
        Song playSong = songs.get(songPosition);
        songTitle = playSong.getTitle();
        long currSong = playSong.getID();
        Uri trackUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                currSong);
        try {
            player.setDataSource(getApplicationContext(), trackUri);
        } catch (Exception e) {
            Log.e("MUSIC SERVICE", "Error setting data source", e);
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
        EventBus.getDefault().post(new SongStatus(songPosition, true));
    }

    public void setSong(int songIndex) {
        EventBus.getDefault().post(new SongStatus(songPosition, false));
        songPosition = songIndex;
    }

    public int getSongPosition() {
        return player.getCurrentPosition();
    }

    public int getDuration() {
        return player.getDuration();
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    public void pausePlayer() {
        player.pause();
        EventBus.getDefault().post(new SongStatus(songPosition, false));
    }

    public void seek(int position) {
        player.seekTo(position);
    }

    public void go() {
        player.start();
        EventBus.getDefault().post(new SongStatus(songPosition, true));
    }

    public void playPrev() {
        EventBus.getDefault().post(new SongStatus(songPosition, false));
        songPosition--;
        if (songPosition < 0) {
            songPosition = songs.size() - 1;
        }
        playSong();
    }

    public void playNext() {
        EventBus.getDefault().post(new SongStatus(songPosition, false));
        songPosition++;
        if (songPosition == songs.size()) {
            songPosition = 0;
        }
        playSong();
    }
}
