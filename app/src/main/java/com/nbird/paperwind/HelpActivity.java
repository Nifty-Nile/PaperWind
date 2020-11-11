package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelpActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ExpandableListView expandableListView;
    HashMap<String, List<String>> listChild;
    List<String> listHeader;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Guide Activity");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        expandableListView=(ExpandableListView) findViewById(R.id.expandablelist);
        listChild=ExpandableListData.getData();
        listHeader=new ArrayList<String>(listChild.keySet());
        customAdapter=new CustomAdapter(this,listHeader,listChild);
        expandableListView.setAdapter(customAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}