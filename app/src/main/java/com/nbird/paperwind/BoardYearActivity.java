package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BoardYearActivity extends AppCompatActivity {

    int position;
    Button previousyearbutton,onlinetestbutton;
    String answerA1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_year);

        previousyearbutton=(Button) findViewById(R.id.previousyearpaper);
        onlinetestbutton=(Button) findViewById(R.id.onlinetestbutton);
        final SharedPreferences set1 = getBaseContext().getSharedPreferences("set", 0);
        final SharedPreferences.Editor editorset = set1.edit();

        final SharedPreferences timesaver = getBaseContext().getSharedPreferences("timesaver123", 0);
        final SharedPreferences.Editor editortimesaver = timesaver.edit();

        final SharedPreferences pausephy = getBaseContext().getSharedPreferences("pausephy123", 0);
        final SharedPreferences.Editor editorpausephy = pausephy.edit();

        final SharedPreferences pausechem = getBaseContext().getSharedPreferences("pausechem123", 0);
        final SharedPreferences.Editor editorpausechem = pausechem.edit();

        final SharedPreferences pausemaths = getBaseContext().getSharedPreferences("pausemaths123", 0);
        final SharedPreferences.Editor editorpausemaths = pausemaths.edit();


        final SharedPreferences counterstopper = getBaseContext().getSharedPreferences("counterstopper123", 0);
        final SharedPreferences.Editor editorcounterstopper = counterstopper.edit();


        final Intent intent=getIntent();

        position=intent.getExtras().getInt("position",0);


        previousyearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),EntranceExamPreviousRecyclerActivity.class);
                intent.putExtra("mode",1);
                intent.putExtra("position",position);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        onlinetestbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final SharedPreferences minutes100 = getBaseContext().getSharedPreferences("minutes100", 0);
                final SharedPreferences.Editor editorminutes100 = minutes100.edit();

                final SharedPreferences seconds100 = getBaseContext().getSharedPreferences("seconds100", 0);
                final SharedPreferences.Editor editorseconds100 = seconds100.edit();

                editorminutes100.clear().commit();
                editorseconds100.clear().commit();

                    Intent intent=new Intent(getBaseContext(),EntranceRecyclerView.class);
                    intent.putExtra("mode",1);
                    intent.putExtra("position",position);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                    finish();







            }
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigatio);

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

}