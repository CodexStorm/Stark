package org.kurukshetra.stark.RESTclient;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONObject;

public class JsonBaseRequest extends JsonObjectRequest{
    public JsonBaseRequest(int method, String url, JSONObject jsonObject, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener){
        super(method,url,jsonObject,successListener,errorListener);
        setRetryPolicy(new DefaultRetryPolicy(1000,1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public JsonBaseRequest(int method, String url, JSONObject jsonObject, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener, int timeOut, int retries){
        super(method,url,jsonObject,successListener,errorListener);
        setRetryPolicy(new DefaultRetryPolicy(timeOut,retries, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        return super.parseNetworkResponse(response);
    }

}
