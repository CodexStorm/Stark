package org.kurukshetra.stark.Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balaji on 12/3/2017.
 */

public class CategoriesList {
    private static List<CategoriesEntity> categoriesEntityList=new ArrayList<CategoriesEntity>();
    public CategoriesList(){

    }

    public static List<CategoriesEntity> getCategoriesEntityList() {
        return categoriesEntityList;
    }

    public static   void setCategoriesEntityList(CategoriesEntity categoriesEntity) {
        categoriesEntityList.add(categoriesEntity);
    }
}
