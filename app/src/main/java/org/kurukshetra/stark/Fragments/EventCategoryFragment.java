package org.kurukshetra.stark.Fragments;


import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.cleveroad.fanlayoutmanager.FanLayoutManager;
import com.cleveroad.fanlayoutmanager.FanLayoutManagerSettings;

import org.kurukshetra.stark.Adapters.EventsAdapter;
import org.kurukshetra.stark.Entities.CategoriesResponseEntity;
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.RESTclient.RESTClientImplementation;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventCategoryFragment extends Fragment {

    RecyclerView eventsCategoryRecyclerView;
    FanLayoutManager fanLayoutManager;
    EventsAdapter eventsAdapter=null;
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
        RESTClientImplementation.listEvents(new CategoriesResponseEntity.eventCategoryListInterface() {
            @Override
            public void onListLoaded(CategoriesResponseEntity categoriesResponseEntity, VolleyError error) {
                eventsAdapter = new EventsAdapter(categoriesResponseEntity,getContext());
                eventsCategoryRecyclerView.setAdapter(eventsAdapter);
                eventsAdapter.notifyDataSetChanged();
                eventsAdapter.setOnItemClickListener(new EventsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int pos, final View view) {
                        if (fanLayoutManager.getSelectedItemPosition() != pos) {
                            fanLayoutManager.switchItem(eventsCategoryRecyclerView, pos);
                        }else {
                            fanLayoutManager.straightenSelectedItem(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    onClick(view, fanLayoutManager.getSelectedItemPosition());
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
        },getActivity());

       return view;
    }

    public void onClick(View view, int pos) {
        /*FullInfoTabFragment fragment = FullInfoTabFragment.newInstance(mAdapter.getModelByPos(pos));
        fragment.setSharedElementEnterTransition(new SharedTransitionSet());
        fragment.setEnterTransition(new Fade());
        setExitTransition(new Fade());
        fragment.setSharedElementReturnTransition(new SharedTransitionSet());
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(view, "shared")
                .replace(R.id.root, fragment)
                .addToBackStack(null)
                .commit();*/
        Toast.makeText(getActivity(),pos+"",Toast.LENGTH_SHORT).show();
    }

}
