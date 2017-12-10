package org.kurukshetra.stark.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        progressProfile = findViewById(R.id.progressProfile);

        RESTClientImplementation.getProfile(UserDetails.getUserToken(ProfileActivity.this), new ProfileResponseEntity.profileGetInterface() {
            @Override
            public void onProfileLoaded(int code, ProfileEntity profileEntity, List<WeEntity> we, VolleyError error) {
                progressProfile.setVisibility(View.GONE);
                if(code == 200 && error == null && profileEntity != null && we != null){
                    profile = profileEntity;
                }
            }
        },ProfileActivity.this);
    }
}
