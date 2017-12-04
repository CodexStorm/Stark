package org.kurukshetra.stark.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Balaji on 11/29/2017.
 */

public class EventsEntity {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String eventName;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("sponsor")
    @Expose
    private String sponsor;
    @SerializedName("tabs")
    @Expose
    private List<Tab> tabs;
    public EventsEntity(String eventName,String slug,String sponsor,List<Tab> tabs){
        this.eventName=eventName;
        this.slug=slug;
        this.sponsor=sponsor;
        this.tabs=tabs;
    }
    public EventsEntity(){}
    public  EventsEntity(EventsEntity eventsEntity){
        this.eventName=eventsEntity.getEventName();
        this.sponsor=eventsEntity.getSponsor();
        this.slug=eventsEntity.getSlug();
        this.id=eventsEntity.getId();
        this.tabs=eventsEntity.getTabs();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() { return eventName;}

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getSlug() { return slug;     }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSponsor(){ return  sponsor;}

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public   List<Tab> getTabs(){ return tabs;}

    public void setTabs(List<Tab> tabs) {
        this.tabs = tabs;
    }

}
 class Tab{
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
    public Tab(){}
     public Tab(Tab tab){
         this.tabbable_type=tab.getTabbable_type();
         this.avatar_one=tab.getAvatar_one();
         this.avatar_two=tab.getAvatar_two();
         this.content=tab.getContent();
         this.title=tab.getTitle();
         this.id=tab.getId();
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
