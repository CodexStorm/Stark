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
import org.kurukshetra.stark.Adapters.CutomAdapter;
import org.kurukshetra.stark.R;

import java.util.ArrayList;

/**
 * Created by sre on 11/24/17.
 */

public class xceed extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.xceed_frag,container,false);
        ListView lv= (ListView)rootView.findViewById(R.id.xceedListView);

        FloatingActionButton fab;
        fab = (FloatingActionButton)rootView.findViewById(R.id.fab7);
        CutomAdapter adapter=new CutomAdapter(this.getActivity(),getContent());
        lv.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rec="xceed@kurukshetra.org.in";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + rec));
                startActivity(intent);
            }
        });

        return rootView;
    }


    private ArrayList<ContactsEntity> getContent() {
        ArrayList<ContactsEntity> arr=new ArrayList<>();
        ContactsEntity contactsEntity =new ContactsEntity("Ashwin Karthik.A",9677241086L);
        arr.add(contactsEntity);
        contactsEntity =new ContactsEntity("Kiran Pranesh.J",9543815552L);
        arr.add(contactsEntity);
        contactsEntity =new ContactsEntity("Sandhya A.K",9486960291L);
        arr.add(contactsEntity);

        return arr;
    }
    @Override
    public String toString() {
        String title="xceed & karnival";
        return title;
    }

}
