package com.example.kijinews;

public class comments {
    String name, comment;

    public comments() {
    }

    public comments(String name, String comment) {
        this.name=name;
        this.comment=comment;
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment){
        this.comment=comment;
    }
}