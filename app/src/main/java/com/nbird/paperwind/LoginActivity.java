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

public class LoginActivity extends AppCompatActivity {
    Button movesignup,login;
    private Dialog loadingDialog;
    TextInputEditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        movesignup=(Button) findViewById(R.id.movesignup);
        login=(Button) findViewById(R.id.login);
        username = (TextInputEditText) findViewById(R.id.username);
        password=(TextInputEditText) findViewById(R.id.password);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingDialog=new Dialog(LoginActivity.this);
                loadingDialog.setContentView(R.layout.activity_loading);
                loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                loadingDialog.setCancelable(false);
                loadingDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if (dataSnapshot.child(username.getText().toString()).exists()) {
                            loadingDialog.dismiss();
                            User user = dataSnapshot.child(username.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(password.getText().toString())) {

                                Intent intent = new Intent(getBaseContext(),Menu1Activity.class);
                                startActivity(intent);

                                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                Toast.makeText(LoginActivity.this, "Log In Successfull!", Toast.LENGTH_LONG).show();
                            } else {

                                Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            loadingDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "User doesn't exist.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(LoginActivity.this, "Not able to connect", Toast.LENGTH_LONG).show();
                        loadingDialog.dismiss();
                    }
                });
            }
        });


        movesignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}