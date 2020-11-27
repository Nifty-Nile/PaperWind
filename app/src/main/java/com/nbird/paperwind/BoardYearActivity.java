package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BoardYearActivity extends AppCompatActivity {

    int position;
    Button previousyearbutton,onlinetestbutton;
    String answerA1000;
    TextView text100,text200,text300,text400,Text4;
    androidx.appcompat.widget.Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    FirebaseAuth fAuth;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference1 = database.getReference("User");
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_year);

        previousyearbutton=(Button) findViewById(R.id.previousyearpaper);
        onlinetestbutton=(Button) findViewById(R.id.onlinetestbutton);

        text100=(TextView) findViewById(R.id.text100);
        text200=(TextView) findViewById(R.id.text200);
        text300=(TextView) findViewById(R.id.text300);
        text400=(TextView) findViewById(R.id.text400);
        Text4=(TextView) findViewById(R.id.Text4);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Category");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            text100.setTextColor(Color.parseColor("#ffffff"));
            text200.setTextColor(Color.parseColor("#ffffff"));
            text300.setTextColor(Color.parseColor("#ffffff"));
            text400.setTextColor(Color.parseColor("#ffffff"));
            Text4.setTextColor(Color.parseColor("#ffffff"));

        }else{
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);

            text100.setTextColor(Color.parseColor("#000000"));
            text200.setTextColor(Color.parseColor("#000000"));
            text300.setTextColor(Color.parseColor("#000000"));
            text400.setTextColor(Color.parseColor("#000000"));
            Text4.setTextColor(Color.parseColor("#000000"));

        }



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
                    intent.putExtra("value",value);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                    finish();







            }
        });

        bottomNavigationView=findViewById(R.id.bottomnavigatio);

        bottomNavigationView.setSelectedItemId(R.id.home);


        // **************** Bottom navigation View **********************

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
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
            bottomNavigationView.setSelectedItemId(R.id.home);
        }


        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        bottomNavigationView.setSelectedItemId(R.id.home);
        BoardYearActivity.super.onBackPressed();
        finish();

    }

}