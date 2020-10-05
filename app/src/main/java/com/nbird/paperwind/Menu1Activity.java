package com.nbird.paperwind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Menu1Activity extends AppCompatActivity {

    List<Exam> lstExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        lstExam=new ArrayList<>();
        parto();
        RecyclerView myrv=(RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewAdapter myAdapter=new RecyclerViewAdapter(this,lstExam);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.share){
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.about){
            Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.history){
            Toast.makeText(this, "history", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.propic){
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.coins){
            Toast.makeText(this, "Money", Toast.LENGTH_SHORT).show();
        }
return true;
    }

    public void parto(){
        lstExam.add(new Exam("Jee Advance",R.drawable.back2,"Joint Entrance Examination – Advanced, formerly the Indian Institutes of Technology-Joint Entrance Examination, is an academic examination held annually in India."));
        lstExam.add(new Exam("Jee Mains",R.drawable.back14,"Joint Entrance Examination – Advanced, formerly the Indian Institutes of Technology-Joint Entrance Examination, is an academic examination held annually in India."));
        lstExam.add(new Exam("NEET",R.drawable.back2,"Joint Entrance Examination – Advanced, formerly the Indian Institutes of Technology-Joint Entrance Examination, is an academic examination held annually in India."));
        lstExam.add(new Exam("Vitee",R.drawable.back1,"Joint Entrance Examination – Advanced, formerly the Indian Institutes of Technology-Joint Entrance Examination, is an academic examination held annually in India."));
        lstExam.add(new Exam("Jee Advance",R.drawable.back2,"Joint Entrance Examination – Advanced, formerly the Indian Institutes of Technology-Joint Entrance Examination, is an academic examination held annually in India."));
        lstExam.add(new Exam("Jee Mains",R.drawable.back14,"Joint Entrance Examination – Advanced, formerly the Indian Institutes of Technology-Joint Entrance Examination, is an academic examination held annually in India."));
        lstExam.add(new Exam("NEET",R.drawable.back2,"Joint Entrance Examination – Advanced, formerly the Indian Institutes of Technology-Joint Entrance Examination, is an academic examination held annually in India."));
        lstExam.add(new Exam("Vitee",R.drawable.back1,"Joint Entrance Examination – Advanced, formerly the Indian Institutes of Technology-Joint Entrance Examination, is an academic examination held annually in India."));
        lstExam.add(new Exam("Jee Advance",R.drawable.back2,"Joint Entrance Examination – Advanced, formerly the Indian Institutes of Technology-Joint Entrance Examination, is an academic examination held annually in India."));
        lstExam.add(new Exam("Jee Mains",R.drawable.back14,"Joint Entrance Examination – Advanced, formerly the Indian Institutes of Technology-Joint Entrance Examination, is an academic examination held annually in India."));
        lstExam.add(new Exam("NEET",R.drawable.back2,"Joint Entrance Examination – Advanced, formerly the Indian Institutes of Technology-Joint Entrance Examination, is an academic examination held annually in India."));
        lstExam.add(new Exam("Vitee",R.drawable.back1,"Joint Entrance Examination – Advanced, formerly the Indian Institutes of Technology-Joint Entrance Examination, is an academic examination held annually in India."));

    }



}