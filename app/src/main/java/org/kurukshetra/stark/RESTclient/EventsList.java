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

/**
 * Created by Balaji on 11/25/2017.
 */

public class EventsList {
    private static final String BASE_URL = Constants.CMS_URL;
    static RequestQueue queue;


    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
