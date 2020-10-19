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

import javax.xml.validation.SchemaFactoryLoader;

public class MathsgridAdapter extends BaseAdapter {
    private int sets=0;
    private int position=0;
    private int mode=0;
    private int set=0;
    private int chem=0;
    private int phy=0;
    private int score=0;
    private Context context;
    private String text;
    private TextView timer;
    private long sec;
    private int minutes;


    public MathsgridAdapter(Context context,int sets,int position,int mode,int set,int chem,int phy,int score,TextView timer,long sec,int minutes) {
        this.sets = sets;
        this.position = position;
        this.mode = mode;
        this.set = set;
        this.chem= chem;
        this.phy=phy;
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
        final SharedPreferences colorred = context.getSharedPreferences("MathsColour", 0);
        final SharedPreferences.Editor editorred = colorred.edit();


        final SharedPreferences reviewmaths = context.getSharedPreferences("ReviewPurpleMaths", 0);
        final SharedPreferences.Editor editorreviewmaths = reviewmaths.edit();

        final SharedPreferences notanswered3 = context.getSharedPreferences("notanswered3", 0);
        final SharedPreferences.Editor editornotanswered3 = notanswered3.edit();


        if(view==null){
            view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.customgridmaths,parent,false);
            boolean answerA1 = colorred.getBoolean(String.valueOf(i), false);
            boolean answerA2 = reviewmaths.getBoolean(String.valueOf(i+1), false);
            boolean answerA3 = notanswered3.getBoolean(String.valueOf(i), false);
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
            boolean answerA1 = colorred.getBoolean(String.valueOf(i), false);
            boolean answerA2 = reviewmaths.getBoolean(String.valueOf(i+1), false);
            boolean answerA3 = notanswered3.getBoolean(String.valueOf(i), false);
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

                editorred.putBoolean(String.valueOf(i), true);
                editorred.commit();

                text= (String) timer.getText();
                Intent intent=new Intent(parent.getContext(),TestMainDisplayActivityASAP.class);
                intent.putExtra("mathsnum",i+1);
                intent.putExtra("position",position);
                intent.putExtra("mode",mode);
                intent.putExtra("set",set);
                intent.putExtra("maths",sets);
                intent.putExtra("Subject",3);
                intent.putExtra("chem",chem);
                intent.putExtra("phy",phy);
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

