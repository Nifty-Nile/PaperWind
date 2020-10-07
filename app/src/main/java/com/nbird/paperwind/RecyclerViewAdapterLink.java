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
                                  Intent browserIntentxp0=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                  mContext.startActivity(browserIntentxp0);
                                  break;
                              case 1:
                                  Intent browserIntentxp1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentxp1);
                                  break;
                              case 2:
                                  Intent browserIntentxp2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentxp2);
                                  break;
                              case 3:
                                  Intent browserIntentxp3=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                  mContext.startActivity(browserIntentxp3);
                                  break;
                              case 4:
                                  Intent browserIntentxp4=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                  mContext.startActivity(browserIntentxp4);
                                  break;
                              case 5:
                                  Intent browserIntentxp5=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentxp5);
                                  break;
                              case 6:
                                  Intent browserIntentxp6=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentxp6);
                                  break;
                              case 7:
                                  Intent browserIntentxc7=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                  mContext.startActivity(browserIntentxc7);
                                  break;
                              case 8:
                                  Intent browserIntentxc8=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                  mContext.startActivity(browserIntentxc8);
                                  break;
                              case 9:
                                  Intent browserIntentxc9=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentxc9);
                                  break;
                              case 10:
                                  Intent browserIntentxc10=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentxc10);
                                  break;
                              case 11:
                                  Intent browserIntentxc11=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                  mContext.startActivity(browserIntentxc11);
                                  break;
                              case 12:
                                  Intent browserIntentxc12=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                  mContext.startActivity(browserIntentxc12);
                                  break;
                              case 13:
                                  Intent browserIntentxc13=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentxc13);
                                  break;
                              case 14:
                                  Intent browserIntentxb14=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentxb14);
                                  break;
                              case 15:
                                  Intent browserIntentxb15=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                  mContext.startActivity(browserIntentxb15);
                                  break;
                              case 16:
                                  Intent browserIntentxb16=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                  mContext.startActivity(browserIntentxb16);
                                  break;
                              case 17:
                                  Intent browserIntentxb17=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentxb17);
                                  break;
                              case 18:
                                  Intent browserIntentxb18=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                  mContext.startActivity(browserIntentxb18);
                                  break;
                              case 19:
                                  Intent browserIntentxb19=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                  mContext.startActivity(browserIntentxb19);
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
