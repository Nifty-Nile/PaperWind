package com.nbird.paperwind;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterLink extends RecyclerView.Adapter<RecyclerViewAdapterLink.MyViewHolder> {
    private Context mContext;
    private List<Exam> mData;
    int Exam,Std,Paper;


    public RecyclerViewAdapterLink(Context mContext,List<Exam> mData,int Exam,int Std,int Paper){
        this.mContext=mContext;
        this.mData=mData;
        this.Exam=Exam;
        this.Std=Std;
        this.Paper=Paper;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.link_tab_activity,parent,false);


        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tv_exam_title.setText(mData.get(position).getTitle());
        holder.img_exam_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.exam_dis.setText(mData.get(position).getDis());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(Exam==1){
                  if(Std==1){
                      if(Paper==3){
                          switch (position){
                              case 0:
                                  Intent browserIntents0=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                  mContext.startActivity(browserIntents0);
                                  break;
                              case 1:
                                  Intent browserIntents1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntents1);
                                  break;
                              case 2:
                                  Intent browserIntents2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntents2);
                                  break;
                          }
                      }
                  }else {
                      if(Paper==3){
                          switch (position){
                              case 0:
                                  Intent browserIntentq0=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                  mContext.startActivity(browserIntentq0);
                                  break;
                              case 1:
                                  Intent browserIntentq1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentq1);
                                  break;
                              case 2:
                                  Intent browserIntentq2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentq2);
                                  break;
                          }
                      }
                  }
              }else{
                  if(Std==1){
                      if(Paper==3){
                          switch (position){
                              case 0:
                                  Intent browserIntentr0=new Intent(Intent.ACTION_VIEW, Uri.parse("http://farmer.gov.in/"));
                                  mContext.startActivity(browserIntentr0);
                                  break;
                              case 1:
                                  Intent browserIntentr1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentr1);
                                  break;
                              case 2:
                                  Intent browserIntentr2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentr2);
                                  break;
                          }
                      }
                  }else {
                      if(Paper==3){
                          switch (position){
                              case 0:
                                  Intent browserIntentp0=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                  mContext.startActivity(browserIntentp0);
                                  break;
                              case 1:
                                  Intent browserIntentp1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentp1);
                                  break;
                              case 2:
                                  Intent browserIntentp2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentp2);
                                  break;
                          }
                      }
                  }
              }




            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_exam_title;
        ImageView img_exam_thumbnail;
        TextView exam_dis;
        CardView cardView;


        public MyViewHolder(View itemView){
            super(itemView);

            tv_exam_title=(TextView) itemView.findViewById(R.id.exam_title);
            img_exam_thumbnail=(ImageView) itemView.findViewById(R.id.exam_img_id);
            exam_dis=(TextView) itemView.findViewById(R.id.exam_dis);
            cardView=(CardView) itemView.findViewById(R.id.cardview_id);

        }
    }

}
