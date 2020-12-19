package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Model.PropicString;
import Model.User;
import de.hdodenhof.circleimageview.CircleImageView;

public class Menu1Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    List<Exam> lstExam;
    Button button1,button2,logout;
    int setter=0;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference1 = database.getReference("User");
    final DatabaseReference reference2 = database.getReference();
    private Context mContext;
    FirebaseAuth fAuth;
    int value;
    String randomuid;
    //variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    ActionBarDrawerToggle mToggle;
    CircleImageView profileImage;
    private static final int PICK_IMAGE =1;
    Uri imageUri;
    CircleImageView nav_image;
    TextView text1,text2,text3;
    String linkdata,mailid123;
    FirebaseStorage storage;
    StorageReference storageReference;
    String imageurl,picurl;
    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        //*************** Hooks **************
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        mToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        button1=(Button) findViewById(R.id.tipButton1);
        button2=(Button) findViewById(R.id.tipButton);
        text1=(TextView) findViewById(R.id.textView5);
        text2=(TextView) findViewById(R.id.cbsedis);
        text3=(TextView) findViewById(R.id.icsedis);

        final SharedPreferences mailreminder = this.getSharedPreferences("mailreminder123", 0);
        final SharedPreferences.Editor editormailreminder = mailreminder.edit();

        SharedPreferences propicurl = this.getSharedPreferences("propicurl123",0);
        final SharedPreferences.Editor editorpropicurl = mailreminder.edit();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.mailidtext);
        mailid123 = mailreminder.getString("123", "abc@gmail.com");
        nav_user.setText(mailid123);
        nav_image = (CircleImageView) hView.findViewById(R.id.proimage);



        fAuth = FirebaseAuth.getInstance();

            reference2.child("User").child(fAuth.getCurrentUser().getUid()).child("personal").child("propic").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // convert the data back to the model
                    imageurl = (String) dataSnapshot.getValue();
                    editorpropicurl.putString("12345", imageurl);
                    editorpropicurl.commit();
                    try{
                        Glide.with(getBaseContext()).load(imageurl).into(nav_image);
                    }catch (Exception e){

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });









        nav_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(Menu1Activity.this);
            }
        });



        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);







        if(answerA0){
            ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            text1.setTextColor(Color.parseColor("#ffffff"));
            text2.setTextColor(Color.parseColor("#ffffff"));
            text3.setTextColor(Color.parseColor("#ffffff"));

        }else{
            ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);


            text1.setTextColor(Color.parseColor("#000000"));
            text2.setTextColor(Color.parseColor("#000000"));
            text3.setTextColor(Color.parseColor("#000000"));

        }

        // ************** Tool bar ***************
        setSupportActionBar(toolbar);






        reference1.child(fAuth.getCurrentUser().getUid()).child("personal").child("money").addValueEventListener(new ValueEventListener() {
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


        //************ Navigation Drawer Menu **************

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        reference2.child("Link").child("linkdata").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 linkdata = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // ******************************


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


        // ************ Exam list Recycler View **************

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


        // **************** Bottom navigation View **********************

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.rankpredictor:
                        startActivity(new Intent(getBaseContext(),RankPredictorActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Formulas:
                        startActivity(new Intent(getBaseContext(),FormulaSTDActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.money:
                        startActivity(new Intent(getBaseContext(),MoneyActivity.class));
                        finish();
                        overridePendingTransition(0,0);

                        return true;
                }
                return false;
            }
        });

    }



    // **************** 3 Dots menu (Top right)*******************

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.share){
            Toast.makeText(this, "Share Me!", Toast.LENGTH_SHORT).show();
            Intent shareIntent=new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plane");
            String shareBody="Download Paper Wind now: "+linkdata;
            String sharesub="Paper Wind";

            shareIntent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
            shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(shareIntent,"Share Using"));
        }else if(id==R.id.about){
           
            //Light and dark mode
             SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
             SharedPreferences.Editor editorlightanddark = lightanddark.edit();

            Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);
            
            if(!answerA0){
                editorlightanddark.putBoolean(String.valueOf(1), true);
                editorlightanddark.commit();
                Toast.makeText(this, "Dark Mode", Toast.LENGTH_SHORT).show();
                ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.mainfield);
                layout.setBackgroundResource(R.drawable.backdarkmode);
                text1.setTextColor(Color.parseColor("#ffffff"));
                text2.setTextColor(Color.parseColor("#ffffff"));
                text3.setTextColor(Color.parseColor("#ffffff"));

            }else{
                editorlightanddark.putBoolean(String.valueOf(1), false);
                editorlightanddark.commit();
                Toast.makeText(this, "Lite Mode", Toast.LENGTH_SHORT).show();
                ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.mainfield);
                layout.setBackgroundResource(R.drawable.background1);


                text1.setTextColor(Color.parseColor("#000000"));
                text2.setTextColor(Color.parseColor("#000000"));
                text3.setTextColor(Color.parseColor("#000000"));
            }
        }else if(id==R.id.history){
            Toast.makeText(this, "History Mode", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getBaseContext(),ExamRecordActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }else if(id==R.id.propic){
            Intent intent=new Intent(this,LatestExamUpdates.class);
            startActivity(intent);
        }
        else if(id==R.id.coins)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(Menu1Activity.this,R.style.AlertDialogTheme);
            View view1= LayoutInflater.from(Menu1Activity.this).inflate(R.layout.alert_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
            builder.setView(view1);
            ((TextView) view1.findViewById(R.id.textTitle)).setText("You have "+value+" Paper Notes");
            ((TextView) view1.findViewById(R.id.textMessage)).setText("Success is never owned, its rented and the rent is due everyday!");
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
        else if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }


    // ************************** Exam List **************************

    public void parto(){
        lstExam.add(new Exam("JEE Advanced",R.drawable.jeeadvanced,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new Exam("JEE Main",R.drawable.jeemain,"Joint Entrance Examination – Main is a national level entrance exam conducted by NTA to offer admission to BE/BTech, BPlan, BArch courses at IIITs, NITs"));
        lstExam.add(new Exam("NEET",R.drawable.neet,"The National Eligibility cum Entrance Test (Undergraduate), formerly AIPMT, is an entrance examination for UG medical courses(MBBS) and dental courses(BDS) in India."));
        lstExam.add(new Exam("VITEEE",R.drawable.viteee,"VITEEE is an annual entrance examination conducted by Vellore Institute of technology, a private university in Vellore, Tamil Nadu founded in 1984."));
        lstExam.add(new Exam("COMED-K",R.drawable.comedk,"State level entrance examination by the Consortium of Medical, Engineering and Dental colleges of Karnataka for admissions to Engineerting and Architecture courses."));
        lstExam.add(new Exam("NDA",R.drawable.nda,"NDA exam is conducted by UPSC twice a year for admissions to Army, Navy, and Air Force wings of the prestigious National Defence Academy, Pune."));
        lstExam.add(new Exam("BITSAT",R.drawable.bitsat,"BITS Admission test is an online entrance examination for admissions to integrated first degree programmes of BITS Pilani, Goa and Hyderabad."));
        lstExam.add(new Exam("KVPY",R.drawable.kvpy,"Kishore Vaigyanik Protsahan Yojana is a scholarship program aimed at encouraging students to take up research careers in the areas of basic sciences by the Indian government."));
        lstExam.add(new Exam("MHCET",R.drawable.mhtcet,"MHT CET (MH CET) or Maharashtra Common Entrance Test is conducted by the State Common Entrance Test Cell for admissions to BE/BTech and Pharmacy programmes (BPharma/PharmaD)."));
        lstExam.add(new Exam("SRMJEE",R.drawable.srmjee1,"The SRM University conducts SRM Joint Engineering Entrance Examination (SRMJEEE) for granting admissions in undergraduate engineering courses. "));
        lstExam.add(new Exam("KCET",R.drawable.kcet,"Karnataka Common Entrance Test is a state-level entrance exam conducted by Karnataka Examination Authority (KEA) organised to provide admission to different UG courses in Karnataka."));
        lstExam.add(new Exam("IPU-CET",R.drawable.ipucet,"Indraprastha University Common Entrance Test offers admission to the various UG and PG courses. The admission is done through Common Entrance Test (CET) or on the merit of the qualifying degrees."));
        lstExam.add(new Exam("MET",R.drawable.met,"The Manipal Entrance Test (MET) for BTech admissions is a common entrance test for admission to Manipal Institute of Technology."));
        lstExam.add(new Exam("WBJEE",R.drawable.wbjee,"West Bengal Joint Entrance Examination is a state-government controlled centralised test for admission to many private and governmental engineering institutions in West Bengal."));
        lstExam.add(new Exam("JEECUP",R.drawable.jeec,"Joint Entrance Examination Council Uttar Pradesh is a State-level Entrance Exam for admission into Diploma, PG Diploma and Post Diploma courses in Engineering & Technology, Pharmacy and Management."));
        lstExam.add(new Exam("NEST",R.drawable.nest,"The National Entrance Screening Test is an annual college entrance examination in India, conducted for admission into NISER, Bhubaneswar and UM-DAE CEBS, Mumbai."));
        lstExam.add(new Exam("PESSAT",R.drawable.pessat,"PESSAT (Peoples Education Society Scholastic Aptitude Test) is conducted by PES University, Bangalore every year."));
        lstExam.add(new Exam("AMUEEE",R.drawable.amuee,"Aligarh Muslim University Engineering Entrance Examination  is conducted once every year by Aligarh Muslim University for admissions into undergraduate engineering (B.E, B.Tech and B.Arch) courses."));
        lstExam.add(new Exam("IISER",R.drawable.iiser,"IISER exam is conducted for admission to BS-MS degree program at one of the seven IISERs. Candidates can get admission through JEE Main, KVPY and SCB channel"));
        lstExam.add(new Exam("UPSEE",R.drawable.upsee,"Uttar Pradesh State Entrance Exam is a state-level entrance examination organized for admission to colleges affiliated to Dr. APJ Abdul Kalam Technical University, Lucknow."));
        lstExam.add(new Exam("NIFT",R.drawable.nift,"National Institute of Fashion Technology conducts the NIFT entrance exam to shortlist aspirants for admission in design programmes offered by it."));

    }


    // ************** Navigation Drawer switch ********************

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.nav_logout:
                fAuth = FirebaseAuth.getInstance();

                AlertDialog.Builder builder=new AlertDialog.Builder(Menu1Activity.this,R.style.AlertDialogTheme);
                View view1= LayoutInflater.from(Menu1Activity.this).inflate(R.layout.alert_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                builder.setView(view1);
                ((TextView) view1.findViewById(R.id.textTitle)).setText("You really want to Logout?");
                ((TextView) view1.findViewById(R.id.textMessage)).setText("Your all data is Saved and are Safe!");
                ((Button) view1.findViewById(R.id.buttonNo)).setText("No");
                ((Button) view1.findViewById(R.id.buttonYes)).setText("Yes,Logout");
                ((ImageView) view1.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_timer_24);

                final AlertDialog alertDialog=builder.create();

                view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(),LoginFireBaseActivity.class));
                        finish();

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
                break;


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

            case R.id.nav_order:
                Intent intent=new Intent(getBaseContext(),PaymentHistoryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;

            case R.id.nav_help:
                Intent intenthelp=new Intent(getBaseContext(),HelpActivity.class);
                startActivity(intenthelp);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;

            case R.id.nav_about:
                Intent intentabout=new Intent(getBaseContext(),AboutUsActivity.class);
                startActivity(intentabout);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;

            case R.id.nav_rate:
                Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(linkdata));
                startActivity(browserIntent);
                break;

            case R.id.nav_contact:
                String[] TO = {"niftynile@gmail.com"};

                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("*/*");
                email.putExtra(Intent.EXTRA_EMAIL, TO);
                if(email.resolveActivity(getPackageManager()) != null)
                    startActivity(email);
                break;

            default :
                        return true;
        }
        return true;
    }



    // ************************ Nav Drawer Slide conditions ***********************

    @Override  //Back button closes drawer
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen((GravityCompat.START)))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        nav_image.setImageBitmap(selectedImage);
                        uploadImage();
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK) {
                        try {
                            imageUri = data.getData();
                            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            nav_image.setImageBitmap(selectedImage);
                            uploadImage();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(Menu1Activity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                        }

                    }else {
                        Toast.makeText(Menu1Activity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    }

    private void uploadImage()
    {
        if (imageUri != null) {

            // Code for showing progressDialog while uploading
            final ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();

            final SharedPreferences mailreminder = this.getSharedPreferences("mailreminder123", 0);
            final SharedPreferences.Editor editormailreminder = mailreminder.edit();


            mailid123 = mailreminder.getString("123", "abc@gmail.com");
            randomuid=UUID.randomUUID().toString();

            ref = storageReference.child("images/" + mailid123+"/"+randomuid);



            // adding listeners on upload
            // or failure of image
            ref.putFile(imageUri)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast.makeText(Menu1Activity.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                                    try{
                                        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                                        StorageReference urlref = storageRef.child("images/" + mailid123+"/"+randomuid);
                                        urlref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                                        {
                                            @Override
                                            public void onSuccess(Uri downloadUrl)
                                            {
                                                imageurl=downloadUrl.toString();

                                                reference1.child(fAuth.getCurrentUser().getUid()).child("personal").child("propic").setValue(imageurl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){

                                                        }else{

                                                        }
                                                    }
                                                });
                                            }
                                        });

                                    }catch (Exception e){

                                    }
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(Menu1Activity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }

}