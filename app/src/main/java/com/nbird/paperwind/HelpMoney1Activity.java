package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class HelpMoney1Activity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    int parent,child;
    TextView textView1,textView2,textView3,textView4,textView5,textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_money1);

        textView1=(TextView) findViewById(R.id.text1);
        textView2=(TextView) findViewById(R.id.text2);
        textView3=(TextView) findViewById(R.id.text3);
        textView4=(TextView) findViewById(R.id.text4);
        textView5=(TextView) findViewById(R.id.text5);
        textView6=(TextView) findViewById(R.id.text6);


        parent=getIntent().getIntExtra("parent",0);
        child=getIntent().getIntExtra("child",0);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Help Section");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        parentfunction();

    }
    public void parentfunction(){
        switch (parent){
            case 1:
              switch (child){
                  case 1:
                      p1c1();break;
                  case 2:
                      p1c2();break;
              }break;
            case 2:
                switch (child){
                    case 1:
                        p2c1();break;
                    case 2:
                        p2c2();break;
                }break;
            case 3:
                switch (child){
                    case 1:
                        p3c1();break;
                }break;
            case 4:
                switch (child){
                    case 1:
                        p4c1();break;
                    case 2:
                        p4c2();break;
                }break;
            case 5:
                switch (child){
                    case 1:
                        p5c1();break;
                    case 2:
                        p5c2();break;
                    case 3:
                        p5c3();break;
                    case 4:
                        p5c4();break;
                }break;
            case 6:
                switch (child){
                    case 1:
                        p6c1();break;
                    case 2:
                        p6c2();break;
                }break;
        }


    }
    public void p1c1(){
      textView1.setText("Paper Notes By Ads:-");
      textView2.setText("Paper Notes refer to virtual money.This virtual money will give you the access to various facilities present in the App.The User could check its Paper nOtes balance on toolBar section .");
      textView3.setText("Every new user will be provided with 50 Paper Notes at the beginning by default.");
      textView4.setText("These paper notes will be required for using the facility of Rank Predictor,College Predictor as well as for tests.You can collect paper notes by watching ads.One Paper note will be rewarded to you for watching an add that is number of Paper Note is directly propotional to the number of ads you have watched.");
      textView5.setText("There are many students who cannot afford to buy paper notes.Therefore we have come up with the ad facility so that everybody can get easy access to Paper Wind's facility.");
    }
    public void p1c2(){
        textView1.setText("Paper Notes By Transaction:-");
        textView2.setText("Paper Notes refer to virtual money.This virtual money will give you the access to various facilities present in the App.The User could check its Paper notes balance on ToolBar section.");
        textView3.setText("Every new user will be provided with 50 Paper Notes at the beginning by default.");
        textView4.setText("These paper notes will be required for using the facility of Rank Predictor,College Predictor as well as for tests.Paper Notes can also be obtained though direct monry transfer that is through online payment.");
        textView5.setText("Payment Gateway is integrated with Razorpay.");
        textView6.setText("The payment facilities include : Credit Card,Debit Card,Net Banking,UPI And Wallet.");

    }
    public void p2c1(){
        textView1.setText("Rank Predictor:-");
        textView2.setText("To predict your Rank in a certain exam go to 'Rank Predictor' section and then select your prefered exam");
        textView3.setText("After selecting the entrance exam you will have to select your category (GENERAL,OBC,SC,ST) ,then your gender (M/F) and then you will be required to enter your Score.");
        textView4.setText("A certain range of rank will be displayed to you as per your score . You can assume that your rank in the actual exam will be within this range.");
        textView5.setText("Data has been uploaded by taking average of previous two-three years from the offocial website of the respective entrance exams.");
        textView6.setText("For each use of the Rank Predictor one Paper Note will be consumed.");

    }
    public void p2c2(){
        textView1.setText("College Predictor:-");
        textView2.setText("To predict your College as per your Rank in a certain entrance exam go to 'College Predictor'.");
        textView3.setText("First select the entrance exam then select your category (GENERAL,OBC,SC,ST), then select your Gender (M/F), then the branch (CSE,ME,ECE,Civiel,Etc.) ,lastly enter your obtained rank and press the 'Get College' buttton.");
        textView5.setText("");
        textView4.setText("A list of colleges as per your entered information will be displayed .All the data has been taken from the official website of the respective entrance exams.");
        textView6.setText("");

    }
    public void p3c1(){
        textView1.setText("Latest News:-");
        textView2.setText("The news icon is located on the Toolbar at the left of the paper notes icon.");
        textView3.setText("Students can check live updates of various entrance exams .For Example:- The Dates of exam,Schedule,Availability of Admit Card,Publication of Answer Key,Etc.");
        textView5.setText("");
        textView4.setText("Exam time table of CBSE and ICSE boards will also be updated here.");
        textView6.setText("");

    }
    public void p4c1(){
        textView1.setText("Entrance Exams Previous Year's Paper:-");
        textView2.setText("This section provides Previous Year's Papers.");
        textView3.setText("For Previous Year's Papers of a particular Entrance Exam,first select the desired exam then select the 'Previous Year's Papers' Button.");
        textView5.setText("A list of papers will be displayed from which you can select.");
        textView4.setText("The papers will be available in the form of PDF.");
        textView6.setText("");

    }
    public void p4c2(){
        textView1.setText("Entrance Exams Online Test:-");
        textView2.setText("This section provides Test of various Entrance Exams.");
        textView3.setText("For Online Test of a particular Entrance Exam,first select the desired exam then select the 'Online Test' Button.");
        textView4.setText("A list of sets will be displayed from which you can select.");
        textView5.setText("Instructions will be displayed at the beginning of the test.Students are requested to read these instructions carefully before proceeding with the test.");
        textView6.setText("One set of each Entrance Exam will be free (You don't have to pay Paper Notes for 1st set) where as to get access for other sets you will be required to pay the desired number of Paper Notes.After the submission of the test you will be directed to the score activity where your performance in the test will be shown with the help of Bar And Pie Charts.");
    }
    public void p5c1(){
        textView1.setText("Previous Year's Paper:-");
        textView2.setText("This section provides Previous Year's Papers.");
        textView3.setText("For Previous Year's Papers of a particular Board Exams,first select the desired Board then select the 'Previous Year's Papers' Button.");
        textView4.setText("Then select your desired subject.");
        textView5.setText("A list of papers will be displayed from which you can select.");
        textView6.setText("The papers will be available in the form of PDF.");

    }
    public void p5c2(){
        textView1.setText("Sample Papers:-");
        textView2.setText("This section provides Sample Papers.");
        textView3.setText("For Sample Papers of a particular Board Exams,first select the desired Board then select the 'Sample Papers' Button.");
        textView4.setText("Then select your desired subject.");
        textView5.setText("A list of papers will be displayed from which you can select.");
        textView6.setText("The papers will be available in the form of PDF.");

    }
    public void p5c3(){
        textView1.setText("Practical Lab Videos:-");
        textView2.setText("This section provides Practical Lab Videos Link.");
        textView3.setText("For Practical Lab Videos of CBSE or CISCE ,first select the  Board then select the 'Practical Lab Videos' Button.");
        textView4.setText("Then select your desired subject.");
        textView5.setText("A list of Expirements will be displayed from which you can select.");
        textView6.setText("After clicking on a perticular expirement you will be directed to YouTube,where you can watch your selected Expirement tutorial.");

    }
    public void p5c4(){
        textView1.setText("NCERT Solurions:-");
        textView2.setText("This section provides NCERT Solutions.");
        textView3.setText("For NCERT Solutions of a CBSE ,first select the CBSE Board then select the 'NCERT Solutions' Button.");
        textView4.setText("Then select your desired subject.");
        textView5.setText("A list of papers will be displayed from which you can select.");
        textView6.setText("The Solutions will be available in the form of PDF.");

    }
    public void p6c1(){
        textView1.setText("Formula For Std 9 and 10:-");
        textView2.setText("This section provides you with the important formulae of different subjects commonly required by the students.");
        textView3.setText("Firstly select the Formula Tab in the Bottom Navigation");
        textView4.setText("Then select std 9 and 10 button.");
        textView5.setText("Then select preferred subject.After selecting your subject you have to select your desired chapter.Ater that you will be redirected to PDF Activity.");
        textView6.setText("The Formulas will be available in the form of PDF.");

    }
    public void p6c2(){
        textView1.setText("Formula For Std 11 and 12:-");
        textView2.setText("This section provides you with the important formulae of different subjects commonly required by the students.");
        textView3.setText("Firstly select the Formula Tab in the Bottom Navigation");
        textView4.setText("Then select std 11 and 12 button.");
        textView5.setText("Then select preferred subject.After selecting your subject you have to select your desired chapter.Ater that you will be redirected to PDF Activity.");
        textView6.setText("The Formulas will be available in the form of PDF.");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

}