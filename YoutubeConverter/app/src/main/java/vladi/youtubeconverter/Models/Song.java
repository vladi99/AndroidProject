package vladi.youtubeconverter.Models;

public class Song {

    private long id;
    private String title;
    private String artist;
    private boolean isPlaying;

    public Song(long songID, String songTitle, String songArtist, Boolean isPng) {
        id = songID;
        title = songTitle;
        artist = songArtist;
        isPlaying = isPng;
    }

    public long getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
