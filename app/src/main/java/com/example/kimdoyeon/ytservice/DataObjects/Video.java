package com.example.kimdoyeon.ytservice.DataObjects;

public class Video {

    private String title;
    private String description;
    private String object_Id;
    private String thumbnail_Url;
    private int is_Playlist;

    public Video(String title, String description, String object_Id, String thumbnail_Url
    , int is_Playlist){
        this.title=title;
        this.description=description;
        this.object_Id =object_Id;
        this.thumbnail_Url=thumbnail_Url;
        this.is_Playlist=is_Playlist;
    }


    public int getIs_Playlist() {
        return is_Playlist;
    }

    public void setIs_Playlist(int is_Playlist) {
        this.is_Playlist = is_Playlist;
    }

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

    public String getObject_Id() {
        return object_Id;
    }

    public void setObject_Id(String object_Id) {
        this.object_Id = object_Id;
    }

    public String getThumbnail_Url() {
        return thumbnail_Url;
    }

    public void setThumbnail_Url(String thumbnail_Url) {
        this.thumbnail_Url = thumbnail_Url;
    }
}
