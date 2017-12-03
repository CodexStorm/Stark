package org.kurukshetra.stark.Common;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import org.kurukshetra.stark.Constants.Constants;

/**
 * Created by ompra on 11/21/2017.
 */

public class UserDetails {

    public static void setUserLoggedIn(Context context, Boolean loginStatus){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("userLoggedIn",loginStatus);
        editor.apply();
    }

    public static Boolean isUserLoggedIn(Context context){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        return (settings.getBoolean("userLoggedIn", false));
    }

    public static void setUserToken(Context context,String token){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token",token);
        editor.apply();
    }


    public static String getUserToken(Context context){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        return  (settings.getString("token",""));
    }

    public static void setIntroOver(Context context,boolean flag){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("intro_over",flag);
        editor.apply();
    }

    public static Boolean getIntroOver(Context context){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        return (settings.getBoolean("intro_over", false));
    }

    public static Typeface getRightiousFont(Context context){
        return Typeface.createFromAsset(context.getAssets(),"fonts/righteous.ttf");
    }
}
