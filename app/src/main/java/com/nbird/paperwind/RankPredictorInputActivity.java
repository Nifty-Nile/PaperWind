package com.nbird.paperwind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class RankPredictorInputActivity extends AppCompatActivity {
    Button rankbutton,collegebutton;
    int SelectedEntranceExam;
    TextInputEditText ranktext,scoretext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_predictor_input);
        rankbutton=(Button) findViewById(R.id.login);
        collegebutton=(Button) findViewById(R.id.getCollege);
        ranktext=(TextInputEditText) findViewById(R.id.username1);
        scoretext=(TextInputEditText) findViewById(R.id.username);


        SelectedEntranceExam=getIntent().getIntExtra("RankEE",0);

        rankbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!rank()){
                    return;
                }

                int score = Integer.valueOf(scoretext.getText().toString());

                Intent intent=new Intent(getBaseContext(),FinalRankPredictorActivity.class);
                intent.putExtra("RankEE",SelectedEntranceExam);
                intent.putExtra("InputPredictor",1);
                intent.putExtra("Score1",score);
                scoretext.setText("");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

        collegebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!college()){
                    return;
                }

                int rank = Integer.valueOf(ranktext.getText().toString());

                Intent intent=new Intent(getBaseContext(),FinalRankPredictorActivity.class);
                intent.putExtra("RankEE",SelectedEntranceExam);
                intent.putExtra("InputPredictor",2);
                intent.putExtra("Rank1",rank);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
    }


    private boolean rank(){
        String name2=scoretext.getText().toString();
        int i,r=0;
        int len=name2.length();
        if(name2.isEmpty()){
            scoretext.setError("Fields cannot be empty");
            return false;
        }else if(len>4){
            scoretext.setError("Score should less than 4 digits.");
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
                scoretext.setError("Score should be an integer");
                return false;
            }
        }
        else
            scoretext.setError(null);
        return true;
    }

    private boolean college(){
        String name2=ranktext.getText().toString();
        int len=name2.length();
        int i,r=0;
        if(name2.isEmpty()){
            ranktext.setError("Field cannot be empty");
            return false;
        }else if(name2.length()>7||name2.length()<0){
            ranktext.setError("Rank cannot be negative or more than 6 digits");
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
                ranktext.setError("Rank should be an integer");
                return false;
            }
        }
        else
            ranktext.setError(null);
        return true;
    }
}