package org.kurukshetra.stark.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by omprakash on 7/12/17.
 */

public class TabEntity {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("value")
    @Expose
    private int value;
    @SerializedName("avatar_one")
    @Expose
    private String avatar_one;
    @SerializedName("avatar_two")
    @Expose
    private String avatar_two;
    @SerializedName("tabbable_type")
    @Expose
    private String tabbable_type;
    public TabEntity(){}
    public TabEntity(TabEntity tabEntity){
        this.tabbable_type= tabEntity.getTabbable_type();
        this.avatar_one= tabEntity.getAvatar_one();
        this.avatar_two= tabEntity.getAvatar_two();
        this.content= tabEntity.getContent();
        this.title= tabEntity.getTitle();
        this.id= tabEntity.getId();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void settabbable_type(String tabbable_type){  this.tabbable_type=tabbable_type; }

    public String getTabbable_type() { return tabbable_type; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar_one() {
        return avatar_one;
    }

    public void setAvatar_one(String avatar_one) {
        this.avatar_one = avatar_one;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getAvatar_two() {
        return avatar_two;
    }

    public void setAvatar_two(String avatar_two) {
        this.avatar_two = avatar_two;
    }
}