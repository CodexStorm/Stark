package org.kurukshetra.stark.Fragments;


import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.cleveroad.fanlayoutmanager.FanLayoutManager;
import com.cleveroad.fanlayoutmanager.FanLayoutManagerSettings;
import com.google.gson.Gson;

import org.kurukshetra.stark.Adapters.EventCategoryAdapter;
import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.CategoriesResponseEntity;
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.RESTclient.RESTClientImplementation;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventCategoryFragment extends Fragment {

    RecyclerView eventsCategoryRecyclerView;
    FanLayoutManager fanLayoutManager;
    CategoriesResponseEntity backup;
    EventCategoryAdapter eventCategoryAdapter =null;
    public EventCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_category, container, false);
        eventsCategoryRecyclerView = view.findViewById(R.id.eventsCategoryRecyclerView);
        FanLayoutManagerSettings fanLayoutManagerSettings = FanLayoutManagerSettings
                .newBuilder(getContext())
                .withFanRadius(true)
                .withViewHeightDp(200)
                .withViewWidthDp(180)
                .build();
        fanLayoutManager = new FanLayoutManager(getContext(),fanLayoutManagerSettings);
        eventsCategoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        eventsCategoryRecyclerView.setLayoutManager(fanLayoutManager);
        if(!UserDetails.getEventList(getActivity()).equals("")) {
            backup = new Gson().fromJson(UserDetails.getEventList(getActivity()),CategoriesResponseEntity.class);
            populateRecyclerView(backup);
        }else {
            RESTClientImplementation.listEvents(new CategoriesResponseEntity.eventCategoryListInterface() {
                @Override
                public void onListLoaded(CategoriesResponseEntity categoriesResponseEntity, VolleyError error) {
                    UserDetails.setEventList(getActivity(),new Gson().toJson(categoriesResponseEntity));
                    backup = categoriesResponseEntity;
                    populateRecyclerView(categoriesResponseEntity);
                }
            }, getActivity());
        }

       return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    void populateRecyclerView(CategoriesResponseEntity categoriesResponseEntity){
        eventCategoryAdapter = new EventCategoryAdapter(categoriesResponseEntity,getContext());
        eventsCategoryRecyclerView.setAdapter(eventCategoryAdapter);
        eventCategoryAdapter.notifyDataSetChanged();
        eventCategoryAdapter.setOnItemClickListener(new EventCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int pos, final View view, final int color) {
                if (fanLayoutManager.getSelectedItemPosition() != pos) {
                    fanLayoutManager.switchItem(eventsCategoryRecyclerView, pos);
                }else {
                    fanLayoutManager.straightenSelectedItem(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            onClick(view, fanLayoutManager.getSelectedItemPosition(),color);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }
            }
        });
    }

    public void onClick(View view, int pos, int color) {

        EventListFragment eventListFragment = new EventListFragment();
        eventListFragment.setEnterTransition(new Fade());
        setExitTransition(new Fade());
        Bundle bundle = new Bundle();
        bundle.putString("event_list",new Gson().toJson(backup.getCategories().get(pos).getEvents()));
        bundle.putInt("bg",color);
        eventListFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,eventListFragment)
                .addToBackStack(null)
                .commit();
    }

}
