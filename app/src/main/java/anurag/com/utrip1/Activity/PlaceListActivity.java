package anurag.com.utrip1.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import anurag.com.utrip1.Activity.Adapter.PlaceListAdapter;
import anurag.com.utrip1.Activity.Model.Data;
import anurag.com.utrip1.Activity.Model.DividerItemDecoration;
import anurag.com.utrip1.Activity.Model.Places;
import anurag.com.utrip1.Activity.Model.RecyclerTouchListener;
import anurag.com.utrip1.Activity.Utils.BottomNavigationViewHelper;
import anurag.com.utrip1.R;

public class PlaceListActivity extends AppCompatActivity {

    private static final String TAG = "";
    //private static final int ACTIVITY_NUM = 4;
    private List<Data> datalist = new ArrayList<>();

    private Spinner days;
    private RecyclerView recyclerView;
    private PlaceListAdapter mAdapter;

    private AlbumsAdapter2 adapter;
    private List<Album2> albumList;

    private static String REGISTER_URL = "http://192.168.0.108:8087/post1";
    private static String KEY_LAT = "lat";
    private static String KEY_LNG = "lng";
    private static String KEY_MUST = "must_see";
    private static String KEY_ADVENTURE = "adventure";
    private static String KEY_ART = "art";
    private static String KEY_ENTERTAINMENT = "entertainment";
    private static String KEY_SHOPPING = "shopping";
    private static String KEY_RESTUARANT = "restaurant";
    private static String KEY_NATURE = "nature";
    private static String KEY_RELIGIOUS = "religious";
    private static String KEY_PARK = "park";
    private static String KEY_NO_DAYS = "no_days";

    List<String[]> mplaces = new ArrayList<>();
    List<String[]> mLat = new ArrayList<>();
    List<String[]> mLong = new ArrayList<>();

    String lati, lon, name;
    long no_days;
    Places places;
    int sel_days = 0;
    int must_see, adventure, art, entertainment, shopping, restaurant, nature, religious, park;
    private ProgressDialog progress;

    String[] build;
    String rad = "10000";
    String api_key = "AIzaSyDJW7_0Q0-7JNdjVZgINbyuVotBQM7g3dI";
    int x = 0;

