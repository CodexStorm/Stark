package org.kurukshetra.stark.Entities;

/**
 * Created by sre on 11/28/17.
 */


public class ContactsEntity {
    String name;
    long phno;

    public ContactsEntity(String name, long phno) {
        this.name = name;
        this.phno = phno;
    }

    public String getName() {
        return name;
    }

    public long getPhno() {
        return phno;
    }
}


