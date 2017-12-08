package org.kurukshetra.stark.Entities.Events;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.kurukshetra.stark.Entities.TabEntity;

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
    private List<TabEntity> tabs;
    public EventsEntity(String eventName,String slug,String sponsor,List<TabEntity> tabEntities){
        this.eventName=eventName;
        this.slug=slug;
        this.sponsor=sponsor;
        this.tabs = tabEntities;
    }
    public EventsEntity(){}
    public  EventsEntity(EventsEntity eventsEntity){
        this.eventName=eventsEntity.getEventName();
        this.sponsor=eventsEntity.getSponsor();
        this.slug=eventsEntity.getSlug();
        this.id=eventsEntity.getId();
        this.tabs =eventsEntity.getTabs();
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

    public   List<TabEntity> getTabs(){ return tabs;}

    public void setTabs(List<TabEntity> tabs) {
        this.tabs = tabs;
    }

}

