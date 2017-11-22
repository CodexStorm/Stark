package org.kurukshetra.stark.Entities;

import com.android.volley.VolleyError;

/**
 * Created by ompra on 11/22/2017.
 */

public class SocialLoginInterface {
    public interface RestClientInterface{
        void onLogin(String token, VolleyError error);
    }
}
