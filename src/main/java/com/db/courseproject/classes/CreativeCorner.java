package com.db.courseproject.classes;

public class CreativeCorner {
    private int id;
    private String url;

    public CreativeCorner() {
    }

    public CreativeCorner(String url) {
        this.url = url;
    }

    public CreativeCorner(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

