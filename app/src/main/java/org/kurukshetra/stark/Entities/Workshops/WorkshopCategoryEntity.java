package org.kurukshetra.stark.Entities.Workshops;

import java.util.List;

/**
 * Created by Balaji on 11/28/2017.
 */

public class WorkshopCategoryEntity {

    private int id;
    private String name;
    private List<WorkshopsEntity> workshops;

    public WorkshopCategoryEntity(WorkshopCategoryEntity workshopCategoryEntity){
        this.name = workshopCategoryEntity.getName();
        this.workshops = workshopCategoryEntity.getWorkshops();
    }
    public WorkshopCategoryEntity(String eventCategory, List<WorkshopsEntity> workshopsEntityList){
        this.name =eventCategory;
        this.workshops =workshopsEntityList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<WorkshopsEntity> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<WorkshopsEntity> workshopsEntityList) {
        this.workshops = workshopsEntityList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
