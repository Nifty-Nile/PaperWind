package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FinalRankPredictorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int SelectedEntranceExam,inputdata,score,Gender0,Cast0,Goldennumber,Branch,inputrank;
    TextView exam123,category123,gender123,rank,rankhead,Scoreint,textView7,Text3,dis2,Text200,rankheading;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private Dialog loadingDialog;
    TextInputEditText scoretext;
    Button getCollege,donebutton;
    String value;
    androidx.appcompat.widget.Toolbar toolbar;
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

        textView7=(TextView) findViewById(R.id.textView7);
        Text3=(TextView) findViewById(R.id.Text3);
        dis2=(TextView) findViewById(R.id.dis2);
        Text200=(TextView) findViewById(R.id.Text200);
        rankheading=(TextView) findViewById(R.id.rankheading);

        SelectedEntranceExam=getIntent().getIntExtra("RankEE",0);
        inputdata=getIntent().getIntExtra("InputPredictor",0);
        score=getIntent().getIntExtra("Score1",0);
        Gender0=getIntent().getIntExtra("Gender",0);
        Cast0=getIntent().getIntExtra("cast",0);
        Branch=getIntent().getIntExtra("Branch",0);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Rank Predictor");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            exam123.setTextColor(Color.parseColor("#ffffff"));
            category123.setTextColor(Color.parseColor("#ffffff"));
            gender123.setTextColor(Color.parseColor("#ffffff"));
            rank.setTextColor(Color.parseColor("#ffffff"));
            Scoreint.setTextColor(Color.parseColor("#ffffff"));
            rankhead.setTextColor(Color.parseColor("#ffffff"));
            textView7.setTextColor(Color.parseColor("#ffffff"));
            Text200.setTextColor(Color.parseColor("#ffffff"));
            Text3.setTextColor(Color.parseColor("#ffffff"));
            dis2.setTextColor(Color.parseColor("#ffffff"));
            rankheading.setTextColor(Color.parseColor("#ffffff"));
        }else{
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);

            exam123.setTextColor(Color.parseColor("#000000"));
            category123.setTextColor(Color.parseColor("#000000"));
            gender123.setTextColor(Color.parseColor("#000000"));
            rank.setTextColor(Color.parseColor("#000000"));
            Scoreint.setTextColor(Color.parseColor("#000000"));
            rankhead.setTextColor(Color.parseColor("#000000"));
            textView7.setTextColor(Color.parseColor("#000000"));
            Text200.setTextColor(Color.parseColor("#000000"));
            Text3.setTextColor(Color.parseColor("#000000"));
            dis2.setTextColor(Color.parseColor("#000000"));
            rankheading.setTextColor(Color.parseColor("#000000"));
        }



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

        scoretext.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            if(!college()) {
                                return false;
                            }
                            inputrank=Integer.valueOf(scoretext.getText().toString());
                            Intent intent=new Intent(getBaseContext(),CollegePredictorMainActivity.class);
                            intent.putExtra("RankEE",SelectedEntranceExam);
                            intent.putExtra("InputPredictor",2);
                            intent.putExtra("Rank1",inputrank);
                            intent.putExtra("Gender",Gender0);
                            intent.putExtra("cast",Cast0);
                            intent.putExtra("Branch",Branch);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                            finish();



                    }
                }
                return false;
            }
        });


        getCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputrank=Integer.valueOf(scoretext.getText().toString());
                Intent intent=new Intent(getBaseContext(),CollegePredictorMainActivity.class);
                intent.putExtra("RankEE",SelectedEntranceExam);
                intent.putExtra("InputPredictor",2);
                intent.putExtra("Rank1",inputrank);
                intent.putExtra("Gender",Gender0);
                intent.putExtra("cast",Cast0);
                intent.putExtra("Branch",Branch);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
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
                if(SelectedEntranceExam==2){
                category123.setText("Category: "+"All Catergory");rankhead.setText("Percentile: ");
            }else {
                category123.setText("Category: " + "General");rankhead.setText("Gen. Rank:");
                break;
            }
            case 2:
                if(SelectedEntranceExam==2){
                    category123.setText("Category: "+"All Catergory");rankhead.setText("Percentile: ");
                }else {
                    category123.setText("Category: " + "Obc");rankhead.setText("OBC Rank:");
                    break;
                }
            case 3:
                if(SelectedEntranceExam==2){
                    category123.setText("Category: "+"All Catergory");rankhead.setText("Percentile: ");
                }else {
                    category123.setText("Category: " + "ST");rankhead.setText("ST Rank:");
                    break;
                }
            case 4:
                if(SelectedEntranceExam==2){
                    category123.setText("Category: "+"All Catergory");rankhead.setText("Percentile: ");
                }else {
                    category123.setText("Category: " + "SC");rankhead.setText("SC Rank:");
                    break;
                }
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
        switch (Cast0) {
            case 1:
                JeeMainsgold_general();
                break;
            case 2:
                JeeMainsgold_general();
                break;
            case 3:
                JeeMainsgold_general();
                break;
            case 4:
                JeeMainsgold_general();
                break;

        }
    }

    public void Neetgold(){

    }

      public void JeeAdvancegold_general() {
          if (score >= 320) {
              Goldennumber = 1;
          } else if (score >= 300) {
              Goldennumber = 2;
          } else if (score >= 280) {
              Goldennumber = 3;
          } else if (score >= 250) {
              Goldennumber = 4;
          } else if (score >= 230) {
              Goldennumber = 5;
          } else if (score >= 200) {
              Goldennumber = 6;
          } else if (score >= 180) {
              Goldennumber = 7;
          }else if (score >= 160) {
              Goldennumber = 8;
          } else if (score >= 150) {
              Goldennumber = 9;
          } else if (score >= 140) {
              Goldennumber = 10;
          } else if (score >= 135) {
              Goldennumber = 11;
          } else if (score >= 130) {
              Goldennumber = 12;
          } else if (score >= 120) {
              Goldennumber = 12;
          } else if (score >= 110) {
              Goldennumber = 14;
          }else if (score >= 100) {
              Goldennumber = 15;
          } else if (score >= 90) {
              Goldennumber = 16;
          } else if (score >= 80) {
              Goldennumber = 17;
          } else if (score >= 70) {
              Goldennumber = 18;
          } else if (score >= 60) {
              Goldennumber = 19;
          } else if (score >= 50) {
              Goldennumber = 20;
          } else if (score >= 30) {
              Goldennumber = 21;
          }else if (score >= 10) {
              Goldennumber = 22;
          } else  {
              Goldennumber = 23;
          }


      }

    public void JeeAdvancegold_obc() {
        if (score >= 310) {
            Goldennumber = 1;
        } else if (score >= 300) {
            Goldennumber = 2;
        } else if (score >= 280) {
            Goldennumber = 3;
        } else if (score >= 260) {
            Goldennumber = 4;
        } else if (score >= 245) {
            Goldennumber = 5;
        } else if (score >= 200) {
            Goldennumber = 6;
        } else if (score >= 180) {
            Goldennumber = 7;
         } else if (score >= 150) {
        Goldennumber = 8;
       } else if (score >= 120) {
        Goldennumber = 9;
       } else if (score >= 100) {
        Goldennumber = 10;
       } else if (score >= 70) {
        Goldennumber = 11;
       }else if (score >= 40) {
            Goldennumber = 12;
        } else if (score >= 20) {
            Goldennumber = 13;
        } else {
            Goldennumber = 14;
        }

    }

    public void JeeAdvancegold_st() {
        if (score >= 290) {
            Goldennumber = 1;
        } else if (score >= 250) {
            Goldennumber = 2;
        } else if (score >= 150) {
            Goldennumber = 3;
        } else if (score >= 130) {
            Goldennumber = 4;
        } else if (score >= 90) {
            Goldennumber = 5;
        } else if (score >= 70) {
            Goldennumber = 6;
        } else if (score >= 60) {
            Goldennumber = 7;
        } else if (score >= 40) {
            Goldennumber = 8;
        } else if (score >= 20) {
            Goldennumber = 9;
        } else {
            Goldennumber = 10;
        }

    }

    public void JeeAdvancegold_sc() {
        if (score >= 300) {
            Goldennumber = 1;
        } else if (score >= 250) {
            Goldennumber = 2;
        } else if (score >= 200) {
            Goldennumber = 3;
        } else if (score >= 170) {
            Goldennumber = 4;
        } else if (score >= 150) {
            Goldennumber = 5;
        } else if (score >= 125) {
            Goldennumber = 6;
        } else if (score >= 100) {
            Goldennumber = 7;
        } else if (score >= 80) {
            Goldennumber = 8;
        } else if (score >= 60) {
            Goldennumber = 9;
        } else if (score >= 40) {
            Goldennumber = 10;
        } else if (score >= 20) {
            Goldennumber = 11;
        }else {
            Goldennumber = 12;
        }

    }

    public void JeeMainsgold_general(){
        if (score >= 280) {
            Goldennumber = 1;
        } else if (score >= 271) {
            Goldennumber = 2;
        } else if (score >= 263) {
            Goldennumber = 3;
        } else if (score >= 250) {
            Goldennumber = 4;
        } else if (score >= 241) {
            Goldennumber = 5;
        } else if (score >= 231) {
            Goldennumber = 6;
        } else if (score >= 221) {
            Goldennumber = 7;
        } else if (score >= 211) {
            Goldennumber = 8;
        }else if (score >= 201) {
            Goldennumber = 9;
        } else if (score >= 191) {
            Goldennumber = 10;
        } else if (score >= 181) {
            Goldennumber = 11;
        } else if (score >= 171) {
            Goldennumber = 12;
        } else if (score >= 161) {
            Goldennumber = 13;
        } else if (score >= 151) {
            Goldennumber = 14;
        }else if (score >= 141) {
            Goldennumber = 15;
        } else if (score >= 131) {
            Goldennumber = 16;
        } else if (score >= 121) {
            Goldennumber = 17;
        } else if (score >= 111) {
            Goldennumber = 18;
        } else if (score >= 101) {
            Goldennumber = 19;
        } else if (score >= 91) {
            Goldennumber = 20;
        }else if (score >= 81) {
            Goldennumber = 21;
        } else if (score >= 71) {
            Goldennumber = 22;
        } else if (score >= 61) {
            Goldennumber = 23;
        } else if (score >= 51) {
            Goldennumber = 24;
        } else if (score >= 45) {
            Goldennumber = 25;
        } else if (score >= 40) {
            Goldennumber = 26;
        }else if (score >= 35) {
            Goldennumber = 27;
        } else if (score >= 30) {
            Goldennumber = 28;
        } else if (score >= 25) {
            Goldennumber = 29;
        } else if (score >= 20) {
            Goldennumber = 30;
        } else if (score >= 15) {
            Goldennumber = 31;
        }else if (score >= 10) {
            Goldennumber = 32;
        } else if (score >= 5) {
            Goldennumber = 33;
        } else if (score >= 1) {
            Goldennumber = 34;
        } else {
            Goldennumber = 35;
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
    private boolean college(){
        String name2=scoretext.getText().toString();
        int len=name2.length();
        int i,r=0;
        if(name2.isEmpty()){
            scoretext.setError("Field cannot be empty");
            return false;
        }else if(name2.length()>7||name2.length()<0){
            scoretext.setError("Rank cannot be negative or more than 6 digits");
            return false;
        }
        else if(1==1) {
            for (i = 0; i < name2.length(); i++) {
                Boolean flag = Character.isDigit(name2.charAt(i));
                if (flag) {
                    r=r+1;
                }
            }
            if(r!=len){
                scoretext.setError("Rank should be an integer");
                return false;
            }
        }
        else
            scoretext.setError(null);
        return true;
    }
}