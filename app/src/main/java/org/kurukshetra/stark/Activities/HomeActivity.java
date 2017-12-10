package org.kurukshetra.stark.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.eftimoff.viewpagertransformers.CubeOutTransformer;
import com.eftimoff.viewpagertransformers.RotateUpTransformer;

import org.kurukshetra.stark.Adapters.HomeScreenPagerAdapter;
import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.LogoutEntity;
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.RESTclient.RESTClientImplementation;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    TextView title;
    FloatingActionButton contactFAB,logoutFAB,aboutFAB,profileFAB;
    ViewPager viewPager;
    RelativeLayout rlRoot,rlprogresshome;
    int[][] mResources = {
            {R.drawable.events,R.drawable.gradient_blue_red,R.string.events},
            {R.drawable.workshop,R.drawable.gradient_ocean,R.string.workshops},
            {R.drawable.karnival,R.drawable.gradient_red_purple,R.string.karnival}
    };
    HomeScreenPagerAdapter homeScreenPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_home);

        contactFAB = findViewById(R.id.contactFAB);
        logoutFAB = findViewById(R.id.logoutFAB);
        aboutFAB = findViewById(R.id.about);
        profileFAB = findViewById(R.id.profileFAB);
        rlRoot = findViewById(R.id.rlRoot);
        rlprogresshome = findViewById(R.id.rlprogresshome);

        logoutFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });

        aboutFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(AboutActivity.class);
            }
        });

        profileFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(ProfileActivity.class);
            }
        });

        contactFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this,view,"transition");
                int revealX = (int) (view.getX() + view.getWidth() /2);
                int revealY = (int) (view.getY() + view.getHeight() /2);
                Intent intent = new Intent(HomeActivity.this,ContactsActivity.class);
                intent.putExtra(ContactsActivity.EXTRA_CIRCULAR_REVEAL_X, revealX);
                intent.putExtra(ContactsActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY);
                ActivityCompat.startActivity(HomeActivity.this,intent,activityOptionsCompat.toBundle());
            }
        });

        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        homeScreenPagerAdapter = new HomeScreenPagerAdapter(this,mResources);
        viewPager.setOnPageChangeListener(this);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);
        viewPager.setPageTransformer(true, new CubeOutTransformer());
        viewPager.setAdapter(homeScreenPagerAdapter);
        homeScreenPagerAdapter.setOnPageClickListener(new HomeScreenPagerAdapter.OnPageClick() {
            @Override
            public void onItemClick(int pos) {
                if(pos == 0){
                    goToActivity(EventsActivity.class);
                }else if(pos == 1){
                    goToActivity(WorkshopsActivity.class);
                }
            }
        });
    }

    void showProgress(){
        rlprogresshome.setVisibility(View.VISIBLE);
    }

    void hideProgress(){
        rlprogresshome.setVisibility(View.GONE);
    }

    private void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("You sure ? :/");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showProgress();
                RESTClientImplementation.logout(new LogoutEntity.RestClientInterface() {
                    @Override
                    public void onLogin(Boolean success, int code,VolleyError error) {
                        if(error == null && code == 200 && success){
                            hideProgress();
                            UserDetails.setUserLoggedIn(HomeActivity.this,false);
                            UserDetails.setUserToken(HomeActivity.this,"");
                            Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }else if(code == 402 || error != null){
                            hideProgress();
                            UserDetails.setUserLoggedIn(HomeActivity.this,false);
                            UserDetails.setUserToken(HomeActivity.this,"");
                            Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                },HomeActivity.this);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        rlRoot.setBackground(getDrawable(mResources[position][1]));
    }
    @Override
    public void onPageSelected(int position) {

    }

    private void goToActivity(Class TargetClass) {
        Intent intent = new Intent(HomeActivity.this,TargetClass);
        startActivity(intent);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {

    }
}
