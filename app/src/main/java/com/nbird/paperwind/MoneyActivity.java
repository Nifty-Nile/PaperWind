package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import java.security.PublicKey;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.User;

import java.util.Calendar;



public class MoneyActivity extends AppCompatActivity implements RewardedVideoAdListener, PaymentResultWithDataListener {
    RewardedVideoAd rewardedVideoAd;
    AlertDialog dialog;
    TextView moneytext, papernotestotal,invisibletimer,textView13,textView14,text1;
    Button Adbutton, buttonrs10, buttonrs20, buttonrs30, buttonrs50, buttonrs100,buttonrs200,buttonrs500;
    int value,triggerinteger,timer9int;
    int numberoftimesvideoplayedin15min;
    FirebaseAuth fAuth;
    androidx.appcompat.widget.Toolbar toolbar;
    String amount;
    long timermanupulationmin,timermanupulationsec;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("User");
    final DatabaseReference reference1 = database.getReference("User");
    final DatabaseReference reference2 = database.getReference("User");

    final int UPI_PAYMENT = 0;
    int indicator=0;
    CountDownTimer countdowntimer;
    Button nobutton,yesbutton;

    String paymentId;
    String mail;
    String number;
    String moneyamount;

    Date currentTime;
    String intcurrentTime;

    BottomNavigationView bottomNavigationView;


    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        fAuth = FirebaseAuth.getInstance();


        moneytext = (TextView) findViewById(R.id.dis1);

        papernotestotal = (TextView) findViewById(R.id.papernotestotal);
        invisibletimer = (TextView) findViewById(R.id.invisibletimer);
        textView13 = (TextView) findViewById(R.id.textView13);
        textView14 = (TextView) findViewById(R.id.textView14);
        text1 = (TextView) findViewById(R.id.text1);
        Adbutton = (Button) findViewById(R.id.tipButton1);
        buttonrs10 = (Button) findViewById(R.id.tipButton2);
        buttonrs20 = (Button) findViewById(R.id.tipButton3);
        buttonrs30 = (Button) findViewById(R.id.tipButton4);
        buttonrs50 = (Button) findViewById(R.id.tipButton5);
        buttonrs100 = (Button) findViewById(R.id.tipButton6);
        buttonrs200 = (Button) findViewById(R.id.tipButton7);
        buttonrs500 = (Button) findViewById(R.id.tipButton8);
        yesbutton = (Button) findViewById(R.id.buttonYes);
        nobutton = (Button) findViewById(R.id.buttonNo);





