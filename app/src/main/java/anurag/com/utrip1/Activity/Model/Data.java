package anurag.com.utrip1.Activity.Model;

import java.io.Serializable;

/**
 * Created by AnuragTrehan on 12/17/2016.
 */

public class Data implements Serializable
{
    String place_name,init_time,end_time;

    public Data() {
    }

    public Data(String place_name, String init_time, String end_time) {
        this.end_time = end_time;
        this.init_time = init_time;
        this.place_name = place_name;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getInit_time() {
        return init_time;
    }

    public void setInit_time(String init_time) {
        this.init_time = init_time;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }
}
