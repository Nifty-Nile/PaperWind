package com.nbird.paperwind;

public class TestRecyclerHolder {
    String image;
    String title;
    String dis;
    int set;
    int phy;
    int chem;
    int maths;
    int bio;


    public TestRecyclerHolder() {
    }



    public TestRecyclerHolder(String image, String title, String dis, int set, int phy, int chem, int maths, int bio) {
        this.image = image;
        this.title = title;
        this.dis = dis;
        this.set = set;
        this.phy = phy;
        this.chem = chem;
        this.maths = maths;
        this.bio = bio;
    }

    public int getPhy() {
        return phy;
    }

    public void setPhy(int phy) {
        this.phy = phy;
    }

    public int getChem() {
        return chem;
    }

    public void setChem(int chem) {
        this.chem = chem;
    }

    public int getMaths() {
        return maths;
    }

    public void setMaths(int maths) {
        this.maths = maths;
    }

    public int getBio() {
        return bio;
    }

    public void setBio(int bio) {
        this.bio = bio;
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
