package org.kurukshetra.stark.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Balaji on 11/28/2017.
 */

public class CategoriesEntity {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String  eventCategory;
    @SerializedName("cardview_events")
    @Expose
    private List<EventsEntity> eventsList;
    public CategoriesEntity(){}
    public CategoriesEntity(CategoriesEntity categoriesEntity){
        this.eventCategory= categoriesEntity.getEventCategory();
        this.eventsList=categoriesEntity.getEventsList();
    }
    public CategoriesEntity(String eventCategory,List<EventsEntity> eventsList){
        this.eventCategory=eventCategory;
        this.eventsList=eventsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<EventsEntity> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<EventsEntity> eventsList) {
        this.eventsList = eventsList;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }
}
