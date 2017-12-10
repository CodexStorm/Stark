package org.kurukshetra.stark.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;

import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.ProfileEntity;
import org.kurukshetra.stark.Entities.ProfileResponseEntity;
import org.kurukshetra.stark.Entities.WeEntity;
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.RESTclient.RESTClientImplementation;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    RelativeLayout progressProfile;
    private ProfileEntity profile = null;
    TextView pname,pid,porg,pdep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_profile);
        progressProfile = findViewById(R.id.progressProfile);

        pname = findViewById(R.id.pname);
        pid = findViewById(R.id.pid);
        porg = findViewById(R.id.porg);
        pdep = findViewById(R.id.pdep);

        RESTClientImplementation.getProfile(UserDetails.getUserToken(ProfileActivity.this), new ProfileResponseEntity.profileGetInterface() {
            @Override
            public void onProfileLoaded(int code, ProfileEntity profileEntity, List<WeEntity> we, VolleyError error) {
                progressProfile.setVisibility(View.GONE);
                if(code == 200 && error == null && profileEntity != null && we != null){
                    profile = profileEntity;
                    pname.setText(profile.getName());
                    pid.setText(profile.getCid());
                    porg.setText(profile.getOrganization());
                    pdep.setText(profile.getField());
                }
            }
        },ProfileActivity.this);
    }
}
