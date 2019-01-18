package l.jordan.myapplication;

public class Photo {
    int albumId;
    int id;
    String title;
    String url;

    public Photo(int id, int albumId, String title, String url){
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.url = url;
    }
}
