package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

public class TestAgrementActivity extends AppCompatActivity {

    Button submitbutton;
    CheckBox checkBox;
    int phy,chem,maths,bio,position,mode,set,score;
    androidx.appcompat.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_agrement);

        submitbutton=(Button) findViewById(R.id.submitbutton);
        checkBox=(CheckBox) findViewById(R.id.checkbox);

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