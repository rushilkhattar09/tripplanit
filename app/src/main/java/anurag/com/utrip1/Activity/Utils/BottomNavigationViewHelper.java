package anurag.com.utrip1.Activity.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import java.lang.reflect.Field;

import anurag.com.utrip1.Activity.HomeActivity;
import anurag.com.utrip1.Activity.MainActivity;
import anurag.com.utrip1.Activity.PlaceListActivity;
import anurag.com.utrip1.Activity.Plan_SelectActivity;
import anurag.com.utrip1.Activity.SecondActivity;
import anurag.com.utrip1.Activity.SelectPlanActivity;
import anurag.com.utrip1.R;

/**
 * Created by rushilkhattar on 24/01/18.
 */

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    /*public static void setupBottomNavigationView(BottomNavigationView bottomNavigationView) {
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
        bottomNavigationView.clearAnimation();
        bottomNavigationView.

    }*/

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    public static void bnavigation(BottomNavigationView bottomNavigationView) {
//        bottomNavigationView.setItemIconTintList(ColorStateList.valueOf(Color.WHITE));
//        bottomNavigationView.setItemIconTintList(ColorStateList.valueOf(R.drawable.selector));
    }

    public static void enableNavigation(final Context context, BottomNavigationView view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.ic_house:
                        Intent intent1 = new Intent(context, HomeActivity.class);//ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_search:
                        Intent intent2 = new Intent(context, SecondActivity.class);//ACTIVITY_NUM = 1
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_circle:
                        Intent intent3 = new Intent(context, MainActivity.class);//ACTIVITY_NUM = 2
                        context.startActivity(intent3);
                        break;

                    case R.id.ic_alert:
                        Intent intent4 = new Intent(context, Plan_SelectActivity.class);//ACTIVITY_NUM = 3
                        context.startActivity(intent4);
                        break;

                    case R.id.ic_android:
                        Intent intent5 = new Intent(context, SelectPlanActivity.class);//ACTIVITY_NUM = 4
                        context.startActivity(intent5);


                }

                return false;
            }
        });
    }
}