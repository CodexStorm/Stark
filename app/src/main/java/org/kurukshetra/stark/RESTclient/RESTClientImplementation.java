package org.kurukshetra.stark.RESTclient;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.kurukshetra.stark.Constants.Constants;
import org.kurukshetra.stark.Entities.Events.EventsCategoryResponseEntity;
import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.LogoutEntity;
import org.kurukshetra.stark.Entities.SocialLoginInterface;
import org.kurukshetra.stark.Entities.LoginEntity;
import org.kurukshetra.stark.Entities.ResponseEntity;
import org.kurukshetra.stark.Entities.Workshops.WorkshopsCategoryResponseEntity;

public class RESTClientImplementation {
    private static final String CMS_URL = Constants.CMS_URL;
    static RequestQueue queue;
    private static final String BASE_URL = Constants.BASE_URL;
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
    private static String getAbsoluteCMSUrl(String relativeUrl) {
        return CMS_URL + relativeUrl;
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

    public static void getWorkshopsList(final WorkshopsCategoryResponseEntity.workshopCategoryListInterface workshopCategoryListInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = getAbsoluteCMSUrl("workshops.json");
        JsonBaseRequest jsonBaseRequest = new JsonBaseRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Login Response", response.toString());
                WorkshopsCategoryResponseEntity workshopsCategoryResponseEntity;
                workshopsCategoryResponseEntity = new Gson().fromJson(response.toString(),WorkshopsCategoryResponseEntity.class);
                workshopCategoryListInterface.onListLoaded(workshopsCategoryResponseEntity,null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error","error");
            }
        },30000,0);
        queue.add(jsonBaseRequest);
    }

    public static void getEventsList(final EventsCategoryResponseEntity.eventCategoryListInterface eventCategoryListInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = getAbsoluteCMSUrl("categories.json");
        JsonBaseRequest jsonBaseRequest = new JsonBaseRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Login Response", response.toString());
                EventsCategoryResponseEntity eventsCategoryResponseEntity;
                eventsCategoryResponseEntity = new Gson().fromJson(response.toString(),EventsCategoryResponseEntity.class);
                eventCategoryListInterface.onListLoaded(eventsCategoryResponseEntity,null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error","error");
            }
        },30000,0);
        queue.add(jsonBaseRequest);
    }

    public static boolean hospitality(final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        final boolean[] b = {false};
        String url = getAbsoluteCMSUrl("hospitalities.json");
        JsonBaseRequest jsonBaseRequest = new JsonBaseRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                UserDetails.setHospitality(context,response.toString());
                b[0] = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error","error");
                b[0] = false;
            }
        },30000,0);
        queue.add(jsonBaseRequest);
        return b[0];
    }

    public static void logout(final LogoutEntity.RestClientInterface restClientInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = getAbsoluteUrl("/api/v1/participant/signout");
        JSONObject postParams = new JSONObject();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", UserDetails.getUserToken(context));
            postParams.put("data",jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonBaseRequest jsonBaseRequest = new JsonBaseRequest(Request.Method.POST, url, postParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Login Response",response.toString());
                Gson gson = new Gson();
                ResponseEntity responseEntity = gson.fromJson(response.toString(),ResponseEntity.class);
                restClientInterface.onLogin(responseEntity.getStatus().getCode(),null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("s","s");
            }
        },30000,0);
        queue.add(jsonBaseRequest);
    }

    public static void googleLogin(final String token, final SocialLoginInterface.RestClientInterface restClientInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = getAbsoluteUrl("/api/v1/participant/signin/google");
        JSONObject postParams = new JSONObject();
        JSONObject id_token = new JSONObject();
        try {
            id_token.put("id_token",token);
            postParams.put("data",id_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonBaseRequest jsonBaseRequest = new JsonBaseRequest(Request.Method.POST, url, postParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Google Login Response",response.toString());
                Gson gson = new Gson();
                ResponseEntity responseEntity = gson.fromJson(response.toString(),ResponseEntity.class);
                try {
                    restClientInterface.onLogin(responseEntity.getToken(), response.getJSONObject("code").getInt("statusCode"), null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        },30000,0);
        queue.add(jsonBaseRequest);
    }

    public static void facebookLogin(final String token, final SocialLoginInterface.RestClientInterface restClientInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = getAbsoluteUrl("/api/v1/participant/signin/facebook");
        JSONObject postParams = new JSONObject();
        JSONObject id_token = new JSONObject();
        try {
            id_token.put("access_token",token);
            postParams.put("data",id_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonBaseRequest jsonBaseRequest = new JsonBaseRequest(Request.Method.POST, url, postParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Facebook Login Response",response.toString());
                Gson gson = new Gson();
                ResponseEntity responseEntity = gson.fromJson(response.toString(),ResponseEntity.class);
                try {
                        restClientInterface.onLogin(responseEntity.getToken(), response.getJSONObject("code").getInt("statusCode"), null);

                } catch (JSONException e) {
                    e.printStackTrace();
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

    }
}
