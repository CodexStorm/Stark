package org.kurukshetra.stark.Fragments.Contacts;

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

public class hospitality extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.hospitality_frag,container,false);
        ListView lv= (ListView)rootView.findViewById(R.id.hospitalityListView);

        FloatingActionButton fab;
        fab = (FloatingActionButton)rootView.findViewById(R.id.fab12);
        ContactsListAdapter adapter=new ContactsListAdapter(this.getActivity(),getContent());
        lv.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rec="hospitality@kurukshetra.org.in";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + rec));
                startActivity(intent);
            }
        });

        return rootView;
    }


    private ArrayList<ContactsEntity> getContent() {
        ArrayList<ContactsEntity> arr=new ArrayList<>();
        ContactsEntity contactsEntity =new ContactsEntity("Kowselya.M",9489223951L);
        arr.add(contactsEntity);
        contactsEntity =new ContactsEntity("Monisha.M",9597795364L);
        arr.add(contactsEntity);

        contactsEntity =new ContactsEntity("Sridhar.K.S",9443802106L);
        arr.add(contactsEntity);

        contactsEntity =new ContactsEntity("Thiru Thamizh Selvan",9941147740L);
        arr.add(contactsEntity);
        return arr;
    }
    @Override
    public String toString() {
        String title="hospitality";
        return title;
    }

}
