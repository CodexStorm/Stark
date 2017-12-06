package org.kurukshetra.stark.Entities;

import java.util.List;

/**
 * Created by Balaji on 11/28/2017.
 */

public class CategoriesEntity {

    private int id;
    private String name;
    private List<EventsEntity> events;
    public CategoriesEntity(CategoriesEntity categoriesEntity){
        this.name = categoriesEntity.getName();
        this.events =categoriesEntity.getEvents();
    }
    public CategoriesEntity(String eventCategory,List<EventsEntity> eventsList){
        this.name =eventCategory;
        this.events =eventsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<EventsEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventsEntity> events) {
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
