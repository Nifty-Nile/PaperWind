package com.nbird.paperwind;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterLink extends RecyclerView.Adapter<RecyclerViewAdapterLink.MyViewHolder> {
    private Context mContext;
    private List<Exam> mData;
    int Exam,Std,Paper,Labsub;


    public RecyclerViewAdapterLink(Context mContext,List<Exam> mData,int Exam,int Std,int Paper,int Labsub){
        this.mContext=mContext;
        this.mData=mData;
        this.Exam=Exam;
        this.Std=Std;
        this.Paper=Paper;
        this.Labsub=Labsub;

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
                          if(Labsub==1) {
                              switch (position) {
                                  case 0:
                                      Intent browserIntentxp0 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=i1mfTg-zFsQ&list=PLwBJMY-LXQs9d7SYR973W0rd1M01eljwo&index=9"));
                                      mContext.startActivity(browserIntentxp0);
                                      break;
                                  case 1:
                                      Intent browserIntentxp1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=r0OfAIEa878&list=PLwBJMY-LXQs9d7SYR973W0rd1M01eljwo&index=8"));
                                      mContext.startActivity(browserIntentxp1);
                                      break;
                                  case 2:
                                      Intent browserIntentxp2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=n8DL_z2fIcg&list=PLwBJMY-LXQs9d7SYR973W0rd1M01eljwo&index=7"));
                                      mContext.startActivity(browserIntentxp2);
                                      break;
                                  case 3:
                                      Intent browserIntentxp3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=E-zQ51o90tI&list=PLwBJMY-LXQs9d7SYR973W0rd1M01eljwo&index=14"));
                                      mContext.startActivity(browserIntentxp3);
                                      break;
                                  case 4:
                                      Intent browserIntentxp4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=xY6AYgkX2RI&list=PLwBJMY-LXQs9d7SYR973W0rd1M01eljwo&index=13"));
                                      mContext.startActivity(browserIntentxp4);
                                      break;
                                  case 5:
                                      Intent browserIntentxp5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=kkhd4Cg85vI"));
                                      mContext.startActivity(browserIntentxp5);
                                      break;
                                  case 6:
                                      Intent browserIntentxp6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iOJa4fooldA"));
                                      mContext.startActivity(browserIntentxp6);
                                      break;
                              }
                          }
                              if (Labsub == 2) {

                                  switch (position) {

                                      case 0:
                                          Intent browserIntentxc7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=hEnqBPoL_4E"));
                                          mContext.startActivity(browserIntentxc7);
                                          break;
                                      case 1:
                                          Intent browserIntentxc8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=FCNYhFyhk3U"));
                                          mContext.startActivity(browserIntentxc8);
                                          break;
                                      case 2:
                                          Intent browserIntentxc9 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=oRNuhlDW3k0"));
                                          mContext.startActivity(browserIntentxc9);
                                          break;
                                      case 3:
                                          Intent browserIntentxc10 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=fgY4CJwYH3I"));
                                          mContext.startActivity(browserIntentxc10);
                                          break;
                                      case 4:
                                          Intent browserIntentxc11 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=-hE2Ro8DANA&list=PLwBJMY-LXQs9d7SYR973W0rd1M01eljwo&index=18"));
                                          mContext.startActivity(browserIntentxc11);
                                          break;
                                      case 5:
                                          Intent browserIntentxc12 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=qcZzmU2DOh4&list=PLwBJMY-LXQs9d7SYR973W0rd1M01eljwo&index=10"));
                                          mContext.startActivity(browserIntentxc12);
                                          break;
                                      case 6:
                                          Intent browserIntentxc13 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=MDRFGyJAuKU"));
                                          mContext.startActivity(browserIntentxc13);
                                          break;

                                  }
                              }
                              if (Labsub == 3) {

                                  switch (position){

                                  case 0:
                                      Intent browserIntentxb14 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=FOLDv1JLzeE"));
                                      mContext.startActivity(browserIntentxb14);
                                      break;
                                  case 1:
                                      Intent browserIntentxb15 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=qwE5_P3cqOU&list=PLwBJMY-LXQs9d7SYR973W0rd1M01eljwo&index=16"));
                                      mContext.startActivity(browserIntentxb15);
                                      break;
                                  case 2:
                                      Intent browserIntentxb16 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=34ESzqzf_Uo&list=PLwBJMY-LXQs9d7SYR973W0rd1M01eljwo&index=6"));
                                      mContext.startActivity(browserIntentxb16);
                                      break;
                                  case 3:
                                      Intent browserIntentxb17 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tZ3OTdJnBis"));
                                      mContext.startActivity(browserIntentxb17);
                                      break;
                                  case 4:
                                      Intent browserIntentxb18 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Edcz_MzM5qc&list=PLB7R1sRBe0tmLGMigOQEwtGITmmByy4Ox&index=17"));
                                      mContext.startActivity(browserIntentxb18);
                                      break;
                                  case 5:
                                      Intent browserIntentxb19 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=SANJW_ZIYrc"));
                                      mContext.startActivity(browserIntentxb19);
                                      break;
                              }
                          }

                      }
                  }else {
                      if(Paper==3){
                          if(Labsub==1) {
                              switch (position) {

                                  case 0:
                                      Intent browserIntentq0 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                      mContext.startActivity(browserIntentq0);
                                      break;
                              }
                          }
                          else if(Labsub==2){
                              switch (position){
                                  case 0:
                                      Intent browserIntentq1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                      mContext.startActivity(browserIntentq1);
                                      break;
                              }
                          }else if(Labsub==3) {
                              switch (position) {
                                  case 0:
                                      Intent browserIntentq2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                      mContext.startActivity(browserIntentq2);
                                      break;
                              }
                          }
                      }
                  }
              }else{
                  if(Std==1){
                      if(Paper==3){
                          if(Labsub==1) {
                              switch (position) {

                                  case 0:
                                      Intent browserIntentq0 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                      mContext.startActivity(browserIntentq0);
                                      break;
                              }
                          }
                          else if(Labsub==2){
                              switch (position){
                                  case 0:
                                      Intent browserIntentq1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                      mContext.startActivity(browserIntentq1);
                                      break;
                              }
                          }else if(Labsub==3) {
                              switch (position) {
                                  case 0:
                                      Intent browserIntentq2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                      mContext.startActivity(browserIntentq2);
                                      break;
                              }
                          }
                      }
                  }else {
                      if(Paper==3){
                          if(Labsub==1) {
                              switch (position) {

                                  case 0:
                                      Intent browserIntentq0 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricoop.nic.in/"));
                                      mContext.startActivity(browserIntentq0);
                                      break;
                              }
                          }
                          else if(Labsub==2){
                              switch (position){
                                  case 0:
                                      Intent browserIntentq1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                      mContext.startActivity(browserIntentq1);
                                      break;
                              }
                          }else if(Labsub==3) {
                              switch (position) {
                                  case 0:
                                      Intent browserIntentq2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://agricollegenews.com/"));
                                      mContext.startActivity(browserIntentq2);
                                      break;
                              }
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
        LinearLayout linearLayout;



        public MyViewHolder(View itemView){
            super(itemView);

            tv_exam_title=(TextView) itemView.findViewById(R.id.exam_title);
            img_exam_thumbnail=(ImageView) itemView.findViewById(R.id.exam_img_id);
            exam_dis=(TextView) itemView.findViewById(R.id.exam_dis);
            cardView=(CardView) itemView.findViewById(R.id.cardview_id);



        }
    }

}
