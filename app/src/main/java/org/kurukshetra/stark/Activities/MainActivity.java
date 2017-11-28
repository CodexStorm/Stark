package org.kurukshetra.stark.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.kurukshetra.stark.Activities.mFragment.Content;
import org.kurukshetra.stark.Activities.mFragment.Creative;
import org.kurukshetra.stark.Activities.mFragment.Design;
import org.kurukshetra.stark.Activities.mFragment.Marketing;
import org.kurukshetra.stark.Activities.mFragment.MyPagerAdpater;
import org.kurukshetra.stark.Activities.mFragment.brandrelations;
import org.kurukshetra.stark.Activities.mFragment.events;
import org.kurukshetra.stark.Activities.mFragment.finance;
import org.kurukshetra.stark.Activities.mFragment.guestlectures;
import org.kurukshetra.stark.Activities.mFragment.hospitality;
import org.kurukshetra.stark.Activities.mFragment.hr;
import org.kurukshetra.stark.Activities.mFragment.industrialrelations;
import org.kurukshetra.stark.Activities.mFragment.logistics;
import org.kurukshetra.stark.Activities.mFragment.media;
import org.kurukshetra.stark.Activities.mFragment.projects;
import org.kurukshetra.stark.Activities.mFragment.promo;
import org.kurukshetra.stark.Activities.mFragment.qac;
import org.kurukshetra.stark.Activities.mFragment.tech;
import org.kurukshetra.stark.Activities.mFragment.workshops;
import org.kurukshetra.stark.Activities.mFragment.xceed;
import org.kurukshetra.stark.R;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener,ViewPager.OnPageChangeListener  {
    ViewPager vp;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vp=(ViewPager)findViewById(R.id.mViewpager_ID);
        this.addPages();
        tabLayout= (TabLayout) findViewById(R.id.mTab_ID);
        tabLayout.setTabGravity(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(vp);

        tabLayout.setOnTabSelectedListener(this);


    }
    private void addPages()
    {
        MyPagerAdpater pagerAdapter=new MyPagerAdpater(this.getSupportFragmentManager());
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
