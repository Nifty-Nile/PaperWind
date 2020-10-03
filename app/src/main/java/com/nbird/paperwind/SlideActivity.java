package com.nbird.paperwind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SlideActivity extends AppCompatActivity {


    private ViewPager slideViewPager;
    private LinearLayout dotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Button nextbutton,backbutton;
    private int currentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        slideViewPager=(ViewPager) findViewById(R.id.slideViewPager);
        dotLayout=(LinearLayout) findViewById(R.id.dotLayout);
        nextbutton=(Button) findViewById(R.id.button5);
        backbutton=(Button) findViewById(R.id.button4);


        sliderAdapter=new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListner);

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage==2){
                    Intent intent=new Intent(getBaseContext(),LoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                slideViewPager.setCurrentItem(currentPage+1);

            }
        });
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(currentPage-1);
            }
        });

    }

    public void addDotsIndicator(int position){
        mDots=new TextView[3];
        dotLayout.removeAllViews();
        for(int i=0;i<mDots.length;i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(60);
            mDots[i].setTextColor(getResources().getColor(R.color.white));
            dotLayout.addView(mDots[i]);

        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }
    ViewPager.OnPageChangeListener viewListner=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage=position;
            if(currentPage==0){
                nextbutton.setEnabled(true);
                backbutton.setEnabled(false);
                backbutton.setVisibility(View.INVISIBLE);
                nextbutton.setScaleX(1);
                nextbutton.setScaleY(1);
                nextbutton.setBackgroundResource(R.color.transparent);
                nextbutton.setText("Next");
                backbutton.setText("");
            }
            else if(position==mDots.length-1){
                nextbutton.setEnabled(true);
                backbutton.setEnabled(true);
                backbutton.setVisibility(View.VISIBLE);

                nextbutton.setBackgroundResource(R.drawable.loadingbackground);

                nextbutton.setText("Finish");
                backbutton.setText("Back");
            }

            else {
                nextbutton.setEnabled(true);
                backbutton.setEnabled(true);
                backbutton.setVisibility(View.VISIBLE);
                nextbutton.setScaleX(1);
                nextbutton.setBackgroundResource(R.color.transparent);
                nextbutton.setScaleY(1);
                nextbutton.setText("Next");
                backbutton.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}