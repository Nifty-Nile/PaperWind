package com.nbird.paperwind;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class PhysicsgridAdapter extends BaseAdapter {
    private int sets=0;
    private int position=0;
    private int mode=0;
    private int set=0;
    private int chem=0;
    private int maths=0;
    private int score=0;
    private Context context;
    private TextView timer;
    private String text;
    private long sec;
    private int minutes;
    private CountDownTimer countDownTimer;



    public PhysicsgridAdapter(Context context,int sets,int position,int mode,int set,int chem,int maths,int score,TextView timer,long sec,int minutes) {
        this.sets = sets;
        this.position = position;
        this.mode = mode;
        this.set = set;
        this.chem=chem;
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
        final View view1;
        final SharedPreferences colorblue = context.getSharedPreferences("PhysicsColour", 0);
        final SharedPreferences.Editor editorblue = colorblue.edit();

        final SharedPreferences reviewphy = context.getSharedPreferences("ReviewPurplePhy", 0);
        final SharedPreferences.Editor editorreviewphy = reviewphy.edit();

        final SharedPreferences notanswered1 = context.getSharedPreferences("notanswered1", 0);
        final SharedPreferences.Editor editornotanswered1 = notanswered1.edit();

        if(view==null){
            view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.customgrid,parent,false);
            boolean answerA1 = colorblue.getBoolean(String.valueOf(i), false);
            boolean answerA2 = reviewphy.getBoolean(String.valueOf(i+1), false);
            boolean answerA3 = notanswered1.getBoolean(String.valueOf(i), false);
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
            boolean answerA1 = colorblue.getBoolean(String.valueOf(i), false);
            boolean answerA2 = reviewphy.getBoolean(String.valueOf(i+1), false);
            boolean answerA3 = notanswered1.getBoolean(String.valueOf(i), false);
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

                countDownTimer=new CountDownTimer(minutes*60*1000,1000) {
                    @Override
                    public void onTick(long l) {
                        sec=sec-1;

                        if(sec==0){
                            minutes--;

                            sec=60;
                        }

                    }



                    @Override
                    public void onFinish() {




                    }


                };


                editorblue.putBoolean(String.valueOf(i), true);
                editorblue.commit();




                text= (String) timer.getText();



                Intent intent=new Intent(parent.getContext(),TestMainDisplayActivityASAP.class);
                intent.putExtra("Phynum",i+1);
                intent.putExtra("position",position);
                intent.putExtra("mode",mode);
                intent.putExtra("set",set);
                intent.putExtra("Subject",1);
                intent.putExtra("phy",sets);
                intent.putExtra("chem",chem);
                intent.putExtra("maths",maths);
                intent.putExtra("score",score);
                intent.putExtra("minutes",minutes);
                intent.putExtra("sec",sec);
                intent.putExtra("text",text);





                parent.getContext().startActivity(intent);
                ((Activity)parent.getContext()).finish();

            }
        });

        ((TextView)view1.findViewById(R.id.iconnumber)).setText(String.valueOf(i+1));
        return view1;
    }
}

