package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Model.User;

public class ScoreActivity extends AppCompatActivity {
    int totalmarks,physcore1,chemscore1,mathsscore1,mode123,set123;
    Button getrank,donebut;
    int SelectedEntranceExam;
    TextView title,marks,phytext,chemtext,mathstext,textView7,textView8,textView9,textView16,textView25,text100,text200;
    FirebaseAuth fAuth;
    String strexamname;
    String strtotal;
    String strphy;
    String strchem;
    String strmaths;
    String strmode,strsetno,ime,fullmarks;
    BarChart barChart;
    PieChart pieChart;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    int x=2,value;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        fAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref=database.getReference().child("User").child(fAuth.getCurrentUser().getUid()).child("ExamRecord").push();

        getrank=(Button) findViewById(R.id.tipButton1);
        donebut=(Button) findViewById(R.id.done);
        barChart=(BarChart) findViewById(R.id.barChart);
        pieChart=(PieChart) findViewById(R.id.pieChart);

        title=(TextView) findViewById(R.id.textView8);
        marks=(TextView) findViewById(R.id.textView9);
        phytext=(TextView) findViewById(R.id.phytext);
        chemtext=(TextView) findViewById(R.id.chemtext);
        mathstext=(TextView) findViewById(R.id.mathstext);

        textView7=(TextView) findViewById(R.id.textView7);
        textView16=(TextView) findViewById(R.id.textView16);
        textView25=(TextView) findViewById(R.id.textView25);
        text100=(TextView) findViewById(R.id.text100);
        text200=(TextView) findViewById(R.id.text200);

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.activity_loading);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);


        totalmarks=getIntent().getIntExtra("totalmarks",0);
        SelectedEntranceExam=getIntent().getIntExtra("RankEE",0);
        physcore1=getIntent().getIntExtra("phymarks",0);
        chemscore1=getIntent().getIntExtra("chemmarks",0);
        mathsscore1=getIntent().getIntExtra("mathsmarks",0);
        mode123=getIntent().getIntExtra("modeint",0);
        set123=getIntent().getIntExtra("set",0);

        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            ScrollView layout =(ScrollView) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            title.setTextColor(Color.parseColor("#ffffff"));
            marks.setTextColor(Color.parseColor("#ffffff"));
            phytext.setTextColor(Color.parseColor("#ffffff"));
            chemtext.setTextColor(Color.parseColor("#ffffff"));
            mathstext.setTextColor(Color.parseColor("#ffffff"));
            textView7.setTextColor(Color.parseColor("#ffffff"));
            textView16.setTextColor(Color.parseColor("#ffffff"));
            textView25.setTextColor(Color.parseColor("#ffffff"));
            text100.setTextColor(Color.parseColor("#ffffff"));
            text200.setTextColor(Color.parseColor("#ffffff"));



        }else{
            ScrollView layout =(ScrollView)findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);

            title.setTextColor(Color.parseColor("#000000"));
            marks.setTextColor(Color.parseColor("#000000"));
            phytext.setTextColor(Color.parseColor("#000000"));
            chemtext.setTextColor(Color.parseColor("#000000"));
            mathstext.setTextColor(Color.parseColor("#000000"));
            textView7.setTextColor(Color.parseColor("#000000"));
            textView16.setTextColor(Color.parseColor("#000000"));
            textView25.setTextColor(Color.parseColor("#000000"));
            text100.setTextColor(Color.parseColor("#000000"));
            text200.setTextColor(Color.parseColor("#000000"));

        }

         manupulator();

          strtotal=Integer.toString(totalmarks);
          strphy=Integer.toString(physcore1);
          strchem=Integer.toString(chemscore1);
          strmaths=Integer.toString(mathsscore1);
          strsetno=Integer.toString(set123);




          ime="https://firebasestorage.googleapis.com/v0/b/paper-wind.appspot.com/o/Pdfpic%2Fhiclipart.com.png?alt=media&token=2b48a128-f0e7-4e3d-8ac2-a5c5c40f51e5";


          phytext.setText("Physics : "+physcore1);
          chemtext.setText("Chemistry : "+chemscore1);
          mathstext.setText("Mathematics : "+mathsscore1);





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

        final ArrayList<BarEntry> visitors=new ArrayList<>();

        visitors.add(new BarEntry(1, totalmarks));

        myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("ExamRecord").orderByChild("totalmarks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {

                    value = Integer.parseInt(dataSnapshot1.child("totalmarks").getValue(String.class));

                    visitors.add(new BarEntry(x, value));
                    x++;
                }
                BarDataSet barDataSet=new BarDataSet(visitors,"Bar Data");
                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(16f);

                BarData barData=new BarData(barDataSet);

                barChart.setFitBars(true);
                barChart.setData(barData);
                barChart.getDescription().setText("Your All Test Staticstics");
                barChart.animateY(2000);


                loadingDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ScoreActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();

            }
        });



    /*    visitors.add(new BarEntry(1,220));
        visitors.add(new BarEntry(2,190));
        visitors.add(new BarEntry(3,20));
        visitors.add(new BarEntry(4,40));
        visitors.add(new BarEntry(5,120));
        visitors.add(new BarEntry(6,320));
        visitors.add(new BarEntry(7,140));
        visitors.add(new BarEntry(8,360));
        visitors.add(new BarEntry(9,120));

     */




        ArrayList<PieEntry> visitors1=new ArrayList<>();

        visitors1.add(new PieEntry(physcore1,"Physics"));
        visitors1.add(new PieEntry(chemscore1,"Chemistry"));
        visitors1.add(new PieEntry(mathsscore1,"Mathematics"));



        PieDataSet pieDataSet=new PieDataSet(visitors1,"Subjects Marks Distribution");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData=new PieData(pieDataSet);


        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Subjects");
        pieChart.animate();







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