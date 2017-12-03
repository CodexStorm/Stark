package org.kurukshetra.stark.Activities;

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
import android.widget.TextView;

import org.kurukshetra.stark.Adapters.HomeScreenPagerAdapter;
import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.R;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    TextView title;
    FloatingActionButton contactFAB;
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

        contactFAB = findViewById(R.id.contactFAB);

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
        title = findViewById(R.id.title);
        title.setTypeface(UserDetails.getRightiousFont(this));
        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        homeScreenPagerAdapter = new HomeScreenPagerAdapter(this,mResources);
        viewPager.setOnPageChangeListener(this);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);
        viewPager.setAdapter(homeScreenPagerAdapter);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           // viewPager.setBackground(getDrawable(mResources[position][1]));
    }
    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    public class ViewHolder {
        private View parallaxView;

        private ViewHolder() { this.parallaxView = null; }
        public ViewHolder(View parallaxView) { this.parallaxView = parallaxView; }
        public View getParallaxView() { return parallaxView; }
        public void setParallaxView(View parallaxView) { this.parallaxView = parallaxView; }
    }
}
