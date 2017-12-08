package org.kurukshetra.stark.Fragments.Workshops;


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
import org.kurukshetra.stark.Adapters.WorkshopCategoryAdapter;
import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.Workshops.WorkshopsCategoryResponseEntity;
import org.kurukshetra.stark.Fragments.Events.EventListFragment;
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.RESTclient.RESTClientImplementation;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkshopCategoryFragment extends Fragment {

    RecyclerView eventsCategoryRecyclerView;
    WorkshopsCategoryResponseEntity backup;
    WorkshopCategoryAdapter workshopCategoryAdapter = null;
    ImageView categoryFrame;
    GridLayoutManager linearLayoutManager;
    public WorkshopCategoryFragment() {
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

        if(!UserDetails.getWorkshopList(getActivity()).equals("")) {
            backup = new Gson().fromJson(UserDetails.getWorkshopList(getActivity()),WorkshopsCategoryResponseEntity.class);
            populateRecyclerView(backup);
        }else {
            RESTClientImplementation.getWorkshopsList(new WorkshopsCategoryResponseEntity.workshopCategoryListInterface() {
                @Override
                public void onListLoaded(WorkshopsCategoryResponseEntity workshopsCategoryResponseEntity, VolleyError error) {
                    UserDetails.setWorkshopList(getActivity(),new Gson().toJson(workshopsCategoryResponseEntity));
                    backup = workshopsCategoryResponseEntity;
                    populateRecyclerView(workshopsCategoryResponseEntity);
                }
            },getActivity());
        }

       return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    void populateRecyclerView(WorkshopsCategoryResponseEntity workshopsCategoryResponseEntity){
        workshopCategoryAdapter = new WorkshopCategoryAdapter(workshopsCategoryResponseEntity,getContext());
        eventsCategoryRecyclerView.setAdapter(workshopCategoryAdapter);
        workshopCategoryAdapter.notifyDataSetChanged();
        eventsCategoryRecyclerView.scheduleLayoutAnimation();
        workshopCategoryAdapter.setOnItemClickListener(new WorkshopCategoryAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClicked(int pos, final View view, final int color1,final int color2, int bgimage) {
                    onClick(view,pos,color1,color2,bgimage);
            }
        });
    }

    public void onClick(View view, int pos, int color1,int color2,int bgimage) {

        WorkshopListFragment workshopListFragment = new WorkshopListFragment();
        workshopListFragment.setEnterTransition(new Fade());
        setExitTransition(new Fade());
        Bundle bundle = new Bundle();
        bundle.putString("workshop_list",new Gson().toJson(backup.getWorkshops().get(pos).getWorkshops()));
        bundle.putInt("color1",color1);
        bundle.putInt("color2",color2);
        bundle.putInt("bgimage",bgimage);
        workshopListFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,workshopListFragment)
                .addToBackStack(null)
                .commit();
    }

}
