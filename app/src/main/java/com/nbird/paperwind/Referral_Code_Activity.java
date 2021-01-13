package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Referral_Code_Activity extends AppCompatActivity {

    Button refcopybutton;
    TextView reftext,heading,discription,textview20,list;
    ClipboardManager myClipboard;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference2 = database.getReference();
    FirebaseAuth fAuth;
    String value;
    private Dialog loadingDialog;
    RecyclerView recyclerView;
    List<RefHolder> reflist;
    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral__code_);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Referral Code Activity");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        refcopybutton=(Button) findViewById(R.id.clipcopybutton);
        reftext= (TextView) findViewById(R.id.reftext);
        heading= (TextView) findViewById(R.id.heading);
        discription= (TextView) findViewById(R.id.discription);
        textview20= (TextView) findViewById(R.id.textView20);
        list= (TextView) findViewById(R.id.list);


        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            heading.setTextColor(Color.parseColor("#ffffff"));
            discription.setTextColor(Color.parseColor("#ffffff"));
            textview20.setTextColor(Color.parseColor("#ffffff"));
            list.setTextColor(Color.parseColor("#ffffff"));



        }else{
            ConstraintLayout layout =(ConstraintLayout) findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);

            heading.setTextColor(Color.parseColor("#000000"));
            discription.setTextColor(Color.parseColor("#000000"));
            textview20.setTextColor(Color.parseColor("#000000"));
            list.setTextColor(Color.parseColor("#000000"));


        }

        fAuth = FirebaseAuth.getInstance();
        loadingDialog=new Dialog(Referral_Code_Activity.this);
        loadingDialog.setContentView(R.layout.activity_loading);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(true);
        loadingDialog.show();
        reference2.child("User").child(fAuth.getCurrentUser().getUid()).child("personal").child("sharecode").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // convert the data back to the model
                    value = dataSnapshot.getValue(String.class);
                reftext.setText(value);
               loadingDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        reflist=new ArrayList<>();

        final RefAdapter categoryAdapter = new RefAdapter(reflist);
        recyclerView.setAdapter(categoryAdapter);

        reference2.child("User").child(fAuth.getCurrentUser().getUid()).child("yourrefcodeuser").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    reflist.add(dataSnapshot1.getValue(RefHolder.class));

                }
                categoryAdapter.notifyDataSetChanged();
                loadingDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Referral_Code_Activity.this,"Data Can't be Loaded", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                finish();
            }
        });


        refcopybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);


                ClipData myClip = ClipData.newPlainText("text", value);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();

            }
        });

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        Referral_Code_Activity.super.onBackPressed();
        finish();

    }
}