package com.nbird.paperwind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FinalRankPredictorActivity extends AppCompatActivity {
    int SelectedEntranceExam,inputdata,score,Gender0,Cast0,Goldennumber;
    TextView exam123,category123,gender123,rank;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private Dialog loadingDialog;
    TextInputEditText scoretext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_rank_predictor);


        exam123=(TextView) findViewById(R.id.textView8);
        category123=(TextView) findViewById(R.id.textcategory);
        gender123=(TextView) findViewById(R.id.gender);
        rank=(TextView) findViewById(R.id.rankint);
        scoretext=(TextInputEditText) findViewById(R.id.username1);

        SelectedEntranceExam=getIntent().getIntExtra("RankEE",0);
        inputdata=getIntent().getIntExtra("InputPredictor",0);
        score=getIntent().getIntExtra("Score1",0);
        Gender0=getIntent().getIntExtra("Gender",0);
        Cast0=getIntent().getIntExtra("cast",0);
        Goldennumber=1;
        TextViewDisplay();
        loadingDialog=new Dialog(FinalRankPredictorActivity.this);
        loadingDialog.setContentView(R.layout.activity_loading);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(true);
        loadingDialog.show();

        myRef.child("RankPredictor").child(String.valueOf(SelectedEntranceExam)).child(String.valueOf(inputdata)).child(String.valueOf(Gender0)).child(String.valueOf(Cast0)).child(String.valueOf(Goldennumber)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String value = snapshot.child("ranky").getValue(String.class);
                rank.setText(value);
                loadingDialog.dismiss();
                scoretext.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FinalRankPredictorActivity.this, "Error while Loading....", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });




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
                category123.setText("Category: "+"General");break;
            case 2:
                category123.setText("Category: "+"Obc");break;
            case 3:
                category123.setText("Category: "+"ST");break;
            case 4:
                category123.setText("Category: "+"SC");break;
        }


    }
}