package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Recycler_Exam_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<Recycler_Exam_Holder> list;
    private Dialog loadingDialog;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    int Exam,Std,Paper,Chapter;
    TextView text0,nodatatext;
    androidx.appcompat.widget.Toolbar toolbar;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__exam_);
        loadAds();
        Exam=getIntent().getIntExtra("Exam",0);
        Std=getIntent().getIntExtra("Std",0);
        Paper=getIntent().getIntExtra("Paper",0);
        Chapter=getIntent().getIntExtra("Chapter",0);

        text0=(TextView) findViewById(R.id.text0);
        nodatatext=(TextView) findViewById(R.id.nodatatext);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Papers List");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);


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

        if(answerA0){
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            text0.setTextColor(Color.parseColor("#ffffff"));
            nodatatext.setTextColor(Color.parseColor("#ffffff"));



        }else{
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);


            text0.setTextColor(Color.parseColor("#000000"));
            nodatatext.setTextColor(Color.parseColor("#000000"));



        }


        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.activity_loading);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(true);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        loadingDialog.show();

        final Recycler_Exam_Adapter categoryAdapter = new Recycler_Exam_Adapter(list,Exam,Std,Paper,Chapter,mInterstitialAd);
        recyclerView.setAdapter(categoryAdapter);


        myRef.child("RecycleExam").child("SchoolExam").child(String.valueOf(Exam)).child(String.valueOf(Std)).child(String.valueOf(Paper)).child(String.valueOf(Chapter)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(Recycler_Exam_Holder.class));

                }


                categoryAdapter.notifyDataSetChanged();
                loadingDialog.dismiss();

                if(list.isEmpty()){
                    nodatatext.setAlpha(1.0f);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Recycler_Exam_Activity.this,"Data Can't be Loaded", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                finish();
            }
        });

    }




    private void loadAds(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitialAd_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}