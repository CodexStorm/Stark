package org.kurukshetra.stark.RESTclient;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

class JsonArrayBaseRequest extends JsonArrayRequest {

    public JsonArrayBaseRequest(int method,String url,JSONArray jsonArray ,Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(method,url,jsonArray,listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(
                10000, 3,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public JsonArrayBaseRequest(int method,String url,JSONArray jsonArray, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener, int timeOut, int retries) {
        super(method,url,jsonArray, listener,errorListener);
        setRetryPolicy(new DefaultRetryPolicy(
                timeOut, retries,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        return super.parseNetworkResponse(response);
    }

}
