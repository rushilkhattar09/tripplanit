package anurag.com.utrip1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import anurag.com.utrip1.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class NewMapMarker extends FragmentActivity {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_map_marker);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

   /* private void setUpMapIfNeeded()
    {
        if (mMap == null)
        {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (mMap != null)
            {
                setUpMap();
            }
        }
    }*/

    private void setUpMap()
    {
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.7253, 75.8655)).title("Indore"));
        // here is marker Adding code
    }
}

