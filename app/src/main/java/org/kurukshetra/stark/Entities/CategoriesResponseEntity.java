package org.kurukshetra.stark.Entities;

import com.android.volley.VolleyError;

import org.kurukshetra.stark.Adapters.EventsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balaji on 12/3/2017.
 */

public class CategoriesResponseEntity {
    private  List<CategoriesEntity> categories =new ArrayList<CategoriesEntity>();
    public interface eventCategoryListInterface {
        void onListLoaded(CategoriesResponseEntity categoriesResponseEntity, VolleyError error);
    }
    public CategoriesResponseEntity(List<CategoriesEntity> categoriesEntities){
        this.categories =categoriesEntities;
    }

    public  List<CategoriesEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesEntity> categories) {
        this.categories = categories;
    }
}
