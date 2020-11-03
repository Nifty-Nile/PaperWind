package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubjectActivity extends AppCompatActivity {

    List<Exam> lstExam;

    private Dialog loadingDialog;
    CardView cardView;
    int Exam,Std,Paper,Labsub;
    TextView textView3;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        cardView=(CardView) findViewById(R.id.cardview_id);

        textView3=(TextView) findViewById(R.id.textView3);


        lstExam=new ArrayList<>();

        SharedPreferences lightanddark = getBaseContext().getSharedPreferences("LightanddDarkMode", 0);
        SharedPreferences.Editor editorlightanddark = lightanddark.edit();

        Boolean answerA0 = lightanddark.getBoolean(String.valueOf(1), false);

        if(answerA0){
            ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.backdarkmode);
            textView3.setTextColor(Color.parseColor("#ffffff"));


        }else{
            ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.mainfield);
            layout.setBackgroundResource(R.drawable.background1);


            textView3.setTextColor(Color.parseColor("#000000"));

        }


        Exam=getIntent().getIntExtra("Exam",0);
        Std=getIntent().getIntExtra("Std",0);
        Paper=getIntent().getIntExtra("Paper",0);
        Labsub=getIntent().getIntExtra("LabSub",0);

        donkey();

        RecyclerView myrv=(RecyclerView) findViewById(R.id.recyclerview);
        final RecyclerViewAdapterLink myAdapter=new RecyclerViewAdapterLink(this,lstExam,Exam,Std,Paper,Labsub);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigatio);

        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        return true;

                    case R.id.rankpredictor:
                        startActivity(new Intent(getApplicationContext(),RankPredictorActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Formulas:
                        startActivity(new Intent(getApplicationContext(),FormulaSTDActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.money:
                        startActivity(new Intent(getApplicationContext(),MoneyActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

    public void donkey(){
        if(Exam==1){
            if(Std==1){
                if(Labsub==1){
                    cbsephy10();
                }else if(Labsub==2){
                    cbsechem10();
                }else if(Labsub==3){
                    cbsebio10();
                }

            }
            else {
                if(Labsub==1){
                       cbsephy12();
                }else if(Labsub==2){
                      cbsechem12();
                }else if(Labsub==3){
                       cbsebio12();
                }

            }
        }
        else if(Exam==2){
            if(Std==1){
                if(Labsub==1){
                        ciscephy10();
                }else if(Labsub==2){
                         ciscechem10();
                }else if(Labsub==3){
                       ciscebio10();
                }

            }
            else {
                if(Labsub==1){
                ciscephy12();
                }else if(Labsub==2){
                  ciscechem12();
                }else if(Labsub==3){
                   ciscebio12();
                }

            }
        }
    }

    public void cbsephy10() {
        lstExam.add(new Exam("Experiment 1", R.drawable.back2, "To study the dependence of potential difference (V) across a resistor on the current (I) passing through it and determine its resistance. Also plot a graph between V and I."));
        lstExam.add(new Exam("Experiment 2", R.drawable.back1, "To determine the equivalent resistance of two resistors when connected in series."));
        lstExam.add(new Exam("Experiment 3", R.drawable.back2, "To determine the equivalent resistance of two resistors when connected in parallel."));
        lstExam.add(new Exam("Experiment 4", R.drawable.back14, "To determine the focal length of a concave mirror by obtaining the image of a distant object."));
        lstExam.add(new Exam("Experiment 5", R.drawable.back2, "To trace the path of a ray of light passing through a rectangular glass slab for different angles of incidence. Measure the angle of incidence, angle of refraction, angle of emergence and interpret the result."));
        lstExam.add(new Exam("Experiment 6", R.drawable.back1, "To trace the path of the rays of light through a glass prism."));
        lstExam.add(new Exam("Experiment 7", R.drawable.back2, "To find the image distance for varying object distances in case of a convex lens and draw corresponding ray diagrams to show the nature of image formed."));

    }

    public void cbsechem10(){
        lstExam.add(new Exam("Experiment 1",R.drawable.back14,"To find the pH of samples by using pH paper/universal indicator."));
        lstExam.add(new Exam("Experiment 2",R.drawable.back2,"(a) To study the properties of acids (HCl) by their reaction. (b) To study the properties of bases(NaOH) by their reaction."));
        lstExam.add(new Exam("Experiment 3",R.drawable.back1,"To perform and observe the action of water on quicklime and classify the reaction."));
        lstExam.add(new Exam("Experiment 4",R.drawable.back2,"1. To observe the action of Zn, Fe, Cu and Al metals on the salt solutions 2. Arrange Zn, Fe, Cu and A1 metals in the decreasing order of reactivity based on the above result."));
        lstExam.add(new Exam("Experiment 5",R.drawable.back14,"To study the following properties of acetic acid (ethanoic acid): Odour, Solubility in water, Effect on litmus, Reaction with sodium bicarbonate."));
        lstExam.add(new Exam("Experiment 6",R.drawable.back2,"To study saponification reaction for preparation of soap."));
        lstExam.add(new Exam("Experiment 7",R.drawable.back1,"To study the comparative cleaning capacity of a sample of soap in soft and hard water."));

    }

    public void cbsebio10(){
        lstExam.add(new Exam("Experiment 1",R.drawable.back14,"To prepare a temporary mount of a leaf peel to show stomata."));
        lstExam.add(new Exam("Experiment 2",R.drawable.back2,"To show experimentally that light is necessary for photosynthesis."));
        lstExam.add(new Exam("Experiment 3",R.drawable.back1,"To show experimentally that carbon dioxide is given out during respiration."));
        lstExam.add(new Exam("Experiment 4",R.drawable.back2,"To study:- Binary fission in Amoeba and budding in yeast with the help of prepared slides."));
        lstExam.add(new Exam("Experiment 5",R.drawable.back14,"To study homology and analogy with the help of models/charts of animals and models/charts/ specimens of plants."));
        lstExam.add(new Exam("Experiment 6",R.drawable.back1,"To identify the different parts of an embryo of a dicot seed (pea, gram or red kidney bean)."));

    }



    public void cbsephy12(){
        lstExam.add(new Exam("Experiment 1 (A)",R.drawable.back2,"To determine resistance per cm of a given wire by plotting a graph for potential difference versus current."));
        lstExam.add(new Exam("Experiment 2 (A)",R.drawable.back2,"To find resistance of a given wire using metre bridge and hence determine the resistivity (specific resistance) of its material."));
        lstExam.add(new Exam("Experiment 3 (A)",R.drawable.back2,"To verify the laws of combination (series) of resistances using a metre bridge."));
        lstExam.add(new Exam("Experiment 4 (A)",R.drawable.back2,"To verify the Laws of combination (parallel) of resistances using a metre bridge."));
        lstExam.add(new Exam("Experiment 5 (A)",R.drawable.back2,"To compare the EMF of two given primary cells using potentiometer."));
        lstExam.add(new Exam("Experiment 6 (A)",R.drawable.back2,"To determine the internal resistance of given primary cell using potentiometer."));
        lstExam.add(new Exam("Experiment 7 (A)",R.drawable.back2,"To determine resistance of a galvanometer by half-deflection method and to find its figure of merit."));
        lstExam.add(new Exam("Experiment 8 (A)",R.drawable.back2,"To convert the given galvanometer (of known resistance and figure of merit) into a voltmeter of desired range and to verify the same."));
        lstExam.add(new Exam("Experiment 9 (A)",R.drawable.back2,"To convert the given galvanometer (of known resistance and figure of merit) into an ammeter of desired range and to verify the same."));
        lstExam.add(new Exam("Experiment 10 (A)",R.drawable.back2,"To find the frequency of AC mains with a sonometer."));
        lstExam.add(new Exam("Experiment 1 (B)",R.drawable.back2,"To find the focal Length of a convex lens by plotting graphs between u and v or between 1/u and 1/v."));
        lstExam.add(new Exam("Experiment 2 (B)",R.drawable.back2,"To find the focal Length of a concave lens, using a convex lens."));
        lstExam.add(new Exam("Experiment 3 (B)",R.drawable.back2,"To determine angle of minimum deviation for a given prism by plotting a graph between angle of incidence and angle of deviation."));
        lstExam.add(new Exam("Experiment 4 (B)",R.drawable.back2,"To determine refractive index of a glass slab using a travelling microscope."));
        lstExam.add(new Exam("Experiment 5 (B)",R.drawable.back2,"To find refractive index of a liquid by using convex Lens and plane mirror."));
        lstExam.add(new Exam("Experiment 6 (B)",R.drawable.back2,"To draw the I-V characteristic curve for a p-n junction in forward bias and reverse bias."));
    }

    public void cbsechem12(){
        lstExam.add(new Exam("A. Chromatography",R.drawable.back2,"Separation of pigments from extracts of leaves and flowers by paper chromatography and determination of Rf values."));
        lstExam.add(new Exam("B. Preparation of Inorganic Compounds",R.drawable.back2,"Preparation of double salt of Ferrous Ammonium Sulphate or Potash Alum."));
        lstExam.add(new Exam("C. Tests for the functional groups",R.drawable.back2,"Unsaturation, alcoholic, phenolic, aldehydic, ketonic, carboxylic and amino (Primary) groups."));
        lstExam.add(new Exam("D. Characteristic tests and their detection",R.drawable.back2,"Tests of carbohydrates, fats and proteins in pure samples and their detection in given food stuffs."));
        lstExam.add(new Exam("E. Volumetric Analysis - Titration",R.drawable.back2,"Determination of concentration/Molarity of KMnO4 solution by titrating it against a std solution of Oxalic acid."));
        lstExam.add(new Exam("F(i). Surface chemistry ",R.drawable.back2,"To Prepare Lyophobic solution."));
        lstExam.add(new Exam("F(ii). Surface chemistry",R.drawable.back2,"To Prepare Lyophilic solution."));
        lstExam.add(new Exam("G. Qualitative analysis",R.drawable.back2,"Determination of one cation and one anion in a given salt."));

    }
    public void cbsebio12(){
        lstExam.add(new Exam("Experiment 1 (A)",R.drawable.back2,"Prepare a temporary mount of the onion root tip to study mitosis."));
        lstExam.add(new Exam("Experiment 2 (A)",R.drawable.back2,"Collect and study soil for texture, moisture content, pH & water holding capacity. Correlate with the kinds of plants found in them."));
        lstExam.add(new Exam("Experiment 3 (A)",R.drawable.back2,"Collect water and study them for pH, clarity and presence of any living organism."));
        lstExam.add(new Exam("Experiment 4 (A)",R.drawable.back2,"Study the effect of different temperatures & 3 different pH on the activity of salivary amylase on starch."));
        lstExam.add(new Exam("Experiment 5 (A)",R.drawable.back2,"Isolate DNA from available plant material such as spinach, green pea seeds, papaya, etc."));
        lstExam.add(new Exam("Experiment 1 (B)",R.drawable.back2,"Flowers adapted to pollination by different agencies (wind, insects, birds)."));
        lstExam.add(new Exam("Experiment 2 (B)",R.drawable.back2,"Identification of stages of gamete development, i.e., T.S. of testis and T.S. of ovary through permanent slides (from grasshopper mice)."));
        lstExam.add(new Exam("Experiment 3 (B)",R.drawable.back2,"Meiosis in onion bud cell or grasshopper testis through permanent slides."));
        lstExam.add(new Exam("Experiment 4 (B)",R.drawable.back2,"T.S. of blastula through permanent slides (Mammalian)."));
        lstExam.add(new Exam("Experiment 5 (B)",R.drawable.back2,"Prepared pedigree charts of any one of the genetic traits such as rolling of tongue, blood groups, ear lobes, widow’s peak and colour blindness."));
        lstExam.add(new Exam("Experiment 6 (B)",R.drawable.back2,"Common disease-causing organisms Like Ascaris, Entamoeba, Plasmodium, Ringworm. Comment on symptoms of diseases that they cause."));
        lstExam.add(new Exam("Experiment 7 (B)",R.drawable.back2,"Two plants and two animals (models/virtual images) found in xeric conditions. Comment upon their morphological adaptations."));
        lstExam.add(new Exam("Experiment 8 (B)",R.drawable.back2,"Two plants and two animals (models/virtual images) found in aquatic conditions. Comment upon their morphological adaptations"));
    }



    public void ciscephy10(){
        lstExam.add(new Exam("Experiment 1",R.drawable.back2,"Determine the mass of a metre rule using a spring balance or by balancing it on a knife-edge some point away."));
        lstExam.add(new Exam("Experiment 2",R.drawable.back2,"Determine the VR and MA of a given pulley system."));
        lstExam.add(new Exam("Experiment 3",R.drawable.back2,"Trace the course of different rays of light refracting through a rectangular glass slab."));
        lstExam.add(new Exam("Experiment 4",R.drawable.back2,"Determine the focal length of a convex lens by (a) the distant object method and (b) using a needle and a plane mirror."));
        lstExam.add(new Exam("Experiment 5",R.drawable.back2,"Determine the focal length of a convex lens by using two pins and formula f = uv/(u+v)."));
        lstExam.add(new Exam("Experiment 6",R.drawable.back2,"For a triangular prism, trace the course of rays passing through it, measure angles i1, i2, A and δ."));
        lstExam.add(new Exam("Experiment 7",R.drawable.back2,"For a ray of light incident normally (i1=0) on one face of a prism, trace course of the ray. Measure the angle δ."));
        lstExam.add(new Exam("Experiment 8",R.drawable.back2,"Calculate the sp. heat of the material of the given calorimeter."));
        }
    public void ciscechem10(){
        lstExam.add(new Exam("Experiment 1",R.drawable.back2,"Action of heat on the following substances: (a)Copper carbonate, zinc carbonate (b)zinc nitrate, copper nitrate, lead nitrate."));
        lstExam.add(new Exam("Experiment 1",R.drawable.back2,"Make a solution of the unknown substance."));
        lstExam.add(new Exam("Experiment 1",R.drawable.back2,"Supply a solution of dilute acid and alkali. Determine which is acidic and which is basic, giving two tests for each."));
        lstExam.add(new Exam("Experiment 1",R.drawable.back2,"Add concentrated hydrochloric acid to each of the given substances, warm them, make observations, identify any product and make deductions."));
        }
    public void ciscebio10(){
        lstExam.add(new Exam("Experiment 1",R.drawable.back2,"Observation of permanent slides of stages of mitosis."));
        lstExam.add(new Exam("Experiment 2",R.drawable.back2,"Experiments demonstrating diffusion, osmosis and absorption."));
        lstExam.add(new Exam("Experiment 3",R.drawable.back2,"Experiments on Transpiration."));
        lstExam.add(new Exam("Experiment 4",R.drawable.back2,"Experiments on Photosynthesis."));
        lstExam.add(new Exam("Experiment 5",R.drawable.back2,"Identification of the structures of the urinary system, heart and kidney (internal structure) and brain (external view)."));
        lstExam.add(new Exam("Experiment 6",R.drawable.back2,"The identification of different types of blood cells under a microscope."));
        lstExam.add(new Exam("Experiment 7",R.drawable.back2,"Identification of the internal structure of the Ear and Eye."));
        lstExam.add(new Exam("Experiment 8",R.drawable.back2,"Identification and location of selected endocrine glands: Adrenal, Pancreas, Thyroid and Pituitary glands."));
         }

    public void ciscephy12(){
        lstExam.add(new Exam("Experiment 1",R.drawable.back2,"To find focal length of a convex lens by using u-v method."));
        lstExam.add(new Exam("Experiment 2",R.drawable.back2,"To find f of a convex lens by displacement method."));
        lstExam.add(new Exam("Experiment 3",R.drawable.back2,"To determine the focal length of a given convex lens with the help of an auxiliary convex lens."));
        lstExam.add(new Exam("Experiment 4",R.drawable.back2,"To determine the focal length of a concave lens, using an auxiliary convex lens, not in contact."));
        lstExam.add(new Exam("Experiment 5",R.drawable.back2,"To determine focal length of concave mirror by using two pins (by u-v method)."));
        lstExam.add(new Exam("Experiment 6",R.drawable.back2,"To determine the refractive index of a liquid by using a convex lens and a plane mirror."));
        lstExam.add(new Exam("Experiment 7",R.drawable.back2,"To determine the focal length of a convex mirror using convex lens."));
        lstExam.add(new Exam("Experiment 8",R.drawable.back2,"Using a metre bridge, determine the resistance of about 100 cm wire. Calculate the specific resistance."));
        lstExam.add(new Exam("Experiment 9",R.drawable.back2,"Verify Ohm’s law for the given unknown resistance."));
        lstExam.add(new Exam("Experiment 10",R.drawable.back2,"To compare emfs of two cells using a potentiometer."));
        lstExam.add(new Exam("Experiment 11",R.drawable.back2,"To determine the internal resistance of a cell by a potentiometer."));
        lstExam.add(new Exam("Experiment 12",R.drawable.back2,"From a potentiometer set up, measure the fall in potential for increasing lengths of a constantan wire."));
        lstExam.add(new Exam("Experiment 13",R.drawable.back2,"To verify the laws of combination of resistances (series and parallel) using metre bridge."));
          }
    public void ciscechem12(){
        lstExam.add(new Exam("Experiment 1",R.drawable.back2,"Titrations Oxidation-reduction titrations."));
        lstExam.add(new Exam("Experiment 2",R.drawable.back2,"Study of the rate of reaction"));
        lstExam.add(new Exam("Experiment 3",R.drawable.back2,"Identification of the following compounds and functional groups based on observations."));
        lstExam.add(new Exam("Experiment 4",R.drawable.back2,"Characteristic tests of carbohydrates and proteins."));
        lstExam.add(new Exam("Experiment 5",R.drawable.back2,"Experiments related to pH change using pH paper or universal indicator."));
        lstExam.add(new Exam("Experiment 6",R.drawable.back2,"Electrochemistry. Setting up a simple voltaic cell."));
        lstExam.add(new Exam("Experiment 7",R.drawable.back2,"Qualitative analysis: identification of single salt containing one anion and one cation."));
           }
    public void ciscebio12(){
        lstExam.add(new Exam("Experiment 1",R.drawable.back2,"Taxonomy: Study floral characteristics through dissection of flowers."));
        lstExam.add(new Exam("Experiment 2 (i)",R.drawable.back2,"Study of arrangement/distribution of stomata in dicot and monocot leaves."));
        lstExam.add(new Exam("Experiment 2 (ii)",R.drawable.back2,"Study of soils from two different sites."));
        lstExam.add(new Exam("Experiment 2 (iii)",R.drawable.back2,"To study the effect of enzyme (amylase) action at three different temperatures and pH on starch solution."));
        lstExam.add(new Exam("Experiment 2 (iv)",R.drawable.back2,"To isolate DNA from available plant material."));
        lstExam.add(new Exam("Experiment 3 (i)",R.drawable.back2,"Germination of pollen grain in a nutrient medium."));
        lstExam.add(new Exam("Experiment 3 (ii)",R.drawable.back2,"T.S. of ovary of any locally available flower."));
        lstExam.add(new Exam("Experiment 3 (iii)",R.drawable.back2,"T.S. of a hydrophyte stem."));
        lstExam.add(new Exam("Experiment 3 (iv)",R.drawable.back2,"T.S. of a xerophytic leaf (Nerium)."));
        lstExam.add(new Exam("Experiment 3 (v)",R.drawable.back2," L.S. of monocot and dicot seed (soaked seeds of maize/wheat, pea/ bean)."));
         }
}