package org.kurukshetra.stark.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import org.kurukshetra.stark.Entities.ContactsEntity;
import org.kurukshetra.stark.R;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by sre on 11/24/17.
 */

public class CutomAdapter extends BaseAdapter {

    Context c;
    ArrayList<ContactsEntity> contactsEntities;
    LayoutInflater inflater;

    public CutomAdapter(Context c, ArrayList<ContactsEntity> contactsEntities) {
        this.c = c;
        this.contactsEntities = contactsEntities;
    }

    @Override
    public int getCount() {
        return contactsEntities.size();
    }

    @Override
    public Object getItem(int i) {
        return contactsEntities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater==null)
        {
            inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view==null)
        {
        view=inflater.inflate(R.layout.contact_card,viewGroup,false);
        }



        TextView nametxt=(TextView)view.findViewById(R.id.nameTxt);
TextView desc=(TextView)view.findViewById(R.id.descTxt);
        ImageButton btn=(ImageButton)view.findViewById(R.id.imageButton2);
        String name;
        name = contactsEntities.get(i).getName();
    final long ph= contactsEntities.get(i).getPhno();
       // final int number = Integer.valueOf(ph);

nametxt.setText(name);
desc.setText(String.valueOf(ph));

btn.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v)
    {

        /**Toast.makeText(c,String.valueOf(ph),Toast.LENGTH_SHORT).show();**/
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+ph));
       startActivity(c,intent,null);
    }

});

        return view;
    }
}
