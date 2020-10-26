package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.User;

public class SignUpFireBaseActivity extends AppCompatActivity {
    Button movelogin,signup;
    private Dialog loadingDialog;
    TextInputEditText username,mail,password;
    FirebaseAuth fAuth;
    int money=50;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("User");

    String personEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_fire_base);
        movelogin=(Button) findViewById(R.id.movelogin);
        signup=(Button) findViewById(R.id.signup);
        password=(TextInputEditText) findViewById(R.id.password);
        username=(TextInputEditText) findViewById(R.id.username2);
        mail=(TextInputEditText) findViewById(R.id.email) ;

        fAuth= FirebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=mail.getText().toString().trim();
                String password1=password.getText().toString().trim();

                if(!username()||!mail()||!password()){
                    return;
                }

                loadingDialog=new Dialog(SignUpFireBaseActivity.this);
                loadingDialog.setContentView(R.layout.activity_loading);
                loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                loadingDialog.setCancelable(true);
                loadingDialog.show();

                fAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                                String usermail=mail.getText().toString();

                                User s1=new User(money);
                                reference.child(fAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(SignUpFireBaseActivity.this, "Record Saved!", Toast.LENGTH_LONG).show();
                                        }else{
                                            Toast.makeText(SignUpFireBaseActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                             //   final SharedPreferences moneybalance= getBaseContext().getSharedPreferences("moneyuser", 0);
                             //   final SharedPreferences.Editor editormoneybalance = moneybalance.edit();

                            //    int moneyuser = moneybalance.getInt(usermail, 100);
                            //    Toast.makeText(getBaseContext(), usermail+  "And" +String.valueOf(moneyuser), Toast.LENGTH_LONG).show();

                            startActivity(new Intent(getApplicationContext(),Menu1Activity.class));
                            finish();
                            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                            loadingDialog.dismiss();
                        }
                        else{
                            Toast.makeText(getBaseContext(), "Error!"+task.getException().getMessage(), Toast.LENGTH_LONG).show();

                            loadingDialog.dismiss();
                        }
                    }
                });
            }
        });
        movelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),LoginFireBaseActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            }
        });

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
}