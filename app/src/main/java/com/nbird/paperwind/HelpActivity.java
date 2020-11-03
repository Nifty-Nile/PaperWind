package com.nbird.paperwind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelpActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    HashMap<String, List<String>> listChild;
    List<String> listHeader;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        expandableListView=(ExpandableListView) findViewById(R.id.expandablelist);
        listChild=ExpandableListData.getData();
        listHeader=new ArrayList<String>(listChild.keySet());
        customAdapter=new CustomAdapter(this,listHeader,listChild);
        expandableListView.setAdapter(customAdapter);


    }
}