package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CollegePredictorMainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private Dialog loadingDialog;
    private List<CollegePredictorMainHolder> list;
    int SelectedEntranceExam,InputText,Rank,Gender,Cast,Branch,PlatinumNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_predictor_main);

        SelectedEntranceExam=getIntent().getIntExtra("RankEE",0);
        InputText=getIntent().getIntExtra("InputPredictor",0);
        Rank=getIntent().getIntExtra("Rank1",0);
        Gender=getIntent().getIntExtra("Gender",0);
        Cast=getIntent().getIntExtra("cast",0);
        Branch=getIntent().getIntExtra("Branch",0);
        InputText=1;
        PlatinumNumber=1;



        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.activity_loading);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();


        final CollegePredictorMainAdapter categoryAdapter = new CollegePredictorMainAdapter(list);
        recyclerView.setAdapter(categoryAdapter);

        loadingDialog.show();

        myRef.child("CollegePredictor").child(String.valueOf(SelectedEntranceExam)).child(String.valueOf(Cast)).child(String.valueOf(Gender)).child(String.valueOf(PlatinumNumber)).orderByChild("branch").equalTo(Branch).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(CollegePredictorMainHolder.class));

                }
                categoryAdapter.notifyDataSetChanged();
                loadingDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CollegePredictorMainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                finish();
            }
        });
    }
}