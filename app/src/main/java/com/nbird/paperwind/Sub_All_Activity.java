package com.nbird.paperwind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sub_All_Activity extends AppCompatActivity {
    Button button1,button2,button3,button4,button5,button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__all_);

        button1=(Button) findViewById(R.id.tipButton1);
        button2=(Button) findViewById(R.id.tipButton2);
        button3=(Button) findViewById(R.id.tipButton3);
        button4=(Button) findViewById(R.id.tipButton4);
        button5=(Button) findViewById(R.id.tipButton5);
        button6=(Button) findViewById(R.id.tipButton6);



        cigar();

    }

    public void cigar(){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),PreviousYearActivity.class);
                intent.putExtra("Chapter",1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),PreviousYearActivity.class);
                intent.putExtra("Chapter",2);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),PreviousYearActivity.class);
                intent.putExtra("Chapter",3);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),PreviousYearActivity.class);
                intent.putExtra("Chapter",4);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),PreviousYearActivity.class);
                intent.putExtra("Chapter",5);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),PreviousYearActivity.class);
                intent.putExtra("Chapter",6);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

    }

}