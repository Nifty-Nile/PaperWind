package com.nbird.paperwind;

public class EntranceExamRecorHolder {
    String totalmarks;
    String nameofexam;
    String modes;
    String setno;
    String phymarks;
    String chemmarks;
    String mathsmarks;
    String exampic;
    String fullmarksexam;

    public EntranceExamRecorHolder() {
    }

    public EntranceExamRecorHolder(String totalmarks, String nameofexam, String modes, String setno, String phymarks, String chemmarks, String mathsmarks, String exampic,String fullmarksexam) {
        this.totalmarks = totalmarks;
        this.nameofexam = nameofexam;
        this.modes = modes;
        this.setno = setno;
        this.phymarks = phymarks;
        this.chemmarks = chemmarks;
        this.mathsmarks = mathsmarks;
        this.exampic = exampic;
        this.fullmarksexam=fullmarksexam;
    }

    public String getFullmarksexam() {
        return fullmarksexam;
    }

    public void setFullmarksexam(String fullmarksexam) {
        this.fullmarksexam = fullmarksexam;
    }

    public String getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(String totalmarks) {
        this.totalmarks = totalmarks;
    }

    public String getNameofexam() {
        return nameofexam;
    }

    public void setNameofexam(String nameofexam) {
        this.nameofexam = nameofexam;
    }

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public String getSetno() {
        return setno;
    }

    public void setSetno(String setno) {
        this.setno = setno;
    }

    public String getPhymarks() {
        return phymarks;
    }

    public void setPhymarks(String phymarks) {
        this.phymarks = phymarks;
    }

    public String getChemmarks() {
        return chemmarks;
    }

    public void setChemmarks(String chemmarks) {
        this.chemmarks = chemmarks;
    }

    public String getMathsmarks() {
        return mathsmarks;
    }

    public void setMathsmarks(String mathsmarks) {
        this.mathsmarks = mathsmarks;
    }

    public String getExampic() {
        return exampic;
    }

    public void setExampic(String exampic) {
        this.exampic = exampic;
    }
}
