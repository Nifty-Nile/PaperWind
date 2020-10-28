package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Menu1Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    List<Exam> lstExam;
    Button button1,button2,logout;
    int setter=0;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference1 = database.getReference("User");
    private Context mContext;
    FirebaseAuth fAuth;
    int value;
    //variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        //*************** Hooks **************

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        // ************** Tool bar ***************

        setSupportActionBar(toolbar);

        //************ Navigation Drawer Menu **************

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

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);



        button1=(Button) findViewById(R.id.tipButton1);
        button2=(Button) findViewById(R.id.tipButton);
        logout=(Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginFireBaseActivity.class));
                finish();
            }
        });

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
            Toast.makeText(this, "History Mode", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getBaseContext(),ExamRecordActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);


        }else if(id==R.id.propic){
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.coins){
            AlertDialog.Builder builder=new AlertDialog.Builder(Menu1Activity.this,R.style.AlertDialogTheme);
            View view1= LayoutInflater.from(Menu1Activity.this).inflate(R.layout.alert_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
            builder.setView(view1);
            ((TextView) view1.findViewById(R.id.textTitle)).setText("You have "+value+" Paper Notes");
            ((TextView) view1.findViewById(R.id.textMessage)).setText("fnef e fuefn eufn euf eeufn ");
            ((Button) view1.findViewById(R.id.buttonNo)).setText("OK");
            ((Button) view1.findViewById(R.id.buttonYes)).setText("Get Paper Notes!");
            ((ImageView) view1.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_timer_24);

            final AlertDialog alertDialog=builder.create();

            view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getBaseContext(),MoneyActivity.class);
                    startActivity(intent);
                    alertDialog.dismiss();
                    finish();
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

                }
            });
            view1.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();

                }
            });

            if(alertDialog.getWindow()!=null){
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            alertDialog.show();
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.nav_tos:
                    Intent browserIntenttos = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/paper-wind.appspot.com/o/PAPERWINDpolicyfiles%2FTERMSOFSERVICE-converted.pdf?alt=media&token=f4c2526d-285a-4594-9e43-bb1cf33b3916"));
                    startActivity(browserIntenttos);
                    break;

            case R.id.nav_ref:
                    Intent browserIntentref = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/paper-wind.appspot.com/o/PAPERWINDpolicyfiles%2FREFUNDPOLICY-converted.pdf?alt=media&token=e96d23ac-b165-4261-83a2-2cd0ccc291b0"));
                    startActivity(browserIntentref);
                    break;

            case R.id.nav_ps:
                    Intent browserIntentps = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/paper-wind.appspot.com/o/PAPERWINDpolicyfiles%2FPRIVACYSTATEMENT-converted.pdf?alt=media&token=174353a9-8814-4834-ba16-4db5c38fdfaf"));
                    startActivity(browserIntentps);
                    break;

            default :
                        return true;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen((GravityCompat.START)))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

}