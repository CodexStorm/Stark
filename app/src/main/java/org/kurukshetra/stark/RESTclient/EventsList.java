package org.kurukshetra.stark.RESTclient;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.kurukshetra.stark.Adapters.EventsAdapter;
import org.kurukshetra.stark.Constants.Constants;
import org.kurukshetra.stark.Entities.CategoriesEntity;
import org.kurukshetra.stark.Entities.CategoriesList;
import org.kurukshetra.stark.Entities.EventsEntity;
import org.kurukshetra.stark.Entities.ResponseEntity;

import java.io.Console;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.kurukshetra.stark.Constants.Constants.BASE_URL;

/**
 * Created by Balaji on 11/25/2017.
 */

public class EventsList {
    private static final String BASE_URL = Constants.BASE_URL;
    static RequestQueue queue;


    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
    public static CategoriesList listEvents(final  CategoriesList categoriesList, final Context context, final EventsAdapter eventsAdapter){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = getAbsoluteUrl("categories.json");

        JsonBaseRequest jsonBaseRequest = new JsonBaseRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Login Response", response.toString());
                try {

                    JSONArray jsonArray = response.getJSONArray("categories");
                    for (int i=0;i<jsonArray.length();i++){
                        Gson gson = new Gson();
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        CategoriesEntity categoriesEntity =new CategoriesEntity(gson.fromJson(jsonObject.toString(),CategoriesEntity.class));
                        categoriesList.setCategoriesEntityList(categoriesEntity);
                        eventsAdapter.setCategoriesEntityList(categoriesList.getCategoriesEntityList());
                        Log.e("Response "+Integer.toString(i),categoriesEntity.getEventCategory());
                    }
                    Log.e("Login Response", jsonArray.toString());

                    //   List<CategoriesEntity> categoriesEntityList=new ArrayList<CategoriesEntity>();
                   // categoriesEntityList.add(gson.fromJson(jsonArray.toString(), CategoriesEntity.class));
                    Log.e("Categories in main", categoriesList.getCategoriesEntityList().toString());

                }

                //}
                catch (JSONException e) {
                    e.printStackTrace();
                    //   }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error in response here",error.toString());
                error.printStackTrace();
            }
        },30000,0);
        queue.add(jsonBaseRequest);
        return categoriesList;
    }
}
