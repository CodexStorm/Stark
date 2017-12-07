package org.kurukshetra.stark.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import org.kurukshetra.stark.Adapters.EventCategoryAdapter;
import org.kurukshetra.stark.Entities.CategoriesResponseEntity;
import org.kurukshetra.stark.Fragments.Events.EventCategoryFragment;
import org.kurukshetra.stark.R;


public class EventsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);
        if (findViewById(R.id.fragment_container) != null) {
            EventCategoryFragment eventCategoryFragment = new EventCategoryFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,eventCategoryFragment).commit();
        }

    }
}
