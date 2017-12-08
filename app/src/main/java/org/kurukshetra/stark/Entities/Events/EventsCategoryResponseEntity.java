package org.kurukshetra.stark.Entities.Events;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balaji on 12/3/2017.
 */

public class EventsCategoryResponseEntity {
    private  List<EventCategoryEntity> categories =new ArrayList<EventCategoryEntity>();
    public interface eventCategoryListInterface {
        void onListLoaded(EventsCategoryResponseEntity eventsCategoryResponseEntity, VolleyError error);
    }
    public EventsCategoryResponseEntity(List<EventCategoryEntity> categoriesEntities){
        this.categories =categoriesEntities;
    }

    public  List<EventCategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<EventCategoryEntity> categories) {
        this.categories = categories;
    }
}
