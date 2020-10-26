package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.User;

public class SignUpActivity extends AppCompatActivity {
    Button movelogin,signup;
    private Dialog loadingDialog;
    TextInputEditText username,mail,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        movelogin=(Button) findViewById(R.id.movelogin);
        signup=(Button) findViewById(R.id.signup);
        password=(TextInputEditText) findViewById(R.id.password);
        username=(TextInputEditText) findViewById(R.id.username2);
        mail=(TextInputEditText) findViewById(R.id.email) ;


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!username()||!mail()||!password()){
                    return;
                }


                loadingDialog=new Dialog(SignUpActivity.this);
                loadingDialog.setContentView(R.layout.activity_loading);
                loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(username.getText().toString()).exists()){
                            loadingDialog.dismiss();
                            username.setError("Mobile no. Already Exists");
                        }
                        else{

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        movelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean username(){
        String name1=username.getText().toString();
        String noWhihteSpaces=("\\A\\w{4,20}\\z");

        if(name1.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }else if(name1.length()>20){
            username.setError("Usernaem should be less than 20 characters");
            return false;
        }else if(!name1.matches(noWhihteSpaces)){
            username.setError("Usernames cannot carry such characters or spaces");
            return false;
        }
        else
            username.setError(null);
        return true;
    }



    private boolean mail(){
        String name2=mail.getText().toString();
        String emailPattern=("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
        if(name2.isEmpty()){
            mail.setError("Fields cannot be empty");
            return false;
        }else if(name2.length()>45){
            mail.setError("Email should be less than 45 characters");
            return false;
        }else if(!name2.matches(emailPattern)){
            mail.setError("Email should not carry such characters or spaces");
            return false;
        }
        else
            mail.setError(null);
        return true;
    }

    private boolean password(){
        String name2=password.getText().toString();
        if(name2.isEmpty()){
            password.setError("Fields cannot be empty");
            return false;
        }else if(name2.length()>18){
            password.setError("Password should be less than 18 characters");
            return false;
        }
        else
            password.setError(null);
        return true;
    }
}