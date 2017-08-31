package vladi.youtubeconverter;

class Video {

    private String path;
    private String name;

    Video(String path, String name) {
        this.path = path;
        this.name = name;
    }

    String getPath(){
        return path;
    }

    String getName(){
        return name;
    }
}
