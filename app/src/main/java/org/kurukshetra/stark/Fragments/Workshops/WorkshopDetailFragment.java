package org.kurukshetra.stark.Fragments.Workshops;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.Events.EventsEntity;
import org.kurukshetra.stark.Entities.TabEntity;
import org.kurukshetra.stark.Entities.Workshops.WorkshopsEntity;
import org.kurukshetra.stark.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkshopDetailFragment extends Fragment {

    WorkshopsEntity workshopsEntity;
    TextView event_name,tab1;
    List<TabEntity> tabs;

    public WorkshopDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        Bundle bundle = getArguments();
        workshopsEntity = new Gson().fromJson(bundle.getString("workshop"),WorkshopsEntity.class);

        event_name = view.findViewById(R.id.event_name);
        event_name.setText(workshopsEntity.getName());
        event_name.setTypeface(UserDetails.getRightiousFont(getContext()));

        tab1 = view.findViewById(R.id.tab1);
        tabs = workshopsEntity.getTabs();
        if(tabs != null && tabs.size() != 0) {
            tab1.setText(Html.fromHtml(tabs.get(0).getContent()));
        }

        return view;
    }

}