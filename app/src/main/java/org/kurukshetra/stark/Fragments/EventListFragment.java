package org.kurukshetra.stark.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.kurukshetra.stark.Adapters.EventListAdapter;
import org.kurukshetra.stark.Entities.EventsEntity;
import org.kurukshetra.stark.R;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment {
    List<EventsEntity> eventsList;
    EventListAdapter eventListAdapter;
    RecyclerView eventListRecyclerView;
    public EventListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        eventListRecyclerView = view.findViewById(R.id.eventListRecyclerView);
        Bundle bundle=getArguments();
        Type eventListType = new TypeToken<ArrayList<EventsEntity>>(){}.getType();
        view.findViewById(R.id.frame).setBackgroundColor(bundle.getInt("bg"));
        eventsList = new Gson().fromJson(bundle.getString("event_list"),eventListType);
        eventListAdapter = new EventListAdapter(eventsList,getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        eventListRecyclerView.setLayoutManager(gridLayoutManager);
        eventListRecyclerView.setAdapter(eventListAdapter);
        eventListAdapter.notifyDataSetChanged();
        return view;
    }

}
