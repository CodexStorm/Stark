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
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.kurukshetra.stark.Constants.Constants;
import org.kurukshetra.stark.Entities.CategoriesEntity;
import org.kurukshetra.stark.Entities.CategoriesList;
import org.kurukshetra.stark.Entities.EventsEntity;
import org.kurukshetra.stark.Entities.ResponseEntity;

import java.io.Console;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

/**
 * Created by Balaji on 11/25/2017.
 */

public class EventsList {
    private static final String BASE_URL = Constants.BASE_URL;
    static RequestQueue queue;
    private static CategoriesList categoriesList=new CategoriesList() ;

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
    public static void listEvents( final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = getAbsoluteUrl("categories.json");

        JsonBaseRequest jsonObjectBaseRequest = new JsonBaseRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Login Response", response.toString());
                try {
                    Gson gson = new Gson();
                    List<CategoriesEntity> entityList = Arrays.asList(gson.fromJson(response.toString(), CategoriesEntity.class));
                    categoriesList.setCategoriesEntityList(entityList);
                    Log.e("Categories in main", categoriesList.getCategoriesEntityList().toString());

                }
                catch (JsonSyntaxException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error in response here",error.toString());
            }
        },40000,0);
        queue.add(jsonObjectBaseRequest);
    }
}
