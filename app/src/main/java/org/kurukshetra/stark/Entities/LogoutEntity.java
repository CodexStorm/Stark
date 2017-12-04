package org.kurukshetra.stark.Entities;

import com.android.volley.VolleyError;

/**
 * Created by ompra on 12/4/2017.
 */

public class LogoutEntity {
    public interface RestClientInterface{
        void onLogin(Boolean success, VolleyError error);
    }
}
