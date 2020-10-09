package com.nbird.paperwind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LinkSubjects extends AppCompatActivity {
    Button phybutton,chembutton,biobutton;
    int Exam,Std,Paper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_subjects);

        Exam=getIntent().getIntExtra("Exam",0);
        Std=getIntent().getIntExtra("Std",0);
        Paper=getIntent().getIntExtra("Paper",0);

        phybutton=(Button) findViewById(R.id.tipButton1);
        chembutton=(Button) findViewById(R.id.tipButton2);
        biobutton=(Button) findViewById(R.id.tipButton3);

        phybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),SubjectActivity.class);
                intent.putExtra("Exam",Exam);
                intent.putExtra("Std",Std);
                intent.putExtra("Paper",Paper);
                intent.putExtra("LabSub",1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        chembutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),SubjectActivity.class);
                intent.putExtra("Exam",Exam);
                intent.putExtra("Std",Std);
                intent.putExtra("Paper",Paper);
                intent.putExtra("LabSub",2);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        biobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),SubjectActivity.class);
                intent.putExtra("Exam",Exam);
                intent.putExtra("Std",Std);
                intent.putExtra("Paper",Paper);
                intent.putExtra("LabSub",3);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
    }
}