package org.kurukshetra.stark.Entities;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by omprakash on 10/12/17.
 */

public class ProfileResponseEntity {
    StatusEntity status;
    ProfileEntity data;
    List<WeEntity> we;

    public ProfileEntity getData() {
        return data;
    }

    public void setData(ProfileEntity data) {
        this.data = data;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }


    public List<WeEntity> getWe() {
        return we;
    }

    public void setWe(List<WeEntity> we) {
        this.we = we;
    }

    public interface profileGetInterface{
        public void onProfileLoaded(int code, ProfileEntity profileEntity, List<WeEntity> we, VolleyError error);
    }
}