    double  lat1[] = new double[12];
    double  lon1[] = new double[12];
    int   time1[] = new int[12];
    double  rating1[] = new double[12];
    String  type1[] = new String[12];
    String  places1[] = new String[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        //  setupBottomNavigationView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // initCollapsingToolbar();

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter2(this, albumList);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //days = (Spinner) findViewById(R.id.days);


        JSONObject jsonObject1 = loadQuestionsFromAssets();
        try {
            JSONArray jsonArray = jsonObject1.getJSONArray("day").getJSONArray(0);
            for (int i = 0; i < jsonArray.length(); i++) {

                Log.d(TAG, "onCreate: " + jsonArray.getJSONObject(i).getString("place"));
                places1[i]=jsonArray.getJSONObject(i).getString("place");
                type1[i]=jsonArray.getJSONObject(i).getString("type");
                rating1[i]=jsonArray.getJSONObject(i).getDouble("rating");
                time1[i]=jsonArray.getJSONObject(i).getInt("time1");
                lat1[i]=jsonArray.getJSONObject(i).getDouble("lat");
                lon1[i]=jsonArray.getJSONObject(i).getDouble("lng");


            }
            //  Log.d(TAG, "onCreate2: " + jsonObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        recyclerView.setHasFixedSize(true);

        mAdapter = new PlaceListAdapter(datalist);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        places = (Places) getIntent().getSerializableExtra("places");
        no_days = getIntent().getLongExtra("no_days", 0);

        build = getIntent().getStringArrayExtra("strings");
        must_see = getIntent().getIntExtra("must_see", 1);
        adventure = getIntent().getIntExtra("adventure", 1);
        art = getIntent().getIntExtra("art", 1);
        entertainment = getIntent().getIntExtra("entertainment", 1);
        shopping = getIntent().getIntExtra("shopping", 1);
        restaurant = getIntent().getIntExtra("restaurant", 1);
        nature = getIntent().getIntExtra("nature", 1);
        religious = getIntent().getIntExtra("religious", 1);
        park = getIntent().getIntExtra("park", 1);
//        nearBy(rad,"restaurant",places.getLat(),places.getLongi());
//        addSpiner();

        lati = places.getLat();
        Log.e("LAT", lati);
        lon = places.getLongi();
        Log.e("LON", lon);
//        design();
        Log.d("DATA", lati);
        Log.d("DATA", lon);
        Log.d("DATA", String.valueOf(no_days));
        Log.d("DATA", String.valueOf(must_see));
        Log.d("DATA", String.valueOf(adventure));
        Log.d("DATA", String.valueOf(art));
        Log.d("DATA", String.valueOf(entertainment));
        Log.d("DATA", String.valueOf(shopping));
        Log.d("DATA", String.valueOf(restaurant));
        Log.d("DATA", String.valueOf(nature));
        Log.d("DATA", String.valueOf(religious));
        Log.d("DATA", String.valueOf(park));

        getPostData();


    }

    /*   private void setupBottomNavigationView(){
           Log.d(TAG, "onCreate: starting.");
           BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
           BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView);

           BottomNavigationViewHelper.enableNavigation(PlaceListActivity.this, bottomNavigationView);

           Menu menu = bottomNavigationView.getMenu();
           MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
           menuItem.setChecked(true);
       }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.next, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.next_button) {

            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
//            intent.putExtra("no_days",no_days);
//            intent.putExtra("places", (ArrayList<String[]>) mplaces);
//            intent.putExtra("lat", (ArrayList<String[]>) mLat);
//            intent.putExtra("lng", (ArrayList<String[]>) mLong);
            String[] day = mplaces.get(sel_days);
            intent.putExtra("places", day);


            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addSpiner() {
//        int adr= Integer.parseInt(no_days);
        List<String> list = new ArrayList<String>();

        for (int i = 1; i <= no_days; i++) {
            list.add(String.valueOf(i));
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(PlaceListActivity.this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        days.setAdapter(dataAdapter);

        days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (mplaces.size() != 0) {
                    preparePlaceListData(i);
                }
                sel_days = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void preparePlaceListData(int position) {
        datalist.clear();
        String[] day = mplaces.get(position);
        Data data;

        for (int i = 0; i < day.length; i++) {
            String[] separated = day[i].split("/");
            data = new Data(separated[0], "09:00AM", "10:00AM");
            datalist.add(data);
        }
//     for(int i=0;i<mplaces.size();i++) {
//
//         String[] abc = mplaces.get(i);
//
//         if (i == 0) {
//             if (sel_days < abc.length) {
//
//                 data = new Data(abc[sel_days], "09:00AM", "10:00AM");
//                 datalist.add(data);
//             }
//         } else if (i == 1) {
//             if (sel_days < abc.length) {
//                 data = new Data(abc[sel_days], "10:00AM", "11:00AM");
//                 datalist.add(data);
//             }
//         }
//            else if(i==2) {
//             if (sel_days < abc.length) {
//                 data = new Data(abc[sel_days], "11:00AM", "12:00PM");
//                 datalist.add(data);
//             }
//         }
//        else if(i==3) {
//             if (sel_days < abc.length) {
//                 data = new Data(abc[sel_days], "12:00AM", "01:00PM");
//                 datalist.add(data);
//             }
//         }
//         else if(i==4) {
//             if (sel_days < abc.length) {
//                 data = new Data(abc[sel_days], "01:00PM", "02:00PM");
//                 datalist.add(data);
//             }
//         }
//         else if(i==5) {
////             if (sel_days < abc.length) {
////                 data = new Data(abc[sel_days], "03:00PM", "04:00PM");
////                 datalist.add(data);
////             }
//         }
//         else if(i==6) {
//             if (sel_days < abc.length) {
//                 data = new Data(abc[sel_days], "04:00PM", "05:00PM");
//                 datalist.add(data);
//             }
//         }
//         else if(i==7) {
//             if (sel_days < abc.length) {
//                 data = new Data(abc[sel_days], "05:00PM", "06:00PM");
//                 datalist.add(data);
//             }
//         }
//         else if(i==8) {
//             if (sel_days < abc.length) {
//                 data = new Data(abc[sel_days], "06:00AM", "07:00PM");
//                 datalist.add(data);
//             }
//         }
//         }
        mAdapter.notifyDataSetChanged();
        progress.cancel();
    }

//     Log.d("DATA",Lat+"/"+Lon);
//     Log.d("PLACES",placesList.get(0).getLat()+"/"+placesList.get(0).getLongi());

//     placesList = gp.nearBy(rad,"restaurant",String.valueOf(28.5272181),String.valueOf(77.0688997));


//     Data data = new Data(placesList.get(0).getName(),"09:00AM","10:00AM");
//     datalist.add(data);
//     data = new Data("KFC","11:00AM","12:00PM");
//     datalist.add(data);
//     data = new Data("Hanuman Mandir","12:00PM","01:00PM");
//     datalist.add(data);
//     mAdapter.notifyDataSetChanged();


    @Override
    public void onResume() {
        super.onResume();

        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getApplicationContext(), new RecyclerTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Data data = datalist.get(position);
                        //       Toast.makeText(getApplicationContext(),data.getPlace_name()+" "+data.getInit_time()+" "+data.getEnd_time(),Toast.LENGTH_SHORT).show();
                    }
                })
        );
        addSpiner();
//        days.setOnItemSelectedListener(this);
    }

    private void getPostData() {
        progress = new ProgressDialog(this);
        progress.setMessage("Downloading Data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("END", "END");
                        Log.e("Response", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray result = jsonObject.getJSONArray("day");

                            //                           Log.e("RESULT",result.toString());
//                            Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
//                            String[] lat = new String[result.length()];
//                            String[] lng = new String[result.length()];
                            Log.i("RESULTS", result.toString());
                            for (int i = 0; i < result.length(); i++) {
                                if (result != null) {
                                    JSONArray jo = result.getJSONArray(i);
                                    String[] day = new String[jo.length()];
                                    for (int j = 0; j < jo.length(); j++) {

                                        JSONObject item = jo.getJSONObject(j);
                                        String place = item.getString("place");
                                        String lat = String.valueOf(item.getDouble("lat"));
                                        String lon = String.valueOf(item.getDouble("lng"));
                                        String rating = item.getString("rating");
                                        String type = item.getString("type");
                                        String append = place + "/" + lat + "/" + lon + "/" + rating + "/" + type;
                                        day[j] = append;
                                        Log.i("APPEND", item.getString("place"));
                                    }
                                    mplaces.add(day);

//                                JSONArray day=jo.getJSONArray(i);
//                                JSONObject geometry = jo.getJSONObject("geometry");
                                    // JSONObject location = geometry.getJSONObject("location");
//                                lati = String.valueOf(location.getDouble("lat"));
//                                lon = String.valueOf(location.getDouble("lng"));
//                                name = jo.getString("name");
//                                strings[i] = name;
//                                lat[i] = lati;
//                                lng[i] = lon;

                                }
                            }

//                            mLat.add(lat);
//                            mLong.add(lng);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //  progress.cancel();
                        preparePlaceListData(0);
                        //      addSpiner();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_LAT, lati);
                params.put(KEY_LNG, lon);
                params.put(KEY_NO_DAYS, String.valueOf(no_days));
                params.put(KEY_MUST, String.valueOf(must_see));
                params.put(KEY_ADVENTURE, String.valueOf(adventure));
                params.put(KEY_ART, String.valueOf(art));
                params.put(KEY_ENTERTAINMENT, String.valueOf(entertainment));
                params.put(KEY_SHOPPING, String.valueOf(shopping));
                params.put(KEY_RESTUARANT, String.valueOf(restaurant));
                params.put(KEY_NATURE, String.valueOf(nature));
                params.put(KEY_RELIGIOUS, String.valueOf(religious));
                params.put(KEY_PARK, String.valueOf(park));
//
//                Log.d("DATA",lati);
//                Log.d("DATA",lon);
//                Log.d("DATA" ,String.valueOf(no_days));
//                Log.d("DATA",String.valueOf(must_see));
//                Log.d("DATA",String.valueOf(adventure));
//                Log.d("DATA",String.valueOf(art));
//                Log.d("DATA",String.valueOf(entertainment));
//                Log.d("DATA",String.valueOf(shopping));
//                Log.d("DATA",String.valueOf(restaurant));
//                Log.d("DATA",String.valueOf(nature));
//                Log.d("DATA",String.valueOf(religious));
//                Log.d("DATA",String.valueOf(park));

                return params;


            }
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                int mStatusCode = response.statusCode;
//                return super.parseNetworkResponse(response);
//            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private JSONObject loadQuestionsFromAssets() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
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


//    public void design()
//    {
//        progress=new ProgressDialog(this);
//        progress.setMessage("Downloading Places");
//        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progress.setIndeterminate(true);
//        progress.setProgress(0);
//        progress.show();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + lati + "," + lon + "&radius=" + rad + "&type=" + "restaurant" + "&key=" + api_key,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("Types","REST");
//
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray result = jsonObject.getJSONArray("results");
//                            String[] strings = new String[result.length()];
//                            String[] lat = new String[result.length()];
//                            String[] lng = new String[result.length()];
// //                           LatLng[] latLngs = new LatLng[result.length()];
//                            for (int i = 0; i < result.length(); i++) {
//                                JSONObject jo = result.getJSONObject(i);
//                                JSONObject geometry = jo.getJSONObject("geometry");
//                                JSONObject location = geometry.getJSONObject("location");
//                                lati = String.valueOf(location.getDouble("lat"));
//                                lon = String.valueOf(location.getDouble("lng"));
//                                name = jo.getString("name");
//                                strings[i] = name;
//                                lat[i]=lati;
//                                lng[i]=lon;
////                                LatLng latLng1= new LatLng(location.getDouble("lat"),location.getDouble("lng"));
////                                latLngs[i]=latLng1;
//
//
//                            }
//                            mplaces.add(strings);
//                            mLat.add(lat);
//                            mLong.add(lng);
////                            mcord.add(latLngs);
////                            nearBy(build[x]);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("Error", error.toString());
//                    }
//
//                });
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(stringRequest);
//    }
//    public void nearBy(String type) {
//        if (x >=0) {
//            Log.d("Response", type);
//            StringRequest stringRequest = new StringRequest("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + lati + "," + lon + "&radius=" + rad + "&type=" + type + "&key=" + api_key,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//
//                            try {
//                                JSONObject jsonObject = new JSONObject(response);
//                                JSONArray result = jsonObject.getJSONArray("results");
//                                String[] strings = new String[result.length()];
//                                String[] lat = new String[result.length()];
//                                String[] lng = new String[result.length()];
//                                for (int i = 0; i < result.length(); i++) {
//                                    JSONObject jo = result.getJSONObject(i);
//                                    JSONObject geometry = jo.getJSONObject("geometry");
//                                    JSONObject location = geometry.getJSONObject("location");
//                                    lati = String.valueOf(location.getDouble("lat"));
//                                    lon = String.valueOf(location.getDouble("lng"));
//                                    name = jo.getString("name");
//                                    strings[i] = name;
//                                    lat[i]=lati;
//                                    lng[i]=lon;
//
//                                }
//                                mplaces.add(strings);
//                                mLat.add(lat);
//                                mLong.add(lng);
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            x=x+1;
//                            if(x<build.length)
//                            {nearBy(build[x]);}
//                            if(x>=build.length)
//                            {
//                                preparePlaceListData();
////                                for(int i =0;i<mplaces.size();i++)
////                                {
////                                    String[] abcd = mplaces.get(i);
////                                    Log.d("Size",abcd[0]);
////                                }
//
//                            }
//
//                        }
//
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.d("Error", error.toString());
//                        }
//                    });
//
//            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//            requestQueue.add(stringRequest);
//        }
//    }


}
