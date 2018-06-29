package anurag.com.utrip1.Activity;

import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;

import anurag.com.utrip1.Manifest;
import anurag.com.utrip1.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapMarkersFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap googleMap;

    private RelativeLayout rlMapLayout;
    double lat4[] = new double[12];
    double lon4[] = new double[12];


    double lat1[] = new double[12];
    double lon1[] = new double[12];
    String  places1[] = new String[12];

    int height, width;
    ArrayList<LatLngBean> markerList;

    HashMap<Marker, LatLngBean> hashMapMarker = new HashMap<>();


   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_map_markers2, null, false);

        setContentView(R.layout.activity_map_markers2;

        rlMapLayout=(RelativeLayout) findViewById(R.id.rlMapLayout);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lat4 = extras.getDoubleArray("mapmarker1");
            lon4 = extras.getDoubleArray("mapmarker2");
        }
        ((SupportMapFragment)this.getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);



        setData();

        return view;
    }
*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();

//        Bundle extras = getContext()getIntent().getExtras();
//        if (extras != null) {
//            lat4 = extras.getDoubleArray("mapmarker1");
//            lon4 = extras.getDoubleArray("mapmarker2");
//        }

        if (getArguments() != null) {
            lat4 = getArguments().getDoubleArray("params");
            lon4 = getArguments().getDoubleArray("params2");
        }

        JSONObject jsonObject1 = loadQuestionsFromAssets();
        try {
            JSONArray jsonArray = jsonObject1.getJSONArray("day").getJSONArray(0);
            for (int i = 0; i < jsonArray.length(); i++) {

                places1[i]=jsonArray.getJSONObject(i).getString("place");
                lat1[i]=jsonArray.getJSONObject(i).getDouble("lat");
                lon1[i]=jsonArray.getJSONObject(i).getDouble("lng");


            }
            //  Log.d(TAG, "onCreate2: " + jsonObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        setData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_map_markers2,
                container, false);

        rlMapLayout = (RelativeLayout) v.findViewById(R.id.rlMapLayout);

        View v1= v.findViewById(R.id.map);
        width = v1.getWidth();
        height = v1.getHeight();


        ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);

        return v;
    }

    private void setData() {
        markerList = new ArrayList<LatLngBean>();
        LatLngBean bean = new LatLngBean();
        bean.setTitle("Ahmedabad");
        bean.setSnippet("Hello,Ahmedabad");
        bean.setLatitude("23.0300");
        bean.setLongitude("72.5800");
        markerList.add(bean);

        LatLngBean bean1 = new LatLngBean();
        bean1.setTitle("Surat");
        bean1.setSnippet("Hello,Surat");
        bean1.setLatitude("21.1700");
        bean1.setLongitude("72.8300");
        markerList.add(bean1);

        LatLngBean bean2 = new LatLngBean();
        bean2.setTitle("Vadodara");
        bean2.setSnippet("Hello,Vadodara");
        bean2.setLatitude("22.3000");
        bean2.setLongitude("73.2000");
        markerList.add(bean2);

//        LoadingGoogleMap(markerList);
    }


    /**
     * @author Hasmukh Bhadani
     * Set googleMap if require
     */
  /*  private void setUpMapIfNeeded()
    {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // Google Play Services are not available
        if(status!=ConnectionResult.SUCCESS)
        {
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }
        else
        {
            if (googleMap == null)
            {

                if (googleMap != null)
                {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    googleMap.setMyLocationEnabled(true);
                    googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                    googleMap.getUiSettings().setZoomControlsEnabled(true);
                }
            }
        }
    }
*/
    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;
        if (googleMap != null) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            if (ActivityCompat.checkSelfPermission(getContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            } else {
                googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.getUiSettings().setZoomControlsEnabled(true);

          /*  LatLng surat = new LatLng(21.1700, 72.8300);
            googleMap.addMarker(new MarkerOptions().position(surat)
                    .title("Marker in Sydney").snippet("A"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(surat));

            LatLng vadodra = new LatLng(22.3000, 73.2000);
            googleMap.addMarker(new MarkerOptions().position(vadodra)
                    .title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(surat));*/

                LatLng place = new LatLng(lat1[5], lon1[5]);

                for (int i = 0; i < lat1.length - 1; i++) {

                    LatLng place1 = new LatLng(lat1[i], lon1[i]);
                    googleMap.addMarker(new MarkerOptions().position(place1)
                            .title(places1[i]).snippet("Destination No " + (i + 1))).showInfoWindow();

                }

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(place));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));

//            setData();


            }
        }
    }/**
     * @author Rushil Khattar
     * Loading Data to the GoogleMap
     */
    // -------------------------Google Map
    void LoadingGoogleMap(ArrayList<LatLngBean> arrayList) {
        if (googleMap != null) {
            googleMap.clear();
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            if (ActivityCompat.checkSelfPermission(getContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);

            if (arrayList.size() > 0) {
                try {
                    ArrayList<LatLng> listLatLng = new ArrayList<LatLng>();
                    for (int i = 0; i < arrayList.size(); i++) {
                        LatLngBean bean = arrayList.get(i);
                        if (bean.getLatitude().length() > 0 && bean.getLongitude().length() > 0) {
                            double lat = Double.parseDouble(bean.getLatitude());
                            double lon = Double.parseDouble(bean.getLongitude());

                            Marker marker = googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(lat, lon))
                                    .title(bean.getTitle())
                                    .snippet(bean.getSnippet())
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                            //Add Marker to Hashmap
                            hashMapMarker.put(marker, bean);

                            //Set Zoom Level of Map pin
                            LatLng object = new LatLng(lat, lon);
                            listLatLng.add(object);
                        }
                    }
                    SetZoomlevel(listLatLng);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

                    @Override
                    public void onInfoWindowClick(Marker position) {
                        LatLngBean bean = hashMapMarker.get(position);
                        //   Toast.makeText(getApplicationContext(), bean.getTitle(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
        } else {
            //  Toast.makeText(getApplicationContext(),"Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
        }
    }

    private JSONObject loadQuestionsFromAssets() {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @author Hasmukh Bhadani
     * Set Zoom level all pin withing screen on GoogleMap
     */
    public void SetZoomlevel(ArrayList<LatLng> listLatLng) {
        if (listLatLng != null && listLatLng.size() == 1) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(listLatLng.get(0), 10));
        } else if (listLatLng != null && listLatLng.size() > 1) {
            final Builder builder = LatLngBounds.builder();
            for (int i = 0; i < listLatLng.size(); i++) {
                builder.include(listLatLng.get(i));
            }

            final ViewTreeObserver treeObserver = rlMapLayout.getViewTreeObserver();
            treeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                @SuppressWarnings("deprecation")
                @Override
                public void onGlobalLayout() {
                    if (googleMap != null) {
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), width, height, 80));
                        rlMapLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                }
            });

        }
    }


}
