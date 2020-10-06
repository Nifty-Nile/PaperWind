package com.nbird.paperwind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView logo,logobackground;





    private static int SPLASH_TIME_OUT = 3500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        logo=(ImageView) findViewById(R.id.logopic);
        logobackground=(ImageView) findViewById(R.id.logobackground);

        Animation logoanim= AnimationUtils.loadAnimation(this,R.anim.logoanim);
        Animation logoanim1= AnimationUtils.loadAnimation(this,R.anim.fadein);
        logo.setAnimation(logoanim);
        logobackground.setAnimation(logoanim1);



        new Handler().postDelayed(new Runnable() {


            public void run() {
                Intent slideIntent = new Intent(MainActivity.this, Menu1Activity.class);
                startActivity(slideIntent);
                finish();

                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }

        }, SPLASH_TIME_OUT);

    }


}