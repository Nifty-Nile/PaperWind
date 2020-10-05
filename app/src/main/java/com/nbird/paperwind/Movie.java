package com.nbird.paperwind;

public class Movie {

    private String title;
    private long image;

    public Movie(String title, long image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
