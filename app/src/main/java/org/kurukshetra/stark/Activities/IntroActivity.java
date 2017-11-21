package org.kurukshetra.stark.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;

import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.SampleSlide;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(SampleSlide.newInstance(R.layout.intro_custom_layout1));
        addSlide(SampleSlide.newInstance(R.layout.intro_custom_layout2));
        addSlide(SampleSlide.newInstance(R.layout.intro_custom_layout3));
        addSlide(SampleSlide.newInstance(R.layout.intro_custom_layout4));
        UserDetails.setIntroOver(IntroActivity.this,true);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {

        super.onDonePressed(currentFragment);
        if(!UserDetails.isUserLoggedIn(IntroActivity.this)) {
            Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(IntroActivity.this,"Already Logged in",Toast.LENGTH_SHORT).show();
        }
    }
}
