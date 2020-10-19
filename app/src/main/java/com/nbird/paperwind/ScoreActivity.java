package com.nbird.paperwind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    int totalmarks;
    Button getrank;
    int SelectedEntranceExam;
    TextView title,marks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        getrank=(Button) findViewById(R.id.tipButton1);

        title=(TextView) findViewById(R.id.textView8);
        marks=(TextView) findViewById(R.id.textView9);



        totalmarks=getIntent().getIntExtra("totalmarks",0);
        SelectedEntranceExam=getIntent().getIntExtra("RankEE",0);

        switch (SelectedEntranceExam){
            case 1:
                title.setText("Entrance Exam : Jee Advance");
                marks.setText("Marks : "+String.valueOf(totalmarks)+"/360");break;

        }


       getrank.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(getBaseContext(),RankPredictorInputActivity.class);
               intent.putExtra("totalmarks",totalmarks);
               intent.putExtra("RankEE",SelectedEntranceExam);

               startActivity(intent);
               overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
           }
       });
    }

    public void onBackPressed() {
        final SharedPreferences minutes100 = getBaseContext().getSharedPreferences("minutes100", 0);
        final SharedPreferences.Editor editorminutes100 = minutes100.edit();

        final SharedPreferences seconds100 = getBaseContext().getSharedPreferences("seconds100", 0);
        final SharedPreferences.Editor editorseconds100 = seconds100.edit();


        editorminutes100.clear().commit();
        editorseconds100.clear().commit();
        Intent intent=new Intent(getBaseContext(),Menu1Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        ScoreActivity.super.onBackPressed();
        finish();
    }
}