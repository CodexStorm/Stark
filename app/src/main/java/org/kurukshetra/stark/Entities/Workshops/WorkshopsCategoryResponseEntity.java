package org.kurukshetra.stark.Entities.Workshops;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balaji on 12/3/2017.
 */

public class WorkshopsCategoryResponseEntity {

    private  List<WorkshopCategoryEntity> workshops = new ArrayList<>();

    public interface workshopCategoryListInterface {
        void onListLoaded(WorkshopsCategoryResponseEntity workshopsCategoryResponseEntity, VolleyError error);
    }

    public WorkshopsCategoryResponseEntity(List<WorkshopCategoryEntity> workshopCategoryEntityList){
        this.workshops =workshopCategoryEntityList;
    }

    public  List<WorkshopCategoryEntity> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<WorkshopCategoryEntity> workshopCategoryEntityList) {
        this.workshops = workshopCategoryEntityList;
    }
}
