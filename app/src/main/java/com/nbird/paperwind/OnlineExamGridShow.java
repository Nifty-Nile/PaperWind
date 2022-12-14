package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OnlineExamGridShow extends AppCompatActivity {
    int phy,chem,maths,bio,position,mode,set,score,physore,chemscore,mathsscore,totalmarks;
    GridView gridView;
    Button submitbutton,pausebutton;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef1=database.getReference();
    DatabaseReference myRef2=database.getReference();
    DatabaseReference myRef3=database.getReference();
    private List<ASSAPHolder> list1,list2,list3;
    TextView timer,timersec,textView11,timerheading,text1,text2,text3,dot;
    CountDownTimer countDownTimer;
    long time,times;
    String value;
    int minutes,sec;
    Button infobutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_exam_grid_show);

        phy=getIntent().getIntExtra("phy",0);
        chem=getIntent().getIntExtra("chem",0);
        maths=getIntent().getIntExtra("maths",0);
        bio=getIntent().getIntExtra("bio",0);
        position=getIntent().getIntExtra("position",0);
        mode=getIntent().getIntExtra("mode",0);
        set=getIntent().getIntExtra("set",0);
        score=getIntent().getIntExtra("score",0);




        final SharedPreferences counterstopper = getBaseContext().getSharedPreferences("counterstopper123", 0);
        final SharedPreferences.Editor editorcounterstopper = counterstopper.edit();


        submitbutton=(Button) findViewById(R.id.submitbutton);
        pausebutton=(Button) findViewById(R.id.PauseTestButton);
        infobutton=(Button) findViewById(R.id.infobutton);
        timer=(TextView) findViewById(R.id.timer);
        timersec=(TextView) findViewById(R.id.sec);

        textView11=(TextView) findViewById(R.id.textView11);
        timerheading=(TextView) findViewById(R.id.timerheading);
        text1=(TextView) findViewById(R.id.text1);
        text2=(TextView) findViewById(R.id.text2);
        text3=(TextView) findViewById(R.id.text3);
        dot=(TextView) findViewById(R.id.don);

        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        infobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(OnlineExamGridShow.this,R.style.AlertDialogTheme);
                View view1= LayoutInflater.from(OnlineExamGridShow.this).inflate(R.layout.infobuttonlayout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                builder.setView(view1);


                final AlertDialog alertDialog=builder.create();

                view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
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
        });



        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            LinearLayout layout =(LinearLayout)findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            timer.setTextColor(Color.parseColor("#ffffff"));
            timersec.setTextColor(Color.parseColor("#ffffff"));
            textView11.setTextColor(Color.parseColor("#ffffff"));
            timerheading.setTextColor(Color.parseColor("#ffffff"));
            text1.setTextColor(Color.parseColor("#ffffff"));
            text2.setTextColor(Color.parseColor("#ffffff"));
            text3.setTextColor(Color.parseColor("#ffffff"));
            dot.setTextColor(Color.parseColor("#ffffff"));



        }else{
            LinearLayout layout =(LinearLayout)findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);

            timer.setTextColor(Color.parseColor("#000000"));
            timersec.setTextColor(Color.parseColor("#000000"));
            textView11.setTextColor(Color.parseColor("#000000"));
            timerheading.setTextColor(Color.parseColor("#000000"));
            text1.setTextColor(Color.parseColor("#000000"));
            text2.setTextColor(Color.parseColor("#000000"));
            text3.setTextColor(Color.parseColor("#000000"));
            dot.setTextColor(Color.parseColor("#000000"));

        }

        list1=new ArrayList<>();
        list2=new ArrayList<>();
        list3=new ArrayList<>();
        final SharedPreferences condition = getBaseContext().getSharedPreferences("Condition", 0);
        final SharedPreferences.Editor editorconditioin = condition.edit();
        final Boolean answerA100 = condition.getBoolean(String.valueOf(1), false);
        final SharedPreferences minutes12345 = getBaseContext().getSharedPreferences("minutes123", 0);
        final SharedPreferences.Editor editorminutes12345 = minutes12345.edit();

        final SharedPreferences seconds13245 = getBaseContext().getSharedPreferences("seconds123", 0);
        final SharedPreferences.Editor editorseconds13245 = seconds13245.edit();

        final SharedPreferences minutes100 = getBaseContext().getSharedPreferences("minutes100", 0);
        final SharedPreferences.Editor editorminutes100 = minutes100.edit();

        final SharedPreferences seconds100 = getBaseContext().getSharedPreferences("seconds100", 0);
        final SharedPreferences.Editor editorseconds100 = seconds100.edit();







        final SharedPreferences settings = getBaseContext().getSharedPreferences("Physics", 0);
        final SharedPreferences.Editor editor = settings.edit();

        final SharedPreferences settings1 = getBaseContext().getSharedPreferences("Maths", 0);
        final SharedPreferences.Editor editor1 = settings1.edit();

        final SharedPreferences settings2 = getBaseContext().getSharedPreferences("Chemistry", 0);
        final SharedPreferences.Editor editor2 = settings2.edit();

        final SharedPreferences colorblue = getBaseContext().getSharedPreferences("PhysicsColour", 0);
        final SharedPreferences.Editor editorblue = colorblue.edit();

        final SharedPreferences colorred = getBaseContext().getSharedPreferences("MathsColour", 0);
        final SharedPreferences.Editor editorred = colorred.edit();

        final SharedPreferences coloryellow = getBaseContext().getSharedPreferences("ChemistryColour", 0);
        final SharedPreferences.Editor editoryellow = coloryellow.edit();

        final SharedPreferences reviewphy = getBaseContext().getSharedPreferences("ReviewPurplePhy", 0);
        final SharedPreferences.Editor editorreviewphy = reviewphy.edit();

        final SharedPreferences reviewchem = getBaseContext().getSharedPreferences("ReviewPurpleChem", 0);
        final SharedPreferences.Editor editorreviewchem = reviewchem.edit();

        final SharedPreferences reviewmaths = getBaseContext().getSharedPreferences("ReviewPurpleMaths", 0);
        final SharedPreferences.Editor editorreviewmaths = reviewmaths.edit();

        final SharedPreferences notanswered1 = getBaseContext().getSharedPreferences("notanswered1", 0);
        final SharedPreferences.Editor editornotanswered1 = notanswered1.edit();

        final SharedPreferences notanswered2 = getBaseContext().getSharedPreferences("notanswered2", 0);
        final SharedPreferences.Editor editornotanswered2 = notanswered2.edit();

        final SharedPreferences notanswered3 = getBaseContext().getSharedPreferences("notanswered3", 0);
        final SharedPreferences.Editor editornotanswered3 = notanswered3.edit();

        final SharedPreferences set1 = getBaseContext().getSharedPreferences("set", 0);
        final SharedPreferences.Editor editorset = set1.edit();

        final SharedPreferences timesaver = getBaseContext().getSharedPreferences("timesaver123", 0);
        final SharedPreferences.Editor editortimesaver = timesaver.edit();

        final SharedPreferences pausephy = getBaseContext().getSharedPreferences("pausephy123", 0);
        final SharedPreferences.Editor editorpausephy = pausephy.edit();

        final SharedPreferences pausechem = getBaseContext().getSharedPreferences("pausechem123", 0);
        final SharedPreferences.Editor editorpausechem = pausechem.edit();

        final SharedPreferences pausemaths = getBaseContext().getSharedPreferences("pausemaths123", 0);
        final SharedPreferences.Editor editorpausemaths = pausemaths.edit();



        switch (position){
            case 2:
                minutes=179;break;
            case 3:
                minutes=179;break;
            case 4:
                minutes=149;break;
            case 5:
                minutes=179;break;
            case 7:
                minutes=179;break;
            case 8:
                minutes=149;break;
            case 9:
                minutes=179;break;
            case 10:
                minutes=149;break;
            case 11:
                minutes=239;break;
            case 12:
                minutes=179;break;
        }

        if(!answerA100) {        //Setting timer for the first time
            editorconditioin.putBoolean(String.valueOf(1), true);
            editorconditioin.commit();



            sec=60;


            countDownTimer=new CountDownTimer(60*1000*(minutes),1000) {
                @Override
                public void onTick(long l) {



                    sec=sec-1;



                    if(sec==0){
                        if(minutes>0){
                            minutes--;
                        }

                        timer.setText(Integer.toString(minutes));
                        timersec.setText("0"+Integer.toString(sec));
                        String a = timer.getText().toString();
                        int A = Integer.parseInt(a);
                        editorminutes100.putInt(String.valueOf(1), A);
                        editorminutes100.commit();
                        String b = timersec.getText().toString();
                        int B = Integer.parseInt(b);
                        editorseconds100.putInt(String.valueOf(1), B);
                        editorseconds100.commit();



                        sec=60;
                    }else{

                        if(sec<10){
                            timersec.setText("0"+Long.toString(sec));
                        }else{
                            timersec.setText(Long.toString(sec));
                        }
                        if(minutes<10){
                            timer.setText("0"+Integer.toString(minutes));

                        }else{
                            timer.setText(Integer.toString(minutes));
                        }

                        String a = timer.getText().toString();
                        int A = Integer.parseInt(a);
                        editorminutes100.putInt(String.valueOf(1), A);
                        editorminutes100.commit();
                        String b = timersec.getText().toString();
                        int B = Integer.parseInt(b);
                        editorseconds100.putInt(String.valueOf(1), B);
                        editorseconds100.commit();
                    }



                }

                @Override
                public void onFinish() {

                    final SharedPreferences settings = getBaseContext().getSharedPreferences("Physics", 0);
                    final SharedPreferences.Editor editor = settings.edit();
                    final SharedPreferences settings1 = getBaseContext().getSharedPreferences("Maths", 0);
                    final SharedPreferences.Editor editor1 = settings1.edit();

                    final SharedPreferences settings2 = getBaseContext().getSharedPreferences("Chemistry", 0);
                    final SharedPreferences.Editor editor2 = settings2.edit();


                    if(position==2){
                        for(int i=1;i<=30;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+4;
                            }
                            else if(answerA0.equals("poty")){

                            }else{
                                physore--;
                            }

                        }
                        for(int i=1;i<=30;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 4;
                            } else if (answerA1.equals("poty")) {

                            } else {
                                chemscore--;

                            }
                        }
                        for(int i=1;i<=30;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 4;
                            } else if (answerA2.equals("poty")) {

                            } else {
                                mathsscore--;

                            }
                        }
                    }else if(position==3){
                        for(int i=1;i<=40;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+4;
                            }
                            else if(answerA0.equals("poty")){

                            }else{
                                physore--;
                            }

                        }
                        for(int i=1;i<=40;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 4;
                            } else if (answerA1.equals("poty")) {

                            } else {
                                chemscore--;

                            }
                        }
                        for(int i=1;i<=90;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 4;
                            } else if (answerA2.equals("poty")) {

                            } else {
                                mathsscore--;

                            }
                        }
                    }
                    if(position==4){
                        for(int i=1;i<=35;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+1;
                            }
                            else if(answerA0.equals("poty")){

                            }else{

                            }

                        }
                        for(int i=1;i<=35;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 1;
                            } else if (answerA1.equals("poty")) {

                            } else {


                            }
                        }
                        for(int i=1;i<=55;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 1;
                            } else if (answerA2.equals("poty")) {

                            } else {


                            }
                        }
                    }
                    if(position==5){
                        for(int i=1;i<=60;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+1;
                            }
                            else if(answerA0.equals("poty")){

                            }else{

                            }

                        }
                        for(int i=1;i<=60;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 1;
                            } else if (answerA1.equals("poty")) {

                            } else {


                            }
                        }
                        for(int i=1;i<=60;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 1;
                            } else if (answerA2.equals("poty")) {

                            } else {


                            }
                        }
                    }
                    if(position==8){
                        for(int i=1;i<=50;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+4;
                            }
                            else if(answerA0.equals("poty")){

                            }else{
                                physore--;
                            }

                        }
                        for(int i=1;i<=50;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 4;
                            } else if (answerA1.equals("poty")) {

                            } else {
                                chemscore--;

                            }
                        }
                        for(int i=1;i<=100;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 4;
                            } else if (answerA2.equals("poty")) {

                            } else {
                                mathsscore--;

                            }
                        }
                    }else if(position==7) {
                        for (int i = 1; i <= 40; i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if (answerA0.equals(list1.get(i - 1).getCorrectoption())) {
                                physore = physore + 3;
                            } else if (answerA0.equals("poty")) {

                            } else {
                                physore--;
                            }

                        }
                        for (int i = 1; i <= 40; i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 3;
                            } else if (answerA1.equals("poty")) {

                            } else {
                                chemscore--;

                            }
                        }
                        for (int i = 1; i <= 65; i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 3;
                            } else if (answerA2.equals("poty")) {

                            } else {
                                mathsscore--;

                            }

                        }
                    }else if(position==9){
                        for(int i=1;i<=50;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+1;
                            }
                            else if(answerA0.equals("poty")){

                            }else{

                            }

                        }
                        for(int i=1;i<=50;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 1;
                            } else if (answerA1.equals("poty")) {

                            } else {


                            }
                        }
                        for(int i=1;i<=50;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 2;
                            } else if (answerA2.equals("poty")) {

                            } else {


                            }
                        }
                    }else if(position==10){
                        for(int i=1;i<=35;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+1;
                            }
                            else if(answerA0.equals("poty")){

                            }else{

                            }

                        }
                        for(int i=1;i<=35;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 1;
                            } else if (answerA1.equals("poty")) {

                            } else {


                            }
                        }
                        for(int i=1;i<=55;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 1;
                            } else if (answerA2.equals("poty")) {

                            } else {


                            }
                        }
                    }else if(position==11){
                        for(int i=1;i<=60;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+1;
                            }
                            else if(answerA0.equals("poty")){

                            }else{

                            }

                        }
                        for(int i=1;i<=60;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 1;
                            } else if (answerA1.equals("poty")) {

                            } else {


                            }
                        }
                        for(int i=1;i<=60;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 1;
                            } else if (answerA2.equals("poty")) {

                            } else {


                            }
                        }
                    }else if(position==12){
                        for(int i=1;i<=50;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+4;
                            }
                            else if(answerA0.equals("poty")){

                            }else{

                            }

                        }
                        for(int i=1;i<=50;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 4;
                            } else if (answerA1.equals("poty")) {

                            } else {


                            }
                        }
                        for(int i=1;i<=50;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 4;
                            } else if (answerA2.equals("poty")) {

                            } else {


                            }
                        }
                    }
















                    final SharedPreferences colorblue = getBaseContext().getSharedPreferences("PhysicsColour", 0);
                    final SharedPreferences.Editor editorblue = colorblue.edit();

                    final SharedPreferences colorred = getBaseContext().getSharedPreferences("MathsColour", 0);
                    final SharedPreferences.Editor editorred = colorred.edit();

                    final SharedPreferences coloryellow = getBaseContext().getSharedPreferences("ChemistryColour", 0);
                    final SharedPreferences.Editor editoryellow = coloryellow.edit();

                    final SharedPreferences reviewphy = getBaseContext().getSharedPreferences("ReviewPurplePhy", 0);
                    final SharedPreferences.Editor editorreviewphy = reviewphy.edit();

                    final SharedPreferences reviewchem = getBaseContext().getSharedPreferences("ReviewPurpleChem", 0);
                    final SharedPreferences.Editor editorreviewchem = reviewchem.edit();

                    final SharedPreferences reviewmaths = getBaseContext().getSharedPreferences("ReviewPurpleMaths", 0);
                    final SharedPreferences.Editor editorreviewmaths = reviewmaths.edit();

                    final SharedPreferences notanswered1 = getBaseContext().getSharedPreferences("notanswered1", 0);
                    final SharedPreferences.Editor editornotanswered1 = notanswered1.edit();

                    final SharedPreferences notanswered2 = getBaseContext().getSharedPreferences("notanswered2", 0);
                    final SharedPreferences.Editor editornotanswered2 = notanswered2.edit();

                    final SharedPreferences notanswered3 = getBaseContext().getSharedPreferences("notanswered3", 0);
                    final SharedPreferences.Editor editornotanswered3 = notanswered3.edit();

                    final SharedPreferences set1 = getBaseContext().getSharedPreferences("set", 0);
                    final SharedPreferences.Editor editorset = set1.edit();

                    final SharedPreferences timesaver = getBaseContext().getSharedPreferences("timesaver123", 0);
                    final SharedPreferences.Editor editortimesaver = timesaver.edit();

                    final SharedPreferences pausephy = getBaseContext().getSharedPreferences("pausephy123", 0);
                    final SharedPreferences.Editor editorpausephy = pausephy.edit();

                    final SharedPreferences pausechem = getBaseContext().getSharedPreferences("pausechem123", 0);
                    final SharedPreferences.Editor editorpausechem = pausechem.edit();

                    final SharedPreferences pausemaths = getBaseContext().getSharedPreferences("pausemaths123", 0);
                    final SharedPreferences.Editor editorpausemaths = pausemaths.edit();



                    totalmarks=physore+chemscore+mathsscore;

                    if(countDownTimer!=null){
                        countDownTimer.cancel();}

                    if(!answerA100){
                        Intent intent=new Intent(getBaseContext(),ScoreActivity.class);
                        intent.putExtra("marks",totalmarks);
                        intent.putExtra("totalmarks",totalmarks);
                        intent.putExtra("RankEE",position);
                        intent.putExtra("phymarks",physore);
                        intent.putExtra("chemmarks",chemscore);
                        intent.putExtra("mathsmarks",mathsscore);
                        intent.putExtra("modeint",mode);
                        intent.putExtra("set",set);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        editor.clear().apply();
                        editor1.clear().apply();
                        editor2.clear().apply();
                        editorblue.clear().apply();
                        editorred.clear().apply();
                        editoryellow.clear().apply();
                        editorreviewphy.clear().apply();
                        editorreviewchem.clear().apply();
                        editorreviewmaths.clear().apply();
                        editorconditioin.clear().apply();
                        editornotanswered1.clear().apply();
                        editornotanswered2.clear().apply();
                        editornotanswered3.clear().apply();
                        editorset.clear().apply();
                        editortimesaver.clear().apply();
                        editorpausephy.clear().apply();
                        editorpausechem.clear().apply();
                        editorpausemaths.clear().apply();
                        finish();
                    }




                }
            };
        }else{
            value=getIntent().getStringExtra("text");

            minutes = minutes100.getInt(String.valueOf(1), 179);
            sec = seconds100.getInt(String.valueOf(1), 60);





            countDownTimer=new CountDownTimer((minutes)*60*1000,1000) {
                @Override
                public void onTick(long l) {




                    sec=sec-1;



                    if(sec==0){
                        minutes--;
                        timer.setText(Integer.toString(minutes));
                        timersec.setText("0"+Integer.toString(sec));
                        String a = timer.getText().toString();
                        int A = Integer.parseInt(a);
                        editorminutes100.putInt(String.valueOf(1), A);
                        editorminutes100.commit();
                        String b = timersec.getText().toString();
                        int B = Integer.parseInt(b);
                        editorseconds100.putInt(String.valueOf(1), B);
                        editorseconds100.commit();



                        sec=60;
                    }else{
                        if(sec<10){
                            timersec.setText("0"+Long.toString(sec));
                        }else{
                            timersec.setText(Long.toString(sec));
                        }
                        if(minutes<10){
                            timer.setText("0"+Integer.toString(minutes));

                        }else{
                            timer.setText(Integer.toString(minutes));
                        }
                        String a = timer.getText().toString();
                        int A = Integer.parseInt(a);
                        editorminutes100.putInt(String.valueOf(1), A);
                        editorminutes100.commit();
                        String b = timersec.getText().toString();
                        int B = Integer.parseInt(b);
                        editorseconds100.putInt(String.valueOf(1), B);
                        editorseconds100.commit();
                    }

                }

                @Override
                public void onFinish() {


                    if(position==2){
                        for(int i=1;i<=30;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+4;
                            }
                            else if(answerA0.equals("poty")){

                            }else{
                                physore--;
                            }

                        }
                        for(int i=1;i<=30;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 4;
                            } else if (answerA1.equals("poty")) {

                            } else {
                                chemscore--;

                            }
                        }
                        for(int i=1;i<=30;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 4;
                            } else if (answerA2.equals("poty")) {

                            } else {
                                mathsscore--;

                            }
                        }
                    }else if(position==3){
                        for(int i=1;i<=40;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+4;
                            }
                            else if(answerA0.equals("poty")){

                            }else{
                                physore--;
                            }

                        }
                        for(int i=1;i<=40;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 4;
                            } else if (answerA1.equals("poty")) {

                            } else {
                                chemscore--;

                            }
                        }
                        for(int i=1;i<=90;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 4;
                            } else if (answerA2.equals("poty")) {

                            } else {
                                mathsscore--;

                            }
                        }
                    }
                    if(position==4){
                        for(int i=1;i<=35;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+1;
                            }
                            else if(answerA0.equals("poty")){

                            }else{

                            }

                        }
                        for(int i=1;i<=35;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 1;
                            } else if (answerA1.equals("poty")) {

                            } else {


                            }
                        }
                        for(int i=1;i<=55;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 1;
                            } else if (answerA2.equals("poty")) {

                            } else {


                            }
                        }
                    }
                    if(position==5){
                        for(int i=1;i<=60;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+1;
                            }
                            else if(answerA0.equals("poty")){

                            }else{

                            }

                        }
                        for(int i=1;i<=60;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 1;
                            } else if (answerA1.equals("poty")) {

                            } else {


                            }
                        }
                        for(int i=1;i<=60;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 1;
                            } else if (answerA2.equals("poty")) {

                            } else {


                            }
                        }
                    }
                    if(position==8){
                        for(int i=1;i<=50;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+4;
                            }
                            else if(answerA0.equals("poty")){

                            }else{
                                physore--;
                            }

                        }
                        for(int i=1;i<=50;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 4;
                            } else if (answerA1.equals("poty")) {

                            } else {
                                chemscore--;

                            }
                        }
                        for(int i=1;i<=100;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 4;
                            } else if (answerA2.equals("poty")) {

                            } else {
                                mathsscore--;

                            }
                        }
                    }else if(position==7) {
                        for (int i = 1; i <= 40; i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if (answerA0.equals(list1.get(i - 1).getCorrectoption())) {
                                physore = physore + 4;
                            } else if (answerA0.equals("poty")) {

                            } else {
                                physore--;
                            }

                        }
                        for (int i = 1; i <= 40; i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 4;
                            } else if (answerA1.equals("poty")) {

                            } else {
                                chemscore--;

                            }
                        }
                        for (int i = 1; i <= 65; i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 4;
                            } else if (answerA2.equals("poty")) {

                            } else {
                                mathsscore--;

                            }

                        }
                    }else if(position==9){
                        for(int i=1;i<=50;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+1;
                            }
                            else if(answerA0.equals("poty")){

                            }else{

                            }

                        }
                        for(int i=1;i<=50;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 1;
                            } else if (answerA1.equals("poty")) {

                            } else {


                            }
                        }
                        for(int i=1;i<=50;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 2;
                            } else if (answerA2.equals("poty")) {

                            } else {


                            }
                        }
                    }else if(position==10){
                        for(int i=1;i<=35;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+1;
                            }
                            else if(answerA0.equals("poty")){

                            }else{

                            }

                        }
                        for(int i=1;i<=35;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 1;
                            } else if (answerA1.equals("poty")) {

                            } else {


                            }
                        }
                        for(int i=1;i<=55;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 1;
                            } else if (answerA2.equals("poty")) {

                            } else {


                            }
                        }
                    }else if(position==11){
                        for(int i=1;i<=60;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+1;
                            }
                            else if(answerA0.equals("poty")){

                            }else{

                            }

                        }
                        for(int i=1;i<=60;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 1;
                            } else if (answerA1.equals("poty")) {

                            } else {


                            }
                        }
                        for(int i=1;i<=60;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 1;
                            } else if (answerA2.equals("poty")) {

                            } else {


                            }
                        }
                    }else if(position==12){
                        for(int i=1;i<=50;i++) {
                            String answerA0 = settings.getString(String.valueOf(i), "poty");

                            if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                                physore=physore+4;
                            }
                            else if(answerA0.equals("poty")){

                            }else{

                            }

                        }
                        for(int i=1;i<=50;i++) {
                            String answerA1 = settings2.getString(String.valueOf(i), "poty");

                            if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                                chemscore = chemscore + 4;
                            } else if (answerA1.equals("poty")) {

                            } else {


                            }
                        }
                        for(int i=1;i<=50;i++) {
                            String answerA2 = settings1.getString(String.valueOf(i), "poty");

                            if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                                mathsscore = mathsscore + 4;
                            } else if (answerA2.equals("poty")) {

                            } else {


                            }
                        }
                    }










                    totalmarks=physore+chemscore+mathsscore;
                    if(countDownTimer!=null){
                        countDownTimer.cancel();}


                    if(!answerA100){
                        Intent intent=new Intent(OnlineExamGridShow.this,ScoreActivity.class);
                        intent.putExtra("totalmarks",totalmarks);
                        intent.putExtra("RankEE",position);
                        intent.putExtra("phymarks",physore);
                        intent.putExtra("chemmarks",chemscore);
                        intent.putExtra("mathsmarks",mathsscore);
                        intent.putExtra("modeint",mode);
                        intent.putExtra("set",set);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        editor.clear().apply();
                        editor1.clear().apply();
                        editor2.clear().apply();
                        editorblue.clear().apply();
                        editorred.clear().apply();
                        editoryellow.clear().apply();
                        editorreviewphy.clear().apply();
                        editorreviewchem.clear().apply();
                        editorreviewmaths.clear().apply();
                        editorconditioin.clear().apply();
                        editornotanswered1.clear().apply();
                        editornotanswered2.clear().apply();
                        editornotanswered3.clear().apply();
                        editorset.clear().apply();
                        editortimesaver.clear().apply();
                        editorpausephy.clear().apply();
                        editorpausechem.clear().apply();
                        editorpausemaths.clear().apply();
                        finish();
                    }

                }
            };
        }





        gridView=findViewById(R.id.grid1);
        final PhysicsgridAdapter adapter=new PhysicsgridAdapter(this,phy,position,mode,set,chem,maths,score,timer,sec,minutes);
        gridView.setAdapter(adapter);

        gridView=findViewById(R.id.grid2);
        final ChemistrygridAdapter adapter2=new ChemistrygridAdapter(this,chem,position,mode,set,phy,maths,score,timer,sec,minutes);
        gridView.setAdapter(adapter2);


        gridView=findViewById(R.id.grid3);
        final MathsgridAdapter adapter3=new MathsgridAdapter(this,maths,position,mode,set,chem,phy,score,timer,sec,minutes);
        gridView.setAdapter(adapter3);



        countDownTimer.start();





        myRef1.child("EntranceExamQuestionsASSAP").child(String.valueOf(position)).child(String.valueOf(set)).child(String.valueOf(1)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    list1.add(snapshot1.getValue(ASSAPHolder.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef2.child("EntranceExamQuestionsASSAP").child(String.valueOf(position)).child(String.valueOf(set)).child(String.valueOf(2)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    list2.add(snapshot1.getValue(ASSAPHolder.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef3.child("EntranceExamQuestionsASSAP").child(String.valueOf(position)).child(String.valueOf(set)).child(String.valueOf(3)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    list3.add(snapshot1.getValue(ASSAPHolder.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        pausebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {





            }
        });


        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(position==2){
                    for(int i=1;i<=30;i++) {
                        String answerA0 = settings.getString(String.valueOf(i), "poty");

                        if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                            physore=physore+4;
                        }
                        else if(answerA0.equals("poty")){

                        }else{
                            physore--;
                        }

                    }
                    for(int i=1;i<=30;i++) {
                        String answerA1 = settings2.getString(String.valueOf(i), "poty");

                        if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                            chemscore = chemscore + 4;
                        } else if (answerA1.equals("poty")) {

                        } else {
                            chemscore--;

                        }
                    }
                    for(int i=1;i<=30;i++) {
                        String answerA2 = settings1.getString(String.valueOf(i), "poty");

                        if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                            mathsscore = mathsscore + 4;
                        } else if (answerA2.equals("poty")) {

                        } else {
                            mathsscore--;

                        }
                    }
                }else if(position==3){
                    for(int i=1;i<=40;i++) {
                        String answerA0 = settings.getString(String.valueOf(i), "poty");

                        if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                            physore=physore+4;
                        }
                        else if(answerA0.equals("poty")){

                        }else{
                            physore--;
                        }

                    }
                    for(int i=1;i<=40;i++) {
                        String answerA1 = settings2.getString(String.valueOf(i), "poty");

                        if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                            chemscore = chemscore + 4;
                        } else if (answerA1.equals("poty")) {

                        } else {
                            chemscore--;

                        }
                    }
                    for(int i=1;i<=90;i++) {
                        String answerA2 = settings1.getString(String.valueOf(i), "poty");

                        if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                            mathsscore = mathsscore + 4;
                        } else if (answerA2.equals("poty")) {

                        } else {
                            mathsscore--;

                        }
                    }
                }
                if(position==4){
                    for(int i=1;i<=35;i++) {
                        String answerA0 = settings.getString(String.valueOf(i), "poty");

                        if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                            physore=physore+1;
                        }
                        else if(answerA0.equals("poty")){

                        }else{

                        }

                    }
                    for(int i=1;i<=35;i++) {
                        String answerA1 = settings2.getString(String.valueOf(i), "poty");

                        if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                            chemscore = chemscore + 1;
                        } else if (answerA1.equals("poty")) {

                        } else {


                        }
                    }
                    for(int i=1;i<=55;i++) {
                        String answerA2 = settings1.getString(String.valueOf(i), "poty");

                        if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                            mathsscore = mathsscore + 1;
                        } else if (answerA2.equals("poty")) {

                        } else {


                        }
                    }
                }
                if(position==5){
                    for(int i=1;i<=60;i++) {
                        String answerA0 = settings.getString(String.valueOf(i), "poty");

                        if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                            physore=physore+1;
                        }
                        else if(answerA0.equals("poty")){

                        }else{

                        }

                    }
                    for(int i=1;i<=60;i++) {
                        String answerA1 = settings2.getString(String.valueOf(i), "poty");

                        if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                            chemscore = chemscore + 1;
                        } else if (answerA1.equals("poty")) {

                        } else {


                        }
                    }
                    for(int i=1;i<=60;i++) {
                        String answerA2 = settings1.getString(String.valueOf(i), "poty");

                        if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                            mathsscore = mathsscore + 1;
                        } else if (answerA2.equals("poty")) {

                        } else {


                        }
                    }
                }
                if(position==8){
                    for(int i=1;i<=50;i++) {
                        String answerA0 = settings.getString(String.valueOf(i), "poty");

                        if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                            physore=physore+4;
                        }
                        else if(answerA0.equals("poty")){

                        }else{
                            physore--;
                        }

                    }
                    for(int i=1;i<=50;i++) {
                        String answerA1 = settings2.getString(String.valueOf(i), "poty");

                        if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                            chemscore = chemscore + 4;
                        } else if (answerA1.equals("poty")) {

                        } else {
                            chemscore--;

                        }
                    }
                    for(int i=1;i<=100;i++) {
                        String answerA2 = settings1.getString(String.valueOf(i), "poty");

                        if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                            mathsscore = mathsscore + 4;
                        } else if (answerA2.equals("poty")) {

                        } else {
                            mathsscore--;

                        }
                    }
                }else if(position==7) {
                    for (int i = 1; i <= 40; i++) {
                        String answerA0 = settings.getString(String.valueOf(i), "poty");

                        if (answerA0.equals(list1.get(i - 1).getCorrectoption())) {
                            physore = physore + 4;
                        } else if (answerA0.equals("poty")) {

                        } else {
                            physore--;
                        }

                    }
                    for (int i = 1; i <= 40; i++) {
                        String answerA1 = settings2.getString(String.valueOf(i), "poty");

                        if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                            chemscore = chemscore + 4;
                        } else if (answerA1.equals("poty")) {

                        } else {
                            chemscore--;

                        }
                    }
                    for (int i = 1; i <= 65; i++) {
                        String answerA2 = settings1.getString(String.valueOf(i), "poty");

                        if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                            mathsscore = mathsscore + 4;
                        } else if (answerA2.equals("poty")) {

                        } else {
                            mathsscore--;

                        }

                    }
                }else if(position==9){
                    for(int i=1;i<=50;i++) {
                        String answerA0 = settings.getString(String.valueOf(i), "poty");

                        if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                            physore=physore+1;
                        }
                        else if(answerA0.equals("poty")){

                        }else{

                        }

                    }
                    for(int i=1;i<=50;i++) {
                        String answerA1 = settings2.getString(String.valueOf(i), "poty");

                        if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                            chemscore = chemscore + 1;
                        } else if (answerA1.equals("poty")) {

                        } else {


                        }
                    }
                    for(int i=1;i<=50;i++) {
                        String answerA2 = settings1.getString(String.valueOf(i), "poty");

                        if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                            mathsscore = mathsscore + 2;
                        } else if (answerA2.equals("poty")) {

                        } else {


                        }
                    }
                }else if(position==10){
                    for(int i=1;i<=35;i++) {
                        String answerA0 = settings.getString(String.valueOf(i), "poty");

                        if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                            physore=physore+1;
                        }
                        else if(answerA0.equals("poty")){

                        }else{

                        }

                    }
                    for(int i=1;i<=35;i++) {
                        String answerA1 = settings2.getString(String.valueOf(i), "poty");

                        if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                            chemscore = chemscore + 1;
                        } else if (answerA1.equals("poty")) {

                        } else {


                        }
                    }
                    for(int i=1;i<=55;i++) {
                        String answerA2 = settings1.getString(String.valueOf(i), "poty");

                        if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                            mathsscore = mathsscore + 1;
                        } else if (answerA2.equals("poty")) {

                        } else {


                        }
                    }
                }else if(position==11){
                    for(int i=1;i<=60;i++) {
                        String answerA0 = settings.getString(String.valueOf(i), "poty");

                        if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                            physore=physore+1;
                        }
                        else if(answerA0.equals("poty")){

                        }else{

                        }

                    }
                    for(int i=1;i<=60;i++) {
                        String answerA1 = settings2.getString(String.valueOf(i), "poty");

                        if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                            chemscore = chemscore + 1;
                        } else if (answerA1.equals("poty")) {

                        } else {


                        }
                    }
                    for(int i=1;i<=60;i++) {
                        String answerA2 = settings1.getString(String.valueOf(i), "poty");

                        if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                            mathsscore = mathsscore + 1;
                        } else if (answerA2.equals("poty")) {

                        } else {


                        }
                    }
                }else if(position==12){
                    for(int i=1;i<=50;i++) {
                        String answerA0 = settings.getString(String.valueOf(i), "poty");

                        if(answerA0.equals(list1.get(i-1).getCorrectoption())){
                            physore=physore+4;
                        }
                        else if(answerA0.equals("poty")){

                        }else{

                        }

                    }
                    for(int i=1;i<=50;i++) {
                        String answerA1 = settings2.getString(String.valueOf(i), "poty");

                        if (answerA1.equals(list2.get(i - 1).getCorrectoption())) {
                            chemscore = chemscore + 4;
                        } else if (answerA1.equals("poty")) {

                        } else {


                        }
                    }
                    for(int i=1;i<=50;i++) {
                        String answerA2 = settings1.getString(String.valueOf(i), "poty");

                        if (answerA2.equals(list3.get(i - 1).getCorrectoption())) {
                            mathsscore = mathsscore + 4;
                        } else if (answerA2.equals("poty")) {

                        } else {


                        }
                    }
                }



                totalmarks=physore+chemscore+mathsscore;



                onButtonPressd();

                editor.clear().apply();
                editor1.clear().apply();
                editor2.clear().apply();
                editorblue.clear().apply();
                editorred.clear().apply();
                editoryellow.clear().apply();
                editorreviewphy.clear().apply();
                editorreviewchem.clear().apply();
                editorreviewmaths.clear().apply();
                editorconditioin.clear().apply();
                editornotanswered1.clear().apply();
                editornotanswered2.clear().apply();
                editornotanswered3.clear().apply();
                editorset.clear().apply();
                editortimesaver.clear().apply();
                editorpausephy.clear().apply();
                editorpausechem.clear().apply();
                editorpausemaths.clear().apply();

            }
        });
    }
    public void onButtonPressd(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final AlertDialog.Builder builder1=new AlertDialog.Builder(this);


        builder.setMessage("Are you sure you want to Submit your Answers?")
                .setCancelable(false).setPositiveButton("Yes,I want to Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                builder1.setMessage("You really want to Submit?")
                        .setCancelable(false).setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final SharedPreferences minutes100 = getBaseContext().getSharedPreferences("minutes100", 0);
                                final SharedPreferences.Editor editorminutes100 = minutes100.edit();

                                final SharedPreferences seconds100 = getBaseContext().getSharedPreferences("seconds100", 0);
                                final SharedPreferences.Editor editorseconds100 = seconds100.edit();

                                editorminutes100.clear().commit();
                                editorseconds100.clear().commit();
                                Intent intent=new Intent(getBaseContext(),ScoreActivity.class);
                                intent.putExtra("totalmarks",totalmarks);
                                intent.putExtra("RankEE",position);
                                intent.putExtra("phymarks",physore);
                                intent.putExtra("chemmarks",chemscore);
                                intent.putExtra("mathsmarks",mathsscore);
                                intent.putExtra("modeint",mode);
                                intent.putExtra("set",set);




                                Toast.makeText(getBaseContext(), String.valueOf(totalmarks), Toast.LENGTH_LONG).show();
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                if(countDownTimer!=null){
                                    countDownTimer.cancel();}
                                finish();

                            }
                        });
                AlertDialog alertDialog1=builder1.create();
                alertDialog1.show();

            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });


        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }



    public void onButtonPressd1(){


        final SharedPreferences set1 = getBaseContext().getSharedPreferences("set", 0);
        final SharedPreferences.Editor editorset = set1.edit();

        final SharedPreferences timesaver = getBaseContext().getSharedPreferences("timesaver123", 0);
        final SharedPreferences.Editor editortimesaver = timesaver.edit();

        final SharedPreferences pausephy = getBaseContext().getSharedPreferences("pausephy123", 0);
        final SharedPreferences.Editor editorpausephy = pausephy.edit();

        final SharedPreferences pausechem = getBaseContext().getSharedPreferences("pausechem123", 0);
        final SharedPreferences.Editor editorpausechem = pausechem.edit();

        final SharedPreferences pausemaths = getBaseContext().getSharedPreferences("pausemaths123", 0);
        final SharedPreferences.Editor editorpausemaths = pausemaths.edit();

        final SharedPreferences counterstopper = getBaseContext().getSharedPreferences("counterstopper123", 0);
        final SharedPreferences.Editor editorcounterstopper = counterstopper.edit();



        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final AlertDialog.Builder builder1=new AlertDialog.Builder(this);


        builder.setMessage("Are you sure you want to Submit your Answers?")
                .setCancelable(false).setPositiveButton("Yes,I want to Pause My Exam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editorset.putInt("Setnumber", set);
                editorset.commit();
                editorpausephy.putInt("editorpausephy100", phy);
                editorpausephy.commit();
                editorpausechem.putInt("editorpausechem100", chem);
                editorpausechem.commit();
                editorpausemaths.putInt("editorpausemaths100", maths);
                editorpausemaths.commit();
                String text = timer.getText().toString();
                editortimesaver.putString("Timesaver",text);
                editortimesaver.commit();

                editorcounterstopper.putBoolean("counterstopper",true);
                editorcounterstopper.commit();

                Intent intent=new Intent(getBaseContext(),Menu1Activity.class);
                countDownTimer.cancel();

                startActivity(intent);

            }
        })
                .setNegativeButton("No,Continue With The Exam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });


        AlertDialog alertDialog=builder.create();
        alertDialog.show();


    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final AlertDialog.Builder builder1=new AlertDialog.Builder(this);


        builder.setMessage("You Are In The Test Mode.You Really Want To Quit The Test?")
                .setCancelable(false).setPositiveButton("Yes,I want to Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                builder1.setMessage("Are You Sure You Want To Quit?")
                        .setCancelable(false).setPositiveButton("Continue With The Test", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final SharedPreferences settings = getBaseContext().getSharedPreferences("Physics", 0);
                                final SharedPreferences.Editor editor = settings.edit();

                                final SharedPreferences settings1 = getBaseContext().getSharedPreferences("Maths", 0);
                                final SharedPreferences.Editor editor1 = settings1.edit();

                                final SharedPreferences settings2 = getBaseContext().getSharedPreferences("Chemistry", 0);
                                final SharedPreferences.Editor editor2 = settings2.edit();

                                final SharedPreferences colorblue = getBaseContext().getSharedPreferences("PhysicsColour", 0);
                                final SharedPreferences.Editor editorblue = colorblue.edit();

                                final SharedPreferences colorred = getBaseContext().getSharedPreferences("MathsColour", 0);
                                final SharedPreferences.Editor editorred = colorred.edit();

                                final SharedPreferences coloryellow = getBaseContext().getSharedPreferences("ChemistryColour", 0);
                                final SharedPreferences.Editor editoryellow = coloryellow.edit();

                                final SharedPreferences reviewphy = getBaseContext().getSharedPreferences("ReviewPurplePhy", 0);
                                final SharedPreferences.Editor editorreviewphy = reviewphy.edit();

                                final SharedPreferences reviewchem = getBaseContext().getSharedPreferences("ReviewPurpleChem", 0);
                                final SharedPreferences.Editor editorreviewchem = reviewchem.edit();

                                final SharedPreferences reviewmaths = getBaseContext().getSharedPreferences("ReviewPurpleMaths", 0);
                                final SharedPreferences.Editor editorreviewmaths = reviewmaths.edit();

                                final SharedPreferences condition = getBaseContext().getSharedPreferences("Condition", 0);
                                final SharedPreferences.Editor editorconditioin = condition.edit();

                                final SharedPreferences notanswered1 = getBaseContext().getSharedPreferences("notanswered1", 0);
                                final SharedPreferences.Editor editornotanswered1 = notanswered1.edit();

                                final SharedPreferences notanswered2 = getBaseContext().getSharedPreferences("notanswered2", 0);
                                final SharedPreferences.Editor editornotanswered2 = notanswered2.edit();

                                final SharedPreferences notanswered3 = getBaseContext().getSharedPreferences("notanswered3", 0);
                                final SharedPreferences.Editor editornotanswered3 = notanswered3.edit();

                                final SharedPreferences set1 = getBaseContext().getSharedPreferences("set", 0);
                                final SharedPreferences.Editor editorset = set1.edit();

                                final SharedPreferences timesaver = getBaseContext().getSharedPreferences("timesaver123", 0);
                                final SharedPreferences.Editor editortimesaver = timesaver.edit();

                                final SharedPreferences pausephy = getBaseContext().getSharedPreferences("pausephy123", 0);
                                final SharedPreferences.Editor editorpausephy = pausephy.edit();

                                final SharedPreferences pausechem = getBaseContext().getSharedPreferences("pausechem123", 0);
                                final SharedPreferences.Editor editorpausechem = pausechem.edit();

                                final SharedPreferences pausemaths = getBaseContext().getSharedPreferences("pausemaths123", 0);
                                final SharedPreferences.Editor editorpausemaths = pausemaths.edit();

                                final SharedPreferences minutes100 = getBaseContext().getSharedPreferences("minutes100", 0);
                                final SharedPreferences.Editor editorminutes100 = minutes100.edit();

                                final SharedPreferences seconds100 = getBaseContext().getSharedPreferences("seconds100", 0);
                                final SharedPreferences.Editor editorseconds100 = seconds100.edit();

                                editorminutes100.clear().commit();
                                editorseconds100.clear().commit();

                                editor.clear().apply();
                                editor1.clear().apply();
                                editor2.clear().apply();
                                editorblue.clear().apply();
                                editorred.clear().apply();
                                editoryellow.clear().apply();
                                editorreviewphy.clear().apply();
                                editorreviewchem.clear().apply();
                                editorreviewmaths.clear().apply();
                                editorconditioin.clear().apply();
                                editornotanswered1.clear().apply();
                                editornotanswered2.clear().apply();
                                editornotanswered3.clear().apply();
                                editorset.clear().apply();
                                editortimesaver.clear().apply();
                                editorpausephy.clear().apply();
                                editorpausechem.clear().apply();
                                editorpausemaths.clear().apply();
                                OnlineExamGridShow.super.onBackPressed();

                                    countDownTimer.cancel();
                            }
                        });
                AlertDialog alertDialog1=builder1.create();
                alertDialog1.show();

            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });


       AlertDialog alertDialog=builder.create();
       alertDialog.show();

    }
}