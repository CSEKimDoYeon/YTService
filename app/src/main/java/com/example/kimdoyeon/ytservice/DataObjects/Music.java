package com.example.kimdoyeon.ytservice.DataObjects;

public class Music {
    private String title;
    private String description;
    private String music_Id;
    private String thumbnail_Url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusic_Id() {
        return music_Id;
    }

    public void setMusic_Id(String music_Id) {
        this.music_Id = music_Id;
    }

    public String getThumbnail_Url() {
        return thumbnail_Url;
    }

    public void setThumbnail_Url(String thumbnail_Url) {
        this.thumbnail_Url = thumbnail_Url;
    }
}
