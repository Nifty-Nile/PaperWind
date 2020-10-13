package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PaperCategoryBoardsActivity extends AppCompatActivity {
    Button linkButton,previousyearpaperbutton,sampleyearpaper,NCERTSolutions;
    int Exam,Std,Paper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_category_boards);

        Exam=getIntent().getIntExtra("Exam",0);
        Std=getIntent().getIntExtra("Std",0);

        linkButton=(Button) findViewById(R.id.linkButton);
        previousyearpaperbutton=(Button) findViewById(R.id.previousyearpaper);
        sampleyearpaper=(Button) findViewById(R.id.samplepaperbutton);
        NCERTSolutions=(Button) findViewById(R.id.ncertSolutions);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigatio);



        previousyearpaperbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Exam == 1) {
                    if (Std == 1) {
                        Intent intent = new Intent(getBaseContext(), Sub_All_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 1);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getBaseContext(), Sub_All_Cbse12_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 1);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    }

                }
                if (Exam == 2) {
                    if (Std == 1) {
                        Intent intent = new Intent(getBaseContext(), All_sub_Cisce10_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 1);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getBaseContext(), All_sub_cisce12_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 1);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    }

                }
            }
        });
        sampleyearpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Exam == 1) {
                    if (Std == 1) {
                        Intent intent = new Intent(getBaseContext(), Sub_All_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 2);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getBaseContext(), Sub_All_Cbse12_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 2);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    }

                }
                if (Exam == 2) {
                    if (Std == 1) {
                        Intent intent = new Intent(getBaseContext(), All_sub_Cisce10_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 2);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getBaseContext(), All_sub_cisce12_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 2);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    }

                }
            }
        });
        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),LinkSubjects.class);
                intent.putExtra("Exam",Exam);
                intent.putExtra("Std",Std);
                intent.putExtra("Paper",3);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                startActivity(intent);
            }
        });
        NCERTSolutions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Exam == 1) {
                    if (Std == 1) {
                        Intent intent = new Intent(getBaseContext(), Sub_All_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 4);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getBaseContext(), Sub_All_Cbse12_Activity.class);
                        intent.putExtra("Exam", Exam);
                        intent.putExtra("Std", Std);
                        intent.putExtra("Paper", 4);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        startActivity(intent);
                    }

                }
            }
        });



        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        return true;

                    case R.id.rankpredictor:
                        startActivity(new Intent(getApplicationContext(),RankPredictorActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Formulas:
                        startActivity(new Intent(getApplicationContext(),FormulaSTDActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.money:
                        startActivity(new Intent(getApplicationContext(),MoneyActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

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
            Toast.makeText(this, "Money", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}