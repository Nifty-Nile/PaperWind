package com.nbird.paperwind;

public class RankMenuHolder {
    int Pic;
    String Title;

    public RankMenuHolder() {
    }

    public RankMenuHolder(int pic, String title) {
        Pic = pic;
        Title = title;
    }

    public int getPic() {
        return Pic;
    }

    public void setPic(int pic) {
        Pic = pic;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
