package org.kurukshetra.stark.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.R;
import org.w3c.dom.Text;

public class KarnivalActivity extends AppCompatActivity {

    private TextView karnival;
    private TextView comingSoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karnival);

        karnival = (TextView)findViewById(R.id.Karnival);
        comingSoon = (TextView)findViewById(R.id.comingSoon);

        karnival.setTypeface(UserDetails.getRightiousFont(KarnivalActivity.this));
        comingSoon.setTypeface(UserDetails.getRightiousFont(KarnivalActivity.this));
    }
}
