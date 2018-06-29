package anurag.com.utrip1.Activity;

/**
 * Created by rushilkhattar on 30/01/18.
 */

public class Album2 {

    private String place3;
    private String type3;
    private double lat3;
    private double lon3;
    private double rating3;
    private int numOfSongs;
    private int thumbnail;
    private String name;

    public Album2() {
    }

    public Album2(String place3, String type3,double lat3, double lon3,double rating3, int thumbnail) {
        this.place3 = place3;
        //this.numOfSongs = numOfSongs;
        this.type3 = type3;
        this.lat3 = lat3;
        this.lon3 = lon3;
        this.rating3 = rating3;

        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place3;
    }

    public void setPlace(String place3) {
        this.place3 = place3;
    }

    public String getType() {
        return type3;
    }

    public void setType(String type3) {
        this.type3 = type3;
    }

    public Double getlat() {
        return lat3;
    }

    public void setlat(Double lat3) {
        this.lat3 = lat3;
    }


    public Double getlon() {
        return lon3;
    }

    public void setlon(Double lon3) {
        this.lon3 = lon3;
    }

    public Double getrating() {
        return rating3;
    }

    public void setrating(Double rating3) {
        this.rating3 = rating3;
    }


    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
