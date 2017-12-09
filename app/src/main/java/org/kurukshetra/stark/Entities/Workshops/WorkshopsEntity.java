package org.kurukshetra.stark.Entities.Workshops;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.kurukshetra.stark.Entities.TabEntity;

import java.util.List;

/**
 * Created by Balaji on 11/29/2017.
 */

public class WorkshopsEntity {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("tabs")
    @Expose
    private List<TabEntity> tabs;
    public WorkshopsEntity(String name, String slug, String sponsor, List<TabEntity> tabEntities){
        this.name = name;
        this.slug=slug;
        this.tabs = tabEntities;
    }
    public WorkshopsEntity(){}
    public WorkshopsEntity(WorkshopsEntity workshopsEntity){
        this.name =workshopsEntity.getName();
        this.slug=workshopsEntity.getSlug();
        this.id=workshopsEntity.getId();
        this.tabs =workshopsEntity.getTabs();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() { return slug;     }

    public void setSlug(String slug) {
        this.slug = slug;
    }


    public   List<TabEntity> getTabs(){ return tabs;}

    public void setTabs(List<TabEntity> tabs) {
        this.tabs = tabs;
    }

}

