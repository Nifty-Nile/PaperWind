package com.nbird.paperwind;

public class RefHolder {
    String url;
    String mail;

    public RefHolder() {
    }

    public RefHolder(String url, String mail) {
        this.url = url;
        this.mail = mail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
