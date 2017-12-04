package org.kurukshetra.stark.Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balaji on 12/3/2017.
 */

public class CategoriesList {
    private  List<CategoriesEntity> categoriesEntityList=new ArrayList<CategoriesEntity>();
    public CategoriesList(){

    }
    public CategoriesList(List<CategoriesEntity> categoriesEntities){
        this.categoriesEntityList=categoriesEntities;
    }

    public  List<CategoriesEntity> getCategoriesEntityList() {
        return categoriesEntityList;
    }

    public    void setCategoriesEntityList(CategoriesEntity categoriesEntity) {
        categoriesEntityList.add(categoriesEntity);
    }
}
