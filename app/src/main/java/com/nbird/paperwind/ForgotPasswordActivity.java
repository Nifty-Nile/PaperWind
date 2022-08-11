package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

import Model.User;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextInputEditText mailid;
    Button getmail,signup,movesignup,movesign11;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mailid=(TextInputEditText) findViewById(R.id.mail);
        getmail=(Button) findViewById(R.id.getpassword);
        signup=(Button) findViewById(R.id.movesignup);
        movesignup=(Button) findViewById(R.id.movesignup);
        movesign11=(Button) findViewById(R.id.movesign111);

        mAuth=FirebaseAuth.getInstance();


        getmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  String usereEmail=mailid.getText().toString();
                  if(TextUtils.isEmpty(usereEmail)){
                      Toast.makeText(getBaseContext(), "Please write a valid Email Id", Toast.LENGTH_LONG).show();
                  }else{
                      mAuth.sendPasswordResetEmail(usereEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                          @Override
                          public void onComplete(@NonNull Task<Void> task) {
                              if(task.isSuccessful()){
                                  Toast.makeText(getBaseContext(), "Please Check Your Email and Spam folder", Toast.LENGTH_LONG).show();
                                  startActivity(new Intent(getBaseContext(),LoginFireBaseActivity.class));
                                  finish();
                              }
                              else{
                                  String message=task.getException().getMessage();
                                  mailid.setError("Error Occured! "+message);
                              }
                          }
                      });
                  }
            }
        });

        mailid.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            String usereEmail=mailid.getText().toString();
                            if(TextUtils.isEmpty(usereEmail)){
                                Toast.makeText(getBaseContext(), "Please write a valid Email Id", Toast.LENGTH_LONG).show();
                            }else{
                                mAuth.sendPasswordResetEmail(usereEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(getBaseContext(), "Please Check Your Email Account.", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getBaseContext(),LoginFireBaseActivity.class));
                                            finish();
                                        }
                                        else{
                                            String message=task.getException().getMessage();
                                            mailid.setError("Error Occured! "+message);
                                        }
                                    }
                                });
                            }

                    }

                }
                return false;
            }
        });


        movesignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),SignUpFireBaseActivity.class);
                startActivity(intent);
                finish();
            }
        });

        movesign11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),LoginFireBaseActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}