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
                entranceexamtitle.setText("Entrance Exam: "+"KVPY");break;
            case 9:
                entranceexamtitle.setText("Entrance Exam: "+"MHCET");break;
            case 10:
                entranceexamtitle.setText("Entrance Exam: "+"SRMJEE");break;
            case 11:
                entranceexamtitle.setText("Entrance Exam: "+"KCET");break;
            case 12:
                entranceexamtitle.setText("Entrance Exam: "+"IPU-CET");break;
            case 13:
                entranceexamtitle.setText("Entrance Exam: "+"MET");break;
            case 14:
                entranceexamtitle.setText("Entrance Exam: "+"WBJEE");break;
            case 15:
                entranceexamtitle.setText("Entrance Exam: "+"JEECUP");break;
            case 16:
                entranceexamtitle.setText("Entrance Exam: "+"NEST");break;
            case 17:
                entranceexamtitle.setText("Entrance Exam: "+"PESSAT");break;
            case 18:
                entranceexamtitle.setText("Entrance Exam: "+"AMUEEE");break;
            case 19:
                entranceexamtitle.setText("Entrance Exam: "+"IISER");break;
            case 20:
                entranceexamtitle.setText("Entrance Exam: "+"UPSEE");break;
            case 21:
                entranceexamtitle.setText("Entrance Exam: "+"NIFT");break;
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
        }else if(SelectedEntranceExam==4){
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
        }else if(SelectedEntranceExam==4){
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
                    branchtext.setText("Branch: "+"CSE");break;
                case 2:
                    branchtext.setText("Branch: "+"IT");break;
                case 3:
                    branchtext.setText("Branch: "+"ECE");break;
                case 4:
                    branchtext.setText("Branch: "+"EEE");break;
                case 5:
                    branchtext.setText("Branch: "+"ME");break;
                case 6:
                    branchtext.setText("Branch: "+"TE");break;
                case 7:
                    branchtext.setText("Branch: "+"Civil"); break;
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
        if (Rank >= 330) {
            PlatinumNumber = 1;
        } else if (Rank >= 300) {
            PlatinumNumber = 2;
        } else if (Rank >= 250) {
            PlatinumNumber = 3;
        } else if (Rank >= 200) {
            PlatinumNumber = 4;
        } else if (Rank >= 150) {
            PlatinumNumber = 5;
        } else if (Rank >= 100) {
            PlatinumNumber = 6;
        } else if (Rank >= 50) {
            PlatinumNumber = 7;
        } else {
            PlatinumNumber = 8;
        }

    }

    public void JeeAdvancegold_obc() {
        if (Rank >= 330) {
            PlatinumNumber = 1;
        } else if (Rank >= 300) {
            PlatinumNumber = 2;
        } else if (Rank >= 250) {
            PlatinumNumber = 3;
        } else if (Rank >= 200) {
            PlatinumNumber = 4;
        } else if (Rank >= 150) {
            PlatinumNumber = 5;
        } else if (Rank >= 100) {
            PlatinumNumber = 6;
        } else if (Rank >= 50) {
            PlatinumNumber = 7;
        } else {
            PlatinumNumber = 8;
        }

    }

    public void JeeAdvancegold_st() {
        if (Rank >= 330) {
            PlatinumNumber = 1;
        } else if (Rank >= 300) {
            PlatinumNumber = 2;
        } else if (Rank >= 250) {
            PlatinumNumber = 3;
        } else if (Rank >= 200) {
            PlatinumNumber = 4;
        } else if (Rank >= 150) {
            PlatinumNumber = 5;
        } else if (Rank >= 100) {
            PlatinumNumber = 6;
        } else if (Rank >= 50) {
            PlatinumNumber = 7;
        } else {
            PlatinumNumber = 8;
        }

    }

    public void JeeAdvancegold_sc() {
        if (Rank >= 330) {
            PlatinumNumber = 1;
        } else if (Rank >= 300) {
            PlatinumNumber = 2;
        } else if (Rank >= 250) {
            PlatinumNumber = 3;
        } else if (Rank >= 200) {
            PlatinumNumber = 4;
        } else if (Rank >= 150) {
            PlatinumNumber = 5;
        } else if (Rank >= 100) {
            PlatinumNumber = 6;
        } else if (Rank >= 50) {
            PlatinumNumber = 7;
        } else {
            PlatinumNumber = 8;
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