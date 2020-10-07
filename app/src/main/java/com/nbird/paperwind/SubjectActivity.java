package com.nbird.paperwind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SubjectActivity extends AppCompatActivity {

    List<Exam> lstExam;
    CardView cardView;
    int Exam,Std,Paper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        cardView=(CardView) findViewById(R.id.cardview_id);


        lstExam=new ArrayList<>();

        Exam=getIntent().getIntExtra("Exam",0);
        Std=getIntent().getIntExtra("Std",0);
        Paper=getIntent().getIntExtra("Paper",0);

        donkey();

        RecyclerView myrv=(RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewAdapterLink myAdapter=new RecyclerViewAdapterLink(this,lstExam,Exam,Std,Paper);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);





    }

    public void donkey(){
        if(Exam==1){
            if(Std==1){
                cbse10();
            }
            else {
                cbse12();
            }
        }
        else if(Exam==2){
            if(Std==1){
                cisce10();
            }
            else {
                cisce12();
            }
        }
    }

    public void cbse10(){
        //physics
        lstExam.add(new Exam("Experiment 1",R.drawable.back2,"To study the dependence of potential difference (V) across a resistor on the current (I) passing through it and determine its resistance. Also plot a graph between V and I."));
        lstExam.add(new Exam("Experiment 2",R.drawable.back1,"To determine the equivalent resistance of two resistors when connected in series."));
        lstExam.add(new Exam("Experiment 3",R.drawable.back2,"To determine the equivalent resistance of two resistors when connected in parallel."));
        lstExam.add(new Exam("Experiment 4",R.drawable.back14,"To determine the focal length of a concave mirror by obtaining the image of a distant object."));
        lstExam.add(new Exam("Experiment 5",R.drawable.back2,"To trace the path of a ray of light passing through a rectangular glass slab for different angles of incidence. Measure the angle of incidence, angle of refraction, angle of emergence and interpret the result."));
        lstExam.add(new Exam("Experiment 6",R.drawable.back1,"To trace the path of the rays of light through a glass prism."));
        lstExam.add(new Exam("Experiment 7",R.drawable.back2,"To find the image distance for varying object distances in case of a convex lens and draw corresponding ray diagrams to show the nature of image formed."));
        //chemistry
        lstExam.add(new Exam("Experiment 1",R.drawable.back14,"To find the pH of samples by using pH paper/universal indicator."));
        lstExam.add(new Exam("Experiment 2",R.drawable.back2,"(a) To study the properties of acids (HCl) by their reaction. (b) To study the properties of bases(NaOH) by their reaction."));
        lstExam.add(new Exam("Experiment 3",R.drawable.back1,"To perform and observe the action of water on quicklime and classify the reaction."));
        lstExam.add(new Exam("Experiment 4",R.drawable.back2,"1. To observe the action of Zn, Fe, Cu and Al metals on the salt solutions 2. Arrange Zn, Fe, Cu and A1 metals in the decreasing order of reactivity based on the above result."));
        lstExam.add(new Exam("Experiment 5",R.drawable.back14,"To study the following properties of acetic acid (ethanoic acid): Odour, Solubility in water, Effect on litmus, Reaction with sodium bicarbonate."));
        lstExam.add(new Exam("Experiment 6",R.drawable.back2,"To study saponification reaction for preparation of soap."));
        lstExam.add(new Exam("Experiment 7",R.drawable.back1,"To study the comparative cleaning capacity of a sample of soap in soft and hard water."));
        //Bio
        lstExam.add(new Exam("Experiment 1",R.drawable.back14,"To prepare a temporary mount of a leaf peel to show stomata."));
        lstExam.add(new Exam("Experiment 2",R.drawable.back2,"To show experimentally that light is necessary for photosynthesis."));
        lstExam.add(new Exam("Experiment 3",R.drawable.back1,"To show experimentally that carbon dioxide is given out during respiration."));
        lstExam.add(new Exam("Experiment 4",R.drawable.back2,"To study:- Binary fission in Amoeba and budding in yeast with the help of prepared slides."));
        lstExam.add(new Exam("Experiment 5",R.drawable.back14,"To study homology and analogy with the help of models/charts of animals and models/charts/ specimens of plants."));
        lstExam.add(new Exam("Experiment 6",R.drawable.back1,"To identify the different parts of an embryo of a dicot seed (pea, gram or red kidney bean)."));
    }

    public void cbse12(){
        lstExam.add(new Exam("Optics",R.drawable.back2,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new Exam("JEE Advanced",R.drawable.back2,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new Exam("JEE Advanced",R.drawable.back2,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
    }

    public void cisce10(){
        lstExam.add(new Exam("Lion",R.drawable.back2,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new Exam("JEE Advanced",R.drawable.back2,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new Exam("JEE Advanced",R.drawable.back2,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
    }

    public void cisce12(){
        lstExam.add(new Exam("London",R.drawable.back2,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new Exam("JEE Advanced",R.drawable.back2,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new Exam("JEE Advanced",R.drawable.back2,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
    }
}