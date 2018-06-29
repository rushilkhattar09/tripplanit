package anurag.com.utrip1.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import anurag.com.utrip1.Activity.Utils.BottomNavigationViewHelper;
import anurag.com.utrip1.R;

/**
 * Created by rushilkhattar on 30/01/18.
 */

public class PlaceListActivity2 extends AppCompatActivity {

    private static final String TAG ="";
    private static final int ACTIVITY_NUM = 1;
    private RecyclerView recyclerView;
    private AlbumsAdapter2 adapter;
    private List<Album2> albumList;
    Button displaymap;
    TextView textView;
    TextView textView1;
    Button b1;


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

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter2(this, albumList);


        textView = findViewById(R.id.love_music);




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



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(PlaceListActivity2.this, MultipleMarkersMaps.class);



                Bundle bundle = new Bundle();
                bundle.putDoubleArray("params", lat1);
                bundle.putDoubleArray("params2", lon1);
                MapMarkersFragment myObj = new MapMarkersFragment();
                myObj.setArguments(bundle);
                intent2.putExtra("mapmarker1", lat1);
                intent2.putExtra("mapmarker2", lon1);
                startActivity(intent2);
            }
        });





        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new PlaceListActivity2.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.back11).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void setupBottomNavigationView(){
        Log.d(TAG, "onCreate: starting.");
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
//        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(PlaceListActivity2.this, bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }
    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.back21,
                R.drawable.bac23,
                R.drawable.back22,
                R.drawable.back15,
                R.drawable.back45,
                R.drawable.back24,
                R.drawable.back48,
                R.drawable.back35,
                R.drawable.back51,
                R.drawable.bac23,
                R.drawable.album11,
                R.drawable.album3,
                R.drawable.album4

        };

        Album2 a = new Album2(places1[0], type1[0],lat1[0],lon1[0],rating1[0],covers[0]);
        albumList.add(a);

        a = new Album2(places1[1], type1[1],lat1[1],lon1[1],rating1[1],covers[1]);
        albumList.add(a);

        a = new Album2(places1[2], type1[2],lat1[2],lon1[2],rating1[2],covers[2]);
        albumList.add(a);

        a = new Album2(places1[3], type1[3],lat1[3],lon1[3],rating1[3],covers[3]);
        albumList.add(a);

        a = new Album2(places1[4], type1[4],lat1[4],lon1[4],rating1[4],covers[4]);
        albumList.add(a);

        a = new Album2(places1[5], type1[5],lat1[5],lon1[5],rating1[5],covers[5]);
        albumList.add(a);

  /*      a = new Album2(places1[6], type1[6],lat1[6],lon1[6],rating1[6],covers[6]);
        albumList.add(a);

        a = new Album2(places1[7], type1[7],lat1[7],lon1[7],rating1[7],covers[7]);
        albumList.add(a);

       a = new Album2(places1[8], type1[8],lat1[8],lon1[8],rating1[8],covers[8]);
        albumList.add(a);

        a = new Album2(places1[9], type1[9],lat1[9],lon1[9],rating1[9],covers[9]);
        albumList.add(a);

        a = new Album2(places1[10], type1[10],lat1[10],lon1[10],rating1[10],covers[10]);
        albumList.add(a);

        a = new Album2(places1[11], type1[11],lat1[11],lon1[11],rating1[11],covers[11]);
        albumList.add(a);


*/


        adapter.notifyDataSetChanged();
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

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

  /*  @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }*/
}
