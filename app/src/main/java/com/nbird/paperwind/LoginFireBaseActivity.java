package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.loader.content.Loader;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Model.User;

public class LoginFireBaseActivity extends AppCompatActivity {

    Button movesignup,login,forgotpassword;
    SignInButton googsignin;
    SignInButton googlelogin;
    private Dialog loadingDialog;
    TextInputEditText mail,password;
    FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=1;
    String personEmail;
    Boolean night;
    int money=30,permission=0;
    String propicurl123="https://firebasestorage.googleapis.com/v0/b/paper-wind.appspot.com/o/BydefalutPic%2Fdefaultpropic.png?alt=media&token=f655727d-9740-4ac9-9ba2-f53ea02dc778";
    String imageurl;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_user = database.getReference("User");
    final DatabaseReference reference1 = database.getReference("User");
    final DatabaseReference reference2 = database.getReference();
    int skipint=0;
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fire_base);

        movesignup=(Button) findViewById(R.id.movesignup);
        login=(Button) findViewById(R.id.login);
        googsignin=(SignInButton) findViewById(R.id.googlesignin);
        mail = (TextInputEditText) findViewById(R.id.username);
        password=(TextInputEditText) findViewById(R.id.password);
        forgotpassword=(Button) findViewById(R.id.forgotpassword);
        mAuth = FirebaseAuth.getInstance();

        SharedPreferences mailreminder = this.getSharedPreferences("mailreminder123",0);
        final SharedPreferences.Editor editormailreminder = mailreminder.edit();

        SharedPreferences propicurl = this.getSharedPreferences("propicurl123",0);
        final SharedPreferences.Editor editorpropicurl = mailreminder.edit();




        FirebaseUser user=mAuth.getCurrentUser();



        if(user!=null){
            startActivity(new Intent(getApplicationContext(),Menu1Activity.class));
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            finish();
        }




        createRequest();

        googsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog=new Dialog(LoginFireBaseActivity.this);

                loadingDialog.setContentView(R.layout.activity_loading);
                loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                loadingDialog.setCancelable(true);
                loadingDialog.show();



                signIn();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mail.getText().toString().trim();
                String password1=password.getText().toString().trim();
                editormailreminder.putString("123", email);
                editormailreminder.commit();
                if(!mail()||!password()){
                    return;
                }
                loadingDialog=new Dialog(LoginFireBaseActivity.this);
                loadingDialog.setContentView(R.layout.activity_loading);
                loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                loadingDialog.setCancelable(true);
                loadingDialog.show();

                mAuth.signInWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getBaseContext(), "Logged In Successfully!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),SartingGuideActivity.class));
                            loadingDialog.dismiss();
                            finish();


                        }else{
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
                            String email=mail.getText().toString().trim();
                            String password1=password.getText().toString().trim();
                            editormailreminder.putString("123", email);
                            editormailreminder.commit();

                            if(!mail()||!password()){
                                return false;
                            }

                            loadingDialog=new Dialog(LoginFireBaseActivity.this);
                            loadingDialog.setContentView(R.layout.activity_loading);
                            loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                            loadingDialog.setCancelable(true);
                            loadingDialog.show();

                            mAuth.signInWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getBaseContext(), "Logged In Successfully!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(),SartingGuideActivity.class));
                                        loadingDialog.dismiss();
                                        finish();
                                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

                                    }else{
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
                            String email=mail.getText().toString().trim();
                            String password1=password.getText().toString().trim();
                            editormailreminder.putString("123", email);
                            editormailreminder.commit();

                            if(!mail()||!password()){
                                return false;
                            }

                            loadingDialog=new Dialog(LoginFireBaseActivity.this);
                            loadingDialog.setContentView(R.layout.activity_loading);
                            loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                            loadingDialog.setCancelable(true);
                            loadingDialog.show();

                            mAuth.signInWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getBaseContext(), "Logged In Successfully!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(),SartingGuideActivity.class));
                                        loadingDialog.dismiss();
                                        finish();
                                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

                                    }else{
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

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),ForgotPasswordActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            }
        });

        movesignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),SignUpFireBaseActivity.class);
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
        }else if(name2.length()>30){
            password.setError("Password should be less than 18 characters");
            return false;
        }
        else
            password.setError(null);
        return true;
    }

