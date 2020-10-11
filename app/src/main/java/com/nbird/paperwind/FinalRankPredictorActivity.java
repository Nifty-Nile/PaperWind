package com.nbird.paperwind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FinalRankPredictorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int SelectedEntranceExam,inputdata,score,Gender0,Cast0,Goldennumber,Branch;
    TextView exam123,category123,gender123,rank,rankhead,Scoreint;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private Dialog loadingDialog;
    TextInputEditText scoretext;
    Button getCollege,donebutton;
    String value;
    private Spinner spinner2;
    private static final String[] paths123 = {"CSE","IT","ECE","EEE","ME","TE","Civil"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_rank_predictor);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(FinalRankPredictorActivity.this, android.R.layout.simple_spinner_item,paths123);


        exam123=(TextView) findViewById(R.id.textView8);
        category123=(TextView) findViewById(R.id.textcategory);
        gender123=(TextView) findViewById(R.id.gender);
        rank=(TextView) findViewById(R.id.rankint);
        Scoreint=(TextView) findViewById(R.id.scoreint);
        rankhead=(TextView) findViewById(R.id.rankhead);
        scoretext=(TextInputEditText) findViewById(R.id.username1);
        getCollege=(Button) findViewById(R.id.getCollege);
        donebutton=(Button) findViewById(R.id.done);

        SelectedEntranceExam=getIntent().getIntExtra("RankEE",0);
        inputdata=getIntent().getIntExtra("InputPredictor",0);
        score=getIntent().getIntExtra("Score1",0);
        Gender0=getIntent().getIntExtra("Gender",0);
        Cast0=getIntent().getIntExtra("cast",0);
        Branch=getIntent().getIntExtra("Branch",0);


        Goldennumber();
        TextViewDisplay();

        loadingDialog=new Dialog(FinalRankPredictorActivity.this);
        loadingDialog.setContentView(R.layout.activity_loading);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(true);
        loadingDialog.show();

        Scoreint.setText(String.valueOf(score));

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);
        spinner2.setOnItemSelectedListener(this);


        myRef.child("RankPredictor").child(String.valueOf(SelectedEntranceExam)).child(String.valueOf(inputdata)).child(String.valueOf(Gender0)).child(String.valueOf(Cast0)).child(String.valueOf(Goldennumber)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                value = snapshot.child("ranky").getValue(String.class);
                rank.setText(value);
                loadingDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FinalRankPredictorActivity.this, "Error while Loading....", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });


        getCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),CollegePredictorMainActivity.class);
                intent.putExtra("RankEE",SelectedEntranceExam);
                intent.putExtra("InputPredictor",2);
                intent.putExtra("Rank1",value);
                intent.putExtra("Gender",Gender0);
                intent.putExtra("cast",Cast0);
                intent.putExtra("Branch",Branch);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        donebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),RankPredictorInputActivity.class);
                intent.putExtra("RankEE",SelectedEntranceExam);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {



            switch (position) {
                case 0:
                    Branch=position+1;
                    break;
                case 1:
                    Branch=position+1;
                    break;
                case 2:
                    Branch=position+1;
                    break;
                case 3:
                    Branch=position+1;
                    break;
                case 4:
                    Branch=position+1;
                    break;
                case 5:
                    Branch=position+1;
                    break;
                case 6:
                    Branch=position+1;
                    break;


        }

    }


    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }


    public void TextViewDisplay(){
        switch (SelectedEntranceExam){
            case 1:
                exam123.setText("Entrance Exam: "+"Jee Advance");break;
            case 2:
                exam123.setText("Entrance Exam: "+"Jee Mains");break;
            case 3:
                exam123.setText("Entrance Exam: "+"NEET");break;
            case 4:
                exam123.setText("Entrance Exam: "+"VITEEE");break;
            case 5:
                exam123.setText("Entrance Exam: "+"COMED-K");break;
            case 6:
                exam123.setText("Entrance Exam: "+"NDA");break;
            case 7:
                exam123.setText("Entrance Exam: "+"BITSAT");break;
            case 8:
                exam123.setText("Entrance Exam: "+"KVPY");break;
            case 9:
                exam123.setText("Entrance Exam: "+"MHCET");break;
            case 10:
                exam123.setText("Entrance Exam: "+"SRMJEE");break;
            case 11:
                exam123.setText("Entrance Exam: "+"KCET");break;
            case 12:
                exam123.setText("Entrance Exam: "+"IPU-CET");break;
            case 13:
                exam123.setText("Entrance Exam: "+"MET");break;
            case 14:
                exam123.setText("Entrance Exam: "+"WBJEE");break;
            case 15:
                exam123.setText("Entrance Exam: "+"JEECUP");break;
            case 16:
                exam123.setText("Entrance Exam: "+"NEST");break;
            case 17:
                exam123.setText("Entrance Exam: "+"PESSAT");break;
            case 18:
                exam123.setText("Entrance Exam: "+"AMUEEE");break;
            case 19:
                exam123.setText("Entrance Exam: "+"IISER");break;
            case 20:
                exam123.setText("Entrance Exam: "+"UPSEE");break;
            case 21:
                exam123.setText("Entrance Exam: "+"NIFT");break;
        }
        switch (Gender0){
            case 1:
                gender123.setText("Gender: "+"Male");break;
            case 2:
                gender123.setText("Gender: "+"Female");break;
        }
        switch (Cast0){
            case 1:
                category123.setText("Category: "+"General");
                rankhead.setText("Gen. Rank:");break;
            case 2:
                category123.setText("Category: "+"Obc");rankhead.setText("OBC Rank:");break;
            case 3:
                category123.setText("Category: "+"ST");rankhead.setText("ST Rank:");break;
            case 4:
                category123.setText("Category: "+"SC");rankhead.setText("SC Rank:");break;
        }


    }

    public void Goldennumber(){
        switch (SelectedEntranceExam){
            case 1:
                JeeAdvancegold();break;
            case 2:
                JeeMainsgold();break;
            case 3:
                Neetgold();break;
        }
    }
    public void JeeAdvancegold() {

        switch (Cast0) {
            case 1:
                JeeAdvancegold_general();
                break;
            case 2:
                JeeAdvancegold_obc();
                break;
            case 3:
                JeeAdvancegold_st();
                break;
            case 4:
                JeeAdvancegold_sc();
                break;
        }
    }

    public void JeeMainsgold(){

    }

    public void Neetgold(){

    }

      public void JeeAdvancegold_general() {
          if (score >= 330) {
              Goldennumber = 1;
          } else if (score >= 300) {
              Goldennumber = 2;
          } else if (score >= 250) {
              Goldennumber = 3;
          } else if (score >= 200) {
              Goldennumber = 4;
          } else if (score >= 150) {
              Goldennumber = 5;
          } else if (score >= 100) {
              Goldennumber = 6;
          } else if (score >= 50) {
              Goldennumber = 7;
          } else {
              Goldennumber = 8;
          }

      }

    public void JeeAdvancegold_obc() {
        if (score >= 330) {
            Goldennumber = 1;
        } else if (score >= 300) {
            Goldennumber = 2;
        } else if (score >= 250) {
            Goldennumber = 3;
        } else if (score >= 200) {
            Goldennumber = 4;
        } else if (score >= 150) {
            Goldennumber = 5;
        } else if (score >= 100) {
            Goldennumber = 6;
        } else if (score >= 50) {
            Goldennumber = 7;
        } else {
            Goldennumber = 8;
        }

    }

    public void JeeAdvancegold_st() {
        if (score >= 330) {
            Goldennumber = 1;
        } else if (score >= 300) {
            Goldennumber = 2;
        } else if (score >= 250) {
            Goldennumber = 3;
        } else if (score >= 200) {
            Goldennumber = 4;
        } else if (score >= 150) {
            Goldennumber = 5;
        } else if (score >= 100) {
            Goldennumber = 6;
        } else if (score >= 50) {
            Goldennumber = 7;
        } else {
            Goldennumber = 8;
        }

    }

    public void JeeAdvancegold_sc() {
        if (score >= 330) {
            Goldennumber = 1;
        } else if (score >= 300) {
            Goldennumber = 2;
        } else if (score >= 250) {
            Goldennumber = 3;
        } else if (score >= 200) {
            Goldennumber = 4;
        } else if (score >= 150) {
            Goldennumber = 5;
        } else if (score >= 100) {
            Goldennumber = 6;
        } else if (score >= 50) {
            Goldennumber = 7;
        } else {
            Goldennumber = 8;
        }

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}