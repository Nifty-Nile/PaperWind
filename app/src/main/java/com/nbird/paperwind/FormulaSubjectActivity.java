package com.nbird.paperwind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FormulaSubjectActivity extends AppCompatActivity {
    Button phybutton,chembutton,biobutton;
    int std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formula_subject);
        phybutton=(Button) findViewById(R.id.tipButton1);
        chembutton=(Button) findViewById(R.id.tipButton2);
        biobutton=(Button) findViewById(R.id.tipButton3);

        std=getIntent().getIntExtra("Std100",0);

        phybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),FormulaChRecyclerActivity.class);
                intent.putExtra("Subject100",1);
                intent.putExtra("Std100",std);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        chembutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),FormulaChRecyclerActivity.class);
                intent.putExtra("Subject100",2);
                intent.putExtra("Std100",std);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        biobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),FormulaChRecyclerActivity.class);
                intent.putExtra("Subject100",3);
                intent.putExtra("Std100",std);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
    }
}