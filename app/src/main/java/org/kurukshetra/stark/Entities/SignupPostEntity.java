package org.kurukshetra.stark.Entities;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by omprakash on 8/12/17.
 */

public class SignupPostEntity {
    String cid,name,password,mobile,organization,field,year,gender,dob;
    boolean student,sa;

    public SignupPostEntity() {
    }

    public SignupPostEntity(String cid, String name, String password, String mobile, String organization, String field, String year, String gender, String dob, boolean student, boolean sa) {
        this.cid = cid;
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.organization = organization;
        this.field = field;
        this.year = year;
        this.gender = gender;
        this.dob = dob;
        this.student = student;
        this.sa = sa;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean getStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public boolean getSa() {
        return sa;
    }

    public void setSa(boolean sa) {
        this.sa = sa;
    }

    public JSONObject getParams(){
        JSONObject sigupPostEntity = null;
        Gson gson = new Gson();
        String signupString = gson.toJson(this);
        try {
            sigupPostEntity = new JSONObject(signupString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sigupPostEntity.remove("dob");
        return sigupPostEntity;
    }
    public interface ProfileUpdateInterface{
        void onUpdate(int code, VolleyError error);
    }
}
