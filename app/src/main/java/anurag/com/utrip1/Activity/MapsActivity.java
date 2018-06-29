package anurag.com.utrip1.Activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import anurag.com.utrip1.Activity.Model.Data;
import anurag.com.utrip1.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    List<String[]> mplaces = new ArrayList<>();
    List<String[]> mLat = new ArrayList<>();
    List<String[]> mLong = new ArrayList<>();
    String[] day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        mplaces=(ArrayList<String[]>) getIntent().getSerializableExtra("places");
//        mLat=(ArrayList<String[]>) getIntent().getSerializableExtra("lat");
//        mLong=(ArrayList<String[]>) getIntent().getSerializableExtra("lng");
        day=getIntent().getStringArrayExtra("places");

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        preparePlaceListData (googleMap);
        // Add a marker in Sydney and move the camera

        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));

    }
    private void preparePlaceListData (GoogleMap googleMap)
    {
        for(int i=0;i<day.length;i++)
        {
            String [] separated = day [i].split("/");
            LatLng latLng = new LatLng(Double.parseDouble(separated[1]), Double.parseDouble(separated[2]));
            googleMap.addMarker(new MarkerOptions().position(latLng).title(separated[0]));
            if(i==0)
            {googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));}
        }

//        for (int i = 0; i < mplaces.size(); i++) {
//            String[] abc = mplaces.get(i);
//            String[] lat = mLat.get(i);
//            String[] lng = mLong.get(i);
//            if (i == 0) {
//                if (sel_days < abc.length ) {
//
//                    LatLng latLng = new LatLng(Double.parseDouble(lat[i]), Double.parseDouble(lng[i]));
//                    googleMap.addMarker(new MarkerOptions().position(latLng).title(abc[i]));
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//                    googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                    // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 200, null);
//
//                }
//            } else if (i == 1) {
//                if (sel_days < abc.length) {
//                    LatLng latLng = new LatLng(Double.parseDouble(lat[i]), Double.parseDouble(lng[i]));
//                    googleMap.addMarker(new MarkerOptions().position(latLng).title(abc[i]));
//
//                }
//            } else if (i == 2) {
//                if (sel_days < abc.length) {
//                    LatLng latLng = new LatLng(Double.parseDouble(lat[i]), Double.parseDouble(lng[i]));
//                    googleMap.addMarker(new MarkerOptions().position(latLng).title(abc[i]));
//
//                }
//            } else if (i == 3) {
//                if (sel_days < abc.length ) {
//                    LatLng latLng = new LatLng(Double.parseDouble(lat[i]), Double.parseDouble(lng[i]));
//                    googleMap.addMarker(new MarkerOptions().position(latLng).title(abc[i]));
//
//                }
//            } else if (i == 4) {
//                if (sel_days < abc.length ) {
//                    LatLng latLng = new LatLng(Double.parseDouble(lat[i]), Double.parseDouble(lng[i]));
//                    googleMap.addMarker(new MarkerOptions().position(latLng).title(abc[i]));
//
//                }
//            } else if (i == 5) {
////                if (sel_days < abc.length && sel_days < lat.length && sel_days < lng.length) {
////                    LatLng latLng = new LatLng(Double.parseDouble(lat[i]), Double.parseDouble(lng[i]));
////                    googleMap.addMarker(new MarkerOptions().position(latLng).title(abc[i]));
////
////                }
//            } else if (i == 6) {
//                if (sel_days < abc.length) {
//                    LatLng latLng = new LatLng(Double.parseDouble(lat[i]), Double.parseDouble(lng[i]));
//                    googleMap.addMarker(new MarkerOptions().position(latLng).title(abc[i]));
//
//                }
//            } else if (i == 7) {
//                if (sel_days < abc.length ) {
//                    LatLng latLng = new LatLng(Double.parseDouble(lat[i]), Double.parseDouble(lng[i]));
//                    googleMap.addMarker(new MarkerOptions().position(latLng).title(abc[i]));
//
//                }
//            } else if (i == 8) {
//                if (sel_days < abc.length ) {
//                    LatLng latLng = new LatLng(Double.parseDouble(lat[i]), Double.parseDouble(lng[i]));
//                    googleMap.addMarker(new MarkerOptions().position(latLng).title(abc[i]));
//
//                }
//            }
//        }

    }
}
