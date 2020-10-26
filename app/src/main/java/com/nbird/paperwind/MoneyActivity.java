package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import Model.User;

public class MoneyActivity extends AppCompatActivity implements RewardedVideoAdListener, PaymentResultListener {
    RewardedVideoAd rewardedVideoAd;
    AlertDialog dialog;
    TextView moneytext, papernotestotal;
    Button Adbutton, buttonrs10, buttonrs20, buttonrs30, buttonrs50, buttonrs100,buttonrs200,buttonrs500;
    int value;
    FirebaseAuth fAuth;
    String amount;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("User");
    final DatabaseReference reference1 = database.getReference("User");
    final DatabaseReference reference2 = database.getReference("User");
    final int UPI_PAYMENT = 0;
    int indicator=0;


    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        fAuth = FirebaseAuth.getInstance();
        moneytext = (TextView) findViewById(R.id.dis1);

        papernotestotal = (TextView) findViewById(R.id.papernotestotal);
        Adbutton = (Button) findViewById(R.id.tipButton1);
        buttonrs10 = (Button) findViewById(R.id.tipButton2);
        buttonrs20 = (Button) findViewById(R.id.tipButton3);
        buttonrs30 = (Button) findViewById(R.id.tipButton4);
        buttonrs50 = (Button) findViewById(R.id.tipButton5);
        buttonrs100 = (Button) findViewById(R.id.tipButton6);
        buttonrs200 = (Button) findViewById(R.id.tipButton7);
        buttonrs500 = (Button) findViewById(R.id.tipButton8);

        Checkout.preload(getApplicationContext());

      /*  dialog = new AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Example Message")
                .setPositiveButton("Ok", null)
                .setNegativeButton("Cancel", null)
                .setCancelable(false)
                .show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (rewardedVideoAd.isLoaded()) {
                    rewardedVideoAd.show();
                }
            }
        });

        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MoneyActivity.super.onBackPressed();
            }
        });*/

        Adbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rewardedVideoAd.isLoaded()) {
                    rewardedVideoAd.show();
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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigatio);

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
                    Toast.makeText(MoneyActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
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

    public void onBackPressed() {
        super.onBackPressed();
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
            options.put("description", "Test Mode Dost");
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

    public void onPaymentSuccess(String s) {
        switch (indicator){
            case 1:
                Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
                value = value + 10;
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
                value = value + 25;
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
                value = value + 40;
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
                value = value + 75;
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
                value = value + 160;
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
                value = value + 400;
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
                value = value + 1200;
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


    public void onPaymentError(int i, String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}

