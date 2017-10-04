package vladi.youtubeconverter;

class Video {

    private String path;
    private String date;

    Video(String path, String name) {
        this.path = path;
        this.date = name;
    }

    String getPath(){
        return path;
    }

    String getDate(){
        return date;
    }
}
