package org.kurukshetra.stark.RESTclient;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.kurukshetra.stark.Constants.Constants;
import org.kurukshetra.stark.Entities.SocialLoginInterface;
import org.kurukshetra.stark.Entities.LoginEntity;
import org.kurukshetra.stark.Entities.ResponseEntity;

public class RESTClientImplementation {

    static RequestQueue queue;
    private static final String BASE_URL = Constants.BASE_URL;
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
                restClientInterface.onLogin(responseEntity.getToken(),null);
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
                restClientInterface.onLogin(responseEntity.getToken(),null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        },30000,0);
        queue.add(jsonBaseRequest);
    }
}
