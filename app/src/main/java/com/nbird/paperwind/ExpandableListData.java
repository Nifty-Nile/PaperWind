package com.nbird.paperwind;

import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListData {
    public static HashMap<String, List<String>> getData(){
        HashMap<String,List<String>> expandableListDetail=new HashMap<String,List<String>>();
        List<String> BoardExams=new ArrayList<String>();

        BoardExams.add("Previous Year's Paper");
        BoardExams.add("Sample Paper");
        BoardExams.add("Practical Lab Videos Links");
        BoardExams.add("NCERT Solutions");

        List<String> EntranceExam=new ArrayList<String>();

        EntranceExam.add("Entrance Exams Previous Year's Paper");
        EntranceExam.add("Entrance Exams Live Test");

        List<String> Predictor=new ArrayList<String>();

        Predictor.add("Rank Predictor");
        Predictor.add("College Predictor");

        List<String> Formual=new ArrayList<String>();

        Formual.add("Std 9 & 10");
        Formual.add("Std 11 & 12");

        List<String> Money=new ArrayList<String>();

        Money.add("Paper Notes By Ads");
        Money.add("Paper Notes By Direct Money Transfer");


        List<String> News=new ArrayList<String>();

        News.add("Live Updates");

        expandableListDetail.put("Board Exams",BoardExams);
        expandableListDetail.put("Entrance Exam",EntranceExam);
        expandableListDetail.put("Predictor",Predictor);
        expandableListDetail.put("Formual",Formual);
        expandableListDetail.put("Money",Money);
        expandableListDetail.put("News",News);

        return expandableListDetail;

    }
}
