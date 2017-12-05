package org.kurukshetra.stark.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.kurukshetra.stark.Entities.ContactsEntity;
import org.kurukshetra.stark.Adapters.ContactsListAdapter;
import org.kurukshetra.stark.R;

import java.util.ArrayList;

/**
 * Created by sre on 11/24/17.
 */

public class promo extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.promo_frag,container,false);
        ListView lv= (ListView)rootView.findViewById(R.id.promoListView);

        FloatingActionButton fab;
        fab = (FloatingActionButton)rootView.findViewById(R.id.fab4);
        ContactsListAdapter adapter=new ContactsListAdapter(this.getActivity(),getContent());
        lv.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rec="promo@kurukshetra.org.in";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + rec));
                startActivity(intent);
            }
        });

        return rootView;
    }


    private ArrayList<ContactsEntity> getContent() {
        ArrayList<ContactsEntity> arr=new ArrayList<>();
        ContactsEntity contactsEntity =new ContactsEntity("Vijay Krishnan.K",9176187247L);
        arr.add(contactsEntity);
        return arr;
    }
    @Override
    public String toString() {
        String title="promo";
        return title;
    }

}
