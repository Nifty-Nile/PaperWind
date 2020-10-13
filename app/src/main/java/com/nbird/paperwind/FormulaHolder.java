package com.nbird.paperwind;

public class FormulaHolder {
    String url;
    String title;
    int set;

    public FormulaHolder() {
    }

    public FormulaHolder(String url, String title,int set) {
        this.url = url;
        this.title = title;
        this.set=set;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
