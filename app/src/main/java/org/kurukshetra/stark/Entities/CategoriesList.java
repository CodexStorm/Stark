package org.kurukshetra.stark.Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balaji on 12/3/2017.
 */

public class CategoriesList {
    private  List<CategoriesEntity> categoriesEntityList;
    public CategoriesList(){

    }
    public CategoriesList(CategoriesEntity categoriesEntity){
        categoriesEntityList.add(categoriesEntity);
    }
    public List<CategoriesEntity> getCategoriesEntityList() {
        return categoriesEntityList;
    }

    public  void setCategoriesEntityList(List<CategoriesEntity> categoriesEntityList) {
        this.categoriesEntityList = categoriesEntityList;
    }
}
