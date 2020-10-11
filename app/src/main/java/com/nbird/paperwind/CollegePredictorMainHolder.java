package com.nbird.paperwind;

public class CollegePredictorMainHolder {
    String image;
    String name;
    int branch;

    public CollegePredictorMainHolder() {
    }

    public CollegePredictorMainHolder(String image, String name, int branch) {
        this.image = image;
        this.name = name;
        this.branch = branch;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }
}
