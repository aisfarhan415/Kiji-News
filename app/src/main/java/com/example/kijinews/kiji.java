package com.example.kijinews;

public class kiji {

    private String id, title, category, content, picture, source;
    private comments[] comments;

    public kiji(String id, String title, String category, String content, String picture, String source) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.picture = picture;
        this.source = source;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public comments[] getComments() {
        return comments;
    }

    public void setComments(comments[] comments) {
        this.comments = comments;
    }
}