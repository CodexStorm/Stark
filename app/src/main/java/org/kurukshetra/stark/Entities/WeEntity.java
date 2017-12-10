package org.kurukshetra.stark.Entities;

/**
 * Created by omprakash on 10/12/17.
 */

public class WeEntity {
    int we_id;
    String we_name;
    boolean workshop,paid;

    public int getWe_id() {
        return we_id;
    }

    public void setWe_id(int we_id) {
        this.we_id = we_id;
    }

    public String getWe_name() {
        return we_name;
    }

    public void setWe_name(String we_name) {
        this.we_name = we_name;
    }

    public boolean isWorkshop() {
        return workshop;
    }

    public void setWorkshop(boolean workshop) {
        this.workshop = workshop;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
