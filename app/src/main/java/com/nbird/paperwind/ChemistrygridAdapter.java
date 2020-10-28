package com.nbird.paperwind;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChemistrygridAdapter extends BaseAdapter {
    private int sets=0;
    private int position=0;
    private int mode=0;
    private int set=0;
    private int phy=0;
    private int maths=0;
    private int score=0;
    private Context context;
    private String text;
    private TextView timer;
    private long sec;
    private int minutes;


    public ChemistrygridAdapter(Context context,int sets,int position,int mode,int set,int phy,int maths,int score,TextView timer,long sec,int minutes) {
        this.sets = sets;
        this.position = position;
        this.mode = mode;
        this.set = set;
        this.phy=phy;
        this.maths=maths;
        this.score=score;
        this.context=context;
        this.timer=timer;
        this.sec=sec;
        this.minutes=minutes;
    }

    @Override
    public int getCount() {
        return sets;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup parent) {
        View view1;
        final SharedPreferences coloryellow = context.getSharedPreferences("ChemistryColour", 0);
        final SharedPreferences.Editor editoryellow = coloryellow.edit();

        final SharedPreferences reviewchem = context.getSharedPreferences("ReviewPurpleChem", 0);
        final SharedPreferences.Editor editorreviewchem = reviewchem.edit();

        final SharedPreferences notanswered2 = context.getSharedPreferences("notanswered2", 0);
        final SharedPreferences.Editor editornotanswered2 = notanswered2.edit();




        if(view==null){
            view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.customgridchem,parent,false);
            boolean answerA1 = coloryellow.getBoolean(String.valueOf(i), false);
            boolean answerA2 = reviewchem.getBoolean(String.valueOf(i+1), false);
            boolean answerA3 = notanswered2.getBoolean(String.valueOf(i), false);
            if(answerA2){
                view1.setBackgroundResource(R.drawable.purplebuttons);
            }else if(answerA3){
                view1.setBackgroundResource(R.drawable.roundbuttonsorange);
            }
            else if(answerA1){
                view1.setBackgroundResource(R.drawable.roundbuttonsgreen);
            }else{
                view1.setBackgroundResource(R.drawable.greybutton);
            }

        }else{
            view1=view;
            boolean answerA1 = coloryellow.getBoolean(String.valueOf(i), false);
            boolean answerA2 = reviewchem.getBoolean(String.valueOf(i+1), false);
            boolean answerA3 = notanswered2.getBoolean(String.valueOf(i), false);
            if(answerA2){
                view1.setBackgroundResource(R.drawable.purplebuttons);
            }
            else if(answerA3){
                view1.setBackgroundResource(R.drawable.roundbuttonsorange);
            }
            else if(answerA1){
                view1.setBackgroundResource(R.drawable.roundbuttonsgreen);
            }else{
                view1.setBackgroundResource(R.drawable.greybutton);
            }
        }
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editoryellow.putBoolean(String.valueOf(i), true);
                editoryellow.commit();

                text= (String) timer.getText();
                Intent intent=new Intent(parent.getContext(),TestMainDisplayActivityASAP.class);
                intent.putExtra("Chemnum",i+1);
                intent.putExtra("position",position);
                intent.putExtra("mode",mode);
                intent.putExtra("set",set);
                intent.putExtra("Subject",2);
                intent.putExtra("chem",sets);
                intent.putExtra("phy",phy);
                intent.putExtra("maths",maths);
                intent.putExtra("score",score);
                intent.putExtra("text",text);
                intent.putExtra("minutes",minutes);
                intent.putExtra("sec",sec);
                parent.getContext().startActivity(intent);
                ((Activity)parent.getContext()).finish();

            }
        });
        ((TextView)view1.findViewById(R.id.iconnumber)).setText(String.valueOf(i+1));
        return view1;
    }
}

