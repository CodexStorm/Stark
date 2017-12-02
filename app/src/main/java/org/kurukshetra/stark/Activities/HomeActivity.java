package org.kurukshetra.stark.Activities;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.kurukshetra.stark.Adapters.HomeScreenPagerAdapter;
import org.kurukshetra.stark.R;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    TextView title;
    ViewPager viewPager;
    int[][] mResources = {
            {R.drawable.events,R.drawable.gradient_blue_red,R.string.events},
            {R.drawable.workshop,R.drawable.gradient_ocean,R.string.workshops},
            {R.drawable.karnival,R.drawable.gradient_red_purple,R.string.karnival}
    };
    HomeScreenPagerAdapter homeScreenPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_home);
       // title = findViewById(R.id.title);
        //title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/righteous.ttf"));
        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        homeScreenPagerAdapter = new HomeScreenPagerAdapter(this,mResources);
        viewPager.setOnPageChangeListener(this);
        viewPager.setAdapter(homeScreenPagerAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            viewPager.setBackground(getDrawable(mResources[position][1]));
    }
    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