public void createRequest(){
    // Configure Google Sign In
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build();

    // Build a GoogleSignInClient with the options specified by gso.
    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
}

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {



                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();





                            // Sign in success, update UI with the signed-in user's information
                            GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(getApplicationContext());

                           /* */





                            if(account!=null) {

                                /*table_user.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        User userbro = dataSnapshot.getValue(User.class);
                                        money=userbro.getMoney();
                                        permission=userbro.getPermission();
                                        propicurl123=userbro.getPropic();
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        System.out.println("The read failed: " + databaseError.getCode());
                                    }
                                });*/





                                personEmail = account.getEmail();
                                final SharedPreferences mailreminder = getBaseContext().getSharedPreferences("mailreminder123", 0);
                                final SharedPreferences.Editor editormailreminder = mailreminder.edit();
                                editormailreminder.putString("123", personEmail);
                                editormailreminder.commit();


                                    reference1.child(mAuth.getCurrentUser().getUid()).child("personal").child("money").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // convert the data back to the model
                                         try{
                                            money = dataSnapshot.getValue(Integer.class);
                                            User s1 = new User(money, permission, propicurl123);
                                            skipint = 1;


                                            reference1.child(mAuth.getCurrentUser().getUid()).child("personal").child("permission").addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    // convert the data back to the model
                                                    try {
                                                        permission = dataSnapshot.getValue(Integer.class);


                                                    } catch (Exception e) {
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                }
                                            });

                                            reference1.child(mAuth.getCurrentUser().getUid()).child("personal").child("propic").addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    // convert the data back to the model
                                                    try {
                                                        propicurl123 = (String) dataSnapshot.getValue();
                                                        User s1 = new User(money, permission, propicurl123);
                                                        table_user.child(mAuth.getCurrentUser().getUid()).child("personal").setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {

                                                                } else {
                                                                    Toast.makeText(LoginFireBaseActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                                                                }
                                                            }
                                                        });
                                                    } catch (Exception e) {
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                }
                                            });

                                        }catch(Exception e){
                                             User s1 = new User(money, permission, propicurl123);
                                             table_user.child(mAuth.getCurrentUser().getUid()).child("personal").setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                 @Override
                                                 public void onComplete(@NonNull Task<Void> task) {
                                                     if (task.isSuccessful()) {

                                                     } else {
                                                         Toast.makeText(LoginFireBaseActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                                                     }
                                                 }
                                             });
                                            }



                                        }
                                        // papernotestotal.setText("Paper Notes: " + String.valueOf(value));



                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    });









                         //       final SharedPreferences moneybalance= getBaseContext().getSharedPreferences("moneyuser", 0);
                         //       final SharedPreferences.Editor editormoneybalance = moneybalance.edit();

                          //      int moneyuser = moneybalance.getInt(personEmail, 100);
                          //      Toast.makeText(getBaseContext(), personEmail+  "And" +String.valueOf(moneyuser), Toast.LENGTH_LONG).show();
                            }

                            loadingDialog.dismiss();

                            final SharedPreferences guide = getBaseContext().getSharedPreferences("guidepre", 0);
                            final SharedPreferences.Editor editorguide = guide.edit();


                            night = guide.getBoolean("locked", false);


                            if(!night){
                                AlertDialog.Builder builder=new AlertDialog.Builder(LoginFireBaseActivity.this,R.style.AlertDialogTheme);
                                View view1= LayoutInflater.from(LoginFireBaseActivity.this).inflate(R.layout.guide_alertdialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
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


                            //Database data input and output





                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getBaseContext(), "Error!"+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            loadingDialog.dismiss();

                        }

                        // ...
                    }
                });
    }

}