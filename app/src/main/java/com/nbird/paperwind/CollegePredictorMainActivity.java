package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CollegePredictorMainActivity extends AppCompatActivity {
    TextView entranceexamtitle,ranktext,gendertext,casttext,branchtext,textView7,textView10;
    RecyclerView recyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private Dialog loadingDialog;
    private List<CollegePredictorMainHolder> list;
    int SelectedEntranceExam,InputText,Rank,Gender,Cast,Branch,PlatinumNumber;
    String rank123;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_predictor_main);

        SelectedEntranceExam=getIntent().getIntExtra("RankEE",0);
        InputText=getIntent().getIntExtra("InputPredictor",0);
        Rank=getIntent().getIntExtra("Rank1",0);
        Gender=getIntent().getIntExtra("Gender",0);
        Cast=getIntent().getIntExtra("cast",0);
        Branch=getIntent().getIntExtra("Branch",0);
        InputText=1;
        PlatinumNumber=1;
        rank123=String.valueOf(Rank);

        entranceexamtitle=(TextView) findViewById(R.id.textView8);
        ranktext=(TextView) findViewById(R.id.textView9);
        gendertext=(TextView) findViewById(R.id.gender);
        casttext=(TextView) findViewById(R.id.textcategory);
        branchtext=(TextView) findViewById(R.id.Branch00);
        textView7=(TextView) findViewById(R.id.textView7);
        textView10=(TextView) findViewById(R.id.textView10);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("College Predictor");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            entranceexamtitle.setTextColor(Color.parseColor("#ffffff"));
            ranktext.setTextColor(Color.parseColor("#ffffff"));
            gendertext.setTextColor(Color.parseColor("#ffffff"));
            casttext.setTextColor(Color.parseColor("#ffffff"));
            branchtext.setTextColor(Color.parseColor("#ffffff"));
            textView7.setTextColor(Color.parseColor("#ffffff"));
            textView10.setTextColor(Color.parseColor("#ffffff"));
        }else{
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);

            entranceexamtitle.setTextColor(Color.parseColor("#000000"));
            ranktext.setTextColor(Color.parseColor("#000000"));
            gendertext.setTextColor(Color.parseColor("#000000"));
            casttext.setTextColor(Color.parseColor("#000000"));
            branchtext.setTextColor(Color.parseColor("#000000"));
            textView7.setTextColor(Color.parseColor("#000000"));
            textView10.setTextColor(Color.parseColor("#000000"));

        }


        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.activity_loading);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(true);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();


        final CollegePredictorMainAdapter categoryAdapter = new CollegePredictorMainAdapter(list);
        recyclerView.setAdapter(categoryAdapter);

        loadingDialog.show();

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigatio);

        bottomNavigationView.setSelectedItemId(R.id.rankpredictor);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Menu1Activity.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.rankpredictor:
                        return true;
                    case R.id.Formulas:
                        startActivity(new Intent(getApplicationContext(),FormulaSTDActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.money:
                        startActivity(new Intent(getApplicationContext(),MoneyActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        Platinumnumber();

        if(SelectedEntranceExam==5||SelectedEntranceExam==11||SelectedEntranceExam==7||SelectedEntranceExam==8||SelectedEntranceExam==10){
            Cast=1;
            Gender=1;
        }

        myRef.child("CollegePredictor").child(String.valueOf(SelectedEntranceExam)).child(String.valueOf(Cast)).child(String.valueOf(Gender)).child(String.valueOf(PlatinumNumber)).orderByChild("branch").equalTo(Branch).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(CollegePredictorMainHolder.class));

                }
                if(list.size() == 0){
                    Toast.makeText(CollegePredictorMainActivity.this, "For this Rank there is no Data Available!", Toast.LENGTH_LONG).show();
                }
                categoryAdapter.notifyDataSetChanged();
                loadingDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CollegePredictorMainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                finish();
            }
        });
        TextViewDisplay();

    }

    public void TextViewDisplay(){
        switch (SelectedEntranceExam){
            case 1:
                entranceexamtitle.setText("Entrance Exam: "+"Jee Advance");break;
            case 2:
                entranceexamtitle.setText("Entrance Exam: "+"Jee Mains");break;
            case 3:
                entranceexamtitle.setText("Entrance Exam: "+"NEET");break;
            case 4:
                entranceexamtitle.setText("Entrance Exam: "+"VITEEE");break;
            case 5:
                entranceexamtitle.setText("Entrance Exam: "+"COMED-K");break;
            case 6:
                entranceexamtitle.setText("Entrance Exam: "+"NDA");break;
            case 7:
                entranceexamtitle.setText("Entrance Exam: "+"BITSAT");break;
            case 8:
                entranceexamtitle.setText("Entrance Exam: "+"MET");break;
            case 9:
                entranceexamtitle.setText("Entrance Exam: "+"MHCET");break;
            case 10:
                entranceexamtitle.setText("Entrance Exam: "+"SRMJEE");break;
            case 11:
                entranceexamtitle.setText("Entrance Exam: "+"KCET");break;
            case 12:
                entranceexamtitle.setText("Entrance Exam: "+"IPU-CET");break;
        }

        if(SelectedEntranceExam==1) {
            switch (Gender) {
                case 1:
                    gendertext.setText("Gender: " + "Male");
                    break;
                case 2:
                    gendertext.setText("Gender: " + "Female");
                    break;
            }
        }else if(SelectedEntranceExam==4||SelectedEntranceExam==5||SelectedEntranceExam==11||SelectedEntranceExam==7||SelectedEntranceExam==8||SelectedEntranceExam==10){
            switch (Gender) {
                case 1:
                    gendertext.setText("Gender: " + "Not Considered");
                    break;
                case 2:
                    gendertext.setText("Gender: " + "Not Considered");
                    break;
            }
        }

        if(SelectedEntranceExam==1) {
            switch (Cast) {
                case 1:
                    casttext.setText("Category: " + "General");
                    ranktext.setText("Gen. Rank: " + rank123);
                    break;
                case 2:
                    casttext.setText("Category: " + "Obc");
                    ranktext.setText("OBC Rank: " + rank123);
                    break;
                case 3:
                    casttext.setText("Category: " + "ST");
                    ranktext.setText("ST Rank: " + rank123);
                    break;
                case 4:
                    casttext.setText("Category: " + "SC");
                    ranktext.setText("SC Rank: " + rank123);
                    break;
            }
        }else if(SelectedEntranceExam==4||SelectedEntranceExam==5||SelectedEntranceExam==11||SelectedEntranceExam==7||SelectedEntranceExam==8||SelectedEntranceExam==10){
            switch (Cast) {
                case 1:
                    casttext.setText("Category: " + "Not Considered");
                    ranktext.setText("Rank: " + rank123);
                    break;
                case 2:
                    casttext.setText("Category: " + "Not Considered");
                    ranktext.setText("Rank: " + rank123);
                    break;
                case 3:
                    casttext.setText("Category: " + "Not Considered");
                    ranktext.setText("Rank: " + rank123);
                    break;
                case 4:
                    casttext.setText("Category: " + "Not Considered");
                    ranktext.setText("Rank: " + rank123);
                    break;
            }
        }

        if(SelectedEntranceExam==1){
            switch (Branch){
                case 1:
                    branchtext.setText("Branch: "+"Computer Science and Engineering");break;
                case 2:
                    branchtext.setText("Branch: "+"Electronics and Communication Engineering");break;
                case 3:
                    branchtext.setText("Branch: "+"Electrical and Electronics Engineering");break;
                case 4:
                    branchtext.setText("Branch: "+"Mechanical Engineering");break;
                case 5:
                    branchtext.setText("Branch: "+"Civil Engineering");break;
                case 6:
                    branchtext.setText("Branch: "+"Mathematics");break;
                case 7:
                    branchtext.setText("Branch: "+"Physics"); break;
                case 8:
                    branchtext.setText("Branch: "+"Chemistry"); break;
            }
        }else if(SelectedEntranceExam==4){
            switch (Branch){
                case 1:
                    branchtext.setText("Branch: "+"Computer Science and Engineering");break;
                case 2:
                    branchtext.setText("Branch: "+"Information technology");break;
                case 3:
                    branchtext.setText("Branch: "+"Computer science and Engg. (Specialisation in Bioinformatics)");break;
                case 4:
                    branchtext.setText("Branch: "+"BioMedical Engineering");break;
                case 5:
                    branchtext.setText("Branch: "+"Biotechnology");break;
                case 6:
                    branchtext.setText("Branch: "+"Civil Engineering");break;
                case 7:
                    branchtext.setText("Branch: "+"Electronics and Communication Engineering"); break;
                case 8:
                    branchtext.setText("Branch: "+"Electrical and Electronics Engineering");break;
                case 9:
                    branchtext.setText("Branch: "+"Electronics and Instrumentation Engineering");break;
                case 10:
                    branchtext.setText("Branch: "+"Mechanical Engineering");break;
                case 11:
                    branchtext.setText("Branch: "+"Mechanical (Spec. in Automotive Engineering)");break;
                case 12:
                    branchtext.setText("Branch: "+"Mechanical (Spec. in Energy Engineering)");break;
                case 13:
                    branchtext.setText("Branch: "+"Chemical Engineering"); break;
                case 14:
                    branchtext.setText("Branch: "+"ECE (Spec.Internet of Things and Sensor)");break;
                case 15:
                    branchtext.setText("Branch: "+"Comp.Science Engg.(Spec.in Information Security)");break;
                case 16:
                    branchtext.setText("Branch: "+"Fashion Technology");break;
                case 17:
                    branchtext.setText("Branch: "+"Electronics and Computer Engineering");break;

            }
        }else if(SelectedEntranceExam==5||SelectedEntranceExam==11){
            switch (Branch){
                case 1:
                    branchtext.setText("Branch: "+"Computer Science and Engineering");break;
                case 2:
                    branchtext.setText("Branch: "+"Information Science and Engineering");break;
                case 3:
                    branchtext.setText("Branch: "+"Electronics and Communication Engineering");break;
                case 4:
                    branchtext.setText("Branch: "+"Mechanical Engineering");break;
                case 5:
                    branchtext.setText("Branch: "+"Electrical and Electronics Engineering");break;
                case 6:
                    branchtext.setText("Branch: "+"Telecommunication Engineering");break;
                case 7:
                    branchtext.setText("Branch: "+"Civil Engineering"); break;
                case 8:
                    branchtext.setText("Branch: "+"Biotechnology");break;
            }
        }else if(SelectedEntranceExam==7){
            switch (Branch){
                case 1:
                    branchtext.setText("Branch: "+"Computer Science and Engineering");break;
                case 2:
                    branchtext.setText("Branch: "+"Electronics and Instrumentation Engineering");break;
                case 3:
                    branchtext.setText("Branch: "+"Chemical Engineering");break;
                case 4:
                    branchtext.setText("Branch: "+"Civil Engineering");break;
                case 5:
                    branchtext.setText("Branch: "+"Electrical and Electronics Engineering");break;
                case 6:
                    branchtext.setText("Branch: "+"Mechanical Engineering");break;
                case 7:
                    branchtext.setText("Branch: "+"Manufacturing Engineering"); break;
                case 8:
                    branchtext.setText("Branch: "+"Electronics and Communication Engineering");break;
            }
        }else if(SelectedEntranceExam==8){
            switch (Branch){
                case 1:
                    branchtext.setText("Branch: "+"Computer Science and Engineering");break;
                case 2:
                    branchtext.setText("Branch: "+"Information technology");break;
                case 3:
                    branchtext.setText("Branch: "+"Communication and Computer Engineering");break;
                case 4:
                    branchtext.setText("Branch: "+"Data Science And Engineering");break;
                case 5:
                    branchtext.setText("Branch: "+"Electronics and Communication Engineering");break;
                case 6:
                    branchtext.setText("Branch: "+"Electrical and Electronics Engineering");break;
                case 7:
                    branchtext.setText("Branch: "+"Mechatronics"); break;
                case 8:
                    branchtext.setText("Branch: "+"Mechanical Engineering");break;
                case 9:
                    branchtext.setText("Branch: "+"Aerospace Engineering");break;
                case 10:
                    branchtext.setText("Branch: "+"Electronics and Instrumentation Engineering");break;
                case 11:
                    branchtext.setText("Branch: "+"Automobile Engineering");break;
                case 12:
                    branchtext.setText("Branch: "+"Biotechnology");break;
                case 13:
                    branchtext.setText("Branch: "+"Bio Medical Engineering"); break;
                case 14:
                    branchtext.setText("Branch: "+"Chemical Engineering");break;
                case 15:
                    branchtext.setText("Branch: "+"Civil Engineering");break;
                case 16:
                    branchtext.setText("Branch: "+"Industrial and Production Engineering");break;
                case 17:
                    branchtext.setText("Branch: "+"Media Technology");break;

            }
        }else if(SelectedEntranceExam==10){
            switch (Branch){
                case 1:
                    branchtext.setText("Branch: "+"Computer Science and Engineering");break;
                case 2:
                    branchtext.setText("Branch: "+"Information technology");break;
                case 3:
                    branchtext.setText("Branch: "+"Electronics and Communication Engineering");break;
                case 4:
                    branchtext.setText("Branch: "+"Mechanical Engineering");break;
                case 5:
                    branchtext.setText("Branch: "+"Electrical and Electronics Engineering");break;
                case 6:
                    branchtext.setText("Branch: "+"Computer Science and Engineering(Software Engineering)");break;
                case 7:
                    branchtext.setText("Branch: "+"Mechatronics"); break;
                case 8:
                    branchtext.setText("Branch: "+"Biotechnology");break;
                case 9:
                    branchtext.setText("Branch: "+"Aerospace Engineering");break;
                case 10:
                    branchtext.setText("Branch: "+"Bio Medical Engineering");break;
                case 11:
                    branchtext.setText("Branch: "+"Automobile Engineering");break;
                case 12:
                    branchtext.setText("Branch: "+"Electronics and Instrumentation Engineering");break;
                case 13:
                    branchtext.setText("Branch: "+"Biotechnology Engineering(Genetics Engineering)"); break;
                case 14:
                    branchtext.setText("Branch: "+"Chemical Engineering");break;
                case 15:
                    branchtext.setText("Branch: "+"Nanotechnology Engineer");break;
                case 16:
                    branchtext.setText("Branch: "+"Civil Engineering");break;
            }
        }
    }

    public void Platinumnumber(){
        switch (SelectedEntranceExam){
            case 1:
                JeeAdvancegold();break;
            case 2:
                JeeMainsgold();break;
            case 3:
                Neetgold();break;
            case 4:
                Viteeegold();break;
            case 5:
                Comedkgold();break;
            case 7:
                Bitsatgold();break;
            case 8:
                Metgold();break;
            case 10:
                Srmjeegold();break;
            case 11:
                Kcetgold();break;
        }
    }

    public void Srmjeegold(){
        if (Rank <= 10000) {
            PlatinumNumber = 1;
        } else if (Rank <= 23000) {
            PlatinumNumber = 2;
        } else if (Rank <= 27000) {
            PlatinumNumber = 3;
        } else if (Rank <= 31000) {
            PlatinumNumber = 4;
        } else if (Rank <= 42000) {
            PlatinumNumber = 5;
        } else if (Rank <= 48000) {
            PlatinumNumber = 6;
        } else if (Rank <= 52000) {
            PlatinumNumber = 7;
        } else if (Rank <= 55000) {
            PlatinumNumber = 8;
        } else if (Rank <= 61000) {
            PlatinumNumber = 9;
        } else if (Rank <= 70000) {
            PlatinumNumber = 10;
        }
    }

    public void Metgold(){
        if (Rank <= 1150) {
            PlatinumNumber = 1;
        } else if (Rank <= 2240) {
            PlatinumNumber = 2;
        } else if (Rank <= 3050) {
            PlatinumNumber = 3;
        } else if (Rank <= 3700) {
            PlatinumNumber = 4;
        } else if (Rank <= 5700) {
            PlatinumNumber = 5;
        } else if (Rank <= 11000) {
            PlatinumNumber = 6;
        } else if (Rank <= 17400) {
            PlatinumNumber = 7;
        } else if (Rank <= 18600) {
            PlatinumNumber = 8;
        } else if (Rank <= 19300) {
            PlatinumNumber = 9;
        } else if (Rank <= 19700) {
            PlatinumNumber = 10;
        } else if (Rank <= 26310) {
            PlatinumNumber = 11;
        } else if (Rank <= 33900) {
            PlatinumNumber = 12;
        } else if (Rank <= 38110) {
            PlatinumNumber = 13;
        } else if (Rank <= 39310) {
            PlatinumNumber = 14;
        } else if (Rank <= 40400) {
            PlatinumNumber = 15;
        } else if (Rank <= 40800) {
            PlatinumNumber = 16;
        }
    }

    public void Comedkgold() {
        if (Rank <= 420) {
            PlatinumNumber = 1;
        } else if (Rank <= 920) {
            PlatinumNumber = 2;
        } else if (Rank <= 1150) {
            PlatinumNumber = 3;
        } else if (Rank <= 3660) {
            PlatinumNumber = 4;
        } else if (Rank <= 4100) {
            PlatinumNumber = 5;
        } else if (Rank <= 5700) {
            PlatinumNumber = 6;
        } else if (Rank <= 8000) {
            PlatinumNumber = 7;
        } else if (Rank <= 12150) {
            PlatinumNumber = 8;
        } else if (Rank <= 13150) {
            PlatinumNumber = 9;
        } else if (Rank <= 16000) {
            PlatinumNumber = 10;
        } else if (Rank <= 17000) {
            PlatinumNumber = 12;
        } else if (Rank <= 20000) {
            PlatinumNumber = 13;
        } else if (Rank <= 25000) {
            PlatinumNumber = 14;
        } else if (Rank <= 29500) {
            PlatinumNumber = 15;
        } else if (Rank <= 35800) {
            PlatinumNumber = 16;
        } else if (Rank <= 40000) {
            PlatinumNumber = 17;
        } else if (Rank <= 50000) {
            PlatinumNumber = 18;
        } else if (Rank > 50000) {
            PlatinumNumber = 19;
        }
    }

    public void Kcetgold() {
        if (Rank <= 920) {
            PlatinumNumber = 1;
        } else if (Rank <= 3600) {
            PlatinumNumber = 2;
        } else if (Rank <= 5700) {
            PlatinumNumber = 3;
        } else if (Rank <= 8000) {
            PlatinumNumber = 4;
        } else if (Rank <= 12150) {
            PlatinumNumber = 5;
        } else if (Rank <= 16000) {
            PlatinumNumber = 6;
        } else if (Rank <= 18000) {
            PlatinumNumber = 7;
        } else if (Rank <= 25000) {
            PlatinumNumber = 8;
        } else if (Rank <= 30000) {
            PlatinumNumber = 9;
        } else if (Rank <= 38000) {
            PlatinumNumber = 10;
        } else if (Rank <= 43000) {
            PlatinumNumber = 12;
        } else if (Rank <= 45000) {
            PlatinumNumber = 13;
        } else if (Rank <= 48000) {
            PlatinumNumber = 14;
        } else if (Rank <= 50000) {
            PlatinumNumber = 15;
        } else if (Rank <= 53000) {
            PlatinumNumber = 16;
        } else if (Rank <= 58000) {
            PlatinumNumber = 17;
        } else if (Rank <= 60000) {
            PlatinumNumber = 18;
        } else if (Rank > 60000) {
            PlatinumNumber = 19;
        }
    }


    public void Bitsatgold() {
        if (Rank <= 304) {
            PlatinumNumber = 1;
        } else if (Rank <= 2319) {
            PlatinumNumber = 2;
        } else if (Rank <= 8789) {
            PlatinumNumber = 3;
        } else if (Rank <= 15000) {
            PlatinumNumber = 4;
        } else if (Rank <= 22441) {
            PlatinumNumber = 5;
        } else if (Rank > 22441) {
            PlatinumNumber = 6;
        }
    }

    public void Viteeegold(){
        if (Rank <= 7000) {
            PlatinumNumber = 1;
        } else if (Rank <= 12000) {
            PlatinumNumber = 2;
        } else if (Rank <= 18000) {
            PlatinumNumber = 3;
        } else if (Rank <= 25000) {
            PlatinumNumber = 4;
        } else if (Rank <= 35000) {
            PlatinumNumber = 5;
        } else if (Rank > 35000) {
            PlatinumNumber = 6;
        }
    }




    public void JeeAdvancegold() {

        switch (Cast) {
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
        switch (Gender){
            case 1:
                jeeadvancegold_general_male();break;
            case 2:
                jeeadvancegold_general_female();break;
        }

    }



    public void JeeAdvancegold_obc() {
        switch (Gender){
            case 1:
                jeeadvancegold_obc_male();break;
            case 2:
                jeeadvancegold_obc_female();break;
        }

    }

    public void JeeAdvancegold_st() {
        switch (Gender){
            case 1:
                jeeadvancegold_st_male();break;
            case 2:
                jeeadvancegold_st_female();break;
        }

    }

    public void JeeAdvancegold_sc() {
        switch (Gender){
            case 1:
                jeeadvancegold_sc_male();break;
            case 2:
                jeeadvancegold_sc_female();break;
        }
    }

    public void jeeadvancegold_general_male(){
        if (Rank <= 90) {
            PlatinumNumber = 1;
        } else if (Rank <= 250) {
            PlatinumNumber = 2;
        } else if (Rank <= 350) {
            PlatinumNumber = 3;
        } else if (Rank <= 450) {
            PlatinumNumber = 4;
        } else if (Rank <= 550) {
            PlatinumNumber = 5;
        } else if (Rank <= 700) {
            PlatinumNumber = 6;
        } else if (Rank <= 900) {
            PlatinumNumber = 7;
        } else if (Rank <= 1100) {
            PlatinumNumber = 8;
        } else if (Rank <= 1500) {
            PlatinumNumber = 9;
        } else if (Rank <= 2000) {
            PlatinumNumber = 10;
        } else if (Rank <= 4000) {
            PlatinumNumber = 11;
        } else if (Rank <= 5000) {
            PlatinumNumber = 12;
        } else if (Rank <= 6000) {
            PlatinumNumber = 13;
        } else if (Rank <= 7000) {
            PlatinumNumber = 14;
        } else if (Rank <= 8000) {
            PlatinumNumber = 15;
        } else if (Rank <= 9000) {
            PlatinumNumber = 16;
        } else if (Rank <= 10000) {
            PlatinumNumber = 17;
        } else if (Rank <= 11000) {
            PlatinumNumber = 18;
        }else if (Rank <= 13000) {
            PlatinumNumber = 19;
        }else if (Rank > 13000) {
            PlatinumNumber = 20;
        }
    }

    public void jeeadvancegold_general_female(){
        if (Rank <= 300) {
            PlatinumNumber = 1;
        } else if (Rank <= 600) {
            PlatinumNumber = 2;
        } else if (Rank <= 1000) {
            PlatinumNumber = 3;
        } else if (Rank <= 1500) {
            PlatinumNumber = 4;
        } else if (Rank <= 2000) {
            PlatinumNumber = 5;
        } else if (Rank <= 2800) {
            PlatinumNumber = 6;
        } else if (Rank <= 3500) {
            PlatinumNumber = 7;
        } else if (Rank <= 4500) {
            PlatinumNumber = 8;
        } else if (Rank <= 5700) {
            PlatinumNumber = 9;
        } else if (Rank <= 6500) {
            PlatinumNumber = 10;
        } else if (Rank <= 7500) {
            PlatinumNumber = 11;
        } else if (Rank <= 8500) {
            PlatinumNumber = 12;
        } else if (Rank <= 11400) {
            PlatinumNumber = 13;
        } else if (Rank <= 11900) {
            PlatinumNumber = 14;
        } else if (Rank <= 13600) {
            PlatinumNumber = 15;
        } else if (Rank <= 14800) {
            PlatinumNumber = 16;
        } else if (Rank <= 15100) {
            PlatinumNumber = 17;
        } else if (Rank <= 17100) {
            PlatinumNumber = 18;
        }else if (Rank <= 19000) {
            PlatinumNumber = 19;
        }else if (Rank > 19000) {
            PlatinumNumber = 20;
        }
    }

    public void jeeadvancegold_obc_male(){
        if (Rank <= 100) {
            PlatinumNumber = 1;
        } else if (Rank <= 200) {
            PlatinumNumber = 2;
        } else if (Rank <= 300) {
            PlatinumNumber = 3;
        } else if (Rank <= 400) {
            PlatinumNumber = 4;
        } else if (Rank <= 500) {
            PlatinumNumber = 5;
        } else if (Rank <= 600) {
            PlatinumNumber = 6;
        } else if (Rank <= 700) {
            PlatinumNumber = 7;
        } else if (Rank <= 800) {
            PlatinumNumber = 8;
        } else if (Rank <= 900) {
            PlatinumNumber = 9;
        } else if (Rank <= 1000) {
            PlatinumNumber = 10;
        } else if (Rank <= 1100) {
            PlatinumNumber = 11;
        } else if (Rank <= 1500) {
            PlatinumNumber = 12;
        } else if (Rank <= 1700) {
            PlatinumNumber = 13;
        } else if (Rank <= 1900) {
            PlatinumNumber = 14;
        } else if (Rank <= 2300) {
            PlatinumNumber = 15;
        } else if (Rank <= 2800) {
            PlatinumNumber = 16;
        } else if (Rank <= 3500) {
            PlatinumNumber = 17;
        } else if (Rank <= 4500) {
            PlatinumNumber = 18;
        }else if (Rank <= 6000) {
            PlatinumNumber = 19;
        }else if (Rank > 6000) {
            PlatinumNumber = 20;
        }
    }

    public void jeeadvancegold_obc_female(){
        if (Rank <= 130) {
            PlatinumNumber = 1;
        } else if (Rank <= 300) {
            PlatinumNumber = 2;
        } else if (Rank <= 550) {
            PlatinumNumber = 3;
        } else if (Rank <= 730) {
            PlatinumNumber = 4;
        } else if (Rank <= 1100) {
            PlatinumNumber = 5;
        } else if (Rank <= 1600) {
            PlatinumNumber = 6;
        } else if (Rank <= 2500) {
            PlatinumNumber = 7;
        } else if (Rank <= 3000) {
            PlatinumNumber = 8;
        } else if (Rank <= 3500) {
            PlatinumNumber = 9;
        } else if (Rank <= 3800) {
            PlatinumNumber = 10;
        } else if (Rank <= 4200) {
            PlatinumNumber = 11;
        } else if (Rank <= 4500) {
            PlatinumNumber = 12;
        } else if (Rank <= 4800) {
            PlatinumNumber = 13;
        } else if (Rank <= 5200) {
            PlatinumNumber = 14;
        } else if (Rank <= 5400) {
            PlatinumNumber = 15;
        } else if (Rank <= 5600) {
            PlatinumNumber = 16;
        } else if (Rank <= 6200) {
            PlatinumNumber = 17;
        } else if (Rank <= 6800) {
            PlatinumNumber = 18;
        }else if (Rank <= 7300) {
            PlatinumNumber = 19;
        }else  {
            PlatinumNumber = 20;
        }
    }

    public void jeeadvancegold_st_male(){
        if (Rank <= 50) {
            PlatinumNumber = 1;
        } else if (Rank <= 100) {
            PlatinumNumber = 2;
        } else if (Rank <= 150) {
            PlatinumNumber = 3;
        } else if (Rank <= 200) {
            PlatinumNumber = 4;
        } else if (Rank <= 250) {
            PlatinumNumber = 5;
        } else if (Rank <= 300) {
            PlatinumNumber = 6;
        } else if (Rank <= 350) {
            PlatinumNumber = 7;
        } else if (Rank <= 400) {
            PlatinumNumber = 8;
        } else if (Rank <= 450) {
            PlatinumNumber = 9;
        } else if (Rank <= 500) {
            PlatinumNumber = 10;
        } else if (Rank <= 550) {
            PlatinumNumber = 11;
        } else if (Rank <= 600) {
            PlatinumNumber = 12;
        } else if (Rank <= 650) {
            PlatinumNumber = 13;
        } else if (Rank <= 700) {
            PlatinumNumber = 14;
        } else if (Rank <= 750) {
            PlatinumNumber = 15;
        } else if (Rank <= 800) {
            PlatinumNumber = 16;
        } else if (Rank <= 900) {
            PlatinumNumber = 17;
        } else if (Rank <= 950) {
            PlatinumNumber = 18;
        }else if (Rank <= 1100) {
            PlatinumNumber = 19;
        }else  {
            PlatinumNumber = 20;
        }
    }

    public void jeeadvancegold_st_female(){
        if (Rank <= 50) {
            PlatinumNumber = 1;
        } else if (Rank <= 100) {
            PlatinumNumber = 2;
        } else if (Rank <= 150) {
            PlatinumNumber = 3;
        } else if (Rank <= 200) {
            PlatinumNumber = 4;
        } else if (Rank <= 250) {
            PlatinumNumber = 5;
        } else if (Rank <= 300) {
            PlatinumNumber = 6;
        } else if (Rank <= 350) {
            PlatinumNumber = 7;
        } else if (Rank <= 400) {
            PlatinumNumber = 8;
        } else if (Rank <= 450) {
            PlatinumNumber = 9;
        } else if (Rank <= 500) {
            PlatinumNumber = 10;
        } else if (Rank <= 550) {
            PlatinumNumber = 11;
        } else if (Rank <= 600) {
            PlatinumNumber = 12;
        } else if (Rank <= 650) {
            PlatinumNumber = 13;
        } else if (Rank <= 700) {
            PlatinumNumber = 14;
        } else if (Rank <= 750) {
            PlatinumNumber = 15;
        } else if (Rank <= 800) {
            PlatinumNumber = 16;
        } else if (Rank <= 900) {
            PlatinumNumber = 17;
        } else if (Rank <= 1100) {
            PlatinumNumber = 18;
        }else if (Rank <= 1500) {
            PlatinumNumber = 19;
        }else  {
            PlatinumNumber = 20;
        }
    }

    public void jeeadvancegold_sc_male(){
        if (Rank <= 100) {
            PlatinumNumber = 1;
        } else if (Rank <= 250) {
            PlatinumNumber = 2;
        } else if (Rank <= 350) {
            PlatinumNumber = 3;
        } else if (Rank <= 450) {
            PlatinumNumber = 4;
        } else if (Rank <= 550) {
            PlatinumNumber = 5;
        } else if (Rank <= 650) {
            PlatinumNumber = 6;
        } else if (Rank <= 750) {
            PlatinumNumber = 7;
        } else if (Rank <= 850) {
            PlatinumNumber = 8;
        } else if (Rank <= 950) {
            PlatinumNumber = 9;
        } else if (Rank <= 1200) {
            PlatinumNumber = 10;
        } else if (Rank <= 1300) {
            PlatinumNumber = 11;
        } else if (Rank <= 1400) {
            PlatinumNumber = 12;
        } else if (Rank <= 1500) {
            PlatinumNumber = 13;
        } else if (Rank <= 1600) {
            PlatinumNumber = 14;
        } else if (Rank <= 1700) {
            PlatinumNumber = 15;
        } else if (Rank <= 1800) {
            PlatinumNumber = 16;
        } else if (Rank <= 1900) {
            PlatinumNumber = 17;
        } else if (Rank <= 2200) {
            PlatinumNumber = 18;
        }else if (Rank <= 2500) {
            PlatinumNumber = 19;
        }else  {
            PlatinumNumber = 20;
        }
    }

    public void jeeadvancegold_sc_female(){
        if (Rank <= 110) {
            PlatinumNumber = 1;
        } else if (Rank <= 200) {
            PlatinumNumber = 2;
        } else if (Rank <= 300) {
            PlatinumNumber = 3;
        } else if (Rank <= 400) {
            PlatinumNumber = 4;
        } else if (Rank <= 500) {
            PlatinumNumber = 5;
        } else if (Rank <= 600) {
            PlatinumNumber = 6;
        } else if (Rank <= 800) {
            PlatinumNumber = 7;
        } else if (Rank <= 1000) {
            PlatinumNumber = 8;
        } else if (Rank <= 1100) {
            PlatinumNumber = 9;
        } else if (Rank <= 1300) {
            PlatinumNumber = 10;
        } else if (Rank <= 1400) {
            PlatinumNumber = 11;
        } else if (Rank <= 1600) {
            PlatinumNumber = 12;
        } else if (Rank <= 2000) {
            PlatinumNumber = 13;
        } else if (Rank <= 2300) {
            PlatinumNumber = 14;
        } else if (Rank <= 2500) {
            PlatinumNumber = 15;
        } else if (Rank <= 2600) {
            PlatinumNumber = 16;
        } else if (Rank <= 2800) {
            PlatinumNumber = 17;
        } else if (Rank <= 3000) {
            PlatinumNumber = 18;
        }else if (Rank <= 3300) {
            PlatinumNumber = 19;
        }else {
            PlatinumNumber = 20;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}