package org.kurukshetra.stark.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import org.kurukshetra.stark.Adapters.EventsAdapter;
import org.kurukshetra.stark.Entities.CategoriesResponseEntity;
import org.kurukshetra.stark.Fragments.EventCategoryFragment;
import org.kurukshetra.stark.R;

/**
 * Created by Balaji on 11/25/2017.
 */

public class EventsActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;
    private CategoriesResponseEntity categoriesResponseEntity;
    //List<String> countries =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_event);
        if (findViewById(R.id.fragment_container) != null) {
            EventCategoryFragment eventCategoryFragment = new EventCategoryFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,eventCategoryFragment).commit();
        }

    }



}
