package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RankPredictorInputActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button rankbutton,collegebutton;
    int SelectedEntranceExam,score,rank,cast=1,branch=1;
    TextInputEditText ranktext,scoretext;
    private Spinner spinner,spinner2;
    private static final String[] paths = {"General","OBC","ST","SC"};
    private static final String[] paths123 = {"CSE","IT","ECE","EEE","ME","TE","Civil"};
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    int bomb,totalmarks;
    FirebaseAuth fAuth;
    int value;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference1 = database.getReference("User");
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_predictor_input);
        rankbutton=(Button) findViewById(R.id.login);
        collegebutton=(Button) findViewById(R.id.getCollege);
        ranktext=(TextInputEditText) findViewById(R.id.username1);
        scoretext=(TextInputEditText) findViewById(R.id.username);
        radioGroup = (RadioGroup) findViewById(R.id.radio);

        toolbar = findViewById(R.id.toolbar);

        spinner = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(RankPredictorInputActivity.this, android.R.layout.simple_spinner_item,paths);
        ArrayAdapter<String>adapter1 = new ArrayAdapter<String>(RankPredictorInputActivity.this, android.R.layout.simple_spinner_item,paths123);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        spinner2.setAdapter(adapter1);
        spinner2.setOnItemSelectedListener(this);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        totalmarks=getIntent().getIntExtra("totalmarks",0);

        scoretext.setText(String.valueOf(totalmarks));



        SelectedEntranceExam=getIntent().getIntExtra("RankEE",0);
        fAuth = FirebaseAuth.getInstance();
        reference1.child(fAuth.getCurrentUser().getUid()).child("money").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // convert the data back to the model
                value = dataSnapshot.getValue(Integer.class);
               // papernotestotal.setText("Paper Notes: " + String.valueOf(value));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        rankbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if(!rank()){
                    return;
                }

                if(value<=0){
                    AlertDialog.Builder builder=new AlertDialog.Builder(RankPredictorInputActivity.this,R.style.AlertDialogTheme);
                    View view1= LayoutInflater.from(RankPredictorInputActivity.this).inflate(R.layout.alert_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("You Have 0 Paper Notes.");
                    ((TextView) view1.findViewById(R.id.textMessage)).setText("Please get some Paper Notes to use this facility!");
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


                }else {



                    value = value - 1;

                    reference1.child(fAuth.getCurrentUser().getUid()).child("money").setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RankPredictorInputActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(RankPredictorInputActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                    score = Integer.valueOf(scoretext.getText().toString());


                    int i = 0;
                    // get selected radio button from radioGroup
                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    // radiobutton by returned id

                    if (selectedId == 2131296568) {
                        i = 1;
                    } else {
                        i = 2;
                    }


                    Intent intent = new Intent(getBaseContext(), FinalRankPredictorActivity.class);
                    intent.putExtra("RankEE", SelectedEntranceExam);
                    intent.putExtra("InputPredictor", 1);
                    intent.putExtra("Score1", score);
                    intent.putExtra("Gender", i);
                    intent.putExtra("cast", cast);

                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    scoretext.setText("");
                    finish();
                }
            }
        });

        collegebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!college()){
                    return;
                }

                if(value<=40){
                    AlertDialog.Builder builder=new AlertDialog.Builder(RankPredictorInputActivity.this,R.style.AlertDialogTheme);
                    View view1= LayoutInflater.from(RankPredictorInputActivity.this).inflate(R.layout.alert_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
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

                    AlertDialog.Builder builder=new AlertDialog.Builder(RankPredictorInputActivity.this,R.style.AlertDialogTheme);
                    View view1= LayoutInflater.from(RankPredictorInputActivity.this).inflate(R.layout.alert_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Use 40 Paper Notes");
                    ((TextView) view1.findViewById(R.id.textMessage)).setText("Discribe whats inside rank predictor..Aakash HEREREERE");
                    ((Button) view1.findViewById(R.id.buttonNo)).setText("Cancel");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("Pay 40 Paper Notes!");
                    ((ImageView) view1.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_timer_24);

                    final AlertDialog alertDialog=builder.create();

                    view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            value = value - 40;

                            reference1.child(fAuth.getCurrentUser().getUid()).child("money").setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RankPredictorInputActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(RankPredictorInputActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });



                            rank = Integer.valueOf(ranktext.getText().toString());

                            int i=0;
                            // get selected radio button from radioGroup
                            int selectedId = radioGroup.getCheckedRadioButtonId();

                            // find the radiobutton by returned id

                            if(selectedId==2131296568){
                                i=1;
                            }else{
                                i=2;
                            }
                            alertDialog.dismiss();
                            Intent intent=new Intent(getBaseContext(),CollegePredictorMainActivity.class);
                            intent.putExtra("RankEE",SelectedEntranceExam);
                            intent.putExtra("InputPredictor",2);
                            intent.putExtra("Rank1",rank);
                            intent.putExtra("Gender",i);
                            intent.putExtra("cast",cast);
                            intent.putExtra("Branch",branch);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                            scoretext.setText("");
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
            }
                });



    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        Spinner spinner = (Spinner)parent;
        Spinner spinner2 = (Spinner)parent;
        if(spinner.getId() == R.id.spinner1)
        {
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
        if(spinner2.getId() == R.id.spinner2)
        {
            switch (position) {
                case 0:
                    branch=position+1;
                    break;
                case 1:
                    branch=position+1;
                    break;
                case 2:
                    branch=position+1;
                    break;
                case 3:
                    branch=position+1;
                    break;
                case 4:
                    branch=position+1;
                    break;
                case 5:
                    branch=position+1;
                    break;
                case 6:
                    branch=position+1;
                    break;

            }
        }

    }


    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }


    private boolean rank(){
        String name2=scoretext.getText().toString();
        int i,r=0;
        int len=name2.length();
        int pecaso=Integer.parseInt(name2);
        bomb=pecaso%1;

        if(name2.isEmpty()){
            scoretext.setError("Fields cannot be empty");
            return false;
        }


       else if(len>4){
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
            if(bomb!=0){
                scoretext.setError("Score should be an integer");
                return false;
            }

        }
        int inum = Integer.parseInt(name2);
        if(inum>exammarkslimit()){
            scoretext.setError("Entered Marks are more than the maximum marks of the selected Entrance Exam.");
            return false;
        }

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

    public int exammarkslimit(){
        switch (SelectedEntranceExam){
            case 1:
                return 360;
            case 2:
                return 300;
            case 3:
                return 720;
        }
        return 360 ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.share){
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.about){
            Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.history){
            Toast.makeText(this, "history", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.propic){
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.coins){
            AlertDialog.Builder builder=new AlertDialog.Builder(RankPredictorInputActivity.this,R.style.AlertDialogTheme);
            View view1= LayoutInflater.from(RankPredictorInputActivity.this).inflate(R.layout.alert_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
            builder.setView(view1);
            ((TextView) view1.findViewById(R.id.textTitle)).setText("You have "+value+" Paper Notes");
            ((TextView) view1.findViewById(R.id.textMessage)).setText("fnef e fuefn eufn euf eeufn ");
            ((Button) view1.findViewById(R.id.buttonNo)).setText("OK");
            ((Button) view1.findViewById(R.id.buttonYes)).setText("Get Paper Notes!");
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
        }
        return true;
    }
}