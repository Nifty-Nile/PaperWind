package com.nbird.paperwind;

public class TestRecyclerHolder {
    String image;
    String title;
    String dis;
    int set;

    public TestRecyclerHolder() {
    }

    public TestRecyclerHolder(String image, String title, String dis,int set) {
        this.image = image;
        this.title = title;
        this.dis = dis;
        this.set=set;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public TestRecyclerHolder(int set) {
        this.set = set;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }
}
