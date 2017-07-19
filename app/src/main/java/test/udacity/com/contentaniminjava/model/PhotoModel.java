package test.udacity.com.contentaniminjava.model;


/**
 * Created by bernatgomez on 18/7/17.
 */
public class PhotoModel {

    private String format;
    private int width;
    private int height;
    private String filename;
    private long id;
    private String author_url;
    private String authot_post;

    public PhotoModel() {

    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor_url() {
        return author_url;
    }

    public void setAuthor_url(String author_url) {
        this.author_url = author_url;
    }

    public String getAuthot_post() {
        return authot_post;
    }

    public void setAuthot_post(String authot_post) {
        this.authot_post = authot_post;
    }
}
