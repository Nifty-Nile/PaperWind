package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.User;

public class TestAgrementActivity extends AppCompatActivity {

    Button submitbutton;
    CheckBox checkBox;
    int phy,chem,maths,bio,position,mode,set,score,value,safe;
    androidx.appcompat.widget.Toolbar toolbar;
    TextView text0,text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11
            ,text12,text13,text14,text15,text16,text17;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth fAuth;
    final DatabaseReference reference1 = database.getReference("User");
    String posconverter,setconverter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_agrement);

        submitbutton=(Button) findViewById(R.id.submitbutton);
        checkBox=(CheckBox) findViewById(R.id.checkbox);



        text0=(TextView) findViewById(R.id.text0);
        text1=(TextView) findViewById(R.id.text1);
        text2=(TextView) findViewById(R.id.text2);
        text3=(TextView) findViewById(R.id.text3);
        text4=(TextView) findViewById(R.id.text4);
        text5=(TextView) findViewById(R.id.text5);
        text6=(TextView) findViewById(R.id.text6);
        text7=(TextView) findViewById(R.id.text7);
        text8=(TextView) findViewById(R.id.text8);
        text9=(TextView) findViewById(R.id.text9);
        text10=(TextView) findViewById(R.id.text10);
        text11=(TextView) findViewById(R.id.text11);
        text12=(TextView) findViewById(R.id.text12);
        text13=(TextView) findViewById(R.id.text13);
        text14=(TextView) findViewById(R.id.text14);
        text15=(TextView) findViewById(R.id.text15);
        text16=(TextView) findViewById(R.id.text16);
        text17=(TextView) findViewById(R.id.text17);


        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            LinearLayout layout =(LinearLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);

            text0.setTextColor(Color.parseColor("#ffffff"));
            text1.setTextColor(Color.parseColor("#ffffff"));
            text2.setTextColor(Color.parseColor("#ffffff"));
            text3.setTextColor(Color.parseColor("#ffffff"));
            text4.setTextColor(Color.parseColor("#ffffff"));
            text5.setTextColor(Color.parseColor("#ffffff"));
            text6.setTextColor(Color.parseColor("#ffffff"));
            text7.setTextColor(Color.parseColor("#ffffff"));
            text8.setTextColor(Color.parseColor("#ffffff"));
            text9.setTextColor(Color.parseColor("#ffffff"));
            text10.setTextColor(Color.parseColor("#ffffff"));
            text11.setTextColor(Color.parseColor("#ffffff"));
            text12.setTextColor(Color.parseColor("#ffffff"));
            text13.setTextColor(Color.parseColor("#ffffff"));
            text14.setTextColor(Color.parseColor("#ffffff"));
            text15.setTextColor(Color.parseColor("#ffffff"));
            text16.setTextColor(Color.parseColor("#ffffff"));
            text17.setTextColor(Color.parseColor("#ffffff"));



        }else{
            LinearLayout layout =(LinearLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);

            text0.setTextColor(Color.parseColor("#000000"));
            text1.setTextColor(Color.parseColor("#000000"));
            text2.setTextColor(Color.parseColor("#000000"));
            text3.setTextColor(Color.parseColor("#000000"));
            text4.setTextColor(Color.parseColor("#000000"));
            text5.setTextColor(Color.parseColor("#000000"));
            text6.setTextColor(Color.parseColor("#000000"));
            text7.setTextColor(Color.parseColor("#000000"));
            text8.setTextColor(Color.parseColor("#000000"));
            text9.setTextColor(Color.parseColor("#000000"));
            text10.setTextColor(Color.parseColor("#000000"));
            text11.setTextColor(Color.parseColor("#000000"));
            text12.setTextColor(Color.parseColor("#000000"));
            text13.setTextColor(Color.parseColor("#000000"));
            text14.setTextColor(Color.parseColor("#000000"));
            text15.setTextColor(Color.parseColor("#000000"));
            text16.setTextColor(Color.parseColor("#000000"));
            text17.setTextColor(Color.parseColor("#000000"));


        }



        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Test Instructions");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        phy=getIntent().getIntExtra("phy",0);
        chem=getIntent().getIntExtra("chem",0);
        maths=getIntent().getIntExtra("maths",0);
        bio=getIntent().getIntExtra("bio",0);
        position=getIntent().getIntExtra("position",0);
        mode=getIntent().getIntExtra("mode",0);
        set=getIntent().getIntExtra("set",0);
        score=getIntent().getIntExtra("score",0);
        value=getIntent().getIntExtra("value",0);
        safe=getIntent().getIntExtra("safe",0);


        switch (position){
            case 2:
                text1.setText("1.  Total duration of examination is 180 minutes.");break;
            case 3:
                text1.setText("1.  Total duration of examination is 180 minutes.");break;
            case 4:
                text1.setText("1.  Total duration of examination is 150 minutes.");break;
            case 5:
                text1.setText("1.  Total duration of examination is 180 minutes.");break;
            case 7:
                text1.setText("1.  Total duration of examination is 180 minutes.");break;
            case 8:
                text1.setText("1.  Total duration of examination is 150 minutes.");break;
            case 9:
                text1.setText("1.  Total duration of examination is 180 minutes.");break;
            case 10:
                text1.setText("1.  Total duration of examination is 150 minutes.");break;
            case 11:
                text1.setText("1.  Total duration of examination is 240 minutes.");break;
            case 12:
                text1.setText("1.  Total duration of examination is 180 minutes.");break;
        }

        switch (position){
            case 2:
                text14.setText("5. Total of 90 questions will be provided to you, with 30 questions per section.");
                text15.setText("6. Correct Answer will carry 4 marks while -1 on wrong Answer.");break;
            case 3:
                text14.setText("5. Total of 180 questions will be provided to you, with 45 questions on Physics And Chemistry. And 90 questions on Maths ,Zoology And Botany.");
                text15.setText("6. Correct Answer will carry 4 marks while -1 on wrong Answer.");break;
            case 4:
                text14.setText("5. Total of 125 questions will be provided to you, with 35 questions on Physics ,35 questions on Chemistry And 55 questions on Mathematics And Logical Resoning.");
                text15.setText("6. Correct Answer will carry 1 marks with no negative marking.");break;
            case 5:
                text14.setText("5. Total of 180 questions will be provided to you,with 60 questions per section.");
                text15.setText("6. Correct Answer will carry 1 marks with no negative marking.");break;
            case 7:
                text14.setText("5. Total of 145 questions will be provided to you,with 40 questions on Physics ,40 questions on Chemistry And 65 questions on Mathematics And Logical Resoning.");
                text15.setText("6. Correct Answer will carry 3 marks while -1 on wrong Answer.");break;
            case 8:
                text14.setText("5. Total of 200 questions will be provided to you,with 50 questions on Physics ,50 questions on Chemistry And 100 questions on Mathematics , English And General Aptitude.");
                text15.setText("6. Correct Answer will carry 4 marks while -1 on wrong Answer.");break;
            case 9:
                text14.setText("5. Total of 150 questions will be provided to you,with 50 questions per section.");
                text15.setText("6. Correct Answer will carry 1 marks with no negative marking.");break;
            case 10:
                text14.setText("5. Total of 125 questions will be provided to you,with 35 questions on Physics ,35 questions on Chemistry And 55 questions on Mathematics , English And Aptitude.");
                text15.setText("6. Correct Answer will carry 1 marks with no negative marking");break;
            case 11:
                text14.setText("5. Total of 180 questions will be provided to you,with 60 questions per section.");
                text15.setText("6. Correct Answer will carry 1 marks with no negative marking");break;
            case 12:
                text14.setText("5. Total of 150 questions will be provided to you,with 50 questions per section.");
                text15.setText("6. Correct Answer will carry 4 marks with no negative marking");break;

        }

        posconverter=Integer.toString(position);
        setconverter=Integer.toString(set);

        fAuth= FirebaseAuth.getInstance();

        final DatabaseReference ref=database.getReference().child("User").child(fAuth.getCurrentUser().getUid()).child("ExamPaperPurchased").push();


        ref.addValueEventListener(new ValueEventListener() {
                                      @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 TestPaperPurshasedHolder user = new TestPaperPurshasedHolder(posconverter,setconverter);
                 ref.setValue(user);

                                      }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        if(safe==0){
            value = value - 20;

            reference1.child(fAuth.getCurrentUser().getUid()).child("personal").child("money").setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(TestAgrementActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(TestAgrementActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                    if(isChecked){
                                                        submitbutton.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                Intent intent=new Intent(getBaseContext(),OnlineExamGridShow.class);
                                                                intent.putExtra("position",position);
                                                                intent.putExtra("mode",mode);
                                                                intent.putExtra("set",set);
                                                                intent.putExtra("phy",phy);
                                                                intent.putExtra("chem",chem);
                                                                intent.putExtra("maths",maths);
                                                                intent.putExtra("bio",bio);
                                                                startActivity(intent);
                                                            }
                                                        });
                                                    }
                                               }
                                           }
        );

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}