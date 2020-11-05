package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Pdf_Display_Activity extends AppCompatActivity {
    private PDFView pdfView;
    private TextView text1;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    int Exam,Std,Paper,Chapter,set;
    private Dialog loadingDialog;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf__display_);

        pdfView=(PDFView) findViewById(R.id.pdfview);
        text1=(TextView) findViewById(R.id.text1);

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.activity_loading);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(true);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("PDF");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Exam=getIntent().getIntExtra("Exam",0);
        Std=getIntent().getIntExtra("Std",0);
        Paper=getIntent().getIntExtra("Paper",0);
        Chapter=getIntent().getIntExtra("Chapter",0);
        set=getIntent().getIntExtra("set",0);

        loadingDialog.show();

        myRef.child("PDF").child("SchoolExam").child(String.valueOf(Exam)).child(String.valueOf(Std)).child(String.valueOf(Paper)).child(String.valueOf(Chapter)).orderByChild("setno").equalTo(set).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = "";

                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    value = snapshot1.child("url").getValue(String.class);
                }
                text1.setText(value);
                Toast.makeText(Pdf_Display_Activity.this, "Loading.....Please Wait", Toast.LENGTH_LONG).show();
                String url=text1.getText().toString();
                new RetrivePdfStream().execute(url);
                loadingDialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Pdf_Display_Activity.this, "Failed To Load", Toast.LENGTH_LONG).show();
                loadingDialog.dismiss();
                finish();
            }
        });}

        class RetrivePdfStream extends AsyncTask<String,Void, InputStream>{

            @Override
            protected InputStream doInBackground(String... strings) {
                InputStream inputStream=null;
                try {
                    URL url=new URL(strings[0]);
                    HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                    if(urlConnection.getResponseCode()==200){
                        inputStream=new BufferedInputStream(urlConnection.getInputStream());
                    }
                }catch (IOException e){
                    return null;
                }
                return inputStream;
            }
            protected void onPostExecute(InputStream inputStream){
                pdfView.fromStream(inputStream).load();
            }
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    }
