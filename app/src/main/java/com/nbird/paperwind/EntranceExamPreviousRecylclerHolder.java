package com.nbird.paperwind;

public class EntranceExamPreviousRecylclerHolder {
    String name;
    String imageurl;
    int set;



    public EntranceExamPreviousRecylclerHolder() {
    }


    public EntranceExamPreviousRecylclerHolder(String name, String imageurl, int set) {
        this.name = name;
        this.imageurl = imageurl;
        this.set=set;
    }
    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}


