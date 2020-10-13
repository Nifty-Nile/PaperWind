package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Menu1Activity extends AppCompatActivity {

    List<Exam> lstExam;
    Button button1,button2;
    int setter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        button1=(Button) findViewById(R.id.tipButton1);
        button2=(Button) findViewById(R.id.tipButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentnext=new Intent(getBaseContext(),Ten_Twelve_Activity.class);
                intentnext.putExtra("Exam",1);
                startActivity(intentnext);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentnext=new Intent(getBaseContext(),Ten_Twelve_Activity.class);
                intentnext.putExtra("Exam",2);
                startActivity(intentnext);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        lstExam=new ArrayList<>();
        parto();
        RecyclerView myrv=(RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewAdapter myAdapter=new RecyclerViewAdapter(this,lstExam,setter);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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



    @Override
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

    public void parto(){
        lstExam.add(new Exam("JEE Advanced",R.drawable.back2,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new Exam("JEE Main",R.drawable.back14,"Joint Entrance Examination – Main is a national level entrance exam conducted by NTA to offer admission to BE/BTech, BPlan, BArch courses at IIITs, NITs"));
        lstExam.add(new Exam("NEET",R.drawable.back2,"The National Eligibility cum Entrance Test (Undergraduate), formerly AIPMT, is an entrance examination for UG medical courses(MBBS) and dental courses(BDS) in India."));
        lstExam.add(new Exam("VITEEE",R.drawable.back1,"VITEEE is an annual entrance examination conducted by Vellore Institute of technology, a private university in Vellore, Tamil Nadu founded in 1984."));
        lstExam.add(new Exam("COMED-K",R.drawable.back2,"State level entrance examination by the Consortium of Medical, Engineering and Dental colleges of Karnataka for admissions to Engineerting and Architecture courses."));
        lstExam.add(new Exam("NDA",R.drawable.back14,"NDA exam is conducted by UPSC twice a year for admissions to Army, Navy, and Air Force wings of the prestigious National Defence Academy, Pune."));
        lstExam.add(new Exam("BITSAT",R.drawable.back2,"BITS Admission test is an online entrance examination for admissions to integrated first degree programmes of BITS Pilani, Goa and Hyderabad."));
        lstExam.add(new Exam("KVPY",R.drawable.back1,"Kishore Vaigyanik Protsahan Yojana is a scholarship program aimed at encouraging students to take up research careers in the areas of basic sciences by the Indian government."));
        lstExam.add(new Exam("MHCET",R.drawable.back2,"MHT CET (MH CET) or Maharashtra Common Entrance Test is conducted by the State Common Entrance Test Cell for admissions to BE/BTech and Pharmacy programmes (BPharma/PharmaD)."));
        lstExam.add(new Exam("SRMJEE",R.drawable.back14,"The SRM University conducts SRM Joint Engineering Entrance Examination (SRMJEEE) for granting admissions in undergraduate engineering courses. "));
        lstExam.add(new Exam("KCET",R.drawable.back2,"Karnataka Common Entrance Test is a state-level entrance exam conducted by Karnataka Examination Authority (KEA) organised to provide admission to different UG courses in Karnataka."));
        lstExam.add(new Exam("IPU-CET",R.drawable.back1,"Indraprastha University Common Entrance Test offers admission to the various UG and PG courses. The admission is done through Common Entrance Test (CET) or on the merit of the qualifying degrees."));
        lstExam.add(new Exam("MET",R.drawable.back2,"The Manipal Entrance Test (MET) for BTech admissions is a common entrance test for admission to Manipal Institute of Technology."));
        lstExam.add(new Exam("WBJEE",R.drawable.back14,"West Bengal Joint Entrance Examination is a state-government controlled centralised test for admission to many private and governmental engineering institutions in West Bengal."));
        lstExam.add(new Exam("JEECUP",R.drawable.back2,"Joint Entrance Examination Council Uttar Pradesh is a State-level Entrance Exam for admission into Diploma, PG Diploma and Post Diploma courses in Engineering & Technology, Pharmacy and Management."));
        lstExam.add(new Exam("NEST",R.drawable.back1,"The National Entrance Screening Test is an annual college entrance examination in India, conducted for admission into NISER, Bhubaneswar and UM-DAE CEBS, Mumbai."));
        lstExam.add(new Exam("PESSAT",R.drawable.back2,"PESSAT (Peoples Education Society Scholastic Aptitude Test) is conducted by PES University, Bangalore every year."));
        lstExam.add(new Exam("AMUEEE",R.drawable.back14,"Aligarh Muslim University Engineering Entrance Examination  is conducted once every year by Aligarh Muslim University for admissions into undergraduate engineering (B.E, B.Tech and B.Arch) courses."));
        lstExam.add(new Exam("IISER",R.drawable.back2,"IISER exam is conducted for admission to BS-MS degree program at one of the seven IISERs. Candidates can get admission through JEE Main, KVPY and SCB channel"));
        lstExam.add(new Exam("UPSEE",R.drawable.back1,"Uttar Pradesh State Entrance Exam is a state-level entrance examination organized for admission to colleges affiliated to Dr. APJ Abdul Kalam Technical University, Lucknow."));
        lstExam.add(new Exam("NIFT",R.drawable.back2,"National Institute of Fashion Technology conducts the NIFT entrance exam to shortlist aspirants for admission in design programmes offered by it."));

    }



}