package org.kurukshetra.stark.RESTclient;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kurukshetra.stark.Adapters.EventsAdapter;
import org.kurukshetra.stark.Constants.Constants;
import org.kurukshetra.stark.Entities.CategoriesEntity;
import org.kurukshetra.stark.Entities.CategoriesList;
import org.kurukshetra.stark.Entities.LoginEntity;
import org.kurukshetra.stark.Entities.ResponseEntity;

public class RESTClientImplementation {
    private static final String BASE_URL = Constants.CMS_URL;
    static RequestQueue queue;

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static void normalLogin(final LoginEntity loginEntity, final LoginEntity.RestClientInterface restClientInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = getAbsoluteUrl("/api/v1/participant/signin");
        JSONObject postParams = new JSONObject();
        try {
            postParams.put("data",loginEntity.getParams());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonBaseRequest jsonBaseRequest = new JsonBaseRequest(Request.Method.POST, url, postParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Login Response",response.toString());
                Gson gson = new Gson();
                ResponseEntity responseEntity = gson.fromJson(response.toString(),ResponseEntity.class);
                restClientInterface.onLogin(responseEntity.getToken(),null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        },30000,0);
        queue.add(jsonBaseRequest);
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
                Toast.makeText(context,"Error in response here"+error.toString(),Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        },30000,0);
        queue.add(jsonBaseRequest);
        //Toast.makeText(context,categoriesList.getCategoriesEntityList().get(3).toString(),Toast.LENGTH_LONG).show();
        return categoriesList;
    }
}
