package org.kurukshetra.stark.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.kurukshetra.stark.Adapters.EventsAdapter;
import org.kurukshetra.stark.Entities.CategoriesList;
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.RESTclient.RESTClientImplementation;

/**
 * Created by Balaji on 11/25/2017.
 */

public class EventsActivity extends AppCompatActivity{


    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;
    private CategoriesList categoriesList;
    //List<String> countries =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventsAdapter=new EventsAdapter();
        setContentView(R.layout.activity_event);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(EventsActivity.this));
        categoriesList = new CategoriesList();
        categoriesList= RESTClientImplementation.listEvents(categoriesList,EventsActivity.this,eventsAdapter);
        Log.e("in main",Integer.toString(categoriesList.getCategoriesEntityList().size()));
        eventsAdapter=new EventsAdapter(categoriesList);
        eventsAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(eventsAdapter);

    }



}
