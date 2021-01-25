package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PaperCategoryBoardsActivity extends AppCompatActivity {
    Button linkButton,previousyearpaperbutton,sampleyearpaper,NCERTSolutions;
    int Exam,Std,Paper;
    TextView text1,text2,text3,text4,text5,text6,text7,text8;
    androidx.appcompat.widget.Toolbar toolbar;
    LinearLayout linearLayout;
    ImageView ncertimage;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_category_boards);

        Exam=getIntent().getIntExtra("Exam",0);
        Std=getIntent().getIntExtra("Std",0);
        text1=(TextView) findViewById(R.id.text1);
        text2=(TextView) findViewById(R.id.text2);
        text3=(TextView) findViewById(R.id.text3);
        text4=(TextView) findViewById(R.id.text4);
        text5=(TextView) findViewById(R.id.text5);
        text6=(TextView) findViewById(R.id.text6);
        text7=(TextView) findViewById(R.id.text7);
        text8=(TextView) findViewById(R.id.text8);
        linearLayout=(LinearLayout) findViewById(R.id.fourlinearlayout);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Category Activity");
        ncertimage=(ImageView) findViewById(R.id.ncertimage);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        linkButton=(Button) findViewById(R.id.linkButton);
        previousyearpaperbutton=(Button) findViewById(R.id.previousyearpaper);
        sampleyearpaper=(Button) findViewById(R.id.samplepaperbutton);
        NCERTSolutions=(Button) findViewById(R.id.ncertSolutions);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigatio);

        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            text1.setTextColor(Color.parseColor("#ffffff"));
            text2.setTextColor(Color.parseColor("#ffffff"));
            text3.setTextColor(Color.parseColor("#ffffff"));
            text4.setTextColor(Color.parseColor("#ffffff"));
            text5.setTextColor(Color.parseColor("#ffffff"));
            text6.setTextColor(Color.parseColor("#ffffff"));
            text7.setTextColor(Color.parseColor("#ffffff"));
            text8.setTextColor(Color.parseColor("#ffffff"));

        }else{
            ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);


            text1.setTextColor(Color.parseColor("#000000"));
            text2.setTextColor(Color.parseColor("#000000"));
            text3.setTextColor(Color.parseColor("#000000"));
            text4.setTextColor(Color.parseColor("#000000"));
            text5.setTextColor(Color.parseColor("#000000"));
            text6.setTextColor(Color.parseColor("#000000"));
            text7.setTextColor(Color.parseColor("#000000"));
            text8.setTextColor(Color.parseColor("#000000"));
        }

        if(Exam==2){
            text7.setAlpha(0);
            text8.setAlpha(0);
            linearLayout.setBackground(null);
            ncertimage.setBackground(null);
            NCERTSolutions.setClickable(false);
            NCERTSolutions.setEnabled(false);
            NCERTSolutions.setBackground(null);
            NCERTSolutions.setText("");
        }

        previousyearpaperbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Exam == 1) {
                    if (Std == 1) {
                        Intent intent = new Intent(getBaseContext(), Sub_All_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 1);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getBaseContext(), Sub_All_Cbse12_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 1);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    }

                }
                if (Exam == 2) {
                    if (Std == 1) {
                        Intent intent = new Intent(getBaseContext(), All_sub_Cisce10_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 1);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getBaseContext(), All_sub_cisce12_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 1);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    }

                }
            }
        });
        sampleyearpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Exam == 1) {
                    if (Std == 1) {
                        Intent intent = new Intent(getBaseContext(), Sub_All_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 2);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getBaseContext(), Sub_All_Cbse12_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 2);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    }

                }
                if (Exam == 2) {
                    if (Std == 1) {
                        Intent intent = new Intent(getBaseContext(), All_sub_Cisce10_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 2);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getBaseContext(), All_sub_cisce12_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 2);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    }

                }
            }
        });
        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),LinkSubjects.class);
                intent.putExtra("Exam",Exam);
                intent.putExtra("Std",Std);
                intent.putExtra("Paper",3);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                startActivity(intent);
            }
        });





        NCERTSolutions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Exam == 1) {
                    if (Std == 1) {
                        Intent intent = new Intent(getBaseContext(), Sub_All_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 4);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getBaseContext(), Sub_All_Cbse12_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 4);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    }

                }
            }
        });



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

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}