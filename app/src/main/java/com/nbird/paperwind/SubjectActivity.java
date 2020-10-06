package com.nbird.paperwind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SubjectActivity extends AppCompatActivity {

    List<Exam> lstExam;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        cardView=(CardView) findViewById(R.id.cardview_id);
        int Exam,Std,Paper;

        lstExam=new ArrayList<>();
        monkey();
        Exam=getIntent().getIntExtra("Exam",0);
        Std=getIntent().getIntExtra("Std",0);
        Paper=getIntent().getIntExtra("Paper",0);
        RecyclerView myrv=(RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewAdapterLink myAdapter=new RecyclerViewAdapterLink(this,lstExam,Exam,Std,Paper);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);





    }
    public void monkey(){
        lstExam.add(new Exam("Sparometre",R.drawable.back2,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new Exam("JEE Advanced",R.drawable.back2,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new Exam("JEE Advanced",R.drawable.back2,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
    }
}