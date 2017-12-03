package org.kurukshetra.stark.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.kurukshetra.stark.Entities.CategoriesEntity;
import org.kurukshetra.stark.Entities.CategoriesList;
import org.kurukshetra.stark.Entities.EventsEntity;
import org.kurukshetra.stark.RESTclient.EventsList;
import org.kurukshetra.stark.RESTclient.RestClientImplementation;
import org.kurukshetra.stark.R;

/**
 * Created by Balaji on 11/25/2017.
 */

public class EventsActivity extends AppCompatActivity{

    Button displayEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        displayEvents=(Button)findViewById(R.id.displayEvents);
        displayEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventsList.listEvents(EventsActivity.this);

            }
        });


    }
    }
