package com.nbird.paperwind;

public class LatestExamUpdatesHolder {

    String exampic;
    String dis;
    String head;
    String optionalpic;

    public LatestExamUpdatesHolder() {
    }

    public LatestExamUpdatesHolder(String exampic, String dis, String head, String optionalpic) {
        this.exampic = exampic;
        this.dis = dis;
        this.head = head;
        this.optionalpic = optionalpic;
    }

    public String getExampic() {
        return exampic;
    }

    public void setExampic(String exampic) {
        this.exampic = exampic;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getOptionalpic() {
        return optionalpic;
    }

    public void setOptionalpic(String optionalpic) {
        this.optionalpic = optionalpic;
    }
}
