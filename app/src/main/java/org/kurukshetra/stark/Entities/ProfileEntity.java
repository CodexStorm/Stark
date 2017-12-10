package org.kurukshetra.stark.Entities;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by omprakash on 8/12/17.
 */

public class ProfileEntity {
    String cid,name,password,mobile,organization,field,year,gender,dob,sacode,refcode;
    boolean student,sa,online,visited,voted;
    int unconfirmed,count;

    public ProfileEntity() {
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

    public String getRefcode() {
        return refcode;
    }

    public void setRefcode(String refcode) {
        this.refcode = refcode;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public boolean isSa() {
        return sa;
    }

    public void setSa(boolean sa) {
        this.sa = sa;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public int getUnconfirmed() {
        return unconfirmed;
    }

    public void setUnconfirmed(int unconfirmed) {
        this.unconfirmed = unconfirmed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSacode() {
        return sacode;
    }

    public void setSacode(String sacode) {
        this.sacode = sacode;
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
        sigupPostEntity.remove("online");
        sigupPostEntity.remove("refcode");
        return sigupPostEntity;
    }
}
