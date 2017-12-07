package org.kurukshetra.stark.Common;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import org.kurukshetra.stark.Constants.Constants;
import org.kurukshetra.stark.R;

import java.util.Random;

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

    public static void setEventList(Context context,String eventList){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("event_list",eventList);
        editor.apply();
    }
    public static String getEventList(Context context){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        return  (settings.getString("event_list",""));
    }

    public static int[] getRandomGradient(){
        Random rnd = new Random();
        final int color1 = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        final int color2 = Color.argb(255, rnd.nextInt(128), rnd.nextInt(128), rnd.nextInt(128));
        /*GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {color1,color2});
        gd.setCornerRadius(0f);
        return gd;*/
        return new int[]{color1,color2};
    }

    public static int getRandomColor(Context context){
        int[] androidColors = context.getResources().getIntArray(R.array.androidcolors);
        return androidColors[new Random().nextInt(androidColors.length)];
    }
}
