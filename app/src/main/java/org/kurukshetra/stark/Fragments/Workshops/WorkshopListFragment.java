package org.kurukshetra.stark.Fragments.Workshops;


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
import org.kurukshetra.stark.Adapters.WorkshopListAdapter;
import org.kurukshetra.stark.Entities.Workshops.WorkshopsEntity;
import org.kurukshetra.stark.Fragments.Events.EventDetailFragment;
import org.kurukshetra.stark.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkshopListFragment extends Fragment {
    List<WorkshopsEntity> workshopList;
    WorkshopListAdapter workshopListAdapter;
    RecyclerView eventListRecyclerView;
    ImageView bgimage;
    Bundle bundle;

    public WorkshopListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        eventListRecyclerView = view.findViewById(R.id.eventListRecyclerView);

        bundle=getArguments();
        int[] colors = new int[]{bundle.getInt("color1"),bundle.getInt("color2")};
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gd.setCornerRadius(0f);

        /*bgimage = view.findViewById(R.id.bgimage);
        bgimage.setImageDrawable(getActivity().getDrawable(bundle.getInt("bgimage")));
        bgimage.setForeground(gd);
        bgimage.setAlpha(0.7f);*/
        Drawable drawable = getActivity().getDrawable(bundle.getInt("bgimage"));
        Type workshopListType = new TypeToken<ArrayList<WorkshopsEntity>>(){}.getType();

        workshopList = new Gson().fromJson(bundle.getString("workshop_list"),workshopListType);
        workshopListAdapter = new WorkshopListAdapter(workshopList,getActivity(),drawable,gd);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        eventListRecyclerView.setLayoutManager(gridLayoutManager);
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(eventListRecyclerView.getContext(), R.anim.layout_fall_down);
        eventListRecyclerView.setLayoutAnimation(controller);
        eventListRecyclerView.setAdapter(workshopListAdapter);
        workshopListAdapter.notifyDataSetChanged();
        workshopListAdapter.setOnItemClickListener(new WorkshopListAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int pos, View view) {
                WorkshopDetailFragment workshopDetailFragment = new WorkshopDetailFragment();
                workshopDetailFragment.setEnterTransition(new Explode());
                setExitTransition(new Fade());
                Bundle b = new Bundle();
                b.putString("workshop",new Gson().toJson(workshopList.get(pos)));
                b.putInt("bgimage",bundle.getInt("bgimage"));
                b.putInt("color1",bundle.getInt("color1"));
                b.putInt("color2",bundle.getInt("color2"));
                workshopDetailFragment.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,workshopDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

}