        Checkout.preload(getApplicationContext());

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Subjects");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            papernotestotal.setTextColor(Color.parseColor("#ffffff"));
            text1.setTextColor(Color.parseColor("#ffffff"));
            textView14.setTextColor(Color.parseColor("#ffffff"));
            textView13.setTextColor(Color.parseColor("#ffffff"));


        }else{
            ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);


            papernotestotal.setTextColor(Color.parseColor("#000000"));
            text1.setTextColor(Color.parseColor("#000000"));
            textView14.setTextColor(Color.parseColor("#000000"));
            textView13.setTextColor(Color.parseColor("#000000"));

        }


        Adbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(numberoftimesvideoplayedin15min<2){

                    if (rewardedVideoAd.isLoaded()) {
                        numberoftimesvideoplayedin15min++;
                        rewardedVideoAd.show();
                    }
                }
                else{ //      triggerinteger = triggermanual.getInt(String.valueOf(1), 0);
                    if(triggerinteger==0){
                        timer();
                    }
                    triggerinteger=1;

                    String lion = invisibletimer.getText().toString();

                    AlertDialog.Builder builder=new AlertDialog.Builder(MoneyActivity.this,R.style.AlertDialogTheme);
                    View view1= LayoutInflater.from(MoneyActivity.this).inflate(R.layout.alert_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                   ((TextView) view1.findViewById(R.id.textTitle)).setText("Come After "+lion+" min");
                   ((TextView) view1.findViewById(R.id.textMessage)).setText("Come After "+lion+" min to use this facility.Please!");
                   ((Button) view1.findViewById(R.id.buttonNo)).setText("Cancel");
                   ((Button) view1.findViewById(R.id.buttonYes)).setText("Urgent? Pay Rs 10");
                   ((ImageView) view1.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_timer_24);

                   final AlertDialog alertDialog=builder.create();

                   view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           indicator = 1;
                           startPayment();
                           alertDialog.dismiss();
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

            }
        });

        MobileAds.initialize(this, getString(R.string.APP_ID));
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();


        reference1.child(fAuth.getCurrentUser().getUid()).child("money").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // convert the data back to the model
                value = dataSnapshot.getValue(Integer.class);
                papernotestotal.setText("Paper Notes: " + String.valueOf(value));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        buttonrs10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicator = 1;
                startPayment();
            }
        });

        buttonrs20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicator = 2;
                startPayment();
            }
        });

        buttonrs30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicator = 3;
                startPayment();
            }
        });

        buttonrs50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicator = 4;
                startPayment();

            }
        });

        buttonrs100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicator = 5;
                startPayment();
            }
        });
        buttonrs200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicator = 6;
                startPayment();
            }
        });
        buttonrs500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicator = 7;
                startPayment();
            }
        });




      /*  reference.child(fAuth.getCurrentUser().getUid()).setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MoneyActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MoneyActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                }
            }
        });*/

        bottomNavigationView = findViewById(R.id.bottomnavigatio);
        bottomNavigationView.setSelectedItemId(R.id.money);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Menu1Activity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.rankpredictor:
                        startActivity(new Intent(getApplicationContext(), RankPredictorActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Formulas:
                        startActivity(new Intent(getApplicationContext(), FormulaSTDActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.money:
                        return true;
                }
                return false;
            }
        });
    }


    public void loadRewardedVideoAd() {
        rewardedVideoAd.loadAd(getString(R.string.ADMOB_UNIT_ID),
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewardedVideoAdLoaded() {
    }

    @Override
    public void onRewardedVideoAdOpened() {
    }

    @Override
    public void onRewardedVideoStarted() {
    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
        dialog.dismiss();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        value = value + 1;
        papernotestotal.setText("Paper Notes: " + String.valueOf(value));
        reference.child(fAuth.getCurrentUser().getUid()).child("money").setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                } else {
                    Toast.makeText(MoneyActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
    }

    @Override
    public void onRewardedVideoCompleted() {
    }

    @Override
    protected void onResume() {
        rewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        rewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        rewardedVideoAd.destroy(this);
        super.onDestroy();
    }






    public void startPayment() {

        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Paper Wind");
            options.put("description", "Get Paper Notes");
            options.put("image", "https://drive.google.com/file/d/1WH96VlHZORi5C9CQjvnmdkAyp6CQelOt/view?usp=sharing");
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");




            switch (indicator){
                case 1:
                    options.put("amount", "1000");break;
                case 2:
                    options.put("amount", "2000");break;
                case 3:
                    options.put("amount", "3000");break;
                case 4:
                    options.put("amount", "5000");break;
                case 5:
                    options.put("amount", "10000");break;
                case 6:
                    options.put("amount", "20000");break;
                case 7:
                    options.put("amount", "50000");break;

            }

            checkout.open(activity, options);
        } catch(Exception e) {
            Toast.makeText(activity, "Error in starting Razorpay Checkout", Toast.LENGTH_SHORT).show();
        }
    }

    public void onPaymentSuccess(String s,PaymentData paymentData) {

        paymentId = paymentData.getPaymentId();
        mail=paymentData.getUserEmail();
        number=paymentData.getUserContact();

        Log.i("Paymentid",paymentId);
        Log.i("mail",mail);
        Log.i("number",number);

        currentTime = Calendar.getInstance().getTime();
        intcurrentTime=String.valueOf(currentTime);



        switch (indicator){
            case 1:
                moneyamount="10";break;
            case 2:
                moneyamount="20";break;
            case 3:
                moneyamount="30";break;
            case 4:
                moneyamount="50";break;
            case 5:
                moneyamount="100";break;
            case 6:
                moneyamount="200";break;
            case 7:
                moneyamount="500";break;
        }
        fAuth = FirebaseAuth.getInstance();
        final DatabaseReference myref = database.getReference().child("User").child(fAuth.getCurrentUser().getUid()).child("PaymentReceipt").push();


        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                PaymentReceiptHolder user= new PaymentReceiptHolder(paymentId,moneyamount,mail,number,intcurrentTime);
                myref.setValue(user);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        switch (indicator){
            case 1:
                Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
                value = value + 20;
                papernotestotal.setText("Paper Notes: " + String.valueOf(value));
                reference.child(fAuth.getCurrentUser().getUid()).child("money").setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MoneyActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MoneyActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                        }
                    }
                }); break;
            case 2:
                Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
                value = value + 50;
                papernotestotal.setText("Paper Notes: " + String.valueOf(value));
                reference.child(fAuth.getCurrentUser().getUid()).child("money").setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MoneyActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MoneyActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                        }
                    }
                });break;
            case 3:
                Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
                value = value + 80;
                papernotestotal.setText("Paper Notes: " + String.valueOf(value));
                reference.child(fAuth.getCurrentUser().getUid()).child("money").setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MoneyActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MoneyActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                        }
                    }
                });break;
            case 4:
                Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
                value = value + 150;
                papernotestotal.setText("Paper Notes: " + String.valueOf(value));
                reference.child(fAuth.getCurrentUser().getUid()).child("money").setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MoneyActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MoneyActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                        }
                    }
                });break;
            case 5:
                Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
                value = value + 320;
                papernotestotal.setText("Paper Notes: " + String.valueOf(value));
                reference.child(fAuth.getCurrentUser().getUid()).child("money").setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MoneyActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MoneyActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                        }
                    }
                });break;
            case 6:
                Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
                value = value + 800;
                papernotestotal.setText("Paper Notes: " + String.valueOf(value));
                reference.child(fAuth.getCurrentUser().getUid()).child("money").setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MoneyActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MoneyActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                        }
                    }
                });break;
            case 7:
                Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
                value = value + 2400;
                papernotestotal.setText("Paper Notes: " + String.valueOf(value));
                reference.child(fAuth.getCurrentUser().getUid()).child("money").setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MoneyActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MoneyActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                        }
                    }
                });break;
        }

    }


    public void onPaymentError(int i, String s,PaymentData paymentData) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void timer(){
        countdowntimer=new CountDownTimer(1000*120, 1000) {
            public void onTick(long millisUntilFinished) {
                timermanupulationsec= millisUntilFinished/1000;
                timermanupulationmin=(timermanupulationsec/60)+(1);
                String goat=String.valueOf(timermanupulationmin);
                invisibletimer.setText(goat);


            }

            public void onFinish() {



                numberoftimesvideoplayedin15min=0;
                   triggerinteger=0;

            }

        }.start();

    }
    public void rs10function(){
        buttonrs10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicator = 1;
                startPayment();
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
        MoneyActivity.super.onBackPressed();
        finish();

    }


}

