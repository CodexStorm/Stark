package org.kurukshetra.stark.Fragments.Events;


import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.kurukshetra.stark.Adapters.EventListAdapter;
import org.kurukshetra.stark.Entities.Events.EventsEntity;
import org.kurukshetra.stark.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment {
    List<EventsEntity> eventsList;
    EventListAdapter eventListAdapter;
    RecyclerView eventListRecyclerView;
    ImageView bgimage;
    int[] colors;
    Bundle bundle;
    public EventListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        eventListRecyclerView = view.findViewById(R.id.eventListRecyclerView);

        bundle=getArguments();
        colors = new int[]{bundle.getInt("color1"),bundle.getInt("color2")};
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gd.setCornerRadius(0f);

        Drawable drawable = getActivity().getDrawable(bundle.getInt("bgimage"));

        Type eventListType = new TypeToken<ArrayList<EventsEntity>>(){}.getType();

        eventsList = new Gson().fromJson(bundle.getString("event_list"),eventListType);
        eventListAdapter = new EventListAdapter(eventsList,getActivity(),drawable,gd);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        eventListRecyclerView.setLayoutManager(gridLayoutManager);
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(eventListRecyclerView.getContext(), R.anim.layout_fall_down);
        eventListRecyclerView.setLayoutAnimation(controller);
        eventListRecyclerView.setAdapter(eventListAdapter);
        eventListAdapter.notifyDataSetChanged();
        eventListAdapter.setOnItemClickListener(new EventListAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int pos, View view) {
                EventDetailFragment eventDetailFragment = new EventDetailFragment();
                eventDetailFragment.setEnterTransition(new Explode());
                setExitTransition(new Fade());
                Bundle b = new Bundle();
                b.putString("event",new Gson().toJson(eventsList.get(pos)));
                b.putInt("bgimage",bundle.getInt("bgimage"));
                b.putInt("color1",bundle.getInt("color1"));
                b.putInt("color2",bundle.getInt("color2"));
                eventDetailFragment.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,eventDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

}
