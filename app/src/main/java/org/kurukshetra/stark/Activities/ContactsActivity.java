package org.kurukshetra.stark.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;

import org.kurukshetra.stark.Fragments.Content;
import org.kurukshetra.stark.Fragments.Creative;
import org.kurukshetra.stark.Fragments.Design;
import org.kurukshetra.stark.Fragments.Marketing;
import org.kurukshetra.stark.Adapters.ContactsPagerAdpater;
import org.kurukshetra.stark.Fragments.brandrelations;
import org.kurukshetra.stark.Fragments.events;
import org.kurukshetra.stark.Fragments.finance;
import org.kurukshetra.stark.Fragments.guestlectures;
import org.kurukshetra.stark.Fragments.hospitality;
import org.kurukshetra.stark.Fragments.hr;
import org.kurukshetra.stark.Fragments.industrialrelations;
import org.kurukshetra.stark.Fragments.logistics;
import org.kurukshetra.stark.Fragments.media;
import org.kurukshetra.stark.Fragments.projects;
import org.kurukshetra.stark.Fragments.promo;
import org.kurukshetra.stark.Fragments.qac;
import org.kurukshetra.stark.Fragments.tech;
import org.kurukshetra.stark.Fragments.workshops;
import org.kurukshetra.stark.Fragments.xceed;
import org.kurukshetra.stark.R;

public class ContactsActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener,ViewPager.OnPageChangeListener  {
    ViewPager vp;
    TabLayout tabLayout;
    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";
    View rootLayout;

    private int revealX;
    private int revealY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final Intent intent = getIntent();
        rootLayout = findViewById(R.id.root_layout);
        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            rootLayout.setVisibility(View.INVISIBLE);
            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);
            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(revealX, revealY);
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } else {
            rootLayout.setVisibility(View.VISIBLE);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contact Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        vp=(ViewPager)findViewById(R.id.mViewpager_ID);
        this.addPages();
        tabLayout= (TabLayout) findViewById(R.id.mTab_ID);
        tabLayout.setTabGravity(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(vp);

        tabLayout.setOnTabSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void revealActivity(int x, int y) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
            circularReveal.setDuration(400);
            circularReveal.setInterpolator(new AccelerateInterpolator());
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        } else {
            finish();
        }
    }

    protected void unRevealActivity() {
        float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                rootLayout, revealX, revealY, finalRadius, 0);
        circularReveal.setDuration(400);
        circularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                rootLayout.setVisibility(View.INVISIBLE);
                finish();
            }
        });
        circularReveal.start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void addPages()
    {
        ContactsPagerAdpater pagerAdapter=new ContactsPagerAdpater(this.getSupportFragmentManager());
     pagerAdapter.addFragment(new Content());
        pagerAdapter.addFragment(new Creative());
        pagerAdapter.addFragment(new Design());
        pagerAdapter.addFragment(new promo());
        pagerAdapter.addFragment(new events());
        pagerAdapter.addFragment(new workshops());
        pagerAdapter.addFragment(new xceed());
        pagerAdapter.addFragment(new brandrelations());
        pagerAdapter.addFragment(new guestlectures());
        pagerAdapter.addFragment(new industrialrelations());
        pagerAdapter.addFragment(new finance());
        pagerAdapter.addFragment(new hospitality());
        pagerAdapter.addFragment(new logistics());
        pagerAdapter.addFragment(new Marketing());
        pagerAdapter.addFragment(new media());
        pagerAdapter.addFragment(new hr());
        pagerAdapter.addFragment(new qac());
        pagerAdapter.addFragment(new projects());
        pagerAdapter.addFragment(new tech());
        vp.setAdapter(pagerAdapter);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vp.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
