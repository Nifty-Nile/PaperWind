package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class TestAgrementActivity extends AppCompatActivity {

    Button submitbutton;
    CheckBox checkBox;
    int phy,chem,maths,bio,position,mode,set,score;
    androidx.appcompat.widget.Toolbar toolbar;
    TextView text0,text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11
            ,text12,text13,text14,text15,text16,text17;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_agrement);

        submitbutton=(Button) findViewById(R.id.submitbutton);
        checkBox=(CheckBox) findViewById(R.id.checkbox);

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
        text13=(TextView) findViewById(R.id.text13);
        text14=(TextView) findViewById(R.id.text14);
        text15=(TextView) findViewById(R.id.text15);
        text16=(TextView) findViewById(R.id.text16);
        text17=(TextView) findViewById(R.id.text17);


        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            LinearLayout layout =(LinearLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);

            text0.setTextColor(Color.parseColor("#ffffff"));
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
            text14.setTextColor(Color.parseColor("#ffffff"));
            text15.setTextColor(Color.parseColor("#ffffff"));
            text16.setTextColor(Color.parseColor("#ffffff"));
            text17.setTextColor(Color.parseColor("#ffffff"));



        }else{
            LinearLayout layout =(LinearLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);

            text0.setTextColor(Color.parseColor("#000000"));
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
            text14.setTextColor(Color.parseColor("#000000"));
            text15.setTextColor(Color.parseColor("#000000"));
            text16.setTextColor(Color.parseColor("#000000"));
            text17.setTextColor(Color.parseColor("#000000"));


        }

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Test Instructions");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        phy=getIntent().getIntExtra("phy",0);
        chem=getIntent().getIntExtra("chem",0);
        maths=getIntent().getIntExtra("maths",0);
        bio=getIntent().getIntExtra("bio",0);
        position=getIntent().getIntExtra("position",0);
        mode=getIntent().getIntExtra("mode",0);
        set=getIntent().getIntExtra("set",0);
        score=getIntent().getIntExtra("score",0);



        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                    if(isChecked){
                                                        submitbutton.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                Intent intent=new Intent(getBaseContext(),OnlineExamGridShow.class);
                                                                intent.putExtra("position",position);
                                                                intent.putExtra("mode",mode);
                                                                intent.putExtra("set",set);
                                                                intent.putExtra("phy",phy);
                                                                intent.putExtra("chem",chem);
                                                                intent.putExtra("maths",maths);
                                                                intent.putExtra("bio",bio);
                                                                startActivity(intent);
                                                            }
                                                        });
                                                    }
                                               }
                                           }
        );

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}