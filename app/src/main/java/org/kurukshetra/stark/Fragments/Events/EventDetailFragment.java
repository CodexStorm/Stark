package org.kurukshetra.stark.Fragments.Events;


import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.Events.EventsEntity;
import org.kurukshetra.stark.Entities.TabEntity;
import org.kurukshetra.stark.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailFragment extends Fragment {

    EventsEntity eventsEntity;
    TextView event_name,tab1;
    List<TabEntity> tabs;
    ImageView eventDetailImage;
    int[] colors;
    RelativeLayout rldetail;

    public EventDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        Bundle bundle = getArguments();
        eventsEntity = new Gson().fromJson(bundle.getString("event"),EventsEntity.class);

        rldetail = view.findViewById(R.id.rldetail);

        colors = new int[]{bundle.getInt("color1"),bundle.getInt("color2")};
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gd.setCornerRadius(0f);

        eventDetailImage = view.findViewById(R.id.eventDetailImage);
        eventDetailImage.setImageDrawable(getActivity().getDrawable(bundle.getInt("bgimage")));
        rldetail.setBackground(gd);
        rldetail.setAlpha(0.5f);

        event_name = view.findViewById(R.id.event_name);
        event_name.setText(eventsEntity.getEventName());
        event_name.setTypeface(UserDetails.getRightiousFont(getContext()));

        tab1 = view.findViewById(R.id.tab1);
        tabs = eventsEntity.getTabs();
        if(tabs != null && tabs.size() != 0) {
            tab1.setText(Html.fromHtml(tabs.get(0).getContent()));
        }

        return view;
    }

}
