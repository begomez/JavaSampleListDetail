package test.udacity.com.contentaniminjava.model;


import java.io.Serializable;

/**
 * Created by bernatgomez on 18/7/17.
 */
public class PhotoModel implements Serializable {

    private String format;
    private int width;
    private int height;
    private String filename;
    private long id;
    private String author;
    private String author_url;
    private String author_post;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor_url() {
        return author_url;
    }

    public void setAuthor_url(String author_url) {
        this.author_url = author_url;
    }

    public String getAuthor_post() {
        return author_post;
    }

    public void setAuthor_post(String author_post) {
        this.author_post = author_post;
    }
}
