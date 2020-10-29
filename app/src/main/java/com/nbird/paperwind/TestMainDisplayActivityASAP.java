package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TestMainDisplayActivityASAP extends AppCompatActivity {

    TextView serialnumber,timer,question,option1,option2,option3,option4,timersec;
    Button backbutton,menubutton,saveandnextbutton,reviewbutton,submitbutton,unmarkbutton;
    RadioButton option1radio,option2radio,option3radio,option4radio;
    RadioGroup radioGroup;
    private List<ASSAPHolder> list;
    LinearLayout linearLayout;
    RadioButton selectedRadioButton;
    private Dialog loadingDialog;
    int count,score;
    int biosum,phynum,mathsnum,chemnum,position,mode,set,subject,subnum=0,phy,chem,maths;
    String answer123,text;
    long time;
    String trial,trail1,value;
    CountDownTimer countDownTimer;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();

    ImageView questionimage;
    int minutes,minutestimer,secondstimer;
    long sec;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main_display_a_s_a_p);

        serialnumber=(TextView) findViewById(R.id.serialnumber);
        timer=(TextView) findViewById(R.id.timer);
        question=(TextView) findViewById(R.id.question);
        timersec=(TextView) findViewById(R.id.sec);
        backbutton=(Button) findViewById(R.id.backbutton);
        menubutton=(Button) findViewById(R.id.menubutton);
        questionimage=(ImageView) findViewById(R.id.questionimage);

        saveandnextbutton=(Button) findViewById(R.id.saveandnextbutton);
        reviewbutton=(Button) findViewById(R.id.reviewbutton);
        unmarkbutton=(Button) findViewById(R.id.unmarkreviewbutton);
        option1radio=(RadioButton) findViewById(R.id.option1radio);
        option2radio=(RadioButton) findViewById(R.id.option2radio);
        option3radio=(RadioButton) findViewById(R.id.option3radio);
        option4radio=(RadioButton) findViewById(R.id.option4radio);
        linearLayout=(LinearLayout) findViewById(R.id.linearLayout16);
        radioGroup=(RadioGroup) findViewById(R.id.radiogroup);
        option1radio=(RadioButton) findViewById(R.id.option1radio);
        option2radio=(RadioButton) findViewById(R.id.option2radio);
        option3radio=(RadioButton) findViewById(R.id.option3radio);
        option4radio=(RadioButton) findViewById(R.id.option4radio);









        if(subnum==0){
            subnum=getIntent().getIntExtra("Phynum",0);
            if(subnum==0){
                subnum=getIntent().getIntExtra("mathsnum",0);
                if(subnum==0){
                    subnum=getIntent().getIntExtra("Chemnum",0);
                }
            }
        }
        final SharedPreferences counterstopper = getBaseContext().getSharedPreferences("counterstopper123", 0);
        final SharedPreferences.Editor editorcounterstopper = counterstopper.edit();



        position=getIntent().getIntExtra("position",0);
        mode=getIntent().getIntExtra("mode",0);
        set=getIntent().getIntExtra("set",0);
        subject=getIntent().getIntExtra("Subject",0);
        phy=getIntent().getIntExtra("phy",0);
        chem=getIntent().getIntExtra("chem",0);
        maths=getIntent().getIntExtra("maths",0);
        value=getIntent().getStringExtra("text");
        minutes=getIntent().getIntExtra("minutes",0);
        sec=getIntent().getLongExtra("sec",0);
        score=getIntent().getIntExtra("score",0);






        final SharedPreferences minutes100 = getBaseContext().getSharedPreferences("minutes100", 0);
        final SharedPreferences.Editor editorminutes100 = minutes100.edit();

        final SharedPreferences seconds100 = getBaseContext().getSharedPreferences("seconds100", 0);
        final SharedPreferences.Editor editorseconds100 = seconds100.edit();


        minutestimer = minutes100.getInt(String.valueOf(1), 0);
        secondstimer = seconds100.getInt(String.valueOf(1), 0);

        countDownTimer=new CountDownTimer((minutestimer+1)*60*1000,1000) {
            @Override
            public void onTick(long l) {
                secondstimer=secondstimer-1;

                if(secondstimer==0){
                    minutestimer--;
                    timer.setText(Integer.toString(minutestimer));
                    timersec.setText("0"+Integer.toString(secondstimer));
                    String a = timer.getText().toString();
                    int A = Integer.parseInt(a);
                    editorminutes100.putInt(String.valueOf(1), A);
                    editorminutes100.commit();

                    String b = timersec.getText().toString();
                    int B = Integer.parseInt(b);
                    editorseconds100.putInt(String.valueOf(1), B);
                    editorseconds100.commit();
                    secondstimer=60;
                }else{


                    if(secondstimer<10){
                        timersec.setText("0"+Long.toString(secondstimer));
                    }else{
                        timersec.setText(Long.toString(secondstimer));
                    }
                    if(minutestimer<10){
                        timer.setText("0"+Integer.toString(minutestimer));

                    }else{
                        timer.setText(Integer.toString(minutestimer));
                    }

                    String a = timer.getText().toString();
                    int A = Integer.parseInt(a);
                    editorminutes100.putInt(String.valueOf(1), A);
                    editorminutes100.commit();

                    String b = timersec.getText().toString();
                    int B = Integer.parseInt(b);
                    editorseconds100.putInt(String.valueOf(1), B);
                    editorseconds100.commit();
                }

            }



            @Override
            public void onFinish() {




            }


        };



        countDownTimer.start();


        loadingDialog=new Dialog(this);
        loadingDialog.setContentView(R.layout.activity_loading);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);

        list=new ArrayList<>();
        loadingDialog.show();


        final SharedPreferences settings = getBaseContext().getSharedPreferences("Physics", 0);
        final SharedPreferences.Editor editor = settings.edit();

        final SharedPreferences settings1 = getBaseContext().getSharedPreferences("Maths", 0);
        final SharedPreferences.Editor editor1 = settings1.edit();

        final SharedPreferences settings2 = getBaseContext().getSharedPreferences("Chemistry", 0);
        final SharedPreferences.Editor editor2 = settings2.edit();

        final SharedPreferences colorblue = getBaseContext().getSharedPreferences("PhysicsColour", 0);
        final SharedPreferences.Editor editorblue = colorblue.edit();

        final SharedPreferences colorred = getBaseContext().getSharedPreferences("MathsColour", 0);
        final SharedPreferences.Editor editorred = colorred.edit();

        final SharedPreferences coloryellow = getBaseContext().getSharedPreferences("ChemistryColour", 0);
        final SharedPreferences.Editor editoryellow = coloryellow.edit();

        final SharedPreferences reviewphy = getBaseContext().getSharedPreferences("ReviewPurplePhy", 0);
        final SharedPreferences.Editor editorreviewphy = reviewphy.edit();

        final SharedPreferences reviewchem = getBaseContext().getSharedPreferences("ReviewPurpleChem", 0);
        final SharedPreferences.Editor editorreviewchem = reviewchem.edit();

        final SharedPreferences reviewmaths = getBaseContext().getSharedPreferences("ReviewPurpleMaths", 0);
        final SharedPreferences.Editor editorreviewmaths = reviewmaths.edit();

        final SharedPreferences notanswered1 = getBaseContext().getSharedPreferences("notanswered1", 0);
        final SharedPreferences.Editor editornotanswered1 = notanswered1.edit();

        final SharedPreferences notanswered2 = getBaseContext().getSharedPreferences("notanswered2", 0);
        final SharedPreferences.Editor editornotanswered2 = notanswered2.edit();

        final SharedPreferences notanswered3 = getBaseContext().getSharedPreferences("notanswered3", 0);
        final SharedPreferences.Editor editornotanswered3 = notanswered3.edit();




        myRef.child("EntranceExamQuestionsASSAP").child(String.valueOf(position)).child(String.valueOf(set)).child(String.valueOf(subject)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    list.add(snapshot1.getValue(ASSAPHolder.class));
                }
                serialnumber.setText("Question No.: "+String.valueOf(subnum)+"/"+list.size());
                int r=list.size();



                question.setText(list.get(subnum-1).getQuestion());
                option1radio.setText(list.get(subnum-1).getOption1());
                option2radio.setText(list.get(subnum-1).getOption2());
                option3radio.setText(list.get(subnum-1).getOption3());
                option4radio.setText(list.get(subnum-1).getOption4());
                Glide.with(getBaseContext()).load(list.get(subnum-1).imageurl).into(questionimage);



                if(subject==1){

                    String answerA0 = settings.getString(String.valueOf(subnum), "poty");

                    editor.putString(String.valueOf(subnum), answerA0);
                    editor.commit();

                    if(answerA0.equals(list.get(subnum-1).getOption1())){
                        radioGroup.clearCheck();
                        option1radio.setChecked(true);
                    }else if(answerA0.equals(list.get(subnum-1).getOption2())){
                        radioGroup.clearCheck();
                        option2radio.setChecked(true);
                    }else if(answerA0.equals(list.get(subnum-1).getOption3())){
                        radioGroup.clearCheck();
                        option3radio.setChecked(true);
                    }else if(answerA0.equals(list.get(subnum-1).getOption4())){
                        radioGroup.clearCheck();
                        option4radio.setChecked(true);
                    }
                }

                if(subject==2){

                    String answerA0 = settings2.getString(String.valueOf(subnum), "poty");

                    editor2.putString(String.valueOf(subnum), answerA0);
                    editor2.commit();

                    if(answerA0.equals(list.get(subnum-1).getOption1())){
                        radioGroup.clearCheck();
                        option1radio.setChecked(true);
                    }else if(answerA0.equals(list.get(subnum-1).getOption2())){
                        radioGroup.clearCheck();
                        option2radio.setChecked(true);
                    }else if(answerA0.equals(list.get(subnum-1).getOption3())){
                        radioGroup.clearCheck();
                        option3radio.setChecked(true);
                    }else if(answerA0.equals(list.get(subnum-1).getOption4())){
                        radioGroup.clearCheck();
                        option4radio.setChecked(true);
                    }
                }

                if(subject==3){

                    String answerA0 = settings1.getString(String.valueOf(subnum), "poty");

                    editor1.putString(String.valueOf(subnum), answerA0);
                    editor1.commit();

                    if(answerA0.equals(list.get(subnum-1).getOption1())){
                        radioGroup.clearCheck();
                        option1radio.setChecked(true);
                    }else if(answerA0.equals(list.get(subnum-1).getOption2())){
                        radioGroup.clearCheck();
                        option2radio.setChecked(true);
                    }else if(answerA0.equals(list.get(subnum-1).getOption3())){
                        radioGroup.clearCheck();
                        option3radio.setChecked(true);
                    }else if(answerA0.equals(list.get(subnum-1).getOption4())){
                        radioGroup.clearCheck();
                        option4radio.setChecked(true);
                    }
                }



                option1radio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(option1radio.isSelected()){
                            radioGroup.clearCheck();
                            option1radio.setSelected(false);
                            option1radio.setChecked(false);
                        }else{
                            radioGroup.clearCheck();
                            option1radio.setSelected(true);
                            option1radio.setChecked(true);
                            option2radio.setSelected(false);
                            option2radio.setChecked(false);
                            option3radio.setSelected(false);
                            option3radio.setChecked(false);
                            option4radio.setSelected(false);
                            option4radio.setChecked(false);
                        }
                    }
                });
                option2radio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if(option2radio.isSelected()){
                            radioGroup.clearCheck();
                            option2radio.setSelected(false);
                            option2radio.setChecked(false);
                        }else{
                            radioGroup.clearCheck();
                            option1radio.setSelected(false);
                            option1radio.setChecked(false);
                            option2radio.setSelected(true);
                            option2radio.setChecked(true);
                            option3radio.setSelected(false);
                            option3radio.setChecked(false);
                            option4radio.setSelected(false);
                            option4radio.setChecked(false);
                        }
                    }
                });
                option3radio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(option3radio.isSelected()){
                            radioGroup.clearCheck();
                            option3radio.setSelected(false);
                            option3radio.setChecked(false);
                        }else{
                            radioGroup.clearCheck();
                            option1radio.setSelected(false);
                            option1radio.setChecked(false);
                            option2radio.setSelected(false);
                            option2radio.setChecked(false);
                            option3radio.setSelected(true);
                            option3radio.setChecked(true);
                            option4radio.setSelected(false);
                            option4radio.setChecked(false);
                        }
                    }
                });
                option4radio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(option4radio.isSelected()){
                            radioGroup.clearCheck();
                            option4radio.setSelected(false);
                            option4radio.setChecked(false);
                        }else{
                            radioGroup.clearCheck();
                            option1radio.setSelected(false);
                            option1radio.setChecked(false);
                            option2radio.setSelected(false);
                            option2radio.setChecked(false);
                            option3radio.setSelected(false);
                            option3radio.setChecked(false);
                            option4radio.setSelected(true);
                            option4radio.setChecked(true);
                        }
                    }
                });

                reviewbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(subject==1){
                            editorreviewphy.putBoolean(String.valueOf(subnum), true);
                            editorreviewphy.commit();

                        }
                        else if(subject==2){
                            editorreviewchem.putBoolean(String.valueOf(subnum), true);
                            editorreviewchem.commit();

                        }


                        else if (subject==3){
                            editorreviewmaths.putBoolean(String.valueOf(subnum), true);
                            editorreviewmaths.commit();

                        }

                    }
                });

                unmarkbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(subject==1){
                            editorreviewphy.putBoolean(String.valueOf(subnum), false);
                            editorreviewphy.commit();

                        }
                        else if(subject==2){
                            editorreviewchem.putBoolean(String.valueOf(subnum), false);
                            editorreviewchem.commit();

                        }


                        else if (subject==3){
                            editorreviewmaths.putBoolean(String.valueOf(subnum), false);
                            editorreviewmaths.commit();

                        }
                    }
                });







                saveandnextbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if(subject==1){


                            if(option1radio.isChecked()||option2radio.isChecked()||option3radio.isChecked()||option4radio.isChecked()){
                                if(radioGroup.getCheckedRadioButtonId()!=-1){
                                    selectedRadioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                                    editor.putString(String.valueOf(subnum), String.valueOf(selectedRadioButton.getText()));
                                    editor.commit();
                                    editornotanswered1.putBoolean(String.valueOf(subnum-1),false);
                                    editornotanswered1.commit();
                                }
                            }
                            else{

                                    editor.putString(String.valueOf(subnum), "poty");
                                    editor.commit();


                                editornotanswered1.putBoolean(String.valueOf(subnum-1),true);
                                editornotanswered1.commit();
                            }

                        }


                        if(subject==2){


                            if(option1radio.isChecked()||option2radio.isChecked()||option3radio.isChecked()||option4radio.isChecked()) {
                                if (radioGroup.getCheckedRadioButtonId() != -1) {
                                    selectedRadioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                                    editor2.putString(String.valueOf(subnum), String.valueOf(selectedRadioButton.getText()));
                                    editor2.commit();
                                    editornotanswered2.putBoolean(String.valueOf(subnum-1),false);
                                    editornotanswered2.commit();
                                }
                            }else{
                                editor2.putString(String.valueOf(subnum), "poty");
                                editor2.commit();
                                editornotanswered2.putBoolean(String.valueOf(subnum-1),true);
                                editornotanswered2.commit();
                            }

                        }

                        if(subject==3){

                            if(option1radio.isChecked()||option2radio.isChecked()||option3radio.isChecked()||option4radio.isChecked()) {
                                if (radioGroup.getCheckedRadioButtonId() != -1) {
                                    selectedRadioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                                    editor1.putString(String.valueOf(subnum), String.valueOf(selectedRadioButton.getText()));
                                    editor1.commit();
                                    editornotanswered3.putBoolean(String.valueOf(subnum-1),false);
                                    editornotanswered3.commit();
                                }
                            }else{
                                editor1.putString(String.valueOf(subnum), "poty");
                                editor1.commit();
                                editornotanswered3.putBoolean(String.valueOf(subnum-1),true);
                                editornotanswered3.commit();
                            }

                        }





                        subnum++;


                        if(subnum==list.size()+1){
                            Intent intent=new Intent(getBaseContext(),OnlineExamGridShow.class);

                            intent.putExtra("minutes",minutes);
                            intent.putExtra("sec",sec);
                            intent.putExtra("text",text);
                            intent.putExtra("position", position);
                            intent.putExtra("set", set);
                            intent.putExtra("Subject", subject);
                            intent.putExtra("phy", phy);
                            intent.putExtra("chem", chem);
                            intent.putExtra("maths", maths);
                            intent.putExtra("score",score);
                            intent.putExtra("mode",mode);



                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                            finish();
                            return;
                        }else{

                            if(subject==1){
                                editorblue.putBoolean(String.valueOf(subnum-1), true);
                                editorblue.commit();

                            }
                            if(subject==2){
                                editoryellow.putBoolean(String.valueOf(subnum-1), true);
                                editoryellow.commit();

                            }


                            if (subject==3){
                                editorred.putBoolean(String.valueOf(subnum-1), true);
                                editorred.commit();

                            }




                            serialnumber.setText("Question No.: "+String.valueOf(subnum)+"/"+list.size());

                            question.setText(list.get(subnum-1).getQuestion());
                            option1radio.setText(list.get(subnum-1).getOption1());
                            option2radio.setText(list.get(subnum-1).getOption2());
                            option3radio.setText(list.get(subnum-1).getOption3());
                            option4radio.setText(list.get(subnum-1).getOption4());
                            Glide.with(getBaseContext()).load(list.get(subnum-1).imageurl).into(questionimage);

                            if(subject==1){                                                    //Remember the options selected

                                String answerA0 = settings.getString(String.valueOf(subnum), "poty");
                                editor.putString(String.valueOf(subnum), answerA0);
                                editor.commit();

                                if(answerA0.equals(list.get(subnum-1).getOption1())){
                                    radioGroup.clearCheck();
                                    option1radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption2())){
                                    radioGroup.clearCheck();
                                    option2radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption3())){
                                    radioGroup.clearCheck();
                                    option3radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption4())){
                                    radioGroup.clearCheck();
                                    option4radio.setChecked(true);
                                }else{
                                    radioGroup.clearCheck();
                                    option1radio.setChecked(false);
                                    option2radio.setChecked(false);
                                    option3radio.setChecked(false);
                                    option4radio.setChecked(false);
                                }
                            }
                            if(subject==2){                                                    //Remember the options selected

                                String answerA0 = settings2.getString(String.valueOf(subnum), "poty");
                                editor2.putString(String.valueOf(subnum), answerA0);
                                editor2.commit();

                                if(answerA0.equals(list.get(subnum-1).getOption1())){
                                    radioGroup.clearCheck();
                                    option1radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption2())){
                                    radioGroup.clearCheck();
                                    option2radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption3())){
                                    radioGroup.clearCheck();
                                    option3radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption4())){
                                    radioGroup.clearCheck();
                                    option4radio.setChecked(true);
                                }else{
                                    radioGroup.clearCheck();
                                    option1radio.setChecked(false);
                                    option2radio.setChecked(false);
                                    option3radio.setChecked(false);
                                    option4radio.setChecked(false);
                                }
                            }
                            if(subject==3){                                                    //Remember the options selected

                                String answerA0 = settings1.getString(String.valueOf(subnum), "poty");
                                editor1.putString(String.valueOf(subnum), answerA0);
                                editor1.commit();

                                if(answerA0.equals(list.get(subnum-1).getOption1())){
                                    radioGroup.clearCheck();
                                    option1radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption2())){
                                    radioGroup.clearCheck();
                                    option2radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption3())){
                                    radioGroup.clearCheck();
                                    option3radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption4())){
                                    radioGroup.clearCheck();
                                    option4radio.setChecked(true);
                                }else{
                                    radioGroup.clearCheck();
                                    option1radio.setChecked(false);
                                    option2radio.setChecked(false);
                                    option3radio.setChecked(false);
                                    option4radio.setChecked(false);
                                }
                            }
                        }



                    }
                });

                backbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        subnum--;

                        if(subnum==0){
                            if(subject==1){
                                if(option1radio.isChecked()||option2radio.isChecked()||option3radio.isChecked()||option4radio.isChecked()) {
                                    editornotanswered1.putBoolean(String.valueOf(subnum),false);
                                    editornotanswered1.commit();
                                }else{
                                    editornotanswered1.putBoolean(String.valueOf(subnum),true);
                                    editornotanswered1.commit();
                                }
                            }else if(subject==2){
                                if(option1radio.isChecked()||option2radio.isChecked()||option3radio.isChecked()||option4radio.isChecked()) {
                                    editornotanswered2.putBoolean(String.valueOf(subnum),false);
                                    editornotanswered2.commit();
                                }else{
                                    editornotanswered2.putBoolean(String.valueOf(subnum),true);
                                    editornotanswered2.commit();
                                }
                            }else if(subject==3){
                                if(option1radio.isChecked()||option2radio.isChecked()||option3radio.isChecked()||option4radio.isChecked()) {
                                    editornotanswered3.putBoolean(String.valueOf(subnum),false);
                                    editornotanswered3.commit();
                                }else{
                                    editornotanswered3.putBoolean(String.valueOf(subnum),true);
                                    editornotanswered3.commit();
                                }
                            }
                            Intent intent=new Intent(getBaseContext(),OnlineExamGridShow.class);
                            intent.putExtra("text",text);
                            intent.putExtra("position", position);
                            intent.putExtra("set", set);
                            intent.putExtra("Subject", subject);
                            intent.putExtra("phy", phy);
                            intent.putExtra("chem", chem);
                            intent.putExtra("maths", maths);
                            intent.putExtra("score",score);
                            intent.putExtra("minutes",minutes);
                            intent.putExtra("sec",sec);
                            intent.putExtra("mode",mode);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                            serialnumber.setText("Question No.: "+String.valueOf(subnum)+"/"+list.size());

                            finish();
                            return;

                        }else{
                         if(subject==1){
                             if(option1radio.isChecked()||option2radio.isChecked()||option3radio.isChecked()||option4radio.isChecked()) {
                                 editornotanswered1.putBoolean(String.valueOf(subnum),false);
                                 editornotanswered1.commit();
                             }else{
                                 editornotanswered1.putBoolean(String.valueOf(subnum),true);
                                 editornotanswered1.commit();
                             }
                         }else if(subject==2){
                             if(option1radio.isChecked()||option2radio.isChecked()||option3radio.isChecked()||option4radio.isChecked()) {
                                 editornotanswered2.putBoolean(String.valueOf(subnum),false);
                                 editornotanswered2.commit();
                             }else{
                                 editornotanswered2.putBoolean(String.valueOf(subnum),true);
                                 editornotanswered2.commit();
                             }
                         }else if(subject==3){
                             if(option1radio.isChecked()||option2radio.isChecked()||option3radio.isChecked()||option4radio.isChecked()) {
                                 editornotanswered3.putBoolean(String.valueOf(subnum),false);
                                 editornotanswered3.commit();
                             }else{
                                 editornotanswered3.putBoolean(String.valueOf(subnum),true);
                                 editornotanswered3.commit();
                             }
                         }


                            serialnumber.setText("Question No.: "+String.valueOf(subnum)+"/"+list.size());
                            question.setText(list.get(subnum-1).getQuestion());
                            option1radio.setText(list.get(subnum-1).getOption1());
                            option2radio.setText(list.get(subnum-1).getOption2());
                            option3radio.setText(list.get(subnum-1).getOption3());
                            option4radio.setText(list.get(subnum-1).getOption4());
                            Glide.with(getBaseContext()).load(list.get(subnum-1).imageurl).into(questionimage);

                            if(subject==1){
                                editorblue.putBoolean(String.valueOf(subnum-1), true);
                                editorblue.commit();

                            }
                            if(subject==2){
                                editoryellow.putBoolean(String.valueOf(subnum-1), true);
                                editoryellow.commit();

                            }


                            if (subject==3){
                                editorred.putBoolean(String.valueOf(subnum-1), true);
                                editorred.commit();

                            }

                            if(subject==1){

                                String answerA0 = settings.getString(String.valueOf(subnum), "poty");
                                editor.putString(String.valueOf(subnum), answerA0);
                                editor.commit();
                                if(answerA0.equals(list.get(subnum-1).getOption1())){
                                    radioGroup.clearCheck();
                                    option1radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption2())){
                                    radioGroup.clearCheck();
                                    option2radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption3())){
                                    radioGroup.clearCheck();
                                    option3radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption4())){
                                    radioGroup.clearCheck();
                                    option4radio.setChecked(true);
                                }else{
                                    radioGroup.clearCheck();
                                    option1radio.setChecked(false);
                                    option2radio.setChecked(false);
                                    option3radio.setChecked(false);
                                    option4radio.setChecked(false);
                                }
                            }

                            if(subject==2){

                                String answerA0 = settings2.getString(String.valueOf(subnum), "poty");
                                editor2.putString(String.valueOf(subnum), answerA0);
                                editor2.commit();
                                if(answerA0.equals(list.get(subnum-1).getOption1())){
                                    radioGroup.clearCheck();
                                    option1radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption2())){
                                    radioGroup.clearCheck();
                                    option2radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption3())){
                                    radioGroup.clearCheck();
                                    option3radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption4())){
                                    radioGroup.clearCheck();
                                    option4radio.setChecked(true);
                                }else{
                                    radioGroup.clearCheck();
                                    option1radio.setChecked(false);
                                    option2radio.setChecked(false);
                                    option3radio.setChecked(false);
                                    option4radio.setChecked(false);
                                }
                            }

                            if(subject==3){

                                String answerA0 = settings1.getString(String.valueOf(subnum), "poty");
                                editor1.putString(String.valueOf(subnum), answerA0);
                                editor1.commit();
                                if(answerA0.equals(list.get(subnum-1).getOption1())){
                                    radioGroup.clearCheck();
                                    option1radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption2())){
                                    radioGroup.clearCheck();
                                    option2radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption3())){
                                    radioGroup.clearCheck();
                                    option3radio.setChecked(true);
                                }else if(answerA0.equals(list.get(subnum-1).getOption4())){
                                    radioGroup.clearCheck();
                                    option4radio.setChecked(true);
                                }else{
                                    radioGroup.clearCheck();
                                    option1radio.setChecked(false);
                                    option2radio.setChecked(false);
                                    option3radio.setChecked(false);
                                    option4radio.setChecked(false);
                                }
                            }
                        }

                    }
                });

                menubutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(subject==1){

                            if(option1radio.isChecked()||option2radio.isChecked()||option3radio.isChecked()||option4radio.isChecked()){

                            }else{
                                editornotanswered1.putBoolean(String.valueOf(subnum-1),true);
                                editornotanswered1.commit();
                            }


                        }
                        if(subject==2){
                            if(option1radio.isChecked()||option2radio.isChecked()||option3radio.isChecked()||option4radio.isChecked()){

                            }else{
                                editornotanswered2.putBoolean(String.valueOf(subnum-1),true);
                                editornotanswered2.commit();
                            }

                        }
                        if(subject==3){
                            if(option1radio.isChecked()||option2radio.isChecked()||option3radio.isChecked()||option4radio.isChecked()){

                            }else{
                                editornotanswered3.putBoolean(String.valueOf(subnum-1),true);
                                editornotanswered3.commit();
                            }

                        }

                        Intent intent=new Intent(getBaseContext(),OnlineExamGridShow.class);
                        intent.putExtra("minutes",minutes);
                        intent.putExtra("sec",sec);
                        intent.putExtra("text",text);
                        intent.putExtra("position", position);
                        intent.putExtra("set", set);
                        intent.putExtra("Subject", subject);
                        intent.putExtra("phy", phy);
                        intent.putExtra("chem", chem);
                        intent.putExtra("maths", maths);
                        intent.putExtra("score",score);
                        intent.putExtra("mode",mode);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        finish();

                    }
                });


                loadingDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TestMainDisplayActivityASAP.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                finish();
            }
        });






    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getBaseContext(),OnlineExamGridShow.class);
        text= (String) timer.getText();
        intent.putExtra("text",text);
        intent.putExtra("position", position);
        intent.putExtra("set", set);
        intent.putExtra("Subject", subject);
        intent.putExtra("phy", phy);
        intent.putExtra("chem", chem);
        intent.putExtra("maths", maths);
        intent.putExtra("score",score);
        intent.putExtra("mode",mode);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

        TestMainDisplayActivityASAP.super.onBackPressed();
        finish();
    }

    public void TextSetter(){
        question.setText(list.get(subnum-1).getQuestion());
        option1radio.setText(list.get(subnum-1).getOption1());
        option2radio.setText(list.get(subnum-1).getOption2());
        option3radio.setText(list.get(subnum-1).getOption3());
        option4radio.setText(list.get(subnum-1).getOption4());
        Glide.with(getBaseContext()).load(list.get(subnum-1).imageurl).into(questionimage);


    }

    public void CheckAnswerMaina(){

    }

   public void marksmanipulationcorrect(){
        switch (position){
            case 1:
                score=score+4;break;
        }
}
   public void marksmanipulationwrong(){
        switch (position){
            case 1:
                score=score-1;break;
        }
   }


   }

