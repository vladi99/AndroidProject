package vladi.youtubeconverter.Models;

/**
 * Created by vladi on 10/9/17.
 * Vladi copyright
 */

public class SongStatus {

    private int position;
    private boolean isPLaying;

    public SongStatus(int position, boolean isPLaying) {
        this.position = position;
        this.isPLaying = isPLaying;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isPLaying() {
        return isPLaying;
    }

    public void setPLaying(boolean PLaying) {
        isPLaying = PLaying;
    }
}
