package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Sub_All_Activity extends AppCompatActivity {
    Button button1,button2,button3,button4,button5,button6;
    int Exam,Std,Paper;
    androidx.appcompat.widget.Toolbar toolbar;
    TextView text0,text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,text13;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__all_);

        Exam=getIntent().getIntExtra("Exam",0);
        Std=getIntent().getIntExtra("Std",0);
        Paper=getIntent().getIntExtra("Paper",0);

        button1=(Button) findViewById(R.id.tipButton1);
        button2=(Button) findViewById(R.id.tipButton2);
        button3=(Button) findViewById(R.id.tipButton3);
        button4=(Button) findViewById(R.id.tipButton4);
        button5=(Button) findViewById(R.id.tipButton5);
        button6=(Button) findViewById(R.id.tipButton6);

        text0=(TextView) findViewById(R.id.text0);
        text1=(TextView) findViewById(R.id.text1);
        text2=(TextView) findViewById(R.id.text2);
        text3=(TextView) findViewById(R.id.text3);
        text4=(TextView) findViewById(R.id.text4);
        text5=(TextView) findViewById(R.id.text5);
        text6=(TextView) findViewById(R.id.text6);
        text7=(TextView) findViewById(R.id.text7);
        text8=(TextView) findViewById(R.id.text8);
        text9=(TextView) findViewById(R.id.text9);
        text10=(TextView) findViewById(R.id.text10);
        text11=(TextView) findViewById(R.id.text11);
        text12=(TextView) findViewById(R.id.text12);
        text13=(TextView) findViewById(R.id.textView4);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Subjects");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            text1.setTextColor(Color.parseColor("#ffffff"));
            text2.setTextColor(Color.parseColor("#ffffff"));
            text3.setTextColor(Color.parseColor("#ffffff"));
            text4.setTextColor(Color.parseColor("#ffffff"));
            text5.setTextColor(Color.parseColor("#ffffff"));
            text6.setTextColor(Color.parseColor("#ffffff"));
            text7.setTextColor(Color.parseColor("#ffffff"));
            text8.setTextColor(Color.parseColor("#ffffff"));
            text9.setTextColor(Color.parseColor("#ffffff"));
            text10.setTextColor(Color.parseColor("#ffffff"));
            text11.setTextColor(Color.parseColor("#ffffff"));
            text12.setTextColor(Color.parseColor("#ffffff"));
            text13.setTextColor(Color.parseColor("#ffffff"));

        }else{
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);


            text1.setTextColor(Color.parseColor("#000000"));
            text2.setTextColor(Color.parseColor("#000000"));
            text3.setTextColor(Color.parseColor("#000000"));
            text4.setTextColor(Color.parseColor("#000000"));
            text5.setTextColor(Color.parseColor("#000000"));
            text6.setTextColor(Color.parseColor("#000000"));
            text7.setTextColor(Color.parseColor("#000000"));
            text8.setTextColor(Color.parseColor("#000000"));
            text9.setTextColor(Color.parseColor("#000000"));
            text10.setTextColor(Color.parseColor("#000000"));
            text11.setTextColor(Color.parseColor("#000000"));
            text12.setTextColor(Color.parseColor("#000000"));
            text13.setTextColor(Color.parseColor("#000000"));

        }



        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigatio);

        bottomNavigationView.setSelectedItemId(R.id.home);


        // **************** Bottom navigation View **********************

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

        cigar();



    }

    public void cigar(){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Recycler_Exam_Activity.class);
                intent.putExtra("Chapter",1);
                intent.putExtra("Exam",Exam);
                intent.putExtra("Std",Std);
                intent.putExtra("Paper",Paper);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Recycler_Exam_Activity.class);
                intent.putExtra("Chapter",2);
                intent.putExtra("Exam",Exam);
                intent.putExtra("Std",Std);
                intent.putExtra("Paper",Paper);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Recycler_Exam_Activity.class);
                intent.putExtra("Chapter",3);
                intent.putExtra("Exam",Exam);
                intent.putExtra("Std",Std);
                intent.putExtra("Paper",Paper);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Recycler_Exam_Activity.class);
                intent.putExtra("Chapter",4);
                intent.putExtra("Exam",Exam);
                intent.putExtra("Std",Std);
                intent.putExtra("Paper",Paper);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Recycler_Exam_Activity.class);
                intent.putExtra("Chapter",5);
                intent.putExtra("Exam",Exam);
                intent.putExtra("Std",Std);
                intent.putExtra("Paper",Paper);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Recycler_Exam_Activity.class);
                intent.putExtra("Chapter",6);
                intent.putExtra("Exam",Exam);
                intent.putExtra("Std",Std);
                intent.putExtra("Paper",Paper);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

}