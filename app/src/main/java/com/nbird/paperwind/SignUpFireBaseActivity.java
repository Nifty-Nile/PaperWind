package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
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
    int money=30,permission=0;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("User");
    String usermail;
    String personEmail;
    Boolean night;
    String propicurl123="https://firebasestorage.googleapis.com/v0/b/paper-wind.appspot.com/o/BydefalutPic%2Fdefaultpropic.png?alt=media&token=f655727d-9740-4ac9-9ba2-f53ea02dc778";

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

        final SharedPreferences mailreminder = this.getSharedPreferences("mailreminder123", 0);
        final SharedPreferences.Editor editormailreminder = mailreminder.edit();


        movelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),LoginFireBaseActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            }
        });

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
                                usermail=mail.getText().toString();
                                editormailreminder.putString("123", usermail);
                                editormailreminder.commit();

                                User s1=new User(money,permission,propicurl123);
                                reference.child(fAuth.getCurrentUser().getUid()).child("personal").setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(SignUpFireBaseActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
                                        }else{
                                            Toast.makeText(SignUpFireBaseActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });



                             //   final SharedPreferences moneybalance= getBaseContext().getSharedPreferences("moneyuser", 0);
                             //   final SharedPreferences.Editor editormoneybalance = moneybalance.edit();

                            //    int moneyuser = moneybalance.getInt(usermail, 100);
                            //    Toast.makeText(getBaseContext(), usermail+  "And" +String.valueOf(moneyuser), Toast.LENGTH_LONG).show();
                            loadingDialog.dismiss();

                            final SharedPreferences guide = getBaseContext().getSharedPreferences("guidepre", 0);
                            final SharedPreferences.Editor editorguide = guide.edit();


                            night = guide.getBoolean("locked", false);


                            if(!night){
                                AlertDialog.Builder builder=new AlertDialog.Builder(SignUpFireBaseActivity.this,R.style.AlertDialogTheme);
                                View view1= LayoutInflater.from(SignUpFireBaseActivity.this).inflate(R.layout.guide_alertdialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                                builder.setView(view1);
                                ((TextView) view1.findViewById(R.id.textTitle)).setText("Want a Breezy Tour Guide for the upcoming Excitement?");
                                ((TextView) view1.findViewById(R.id.textMessage)).setText("Welcome to Paper Wind!");
                                ((Button) view1.findViewById(R.id.buttonNo)).setText("No");
                                ((Button) view1.findViewById(R.id.buttonYes)).setText("Yes,I Want A Guide");


                                final AlertDialog alertDialog=builder.create();

                                view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        final SharedPreferences guide = getBaseContext().getSharedPreferences("guidepre", 0);
                                        final SharedPreferences.Editor editorguide = guide.edit();
                                        editorguide.putBoolean("locked", false);
                                        editorguide.commit();
                                        startActivity(new Intent(getApplicationContext(),SartingGuideActivity.class));
                                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                        finish();

                                    }
                                });
                                view1.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(getApplicationContext(),Menu1Activity.class));
                                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                        finish();
                                    }
                                });

                                if(alertDialog.getWindow()!=null){
                                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                                }
                                alertDialog.show();

                            }else{
                                startActivity(new Intent(getApplicationContext(),Menu1Activity.class));
                                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                finish();
                            }

                        }
                        else{
                            Toast.makeText(getBaseContext(), "Error!"+task.getException().getMessage(), Toast.LENGTH_LONG).show();

                            loadingDialog.dismiss();
                        }
                    }
                });
            }
        });


        mail.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            final String email=mail.getText().toString().trim();
                            String password1=password.getText().toString().trim();

                            if(!username()||!mail()||!password()){
                                return false;
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
                                        editormailreminder.putString("123", usermail);
                                        editormailreminder.commit();
                                        User s1=new User(money,permission,propicurl123);
                                        reference.child(fAuth.getCurrentUser().getUid()).child("personal").setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(SignUpFireBaseActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
                                                }else{
                                                    Toast.makeText(SignUpFireBaseActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });



                                        //   final SharedPreferences moneybalance= getBaseContext().getSharedPreferences("moneyuser", 0);
                                        //   final SharedPreferences.Editor editormoneybalance = moneybalance.edit();

                                        //    int moneyuser = moneybalance.getInt(usermail, 100);
                                        //    Toast.makeText(getBaseContext(), usermail+  "And" +String.valueOf(moneyuser), Toast.LENGTH_LONG).show();

                                        loadingDialog.dismiss();

                                        final SharedPreferences guide = getBaseContext().getSharedPreferences("guidepre", 0);
                                        final SharedPreferences.Editor editorguide = guide.edit();


                                        night = guide.getBoolean("locked", false);


                                        if(!night){
                                            AlertDialog.Builder builder=new AlertDialog.Builder(SignUpFireBaseActivity.this,R.style.AlertDialogTheme);
                                            View view1= LayoutInflater.from(SignUpFireBaseActivity.this).inflate(R.layout.guide_alertdialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                                            builder.setView(view1);
                                            ((TextView) view1.findViewById(R.id.textTitle)).setText("Want a Breezy Tour Guide for the upcoming Excitement?");
                                            ((TextView) view1.findViewById(R.id.textMessage)).setText("Welcome to Paper Wind!");
                                            ((Button) view1.findViewById(R.id.buttonNo)).setText("No");
                                            ((Button) view1.findViewById(R.id.buttonYes)).setText("Yes,I Want A Guide");


                                            final AlertDialog alertDialog=builder.create();

                                            view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    final SharedPreferences guide = getBaseContext().getSharedPreferences("guidepre", 0);
                                                    final SharedPreferences.Editor editorguide = guide.edit();
                                                    editorguide.putBoolean("locked", false);
                                                    editorguide.commit();
                                                    startActivity(new Intent(getApplicationContext(),SartingGuideActivity.class));
                                                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                                    finish();

                                                }
                                            });
                                            view1.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    startActivity(new Intent(getApplicationContext(),Menu1Activity.class));
                                                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                                    finish();
                                                }
                                            });

                                            if(alertDialog.getWindow()!=null){
                                                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                                            }
                                            alertDialog.show();

                                        }else{
                                            startActivity(new Intent(getApplicationContext(),Menu1Activity.class));
                                            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                            finish();
                                        }
                                    }
                                    else{
                                        Toast.makeText(getBaseContext(), "Error!"+task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                        loadingDialog.dismiss();
                                    }
                                }
                            });

                    }
                }
                return false;
            }
        });


        password.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            final String email=mail.getText().toString().trim();
                            String password1=password.getText().toString().trim();

                            if(!username()||!mail()||!password()){
                                return false;
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
                                        editormailreminder.putString("123", usermail);
                                        editormailreminder.commit();
                                        User s1=new User(money,permission,propicurl123);
                                        reference.child(fAuth.getCurrentUser().getUid()).child("personal").setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(SignUpFireBaseActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
                                                }else{
                                                    Toast.makeText(SignUpFireBaseActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });



                                        //   final SharedPreferences moneybalance= getBaseContext().getSharedPreferences("moneyuser", 0);
                                        //   final SharedPreferences.Editor editormoneybalance = moneybalance.edit();

                                        //    int moneyuser = moneybalance.getInt(usermail, 100);
                                        //    Toast.makeText(getBaseContext(), usermail+  "And" +String.valueOf(moneyuser), Toast.LENGTH_LONG).show();

                                        loadingDialog.dismiss();

                                        final SharedPreferences guide = getBaseContext().getSharedPreferences("guidepre", 0);
                                        final SharedPreferences.Editor editorguide = guide.edit();


                                        night = guide.getBoolean("locked", false);


                                        if(!night){
                                            AlertDialog.Builder builder=new AlertDialog.Builder(SignUpFireBaseActivity.this,R.style.AlertDialogTheme);
                                            View view1= LayoutInflater.from(SignUpFireBaseActivity.this).inflate(R.layout.guide_alertdialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                                            builder.setView(view1);
                                            ((TextView) view1.findViewById(R.id.textTitle)).setText("Want a Breezy Tour Guide for the upcoming Excitement?");
                                            ((TextView) view1.findViewById(R.id.textMessage)).setText("Welcome to Paper Wind!");
                                            ((Button) view1.findViewById(R.id.buttonNo)).setText("No");
                                            ((Button) view1.findViewById(R.id.buttonYes)).setText("Yes,I Want A Guide");


                                            final AlertDialog alertDialog=builder.create();

                                            view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    final SharedPreferences guide = getBaseContext().getSharedPreferences("guidepre", 0);
                                                    final SharedPreferences.Editor editorguide = guide.edit();
                                                    editorguide.putBoolean("locked", false);
                                                    editorguide.commit();
                                                    startActivity(new Intent(getApplicationContext(),SartingGuideActivity.class));
                                                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                                    finish();

                                                }
                                            });
                                            view1.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    startActivity(new Intent(getApplicationContext(),Menu1Activity.class));
                                                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                                    finish();
                                                }
                                            });

                                            if(alertDialog.getWindow()!=null){
                                                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                                            }
                                            alertDialog.show();

                                        }else{
                                            startActivity(new Intent(getApplicationContext(),Menu1Activity.class));
                                            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                            finish();
                                        }
                                    }
                                    else{
                                        Toast.makeText(getBaseContext(), "Error!"+task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                        loadingDialog.dismiss();
                                    }
                                }
                            });

                    }
                }
                return false;
            }
        });


        mail.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            final String email=mail.getText().toString().trim();
                            String password1=password.getText().toString().trim();

                            if(!username()||!mail()||!password()){
                                return false;
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
                                        usermail=mail.getText().toString();
                                        editormailreminder.putString("123", usermail);
                                        editormailreminder.commit();
                                        User s1=new User(money,permission,propicurl123);
                                        reference.child(fAuth.getCurrentUser().getUid()).child("personal").setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(SignUpFireBaseActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
                                                }else{
                                                    Toast.makeText(SignUpFireBaseActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });



                                        //   final SharedPreferences moneybalance= getBaseContext().getSharedPreferences("moneyuser", 0);
                                        //   final SharedPreferences.Editor editormoneybalance = moneybalance.edit();

                                        //    int moneyuser = moneybalance.getInt(usermail, 100);
                                        //    Toast.makeText(getBaseContext(), usermail+  "And" +String.valueOf(moneyuser), Toast.LENGTH_LONG).show();

                                        loadingDialog.dismiss();

                                        final SharedPreferences guide = getBaseContext().getSharedPreferences("guidepre", 0);
                                        final SharedPreferences.Editor editorguide = guide.edit();


                                        night = guide.getBoolean("locked", false);


                                        if(!night){
                                            AlertDialog.Builder builder=new AlertDialog.Builder(SignUpFireBaseActivity.this,R.style.AlertDialogTheme);
                                            View view1= LayoutInflater.from(SignUpFireBaseActivity.this).inflate(R.layout.guide_alertdialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                                            builder.setView(view1);
                                            ((TextView) view1.findViewById(R.id.textTitle)).setText("Want a Breezy Tour Guide for the upcoming Excitement?");
                                            ((TextView) view1.findViewById(R.id.textMessage)).setText("Welcome to Paper Wind!");
                                            ((Button) view1.findViewById(R.id.buttonNo)).setText("No");
                                            ((Button) view1.findViewById(R.id.buttonYes)).setText("Yes,I Want A Guide");


                                            final AlertDialog alertDialog=builder.create();

                                            view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    final SharedPreferences guide = getBaseContext().getSharedPreferences("guidepre", 0);
                                                    final SharedPreferences.Editor editorguide = guide.edit();
                                                    editorguide.putBoolean("locked", false);
                                                    editorguide.commit();
                                                    startActivity(new Intent(getApplicationContext(),SartingGuideActivity.class));
                                                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                                    finish();

                                                }
                                            });
                                            view1.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    startActivity(new Intent(getApplicationContext(),Menu1Activity.class));
                                                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                                    finish();
                                                }
                                            });

                                            if(alertDialog.getWindow()!=null){
                                                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                                            }
                                            alertDialog.show();

                                        }else{
                                            startActivity(new Intent(getApplicationContext(),Menu1Activity.class));
                                            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                            finish();
                                        }
                                    }
                                    else{
                                        Toast.makeText(getBaseContext(), "Error!"+task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                        loadingDialog.dismiss();
                                    }
                                }
                            });

                    }
                }
                return false;
            }
        });

        username.setOnClickListener(new View.OnClickListener() {
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