package org.kurukshetra.stark.Activities;

import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.Events.EventsCategoryResponseEntity;
import org.kurukshetra.stark.Entities.Workshops.WorkshopsCategoryResponseEntity;
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.RESTclient.RESTClientImplementation;

public class SplashActivity extends AppCompatActivity {
    LottieAnimationView animationView;
    EventsCategoryResponseEntity eventBackup;
    private WorkshopsCategoryResponseEntity workshopBackup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        requestPermission();
        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(SplashActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(SplashActivity.this,
                        new String[]{Manifest.permission.INTERNET,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS},
                        200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 200:{

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLists();

                } else {
                    Toast.makeText(SplashActivity.this,"Please enable permissions in settings",Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void getLists() {
        RESTClientImplementation.getEventsList(new EventsCategoryResponseEntity.eventCategoryListInterface() {
            @Override
            public void onListLoaded(EventsCategoryResponseEntity eventsCategoryResponseEntity, VolleyError error) {
                eventBackup = eventsCategoryResponseEntity;
                UserDetails.setEventList(SplashActivity.this,new Gson().toJson(eventsCategoryResponseEntity));
                RESTClientImplementation.getWorkshopsList(new WorkshopsCategoryResponseEntity.workshopCategoryListInterface() {
                    @Override
                    public void onListLoaded(WorkshopsCategoryResponseEntity workshopsCategoryResponseEntity, VolleyError error) {
                        workshopBackup = workshopsCategoryResponseEntity;
                        UserDetails.setWorkshopList(SplashActivity.this,new Gson().toJson(workshopsCategoryResponseEntity));
                        validateLogin();
                    }
                },SplashActivity.this);
            }
        }, SplashActivity.this);


    }

    void validateLogin(){
        if (UserDetails.getIntroOver(SplashActivity.this)) {
            if (!UserDetails.isUserLoggedIn(SplashActivity.this)) {
                goToActivity(LoginActivity.class);
            } else {
                goToActivity(HomeActivity.class);
            }
        } else {
            goToActivity(IntroActivity.class);
        }
    }

    void goToActivity(Class c){
        Intent intent = new Intent(SplashActivity.this, c);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
