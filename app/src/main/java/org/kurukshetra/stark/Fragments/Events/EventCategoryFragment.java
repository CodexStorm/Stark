package org.kurukshetra.stark.Fragments.Events;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.kurukshetra.stark.Adapters.EventCategoryAdapter;
import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.Events.EventsCategoryResponseEntity;
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.RESTclient.RESTClientImplementation;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventCategoryFragment extends Fragment {

    RecyclerView eventsCategoryRecyclerView;
    EventsCategoryResponseEntity backup;
    EventCategoryAdapter eventCategoryAdapter = null;
    ImageView categoryFrame;
    GridLayoutManager linearLayoutManager;
    public EventCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_category, container, false);
        eventsCategoryRecyclerView = view.findViewById(R.id.eventsCategoryRecyclerView);
        linearLayoutManager = new GridLayoutManager(getContext(),2);
        eventsCategoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        eventsCategoryRecyclerView.setLayoutManager(linearLayoutManager);
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(eventsCategoryRecyclerView.getContext(), R.anim.layout_fall_down);
        eventsCategoryRecyclerView.setLayoutAnimation(controller);

        if(!UserDetails.getEventList(getActivity()).equals("")) {
            backup = new Gson().fromJson(UserDetails.getEventList(getActivity()),EventsCategoryResponseEntity.class);
            populateRecyclerView(backup);
        }else {
            RESTClientImplementation.getEventsList(new EventsCategoryResponseEntity.eventCategoryListInterface() {
                @Override
                public void onListLoaded(EventsCategoryResponseEntity eventsCategoryResponseEntity, VolleyError error) {
                    UserDetails.setEventList(getActivity(),new Gson().toJson(eventsCategoryResponseEntity));
                    backup = eventsCategoryResponseEntity;
                    populateRecyclerView(eventsCategoryResponseEntity);
                }
            }, getActivity());
        }

       return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    void populateRecyclerView(EventsCategoryResponseEntity eventsCategoryResponseEntity){
        eventCategoryAdapter = new EventCategoryAdapter(eventsCategoryResponseEntity,getContext());
        eventsCategoryRecyclerView.setAdapter(eventCategoryAdapter);
        eventCategoryAdapter.notifyDataSetChanged();
        eventsCategoryRecyclerView.scheduleLayoutAnimation();
        eventCategoryAdapter.setOnItemClickListener(new EventCategoryAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClicked(int pos, final View view, final int color1,final int color2, int bgimage) {
                    onClick(view,pos,color1,color2,bgimage);
            }
        });
    }

    public void onClick(View view, int pos, int color1,int color2,int bgimage) {

        EventListFragment eventListFragment = new EventListFragment();
        eventListFragment.setEnterTransition(new Fade());
        setExitTransition(new Fade());
        Bundle bundle = new Bundle();
        bundle.putString("event_list",new Gson().toJson(backup.getCategories().get(pos).getEvents()));
        bundle.putInt("color1",color1);
        bundle.putInt("color2",color2);
        bundle.putInt("bgimage",bgimage);
        eventListFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,eventListFragment)
                .addToBackStack(null)
                .commit();
    }

}
