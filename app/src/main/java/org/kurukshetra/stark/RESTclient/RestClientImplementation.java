package org.kurukshetra.stark.RESTclient;

import com.android.volley.RequestQueue;

import org.kurukshetra.stark.Constants.Constants;

public class RESTclientImplementation {
    static RequestQueue queue;
    static final String BASE_URL = Constants.BASE_URL;
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
