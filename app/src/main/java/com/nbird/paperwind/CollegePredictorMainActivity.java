package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CollegePredictorMainActivity extends AppCompatActivity {
    TextView entranceexamtitle,ranktext,gendertext,casttext,branchtext;
    RecyclerView recyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private Dialog loadingDialog;
    private List<CollegePredictorMainHolder> list;
    int SelectedEntranceExam,InputText,Rank,Gender,Cast,Branch,PlatinumNumber;

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

        entranceexamtitle=(TextView) findViewById(R.id.textView8);
        ranktext=(TextView) findViewById(R.id.textView9);
        gendertext=(TextView) findViewById(R.id.gender);
        casttext=(TextView) findViewById(R.id.textcategory);
        branchtext=(TextView) findViewById(R.id.Branch00);




        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.activity_loading);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();


        final CollegePredictorMainAdapter categoryAdapter = new CollegePredictorMainAdapter(list);
        recyclerView.setAdapter(categoryAdapter);

        loadingDialog.show();



        myRef.child("CollegePredictor").child(String.valueOf(SelectedEntranceExam)).child(String.valueOf(Cast)).child(String.valueOf(Gender)).child(String.valueOf(PlatinumNumber)).orderByChild("branch").equalTo(Branch).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(CollegePredictorMainHolder.class));

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
        Platinumnumber();
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
        switch (Gender){
            case 1:
                gendertext.setText("Gender: "+"Male");break;
            case 2:
                gendertext.setText("Gender: "+"Female");break;
        }
        switch (Cast){
            case 1:
                casttext.setText("Category: "+"General");ranktext.setText("Gen. Rank: "+String.valueOf(Rank));break;
            case 2:
                casttext.setText("Category: "+"Obc");ranktext.setText("OBC Rank: "+String.valueOf(Rank));break;
            case 3:
                casttext.setText("Category: "+"ST");ranktext.setText("ST Rank: "+String.valueOf(Rank));break;
            case 4:
                casttext.setText("Category: "+"SC");ranktext.setText("SC Rank: "+String.valueOf(Rank));break;
        }
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



    }

    public void Platinumnumber(){
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
}