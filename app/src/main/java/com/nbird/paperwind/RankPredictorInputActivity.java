package com.nbird.paperwind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RankPredictorInputActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button rankbutton,collegebutton;
    int SelectedEntranceExam,score,rank,cast;
    TextInputEditText ranktext,scoretext;
    private Spinner spinner;
    private static final String[] paths = {"General","OBC","ST","SC"};
    private RadioGroup radioGroup;
    private RadioButton radioButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_predictor_input);
        rankbutton=(Button) findViewById(R.id.login);
        collegebutton=(Button) findViewById(R.id.getCollege);
        ranktext=(TextInputEditText) findViewById(R.id.username1);
        scoretext=(TextInputEditText) findViewById(R.id.username);
        radioGroup = (RadioGroup) findViewById(R.id.radio);

        spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(RankPredictorInputActivity.this, android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);




        SelectedEntranceExam=getIntent().getIntExtra("RankEE",0);

        rankbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!rank()){
                    return;
                }

                score = Integer.valueOf(scoretext.getText().toString());


                int i=0;
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id

                if(selectedId==2131296536){
                    i=1;
                }else{
                    i=2;
                }


                Intent intent=new Intent(getBaseContext(),FinalRankPredictorActivity.class);
                intent.putExtra("RankEE",SelectedEntranceExam);
                intent.putExtra("InputPredictor",1);
                intent.putExtra("Score1",score);
                intent.putExtra("Gender",i);
                intent.putExtra("cast",cast);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                scoretext.setText("");
            }
        });

        collegebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!college()){
                    return;
                }

                rank = Integer.valueOf(ranktext.getText().toString());

                int i=0;
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id

                if(selectedId==2131296534){
                    i=1;
                }else{
                    i=2;
                }

                Intent intent=new Intent(getBaseContext(),FinalRankPredictorActivity.class);
                intent.putExtra("RankEE",SelectedEntranceExam);
                intent.putExtra("InputPredictor",2);
                intent.putExtra("Rank1",rank);
                intent.putExtra("Gender",i);
                intent.putExtra("cast",cast);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                scoretext.setText("");
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {



        switch (position) {
            case 0:
                cast=position+1;
                break;
            case 1:
                cast=position+1;
                break;
            case 2:
                cast=position+1;
                break;
            case 3:
                cast=position+1;
                break;

        }
    }


    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}