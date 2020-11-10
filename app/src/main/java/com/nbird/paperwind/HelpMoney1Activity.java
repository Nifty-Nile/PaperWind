package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class HelpMoney1Activity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    int parent,child;
    TextView textView1,textView2,textView3,textView4,textView5,textView6;
    ImageView imageView1,imageView2,imageView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_money1);

        parent=getIntent().getIntExtra("parent",0);
        child=getIntent().getIntExtra("child",0);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Guide Activity");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        parentfunction();

    }
    public void parentfunction(){
        switch (parent){
            case 1:
              switch (child){
                  case 1:
                      p1c1();break;
                  case 2:
                      p1c2();break;
              }break;
            case 2:
                switch (child){
                    case 1:
                        p2c1();break;
                    case 2:
                        p2c2();break;
                }break;
            case 3:
                switch (child){
                    case 1:
                        p3c1();break;
                }break;
            case 4:
                switch (child){
                    case 1:
                        p4c1();break;
                    case 2:
                        p4c2();break;
                }break;
            case 5:
                switch (child){
                    case 1:
                        p5c1();break;
                    case 2:
                        p5c2();break;
                    case 3:
                        p5c3();break;
                    case 4:
                        p5c4();break;
                }break;
            case 6:
                switch (child){
                    case 1:
                        p6c1();break;
                    case 2:
                        p6c2();break;
                }break;
        }


    }
    public void p1c1(){

    }
    public void p1c2(){

    }
    public void p2c1(){

    }
    public void p2c2(){

    }
    public void p3c1(){

    }
    public void p4c1(){

    }
    public void p4c2(){

    }
    public void p5c1(){

    }
    public void p5c2(){

    }
    public void p5c3(){

    }
    public void p5c4(){

    }
    public void p6c1(){

    }
    public void p6c2(){

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

}