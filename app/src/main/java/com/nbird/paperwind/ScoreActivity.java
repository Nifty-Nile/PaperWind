package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.User;

public class ScoreActivity extends AppCompatActivity {
    int totalmarks,physcore1,chemscore1,mathsscore1,mode123,set123;
    Button getrank,donebut;
    int SelectedEntranceExam;
    TextView title,marks;
    FirebaseAuth fAuth;
    String strexamname;
    String strtotal;
    String strphy;
    String strchem;
    String strmaths;
    String strmode,strsetno,ime,fullmarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        fAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref=database.getReference().child("User").child(fAuth.getCurrentUser().getUid()).child("ExamRecord").push();



        getrank=(Button) findViewById(R.id.tipButton1);
        donebut=(Button) findViewById(R.id.done);

        title=(TextView) findViewById(R.id.textView8);
        marks=(TextView) findViewById(R.id.textView9);



        totalmarks=getIntent().getIntExtra("totalmarks",0);
        SelectedEntranceExam=getIntent().getIntExtra("RankEE",0);
        physcore1=getIntent().getIntExtra("phymarks",0);
        chemscore1=getIntent().getIntExtra("chemmarks",0);
        mathsscore1=getIntent().getIntExtra("mathsmarks",0);
        mode123=getIntent().getIntExtra("modeint",0);
        set123=getIntent().getIntExtra("set",0);

         manupulator();

          strtotal=Integer.toString(totalmarks);
          strphy=Integer.toString(physcore1);
          strchem=Integer.toString(chemscore1);
          strmaths=Integer.toString(mathsscore1);
          strsetno=Integer.toString(set123);



          ime="https://firebasestorage.googleapis.com/v0/b/paper-wind.appspot.com/o/Pdfpic%2Fhiclipart.com.png?alt=media&token=2b48a128-f0e7-4e3d-8ac2-a5c5c40f51e5";





        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    EntranceExamRecorHolder user= new EntranceExamRecorHolder(strtotal,strexamname,strmode,strsetno,strphy,strchem,strmaths,ime,fullmarks);
                    ref.setValue(user);
                    Toast.makeText(ScoreActivity.this,"Exam Data Saved!",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ScoreActivity.this,"Exam Data Unable to Save!",Toast.LENGTH_LONG).show();
            }
        });

       donebut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(getBaseContext(),Menu1Activity.class);
               startActivity(intent);
               overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
               finish();
           }
       });



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

    public void manupulator(){
        switch (SelectedEntranceExam){
            case 1:
                strexamname="Jee Mains";
                fullmarks="Jee Mains Full Marks : 300";
                title.setText("Entrance Exam : Jee Mains");
                marks.setText("Marks : "+String.valueOf(totalmarks)+"/360");break;

        }

        switch (mode123){
            case 1:
                strmode="Entrance Text";break;
        }
    }
}