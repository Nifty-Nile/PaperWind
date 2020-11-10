package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EntranceRecyclerView extends AppCompatActivity {
    RecyclerView recyclerView;
    int position,mode;
    private List<TestRecyclerHolder> list;
    private Dialog loadingDialog;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    TextView textView3;
    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance_recycler_view);

        position=getIntent().getIntExtra("position",0);
        mode=getIntent().getIntExtra("mode",0);

        textView3=(TextView) findViewById(R.id.textView3);

        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Sets");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigatio);

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

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            textView3.setTextColor(Color.parseColor("#ffffff"));

        }else{
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);

            textView3.setTextColor(Color.parseColor("#000000"));


        }

        loadingDialog=new Dialog(EntranceRecyclerView.this);
        loadingDialog.setContentView(R.layout.activity_loading);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(true);
        loadingDialog.show();

        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        final TestRecyclerAdapter categoryAdapter = new TestRecyclerAdapter(list,position,mode);
        recyclerView.setAdapter(categoryAdapter);

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

        final SharedPreferences condition = getBaseContext().getSharedPreferences("Condition", 0);
        final SharedPreferences.Editor editorconditioin = condition.edit();

        final SharedPreferences notanswered1 = getBaseContext().getSharedPreferences("notanswered1", 0);
        final SharedPreferences.Editor editornotanswered1 = notanswered1.edit();

        final SharedPreferences notanswered2 = getBaseContext().getSharedPreferences("notanswered2", 0);
        final SharedPreferences.Editor editornotanswered2 = notanswered2.edit();

        final SharedPreferences notanswered3 = getBaseContext().getSharedPreferences("notanswered3", 0);
        final SharedPreferences.Editor editornotanswered3 = notanswered3.edit();

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

        final SharedPreferences minutes100 = getBaseContext().getSharedPreferences("minutes100", 0);
        final SharedPreferences.Editor editorminutes100 = minutes100.edit();

        final SharedPreferences seconds100 = getBaseContext().getSharedPreferences("seconds100", 0);
        final SharedPreferences.Editor editorseconds100 = seconds100.edit();


        editorminutes100.clear().apply();
        editorseconds100.clear().apply();
        editor.clear().apply();
        editor1.clear().apply();
        editor2.clear().apply();
        editorblue.clear().apply();
        editorred.clear().apply();
        editoryellow.clear().apply();
        editorreviewphy.clear().apply();
        editorreviewchem.clear().apply();
        editorreviewmaths.clear().apply();
        editorconditioin.clear().apply();
        editornotanswered1.clear().apply();
        editornotanswered2.clear().apply();
        editornotanswered3.clear().apply();
        editorset.clear().apply();
        editortimesaver.clear().apply();
        editorpausephy.clear().apply();
        editorpausechem.clear().apply();
        editorpausemaths.clear().apply();
        editorcounterstopper.clear().apply();

        myRef.child("EntranceExamTestRecyclerView").child(String.valueOf(position)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(TestRecyclerHolder.class));

                }
                categoryAdapter.notifyDataSetChanged();
                loadingDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EntranceRecyclerView.this,"Data Can't be Loaded", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                finish();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}