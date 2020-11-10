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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FormulaSubjectActivity extends AppCompatActivity {
    Button phybutton,chembutton,biobutton;
    int std;
    TextView text1,text2,text3,text0;
    ImageView mathsimage,phyimage,chemimage;
    androidx.appcompat.widget.Toolbar toolbar;
    LinearLayout linearLayout11,linearLayout10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formula_subject);
        phybutton=(Button) findViewById(R.id.tipButton1);
        chembutton=(Button) findViewById(R.id.tipButton2);
        biobutton=(Button) findViewById(R.id.tipButton3);

        linearLayout11=(LinearLayout) findViewById(R.id.linearLayout11);
        linearLayout10=(LinearLayout) findViewById(R.id.linearLayout10);

        text1=(TextView) findViewById(R.id.text1);
        text2=(TextView) findViewById(R.id.text2);
        text3=(TextView) findViewById(R.id.text3);
        text0=(TextView) findViewById(R.id.text0);

        mathsimage=(ImageView)findViewById(R.id.mathsimage);
        phyimage=(ImageView)findViewById(R.id.phyimage);
        chemimage=(ImageView)findViewById(R.id.chemimage);

        std=getIntent().getIntExtra("Std100",0);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Subjects Activity");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigatio);

        bottomNavigationView.setSelectedItemId(R.id.Formulas);


        // **************** Bottom navigation View **********************



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Menu1Activity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.rankpredictor:
                        startActivity(new Intent(getApplicationContext(),RankPredictorActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.Formulas:
                        return true;
                    case R.id.money:
                        startActivity(new Intent(getApplicationContext(),MoneyActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        if(std==1){
            phyimage.setBackgroundResource(R.drawable.science123);
            phybutton.setText("Science");
            chemimage.setBackgroundResource(R.drawable.mathsicons);
            chembutton.setText("Mathematics");
            chembutton.setTextSize(13);
            chembutton.setBackgroundResource(R.drawable.roundbuttonsgreen);
            mathsimage.setBackgroundResource(R.color.transparent);
            biobutton.setAlpha(0);
            biobutton.setEnabled(false);
            text3.setText("");
            linearLayout11.setBackgroundResource(R.color.transparent);
            linearLayout10.setBackgroundResource(R.drawable.strokegreen);
        }

        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            text1.setTextColor(Color.parseColor("#ffffff"));
            text2.setTextColor(Color.parseColor("#ffffff"));
            text3.setTextColor(Color.parseColor("#ffffff"));
            text0.setTextColor(Color.parseColor("#ffffff"));


        }else{
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);


            text1.setTextColor(Color.parseColor("#000000"));
            text2.setTextColor(Color.parseColor("#000000"));
            text3.setTextColor(Color.parseColor("#000000"));
            text0.setTextColor(Color.parseColor("#000000"));

        }

        phybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),FormulaChRecyclerActivity.class);
                intent.putExtra("Subject100",1);
                intent.putExtra("Std100",std);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        chembutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),FormulaChRecyclerActivity.class);
                intent.putExtra("Subject100",2);
                intent.putExtra("Std100",std);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        biobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),FormulaChRecyclerActivity.class);
                intent.putExtra("Subject100",3);
                intent.putExtra("Std100",std);
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