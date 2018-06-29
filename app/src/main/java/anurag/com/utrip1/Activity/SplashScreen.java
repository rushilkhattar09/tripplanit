package anurag.com.utrip1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.analytics.FirebaseAnalytics;

import anurag.com.utrip1.R;

/**
 * Created by rushilkhattar on 18/01/18.
 */

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screen_splash);




        RelativeLayout r1 = (RelativeLayout) findViewById(R.id.rl2);

        ImageView iv = new ImageView(this);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(30, 40);
        params.leftMargin = 50;
        params.topMargin = 60;
        r1.addView(iv, params);



        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
