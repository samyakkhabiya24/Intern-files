package com.db.courseproject.classes;

import java.time.LocalDate;

public class Album {
    private int id;
    private String title;
    private int coverPhotoId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String coverPhotoUrl;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCoverPhotoId() {
        return coverPhotoId;
    }

    public void setCoverPhotoId(int coverPhotoId) {
        this.coverPhotoId = coverPhotoId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public String getCoverPhotoUrl() {
        return coverPhotoUrl;
    }

    public void setCoverPhotoUrl(String coverPhotoUrl) {
        this.coverPhotoUrl = coverPhotoUrl;
    }
}
