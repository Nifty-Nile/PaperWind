package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import co.mobiwise.materialintro.animation.MaterialIntroListener;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;

public class SartingGuideActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    Button cbsebutton,icsebutton;
    CardView cardView;
    View bottomview1;
    TextView text1,text2,text3,text4,text5,text6,text7,text8;
    Boolean night;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarting_guide);

        cbsebutton=(Button) findViewById(R.id.tipButton1);
        icsebutton=(Button) findViewById(R.id.tipButton);
        cardView=(CardView) findViewById(R.id.cardView1);
        text1=(TextView) findViewById(R.id.hometext);
        text2=(TextView) findViewById(R.id.predictortext);
        text3=(TextView) findViewById(R.id.formulatext);
        text4=(TextView) findViewById(R.id.moneytext);
        text5=(TextView) findViewById(R.id.menutext);
        text6=(TextView) findViewById(R.id.updatetext);
        text7=(TextView) findViewById(R.id.moneyinfotext);
        text8=(TextView) findViewById(R.id.threemenutext);

        final SharedPreferences guide = getBaseContext().getSharedPreferences("guidepre", 0);
        final SharedPreferences.Editor editorguide = guide.edit();


        night = guide.getBoolean("locked", false);

        if(night){
            Intent intent=new Intent(getBaseContext(),Menu1Activity.class);
            startActivity(intent);
            finish();
        }
        else{


            editorguide.putBoolean("locked", true);
            editorguide.commit();


            new MaterialIntroView.Builder(this)
                    .enableDotAnimation(true)
                    .enableIcon(false)
                    .setIdempotent(false)
                    .setFocusGravity(FocusGravity.CENTER)
                    .setFocusType(Focus.MINIMUM)
                    .setDelayMillis(500)
                    .enableFadeAnimation(true)
                    .performClick(true)
                    .setInfoText("Get access to a wide range of content exclusively for CBSE.")
                    .setShape(ShapeType.RECTANGLE)
                    .setTarget(cbsebutton)
                    .setTextColor(Color.WHITE)
                    .setUsageId("intro_card") //THIS SHOULD BE UNIQUE ID
                    .setMaskColor(getResources().getColor(R.color.transparentlightblue))
                    .setListener(new MaterialIntroListener() {
                        @Override
                        public void onUserClicked(String materialIntroViewId) {
                            new MaterialIntroView.Builder(SartingGuideActivity.this).enableDotAnimation(true)
                                    .enableIcon(false)
                                    .setFocusGravity(FocusGravity.CENTER)
                                    .setFocusType(Focus.MINIMUM)
                                    .setDelayMillis(500)
                                    .setIdempotent(false)
                                    .enableFadeAnimation(true)
                                    .performClick(true)
                                    .setTextColor(Color.WHITE)
                                    .setInfoText("ICSE study material, tests and Question Papers. Just a click Away!")
                                    .setShape(ShapeType.RECTANGLE)
                                    .setTarget(icsebutton)
                                    .setUsageId("intro_card2") //THIS SHOULD BE UNIQUE ID
                                    .setMaskColor(getResources().getColor(R.color.transparentlightblue))
                                    .setListener(new MaterialIntroListener() {
                                        @Override
                                        public void onUserClicked(String materialIntroViewId) {
                                            new MaterialIntroView.Builder(SartingGuideActivity.this)
                                                    .enableDotAnimation(true)
                                                    .enableIcon(false)
                                                    .setIdempotent(false)
                                                    .setFocusGravity(FocusGravity.CENTER)
                                                    .setFocusType(Focus.MINIMUM)
                                                    .setDelayMillis(500)
                                                    .enableFadeAnimation(true)
                                                    .performClick(true)
                                                    .setInfoText("Exclusive content for India's top examinations to boost your path to success! Contains custom made Test Papers and Previous Year's Papers.")
                                                    .setShape(ShapeType.RECTANGLE)
                                                    .setTarget(cardView)
                                                    .setTextColor(Color.WHITE)
                                                    .setUsageId("intro_card3") //THIS SHOULD BE UNIQUE ID
                                                    .setMaskColor(getResources().getColor(R.color.transparentlightblue))
                                                    .setListener(new MaterialIntroListener() {
                                                        @Override
                                                        public void onUserClicked(String materialIntroViewId) {
                                                            new MaterialIntroView.Builder(SartingGuideActivity.this).enableDotAnimation(true)
                                                                    .enableIcon(false)
                                                                    .setFocusGravity(FocusGravity.CENTER)
                                                                    .setFocusType(Focus.MINIMUM)
                                                                    .setDelayMillis(500)
                                                                    .setIdempotent(false)
                                                                    .enableFadeAnimation(true)
                                                                    .performClick(true)
                                                                    .setTextColor(Color.WHITE)
                                                                    .setInfoText("Start all over again? Here you go!")
                                                                    .setShape(ShapeType.CIRCLE)
                                                                    .setTarget(text1)
                                                                    .setUsageId("intro_card4") //THIS SHOULD BE UNIQUE ID
                                                                    .setMaskColor(getResources().getColor(R.color.transparentlightblue))
                                                                    .setListener(new MaterialIntroListener() {
                                                                        @Override
                                                                        public void onUserClicked(String materialIntroViewId) {
                                                                            new MaterialIntroView.Builder(SartingGuideActivity.this)
                                                                                    .enableDotAnimation(true)
                                                                                    .enableIcon(false)
                                                                                    .setIdempotent(false)
                                                                                    .setFocusGravity(FocusGravity.CENTER)
                                                                                    .setFocusType(Focus.MINIMUM)
                                                                                    .setDelayMillis(500)
                                                                                    .enableFadeAnimation(true)
                                                                                    .performClick(true)
                                                                                    .setInfoText("Predict your Rank and Colleges based on your score. Your Post-Exam anxiety, just got covered!")
                                                                                    .setShape(ShapeType.CIRCLE)
                                                                                    .setTarget(text2)
                                                                                    .setTextColor(Color.WHITE)
                                                                                    .setUsageId("intro_card5") //THIS SHOULD BE UNIQUE ID
                                                                                    .setMaskColor(getResources().getColor(R.color.transparentlightblue))
                                                                                    .setListener(new MaterialIntroListener() {
                                                                                        @Override
                                                                                        public void onUserClicked(String materialIntroViewId) {
                                                                                            new MaterialIntroView.Builder(SartingGuideActivity.this).enableDotAnimation(true)
                                                                                                    .enableIcon(false)
                                                                                                    .setFocusGravity(FocusGravity.CENTER)
                                                                                                    .setFocusType(Focus.MINIMUM)
                                                                                                    .setDelayMillis(500)
                                                                                                    .setIdempotent(false)
                                                                                                    .enableFadeAnimation(true)
                                                                                                    .performClick(true)
                                                                                                    .setTextColor(Color.WHITE)
                                                                                                    .setInfoText("All the important formulae just at your fingertips for the quickest Revision Tactic!")
                                                                                                    .setShape(ShapeType.CIRCLE)
                                                                                                    .setTarget(text3)
                                                                                                    .setUsageId("intro_card6") //THIS SHOULD BE UNIQUE ID
                                                                                                    .setMaskColor(getResources().getColor(R.color.transparentlightblue))
                                                                                                    .setListener(new MaterialIntroListener() {
                                                                                                        @Override
                                                                                                        public void onUserClicked(String materialIntroViewId) {
                                                                                                            new MaterialIntroView.Builder(SartingGuideActivity.this)
                                                                                                                    .enableDotAnimation(true)
                                                                                                                    .enableIcon(false)
                                                                                                                    .setIdempotent(false)
                                                                                                                    .setFocusGravity(FocusGravity.CENTER)
                                                                                                                    .setFocusType(Focus.MINIMUM)
                                                                                                                    .setDelayMillis(500)
                                                                                                                    .enableFadeAnimation(true)
                                                                                                                    .performClick(true)
                                                                                                                    .setInfoText("Unlock Exclusive Features of PAPER WIND at 'Throw-Away' Prices!")
                                                                                                                    .setShape(ShapeType.CIRCLE)
                                                                                                                    .setTarget(text4)
                                                                                                                    .setTextColor(Color.WHITE)
                                                                                                                    .setUsageId("intro_card7") //THIS SHOULD BE UNIQUE ID
                                                                                                                    .setMaskColor(getResources().getColor(R.color.transparentlightblue))
                                                                                                                    .setListener(new MaterialIntroListener() {
                                                                                                                        @Override
                                                                                                                        public void onUserClicked(String materialIntroViewId) {
                                                                                                                            new MaterialIntroView.Builder(SartingGuideActivity.this).enableDotAnimation(true)
                                                                                                                                    .enableIcon(false)
                                                                                                                                    .setFocusGravity(FocusGravity.CENTER)
                                                                                                                                    .setFocusType(Focus.MINIMUM)
                                                                                                                                    .setDelayMillis(500)
                                                                                                                                    .setIdempotent(false)
                                                                                                                                    .enableFadeAnimation(true)
                                                                                                                                    .performClick(true)
                                                                                                                                    .setTextColor(Color.WHITE)
                                                                                                                                    .setInfoText("We got it all personalised! Know your recent orders ,Help guide and various other features!")
                                                                                                                                    .setShape(ShapeType.CIRCLE)
                                                                                                                                    .setTarget(text5)
                                                                                                                                    .setUsageId("intro_card8") //THIS SHOULD BE UNIQUE ID
                                                                                                                                    .setMaskColor(getResources().getColor(R.color.transparentlightblue))
                                                                                                                                    .setListener(new MaterialIntroListener() {
                                                                                                                                        @Override
                                                                                                                                        public void onUserClicked(String materialIntroViewId) {
                                                                                                                                            new MaterialIntroView.Builder(SartingGuideActivity.this).enableDotAnimation(true)
                                                                                                                                                    .enableIcon(false)
                                                                                                                                                    .setFocusGravity(FocusGravity.CENTER)
                                                                                                                                                    .setFocusType(Focus.MINIMUM)
                                                                                                                                                    .setDelayMillis(500)
                                                                                                                                                    .setIdempotent(false)
                                                                                                                                                    .enableFadeAnimation(true)
                                                                                                                                                    .performClick(true)
                                                                                                                                                    .setTextColor(Color.WHITE)
                                                                                                                                                    .setInfoText("Tracking Exam Dates,Exam Portions And All Exam Updates just got Easier!")
                                                                                                                                                    .setShape(ShapeType.CIRCLE)
                                                                                                                                                    .setTarget(text6)
                                                                                                                                                    .setUsageId("intro_card9") //THIS SHOULD BE UNIQUE ID
                                                                                                                                                    .setMaskColor(getResources().getColor(R.color.transparentlightblue))
                                                                                                                                                    .setListener(new MaterialIntroListener() {
                                                                                                                                                        @Override
                                                                                                                                                        public void onUserClicked(String materialIntroViewId) {
                                                                                                                                                            new MaterialIntroView.Builder(SartingGuideActivity.this)
                                                                                                                                                                    .enableDotAnimation(true)
                                                                                                                                                                    .enableIcon(false)
                                                                                                                                                                    .setIdempotent(false)
                                                                                                                                                                    .setFocusGravity(FocusGravity.CENTER)
                                                                                                                                                                    .setFocusType(Focus.MINIMUM)
                                                                                                                                                                    .setDelayMillis(500)
                                                                                                                                                                    .enableFadeAnimation(true)
                                                                                                                                                                    .performClick(true)
                                                                                                                                                                    .setInfoText("Know your Paper Notes balance right up your alley!")
                                                                                                                                                                    .setShape(ShapeType.CIRCLE)
                                                                                                                                                                    .setTarget(text7)
                                                                                                                                                                    .setTextColor(Color.WHITE)
                                                                                                                                                                    .setUsageId("intro_card10") //THIS SHOULD BE UNIQUE ID
                                                                                                                                                                    .setMaskColor(getResources().getColor(R.color.transparentlightblue))
                                                                                                                                                                    .setListener(new MaterialIntroListener() {
                                                                                                                                                                        @Override
                                                                                                                                                                        public void onUserClicked(String materialIntroViewId) {
                                                                                                                                                                            new MaterialIntroView.Builder(SartingGuideActivity.this).enableDotAnimation(true)
                                                                                                                                                                                    .enableIcon(false)
                                                                                                                                                                                    .setFocusGravity(FocusGravity.CENTER)
                                                                                                                                                                                    .setFocusType(Focus.MINIMUM)
                                                                                                                                                                                    .setDelayMillis(500)
                                                                                                                                                                                    .setIdempotent(false)
                                                                                                                                                                                    .enableFadeAnimation(true)
                                                                                                                                                                                    .performClick(true)
                                                                                                                                                                                    .setTextColor(Color.WHITE)
                                                                                                                                                                                    .setInfoText("Exam History,Sharing And Dark Mode ? Click Me!")
                                                                                                                                                                                    .setShape(ShapeType.CIRCLE)
                                                                                                                                                                                    .setTarget(text8)
                                                                                                                                                                                    .setUsageId("intro_card11") //THIS SHOULD BE UNIQUE ID
                                                                                                                                                                                    .setMaskColor(getResources().getColor(R.color.transparentlightblue))
                                                                                                                                                                                    .setListener(new MaterialIntroListener() {
                                                                                                                                                                                        @Override
                                                                                                                                                                                        public void onUserClicked(String materialIntroViewId) {
                                                                                                                                                                                            Intent intent=new Intent(getBaseContext(),Menu1Activity.class);
                                                                                                                                                                                            startActivity(intent);
                                                                                                                                                                                            finish();
                                                                                                                                                                                        }
                                                                                                                                                                                    })
                                                                                                                                                                                    .show();
                                                                                                                                                                        }
                                                                                                                                                                    })
                                                                                                                                                                    .show();
                                                                                                                                                        }
                                                                                                                                                    })
                                                                                                                                                    .show();
                                                                                                                                        }
                                                                                                                                    })
                                                                                                                                    .show();
                                                                                                                        }
                                                                                                                    })
                                                                                                                    .show();
                                                                                                        }
                                                                                                    })
                                                                                                    .show();
                                                                                        }
                                                                                    })
                                                                                    .show();
                                                                        }
                                                                    })
                                                                    .show();
                                                        }
                                                    })
                                                    .show();
                                        }
                                    })
                                    .show();
                        }
                    })
                    .show();

        }





        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigatio);

        bottomNavigationView.setSelectedItemId(R.id.home);




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        return true;

                    case R.id.rankpredictor:
                        startActivity(new Intent(getApplicationContext(),RankPredictorActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Formulas:
                        startActivity(new Intent(getApplicationContext(),FormulaSTDActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.money:
                        startActivity(new Intent(getApplicationContext(),MoneyActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.share){
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.about){
            Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.history){
            Toast.makeText(this, "History Mode", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.propic){
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.coins){
            Toast.makeText(this, "Coins", Toast.LENGTH_SHORT).show();
        }
        return true;
    }





}