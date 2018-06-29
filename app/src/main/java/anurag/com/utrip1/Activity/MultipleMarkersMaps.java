package anurag.com.utrip1.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import anurag.com.utrip1.R;

public class MultipleMarkersMaps extends AppCompatActivity {

    double  lat4[] = new double[12];
    double  lon4[] = new double[12];





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_markers_maps);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        /**
         * 1. Receive data via intent
         * 2. How to connect Fragment with activity
         * Layout -> Framelayout
         * 3. Pass data to fragment via bundle
         *
         *
         * IN FRAGMENT ->
         * 1. Receive data from bundle in onCreate
         * 2. Inflate/attach any view items in on
         */

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lat4 = extras.getDoubleArray("mapmarker1");
            lon4 = extras.getDoubleArray("mapmarker2");
       }


        MapMarkersFragment fragment = new MapMarkersFragment();

        fragmentTransaction.add(R.id.framelayout, fragment, "MultipleMarker");
        fragmentTransaction.commit();
    }
}
