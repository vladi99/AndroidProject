package vladi.youtubeconverter.Models;

public class Video {

    private String path;
    private String date;

    public Video(String path, String name) {
        this.path = path;
        this.date = name;
    }

    public String getPath(){
        return path;
    }

    public String getDate(){
        return date;
    }
}
