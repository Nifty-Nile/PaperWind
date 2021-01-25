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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FinalRankPredictorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int SelectedEntranceExam,inputdata,score,Gender0,Cast0,Goldennumber,branch,inputrank;
    TextView exam123,category123,gender123,rank,rankhead,Scoreint,textView7,Text3,dis2,Text200,rankheading;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private Dialog loadingDialog;
    TextInputEditText scoretext;
    Button getCollege,donebutton;
    String value;
    androidx.appcompat.widget.Toolbar toolbar;
    private Spinner spinner2;
    private static String[] paths123;
    int post,value1;
    FirebaseAuth fAuth;
    int permission;
    final DatabaseReference reference1 = database.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_rank_predictor);
        spinner2 = (Spinner)findViewById(R.id.spinner2);


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
        branch=getIntent().getIntExtra("Branch",0);
        fAuth = FirebaseAuth.getInstance();
        reference1.child(fAuth.getCurrentUser().getUid()).child("personal").child("permission").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                post = dataSnapshot.getValue(Integer.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference1.child(fAuth.getCurrentUser().getUid()).child("personal").child("money").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // convert the data back to the model
                value1 = dataSnapshot.getValue(Integer.class);
                // papernotestotal.setText("Paper Notes: " + String.valueOf(value));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(SelectedEntranceExam==1){
            paths123 = new String[]{"Computer Science and Engineering", "Electronics and Communication Engineering", "Electrical and Electronics Engineering", "Mechanical Engineering", "Civil Engineering", "Mathematics", "Physics","Chemistry"};
        }else if(SelectedEntranceExam==4){
            paths123 = new String[]{"Computer Science and Engineering", "Information technology", "Computer science and Engg. (Specialisation in Bioinformatics)", "BioMedical Engineering", "Biotechnology", "Civil Engineering", "Electronics and Communication Engineering","Electrical and Electronics Engineering","Electronics and Instrumentation Engineering","Mechanical Engineering","Mechanical (Spec. in Automotive Engineering)","Mechanical (Spec. in Energy Engineering)","Chemical Engineering","ECE (Spec.Internet of Things and Sensor)","Comp.Science Engg.(Spec.in Information Security)","Fashion Technology","Electronics and Computer Engineering"};
        }else if(SelectedEntranceExam==5||SelectedEntranceExam==11){
            paths123 = new String[]{"Computer Science and Engineering", "Information Science and Engineering", "Electronics and Communication Engineering", "Mechanical Engineering", "Electrical and Electronics Engineering", "Telecommunication Engineering", "Civil Engineering","Biotechnology"};
        }else if(SelectedEntranceExam==7||SelectedEntranceExam==2||SelectedEntranceExam==3||SelectedEntranceExam==6||SelectedEntranceExam==9||SelectedEntranceExam==12){
            paths123 = new String[]{"Computer Science and Engineering", "Electronics and Instrumentation Engineering", "Chemical Engineering", "Civil Engineering", "Electrical and Electronics Engineering", "Mechanical Engineering", "Manufacturing engineering","Electronics and Communication Engineering"};
        }else if(SelectedEntranceExam==8){
            paths123 = new String[]{"Computer Science and Engineering", "Information Technology", "Communication and Computer Engineering", "Data Science And Engineering", "Electronics and Communication Engineering", "Electrical and Electronics Engineering", "Mechatronics","Mechanical Engineering","Aerospace Engineering","Electronics and Instrumentation Engineering","Automobile Engineering","Biotechnology","Bio Medical Engineering","Chemical Engineering","Civil Engineering","Industrial and Production Engineering","Media Technology"};
        }else if(SelectedEntranceExam==10){
            paths123 = new String[]{"Computer Science and Engineering", "Information Technology","Electronics and Communication Engineering","Mechanical Engineering","Electrical and Electronics Engineering","Computer Science and Engineering(Software Engineering)","Mechatronics","Biotechnology","Aerospace Engineering","Bio Medical Engineering","Automobile Engineering","Electronics and Instrumentation Engineering","Biotechnology Engineering(Genetics Engineering)","Chemical Engineering","Nanotechnology Engineer","Civil Engineering"};
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(FinalRankPredictorActivity.this, android.R.layout.simple_spinner_item,paths123);

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
                    if(!college()){
                        return false;
                    }
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            if(!college()) {
                                return false;
                            }

                            if(post==0){
                                if(value1<=40){
                                    AlertDialog.Builder builder=new AlertDialog.Builder(FinalRankPredictorActivity.this,R.style.AlertDialogTheme);
                                    View view1= LayoutInflater.from(FinalRankPredictorActivity.this).inflate(R.layout.alert_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                                    builder.setView(view1);
                                    ((TextView) view1.findViewById(R.id.textTitle)).setText("You need 40 Paper Notes!");
                                    ((TextView) view1.findViewById(R.id.textMessage)).setText("To use College Predictor you must have 40 Paper Notes!");
                                    ((Button) view1.findViewById(R.id.buttonNo)).setText("OK");
                                    ((Button) view1.findViewById(R.id.buttonYes)).setText("Get Some Paper Notes!");
                                    ((ImageView) view1.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_timer_24);

                                    final AlertDialog alertDialog=builder.create();

                                    view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {


                                            Intent intent=new Intent(getBaseContext(),MoneyActivity.class);
                                            startActivity(intent);
                                            alertDialog.dismiss();
                                            finish();
                                            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

                                        }
                                    });
                                    view1.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            alertDialog.dismiss();

                                        }
                                    });

                                    if(alertDialog.getWindow()!=null){
                                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                                    }
                                    alertDialog.show();
                                }else{

                                    AlertDialog.Builder builder=new AlertDialog.Builder(FinalRankPredictorActivity.this,R.style.AlertDialogTheme);
                                    View view1= LayoutInflater.from(FinalRankPredictorActivity.this).inflate(R.layout.alert_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                                    builder.setView(view1);
                                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Use 40 Paper Notes");
                                    ((TextView) view1.findViewById(R.id.textMessage)).setText("Pay 40 Paper Notes To Get Life Time Full Access To College Predictor");
                                    ((Button) view1.findViewById(R.id.buttonNo)).setText("Cancel");
                                    ((Button) view1.findViewById(R.id.buttonYes)).setText("Pay 40 Paper Notes!");
                                    ((ImageView) view1.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_timer_24);

                                    final AlertDialog alertDialog=builder.create();

                                    view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            value1 = value1 - 40;

                                            reference1.child(fAuth.getCurrentUser().getUid()).child("personal").child("money").setValue(value1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(FinalRankPredictorActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        Toast.makeText(FinalRankPredictorActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });



                                            permission=1;
                                            reference1.child(fAuth.getCurrentUser().getUid()).child("personal").child("permission").setValue(permission).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(FinalRankPredictorActivity.this, "College Predictor Is All Yours Now! Use It For Unlimited Time.", Toast.LENGTH_LONG).show();
                                                    }else{
                                                        Toast.makeText(FinalRankPredictorActivity.this, "An Error Occured!", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });










                                            alertDialog.dismiss();
                                            inputrank=Integer.valueOf(scoretext.getText().toString());
                                            Intent intent=new Intent(getBaseContext(),CollegePredictorMainActivity.class);
                                            intent.putExtra("RankEE",SelectedEntranceExam);
                                            intent.putExtra("InputPredictor",2);
                                            intent.putExtra("Rank1",inputrank);
                                            intent.putExtra("Gender",Gender0);
                                            intent.putExtra("cast",Cast0);
                                            intent.putExtra("Branch",branch);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                            finish();



                                        }
                                    });
                                    view1.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            alertDialog.dismiss();

                                        }
                                    });

                                    if(alertDialog.getWindow()!=null){
                                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                                    }
                                    alertDialog.show();


                                }
                            }else{

                                inputrank=Integer.valueOf(scoretext.getText().toString());
                                Intent intent=new Intent(getBaseContext(),CollegePredictorMainActivity.class);
                                intent.putExtra("RankEE",SelectedEntranceExam);
                                intent.putExtra("InputPredictor",2);
                                intent.putExtra("Rank1",inputrank);
                                intent.putExtra("Gender",Gender0);
                                intent.putExtra("cast",Cast0);
                                intent.putExtra("Branch",branch);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                finish();
                            }
                            return true;

                        default:
                            break;
                    }
                }
                return false;
            }
        });


        getCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!college()){
                    return;
                }



                if(post==0){
                    if(value1==40){
                        AlertDialog.Builder builder=new AlertDialog.Builder(FinalRankPredictorActivity.this,R.style.AlertDialogTheme);
                        View view1= LayoutInflater.from(FinalRankPredictorActivity.this).inflate(R.layout.alert_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                        builder.setView(view1);
                        ((TextView) view1.findViewById(R.id.textTitle)).setText("You need 40 Paper Notes!");
                        ((TextView) view1.findViewById(R.id.textMessage)).setText("To use Rank Predictor you must have 40 Paper Notes!");
                        ((Button) view1.findViewById(R.id.buttonNo)).setText("OK");
                        ((Button) view1.findViewById(R.id.buttonYes)).setText("Get Some Paper Notes!");
                        ((ImageView) view1.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_timer_24);

                        final AlertDialog alertDialog=builder.create();

                        view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                Intent intent=new Intent(getBaseContext(),MoneyActivity.class);
                                startActivity(intent);
                                alertDialog.dismiss();
                                finish();
                                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

                            }
                        });
                        view1.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();

                            }
                        });

                        if(alertDialog.getWindow()!=null){
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                        }
                        alertDialog.show();
                    }else{

                        AlertDialog.Builder builder=new AlertDialog.Builder(FinalRankPredictorActivity.this,R.style.AlertDialogTheme);
                        View view1= LayoutInflater.from(FinalRankPredictorActivity.this).inflate(R.layout.alert_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                        builder.setView(view1);
                        ((TextView) view1.findViewById(R.id.textTitle)).setText("Use 40 Paper Notes");
                        ((TextView) view1.findViewById(R.id.textMessage)).setText("Pay 40 Paper Notes To Get Life Time Full Access To College Predictor");
                        ((Button) view1.findViewById(R.id.buttonNo)).setText("Cancel");
                        ((Button) view1.findViewById(R.id.buttonYes)).setText("Pay 40 Paper Notes!");
                        ((ImageView) view1.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_timer_24);

                        final AlertDialog alertDialog=builder.create();

                        view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                value1 = value1 - 40;

                                reference1.child(fAuth.getCurrentUser().getUid()).child("personal").child("money").setValue(value1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(FinalRankPredictorActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(FinalRankPredictorActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });



                                permission=1;
                                reference1.child(fAuth.getCurrentUser().getUid()).child("personal").child("permission").setValue(permission).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(FinalRankPredictorActivity.this, "College Predictor Is All Yours Now! Use It For Unlimited Time.", Toast.LENGTH_LONG).show();
                                        }else{
                                            Toast.makeText(FinalRankPredictorActivity.this, "An Error Occured!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });









                                alertDialog.dismiss();

                                inputrank=Integer.valueOf(scoretext.getText().toString());
                                Intent intent=new Intent(getBaseContext(),CollegePredictorMainActivity.class);
                                intent.putExtra("RankEE",SelectedEntranceExam);
                                intent.putExtra("InputPredictor",2);
                                intent.putExtra("Rank1",inputrank);
                                intent.putExtra("Gender",Gender0);
                                intent.putExtra("cast",Cast0);
                                intent.putExtra("Branch",branch);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                finish();



                            }
                        });
                        view1.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();

                            }
                        });

                        if(alertDialog.getWindow()!=null){
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                        }
                        alertDialog.show();


                    }
                }else{


                    inputrank=Integer.valueOf(scoretext.getText().toString());
                    Intent intent=new Intent(getBaseContext(),CollegePredictorMainActivity.class);
                    intent.putExtra("RankEE",SelectedEntranceExam);
                    intent.putExtra("InputPredictor",2);
                    intent.putExtra("Rank1",inputrank);
                    intent.putExtra("Gender",Gender0);
                    intent.putExtra("cast",Cast0);
                    intent.putExtra("Branch",branch);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                    finish();
                }




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



        if (SelectedEntranceExam == 1) {
            switch (position) {
                case 0:
                    branch = position + 1;
                    break;
                case 1:
                    branch = position + 1;
                    break;
                case 2:
                    branch = position + 1;
                    break;
                case 3:
                    branch = position + 1;
                    break;
                case 4:
                    branch = position + 1;
                    break;
                case 5:
                    branch = position + 1;
                    break;
                case 6:
                    branch = position + 1;
                    break;
                case 7:
                    branch = position + 1;
                    break;

            }
        } else if(SelectedEntranceExam == 4) {
            switch (position) {
                case 0:
                    branch = position + 1;
                    break;
                case 1:
                    branch = position + 1;
                    break;
                case 2:
                    branch = position + 1;
                    break;
                case 3:
                    branch = position + 1;
                    break;
                case 4:
                    branch = position + 1;
                    break;
                case 5:
                    branch = position + 1;
                    break;
                case 6:
                    branch = position + 1;
                    break;
                case 7:
                    branch = position + 1;
                    break;
                case 8:
                    branch = position + 1;
                    break;
                case 9:
                    branch = position + 1;
                    break;
                case 10:
                    branch = position + 1;
                    break;
                case 11:
                    branch = position + 1;
                    break;
                case 12:
                    branch = position + 1;
                    break;
                case 13:
                    branch = position + 1;
                    break;
                case 14:
                    branch = position + 1;
                    break;
                case 15:
                    branch = position + 1;
                    break;
                case 16:
                    branch = position + 1;
                    break;
                case 17:
                    branch = position + 1;
                    break;
            }
        }else if(SelectedEntranceExam == 5||SelectedEntranceExam==11){
            switch (position) {
                case 0:
                    branch = position + 1;
                    break;
                case 1:
                    branch = position + 1;
                    break;
                case 2:
                    branch = position + 1;
                    break;
                case 3:
                    branch = position + 1;
                    break;
                case 4:
                    branch = position + 1;
                    break;
                case 5:
                    branch = position + 1;
                    break;
                case 6:
                    branch = position + 1;
                    break;
                case 7:
                    branch = position + 1;
                    break;
            }
        }else if(SelectedEntranceExam == 7){
            switch (position) {
                case 0:
                    branch = position + 1;
                    break;
                case 1:
                    branch = position + 1;
                    break;
                case 2:
                    branch = position + 1;
                    break;
                case 3:
                    branch = position + 1;
                    break;
                case 4:
                    branch = position + 1;
                    break;
                case 5:
                    branch = position + 1;
                    break;
                case 6:
                    branch = position + 1;
                    break;
                case 7:
                    branch = position + 1;
                    break;
            }
        }else if(SelectedEntranceExam == 8) {
            switch (position) {
                case 0:
                    branch = position + 1;
                    break;
                case 1:
                    branch = position + 1;
                    break;
                case 2:
                    branch = position + 1;
                    break;
                case 3:
                    branch = position + 1;
                    break;
                case 4:
                    branch = position + 1;
                    break;
                case 5:
                    branch = position + 1;
                    break;
                case 6:
                    branch = position + 1;
                    break;
                case 7:
                    branch = position + 1;
                    break;
                case 8:
                    branch = position + 1;
                    break;
                case 9:
                    branch = position + 1;
                    break;
                case 10:
                    branch = position + 1;
                    break;
                case 11:
                    branch = position + 1;
                    break;
                case 12:
                    branch = position + 1;
                    break;
                case 13:
                    branch = position + 1;
                    break;
                case 14:
                    branch = position + 1;
                    break;
                case 15:
                    branch = position + 1;
                    break;
                case 16:
                    branch = position + 1;
                    break;
            }
        }else if(SelectedEntranceExam == 10) {
            switch (position) {
                case 0:
                    branch = position + 1;
                    break;
                case 1:
                    branch = position + 1;
                    break;
                case 2:
                    branch = position + 1;
                    break;
                case 3:
                    branch = position + 1;
                    break;
                case 4:
                    branch = position + 1;
                    break;
                case 5:
                    branch = position + 1;
                    break;
                case 6:
                    branch = position + 1;
                    break;
                case 7:
                    branch = position + 1;
                    break;
                case 8:
                    branch = position + 1;
                    break;
                case 9:
                    branch = position + 1;
                    break;
                case 10:
                    branch = position + 1;
                    break;
                case 11:
                    branch = position + 1;
                    break;
                case 12:
                    branch = position + 1;
                    break;
                case 13:
                    branch = position + 1;
                    break;
                case 14:
                    branch = position + 1;
                    break;
            }
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
                exam123.setText("Entrance Exam: "+"MET");break;
            case 9:
                exam123.setText("Entrance Exam: "+"MHCET");break;
            case 10:
                exam123.setText("Entrance Exam: "+"SRMJEE");break;
            case 11:
                exam123.setText("Entrance Exam: "+"KCET");break;
            case 12:
                exam123.setText("Entrance Exam: "+"IPU-CET");break;
          /*  case 14:
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
                exam123.setText("Entrance Exam: "+"NIFT");break;*/
        }
        switch (Gender0){
            case 1:
                if(SelectedEntranceExam==1||SelectedEntranceExam==3){
                    gender123.setText("Gender: "+"Male");break;
                }else{
                    gender123.setText("Gender: "+"Not Considered");break;
                }
            case 2:
                if(SelectedEntranceExam==1||SelectedEntranceExam==3){
                    gender123.setText("Gender: "+"Female");break;
                }else{
                    gender123.setText("Gender: "+"Not Considered");break;
                }
        }
        switch (Cast0){
            case 1:
                if(SelectedEntranceExam==2){
                category123.setText("Category: "+"All Catergory");rankhead.setText("Percentile: ");
            }else if(SelectedEntranceExam==4||SelectedEntranceExam==5||SelectedEntranceExam==6||SelectedEntranceExam==7||SelectedEntranceExam==8||SelectedEntranceExam==9||SelectedEntranceExam==10||SelectedEntranceExam==11||SelectedEntranceExam==12){
                    category123.setText("Category: "+"All Catergory");rankhead.setText("Rank: ");
                } else{
                category123.setText("Category: " + "General");rankhead.setText("Gen. Rank:");
                break;
            }
            case 2:
                if(SelectedEntranceExam==2){
                    category123.setText("Category: "+"All Catergory");rankhead.setText("Percentile: ");
                }else if(SelectedEntranceExam==4||SelectedEntranceExam==5||SelectedEntranceExam==6||SelectedEntranceExam==7||SelectedEntranceExam==8||SelectedEntranceExam==9||SelectedEntranceExam==10||SelectedEntranceExam==11||SelectedEntranceExam==12){
                    category123.setText("Category: "+"All Catergory");rankhead.setText("Rank: ");
                }else {
                    category123.setText("Category: " + "Obc");rankhead.setText("OBC Rank:");
                    break;
                }
            case 3:
                if(SelectedEntranceExam==2){
                    category123.setText("Category: "+"All Catergory");rankhead.setText("Percentile: ");
                }else if(SelectedEntranceExam==4||SelectedEntranceExam==5||SelectedEntranceExam==6||SelectedEntranceExam==7||SelectedEntranceExam==8||SelectedEntranceExam==9||SelectedEntranceExam==10||SelectedEntranceExam==11||SelectedEntranceExam==12){
                    category123.setText("Category: "+"All Catergory");rankhead.setText("Rank: ");
                }else {
                    category123.setText("Category: " + "ST");rankhead.setText("ST Rank:");
                    break;
                }
            case 4:
                if(SelectedEntranceExam==2){
                    category123.setText("Category: "+"All Catergory");rankhead.setText("Percentile: ");
                }else if(SelectedEntranceExam==4||SelectedEntranceExam==5||SelectedEntranceExam==6||SelectedEntranceExam==7||SelectedEntranceExam==8||SelectedEntranceExam==9||SelectedEntranceExam==10||SelectedEntranceExam==11||SelectedEntranceExam==12){
                    category123.setText("Category: "+"All Catergory");rankhead.setText("Rank: ");
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
            case 4:
                Viteegold();break;
            case 5:
                Comedkgold();break;
            case 6:
                Ndagold();break;
            case 7:
                Bitsat();break;
            case 8:
                Metgold();break;
            case 9:
                Mhcetgold();break;
            case 10:
                Srmjeegold();break;
            case 11:
                Kcetgold();break;
            case 12:
                Ipucetgold();break;
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

    public void Mhcetgold(){
        if (score >= 190) {
            Goldennumber = 1;
        } else if (score >= 185) {
            Goldennumber = 2;
        } else if (score >= 182) {
            Goldennumber = 3;
        } else if (score >= 180) {
            Goldennumber = 4;
        } else if (score >= 175) {
            Goldennumber = 5;
        } else if (score >= 168) {
            Goldennumber = 6;
        } else if (score >= 164) {
            Goldennumber = 7;
        }else if (score >= 158) {
            Goldennumber = 8;
        } else if (score >= 154) {
            Goldennumber = 9;
        } else if (score >= 148) {
            Goldennumber = 10;
        } else if (score >= 144) {
            Goldennumber = 11;
        } else if (score >= 140) {
            Goldennumber = 12;
        } else if (score >= 134) {
            Goldennumber = 12;
        } else if (score >= 128) {
            Goldennumber = 14;
        }else if (score >= 120) {
            Goldennumber = 15;
        } else if (score >= 115) {
            Goldennumber = 16;
        } else if (score >= 108) {
            Goldennumber = 17;
        } else if (score >= 100) {
            Goldennumber = 18;
        } else if (score >= 95) {
            Goldennumber = 19;
        } else if (score >= 92) {
            Goldennumber = 20;
        } else if (score >= 88) {
            Goldennumber = 21;
        }else if (score >= 82) {
            Goldennumber = 22;
        }else if (score >= 78) {
            Goldennumber = 23;
        } else if (score >= 74) {
            Goldennumber = 24;
        } else if (score >= 72) {
            Goldennumber = 25;
        }else if (score >= 68) {
            Goldennumber = 26;
        }else if (score >= 64) {
            Goldennumber = 27;
        } else if (score >= 62) {
            Goldennumber = 28;
        } else if (score >= 58) {
            Goldennumber = 29;
        } else if (score >= 48) {
            Goldennumber = 30;
        } else  {
            Goldennumber = 31;
        }
    }

    public void Srmjeegold(){
        if (score >= 250) {
            Goldennumber = 1;
        } else if (score >= 231) {
            Goldennumber = 2;
        } else if (score >= 200) {
            Goldennumber = 3;
        } else if (score >= 170) {
            Goldennumber = 4;
        } else if (score >= 150) {
            Goldennumber = 5;
        } else if (score >= 130) {
            Goldennumber = 6;
        } else  {
            Goldennumber = 7;
        }
    }

    public void Kcetgold(){
        if (score >= 170) {
            Goldennumber = 1;
        } else if (score >= 167) {
            Goldennumber = 2;
        } else if (score >= 164) {
            Goldennumber = 3;
        } else if (score >= 161) {
            Goldennumber = 4;
        } else if (score >= 158) {
            Goldennumber = 5;
        } else if (score >= 156) {
            Goldennumber = 6;
        } else if (score >= 148) {
            Goldennumber = 7;
        }else if (score >= 144) {
            Goldennumber = 8;
        } else if (score >= 140) {
            Goldennumber = 9;
        } else if (score >= 136) {
            Goldennumber = 10;
        } else if (score >= 130) {
            Goldennumber = 11;
        } else if (score >= 126) {
            Goldennumber = 12;
        } else if (score >= 120) {
            Goldennumber = 12;
        } else if (score >= 116) {
            Goldennumber = 14;
        }else if (score >= 105) {
            Goldennumber = 15;
        } else if (score >= 95) {
            Goldennumber = 16;
        } else if (score >= 90) {
            Goldennumber = 17;
        } else if (score >= 85) {
            Goldennumber = 18;
        } else if (score >= 80) {
            Goldennumber = 19;
        } else if (score >= 75) {
            Goldennumber = 20;
        } else if (score >= 70) {
            Goldennumber = 21;
        }else if (score >= 65) {
            Goldennumber = 22;
        }else if (score >= 60) {
            Goldennumber = 23;
        } else if (score >= 55) {
            Goldennumber = 24;
        } else if (score >= 50) {
            Goldennumber = 25;
        }else if (score >= 45) {
            Goldennumber = 26;
        } else  {
            Goldennumber = 27;
        }
    }

    public void Metgold(){
        if (score >= 560) {
            Goldennumber = 1;
        } else if (score >= 540) {
            Goldennumber = 2;
        } else if (score >= 500) {
            Goldennumber = 3;
        } else if (score >= 480) {
            Goldennumber = 4;
        } else if (score >= 460) {
            Goldennumber = 5;
        } else if (score >= 440) {
            Goldennumber = 6;
        } else if (score >= 420) {
            Goldennumber = 7;
        }else if (score >= 400) {
            Goldennumber = 8;
        } else if (score >= 380) {
            Goldennumber = 9;
        } else if (score >= 360) {
            Goldennumber = 10;
        } else if (score >= 320) {
            Goldennumber = 11;
        } else if (score >= 280) {
            Goldennumber = 12;
        } else if (score >= 260) {
            Goldennumber = 13;
        } else  {
            Goldennumber = 14;
        }
    }

    public void Ipucetgold(){
        if (score >= 350) {
            Goldennumber = 1;
        } else if (score >= 330) {
            Goldennumber = 2;
        } else if (score >= 310) {
            Goldennumber = 3;
        } else if (score >= 290) {
            Goldennumber = 4;
        } else if (score >= 250) {
            Goldennumber = 5;
        } else if (score >= 235) {
            Goldennumber = 6;
        } else if (score >= 220) {
            Goldennumber = 7;
        }else if (score >= 210) {
            Goldennumber = 8;
        } else  {
            Goldennumber = 35;
        }
    }

    public void Ndagold(){
        if (score >= 680) {
            Goldennumber = 1;
        }  else  {
            Goldennumber = 2;
        }
    }

    public void Bitsat(){
        if (score >= 400) {
            Goldennumber = 1;
        } else if (score >= 350) {
            Goldennumber = 2;
        } else if (score >= 300) {
            Goldennumber = 3;
        } else if (score >= 250) {
            Goldennumber = 4;
        }else  {
            Goldennumber = 5;
        }
    }
    public void Comedkgold(){
        if (score >= 170) {
            Goldennumber = 1;
        } else if (score >= 160) {
            Goldennumber = 2;
        } else if (score >= 150) {
            Goldennumber = 3;
        } else if (score >= 140) {
            Goldennumber = 4;
        } else if (score >= 130) {
            Goldennumber = 5;
        } else if (score >= 120) {
            Goldennumber = 6;
        } else if (score >= 110) {
            Goldennumber = 7;
        }else if (score >= 100) {
            Goldennumber = 8;
        } else if (score >= 90) {
            Goldennumber = 9;
        } else if (score >= 80) {
            Goldennumber = 10;
        } else if (score >= 70) {
            Goldennumber = 11;
        } else if (score >= 60) {
            Goldennumber = 12;
        } else if (score >= 50) {
            Goldennumber = 12;
        } else if (score >= 40) {
            Goldennumber = 14;
        }else if (score >= 30) {
            Goldennumber = 15;
        } else if (score >= 20) {
            Goldennumber = 16;
        } else  {
            Goldennumber = 17;
        }
    }

    public void Viteegold(){
        if (score >= 120) {
            Goldennumber = 1;
        } else if (score >= 115) {
            Goldennumber = 2;
        } else if (score >= 110) {
            Goldennumber = 3;
        } else if (score >= 105) {
            Goldennumber = 4;
        } else if (score >= 100) {
            Goldennumber = 5;
        } else if (score >= 95) {
            Goldennumber = 6;
        } else if (score >= 90) {
            Goldennumber = 7;
        }else if (score >= 85) {
            Goldennumber = 8;
        } else if (score >= 80) {
            Goldennumber = 9;
        } else if (score >= 75) {
            Goldennumber = 10;
        } else if (score >= 70) {
            Goldennumber = 11;
        } else if (score >= 65) {
            Goldennumber = 12;
        } else if (score >= 60) {
            Goldennumber = 12;
        } else if (score >= 55) {
            Goldennumber = 14;
        }else if (score >= 50) {
            Goldennumber = 15;
        } else if (score >= 42) {
            Goldennumber = 16;
        } else if (score >= 35) {
            Goldennumber = 17;
        } else if (score >= 30) {
            Goldennumber = 18;
        } else if (score >= 25) {
            Goldennumber = 19;
        } else if (score >= 20) {
            Goldennumber = 20;
        } else  {
            Goldennumber = 21;
        }
    }

    public void JeeMainsgold(){
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
        }else if (score >= 211) {
            Goldennumber = 8;
        } else if (score >= 201) {
            Goldennumber = 9;
        } else if (score >= 191) {
            Goldennumber = 10;
        } else if (score >= 181) {
            Goldennumber = 11;
        } else if (score >= 171) {
            Goldennumber = 12;
        } else if (score >= 161) {
            Goldennumber = 12;
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
        } else if (score >= 81) {
            Goldennumber = 21;
        }else if (score >= 71) {
            Goldennumber = 22;
        }else if (score >= 61) {
            Goldennumber = 23;
        } else if (score >= 51) {
            Goldennumber = 24;
        } else if (score >= 45) {
            Goldennumber = 25;
        }else if (score >= 40) {
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
        } else if (score >= 10) {
            Goldennumber = 32;
        } else if (score >= 5) {
            Goldennumber = 33;
        }else if (score >= 1) {
            Goldennumber = 34;
        } else  {
            Goldennumber = 35;
        }
    }

    public void Neetgold(){
            switch (Cast0) {
                case 1:
                    neetgold_general();
                    break;
                case 2:
                    neetgold_obc();
                    break;
                case 3:
                    neetgold_st();
                    break;
                case 4:
                    neetgold_sc();
                    break;
            }
    }

    public void neetgold_general() {
        if (score >= 700) {
            Goldennumber = 1;
        } else if (score >= 690) {
            Goldennumber = 2;
        } else if (score >= 680) {
            Goldennumber = 3;
        } else if (score >= 670) {
            Goldennumber = 4;
        } else if (score >= 660) {
            Goldennumber = 5;
        } else if (score >= 650) {
            Goldennumber = 6;
        } else if (score >= 639) {
            Goldennumber = 7;
        }else if (score >= 630) {
            Goldennumber = 8;
        } else if (score >= 620) {
            Goldennumber = 9;
        } else if (score >= 610) {
            Goldennumber = 10;
        } else if (score >= 600) {
            Goldennumber = 11;
        } else if (score >= 590) {
            Goldennumber = 12;
        } else if (score >= 580) {
            Goldennumber = 12;
        } else if (score >= 570) {
            Goldennumber = 14;
        }else if (score >= 560) {
            Goldennumber = 15;
        } else if (score >= 550) {
            Goldennumber = 16;
        } else if (score >= 540) {
            Goldennumber = 17;
        } else if (score >= 530) {
            Goldennumber = 18;
        } else if (score >= 520) {
            Goldennumber = 19;
        } else if (score >= 518) {
            Goldennumber = 20;
        } else if (score >= 507) {
            Goldennumber = 21;
        }else if (score >= 496) {
            Goldennumber = 22;
        }else if (score >= 485) {
            Goldennumber = 23;
        } else if (score >= 474) {
            Goldennumber = 24;
        } else if (score >= 463) {
            Goldennumber = 25;
        } else if (score >= 452) {
            Goldennumber = 26;
        } else if (score >= 441) {
            Goldennumber = 27;
        } else if (score >= 430) {
            Goldennumber = 28;
        }else if (score >= 419) {
            Goldennumber = 29;
        } else if (score >= 408) {
            Goldennumber = 30;
        } else if (score >= 397) {
            Goldennumber = 31;
        } else if (score >= 386) {
            Goldennumber = 32;
        } else if (score >= 375) {
            Goldennumber = 33;
        } else if (score >= 364) {
            Goldennumber = 34;
        } else if (score >= 353) {
            Goldennumber = 35;
        }else if (score >= 342) {
            Goldennumber = 36;
        } else if (score >= 329) {
            Goldennumber = 37;
        } else if (score >= 318) {
            Goldennumber = 38;
        } else if (score >= 307) {
            Goldennumber = 39;
        } else if (score >= 296) {
            Goldennumber = 40;
        } else if (score >= 285) {
            Goldennumber = 41;
        } else if (score >= 274) {
            Goldennumber = 42;
        }else if (score >= 263) {
            Goldennumber = 43;
        } else if (score >= 252) {
            Goldennumber = 44;
        } else if (score >= 241) {
            Goldennumber = 45;
        } else if (score >= 230) {
            Goldennumber = 46;
        } else if (score >= 219) {
            Goldennumber = 47;
        } else if (score >= 208) {
            Goldennumber = 48;
        } else if (score >= 197) {
            Goldennumber = 49;
        } else if (score >= 186) {
            Goldennumber = 50;
        }else if (score >= 175) {
            Goldennumber = 51;
        }else if (score >= 164) {
            Goldennumber = 52;
        } else if (score >= 144) {
            Goldennumber = 53;
        } else if (score >= 133) {
            Goldennumber = 54;
        } else if (score >= 122) {
            Goldennumber = 55;
        } else if (score >= 111) {
            Goldennumber = 56;
        } else if (score >= 100) {
            Goldennumber = 57;
        }else if (score >= 83) {
            Goldennumber = 58;
        } else if (score >= 70) {
            Goldennumber = 59;
        } else if (score >= 58) {
            Goldennumber = 60;
        }else if (score >= 34) {
            Goldennumber = 61;
        } else  {
            Goldennumber = 62;
        }
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

    public void neetgold_obc() {
        if (score >= 680) {
            Goldennumber = 1;
        } else if (score >= 660) {
            Goldennumber = 2;
        } else if (score >= 650) {
            Goldennumber = 3;
        } else if (score >= 630) {
            Goldennumber = 4;
        } else if (score >= 625) {
            Goldennumber = 5;
        } else if (score >= 610) {
            Goldennumber = 6;
        } else if (score >= 590) {
            Goldennumber = 7;
        } else if (score >= 570) {
            Goldennumber = 8;
        } else if (score >= 540) {
            Goldennumber = 9;
        } else if (score >= 535) {
            Goldennumber = 10;
        } else if (score >= 530) {
            Goldennumber = 11;
        }else if (score >= 500) {
            Goldennumber = 12;
        } else if (score >= 450) {
            Goldennumber = 13;
        }else if (score >= 400) {
            Goldennumber = 14;
        } else if (score >= 350) {
            Goldennumber = 15;
        } else if (score >= 300) {
            Goldennumber = 16;
        } else if (score >= 250) {
            Goldennumber = 17;
        } else if (score >= 200) {
            Goldennumber = 18;
        } else if (score >= 100) {
            Goldennumber = 19;
        }else {
            Goldennumber = 20;
        }
    }


    public void neetgold_st() {
        if (score >= 670) {
            Goldennumber = 1;
        } else if (score >= 650) {
            Goldennumber = 2;
        } else if (score >= 625) {
            Goldennumber = 3;
        } else if (score >= 600) {
            Goldennumber = 4;
        } else if (score >= 580) {
            Goldennumber = 5;
        } else if (score >= 550) {
            Goldennumber = 6;
        } else if (score >= 510) {
            Goldennumber = 7;
        } else if (score >= 490) {
            Goldennumber = 8;
        } else if (score >= 470) {
            Goldennumber = 9;
        } else if (score >= 450) {
            Goldennumber = 10;
        } else if (score >= 440) {
            Goldennumber = 11;
        }else if (score >= 410) {
            Goldennumber = 12;
        } else if (score >= 390) {
            Goldennumber = 13;
        }else if (score >= 370) {
            Goldennumber = 14;
        } else if (score >= 350) {
            Goldennumber = 15;
        } else if (score >= 300) {
            Goldennumber = 16;
        } else if (score >= 250) {
            Goldennumber = 17;
        } else if (score >= 200) {
            Goldennumber = 18;
        } else if (score >= 100) {
            Goldennumber = 19;
        }else {
            Goldennumber = 20;
        }
    }

    public void neetgold_sc() {
        if (score >= 650) {
            Goldennumber = 1;
        } else if (score >= 620) {
            Goldennumber = 2;
        } else if (score >= 600) {
            Goldennumber = 3;
        } else if (score >= 570) {
            Goldennumber = 4;
        } else if (score >= 560) {
            Goldennumber = 5;
        } else if (score >= 550) {
            Goldennumber = 6;
        } else if (score >= 540) {
            Goldennumber = 7;
        } else if (score >= 520) {
            Goldennumber = 8;
        } else if (score >= 510) {
            Goldennumber = 9;
        } else if (score >= 480) {
            Goldennumber = 10;
        } else if (score >= 470) {
            Goldennumber = 11;
        }else if (score >= 450) {
            Goldennumber = 12;
        } else if (score >= 440) {
            Goldennumber = 13;
        }else if (score >= 420) {
            Goldennumber = 14;
        } else if (score >= 380) {
            Goldennumber = 15;
        } else if (score >= 340) {
            Goldennumber = 16;
        } else if (score >= 300) {
            Goldennumber = 17;
        } else if (score >= 250) {
            Goldennumber = 18;
        } else if (score >= 225) {
            Goldennumber = 19;
        }else if (score >= 200) {
            Goldennumber = 20;
        } else if (score >= 150) {
            Goldennumber = 21;
        } else if (score >= 100) {
            Goldennumber = 22;
        } else {
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