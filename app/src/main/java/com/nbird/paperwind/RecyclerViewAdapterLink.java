package com.nbird.paperwind;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        holder.tv_exam_title.setText(mData.get(position).getTitle());
        holder.exam_dis.setText(mData.get(position).getDis());
        holder.exam_img_id.setImageResource(mData.get(position).getThumbnail());




        switch (Labsub){
            case 1:
                holder.cardView.setCardBackgroundColor(Color.parseColor("#ADE1F5"));break;
            case 2:
                holder.cardView.setCardBackgroundColor(Color.parseColor("#FAF8C8"));
                holder.tv_exam_title.setBackgroundResource(R.drawable.lightyellow2);break;

            case 3:
                holder.cardView.setCardBackgroundColor(Color.parseColor("#CEFAC8"));
                holder.tv_exam_title.setBackgroundResource(R.drawable.lightgreen2);break;
        }





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
                                      Intent browserIntentxiip0 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=n3w5c2VYcoU"));
                                      mContext.startActivity(browserIntentxiip0);
                                      break;
                                  case 1:
                                      Intent browserIntentxiip1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=2kdcR1QBqAI&t=7s"));
                                      mContext.startActivity(browserIntentxiip1);
                                      break;
                                  case 2:
                                      Intent browserIntentxiip2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ZUBihmkuEic"));
                                      mContext.startActivity(browserIntentxiip2);
                                      break;
                                  case 3:
                                      Intent browserIntentxiip3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ZUBihmkuEic"));
                                      mContext.startActivity(browserIntentxiip3);
                                      break;
                                  case 4:
                                      Intent browserIntentxiip4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=sGcJTeVzqug"));
                                      mContext.startActivity(browserIntentxiip4);
                                      break;
                                  case 5:
                                      Intent browserIntentxiip5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=4cHOeGqZQrc"));
                                      mContext.startActivity(browserIntentxiip5);
                                      break;
                                  case 6:
                                      Intent browserIntentxiip6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dNRXVJozwOY"));
                                      mContext.startActivity(browserIntentxiip6);
                                      break;
                                  case 7:
                                      Intent browserIntentxiip7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=j66SG4uOyEU"));
                                      mContext.startActivity(browserIntentxiip7);
                                      break;
                                  case 8:
                                      Intent browserIntentxiip8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=j66SG4uOyEU"));
                                      mContext.startActivity(browserIntentxiip8);
                                      break;
                                  case 9:
                                      Intent browserIntentxiip9 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=K-5O1OWQwRA"));
                                      mContext.startActivity(browserIntentxiip9);
                                      break;
                                  case 10:
                                      Intent browserIntentxiip10 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6nO9bQMI7UE"));
                                      mContext.startActivity(browserIntentxiip10);
                                      break;
                                  case 11:
                                      Intent browserIntentxiip11 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6nO9bQMI7UE"));
                                      mContext.startActivity(browserIntentxiip11);
                                      break;
                                  case 12:
                                      Intent browserIntentxiip12 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=AMjCrwoA77A"));
                                      mContext.startActivity(browserIntentxiip12);
                                      break;
                                  case 13:
                                      Intent browserIntentxiip13 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=2BB7p7fdSzc"));
                                      mContext.startActivity(browserIntentxiip13);
                                      break;
                                  case 14:
                                      Intent browserIntentxiip14 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Hcnk9sxTLek"));
                                      mContext.startActivity(browserIntentxiip14);
                                      break;
                                  case 15:
                                      Intent browserIntentxiip15 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=m1wRzTq0m5M"));
                                      mContext.startActivity(browserIntentxiip15);
                                      break;
                              }
                          }
                          else if(Labsub==2){
                              switch (position){
                                  case 0:
                                      Intent browserIntentxiic0=new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/rwSf_12aMfE"));
                                      mContext.startActivity(browserIntentxiic0);
                                      break;
                                  case 1:
                                      Intent browserIntentxiic1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=_9_tsRZkoYM"));
                                      mContext.startActivity(browserIntentxiic1);
                                      break;
                                  case 2:
                                      Intent browserIntentxiic2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.olabs.edu.in/?sub=73&brch=8&sim=141&cnt=715"));
                                      mContext.startActivity(browserIntentxiic2);
                                      break;
                                  case 3:
                                      Intent browserIntentxiic3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/d-I2JSlEKtA"));
                                      mContext.startActivity(browserIntentxiic3);
                                      break;
                                  case 4:
                                      Intent browserIntentxiic4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/71MTp2Ley3U"));
                                      mContext.startActivity(browserIntentxiic4);
                                      break;
                                  case 5:
                                      Intent browserIntentxiic5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/MzmSFocMdJk"));
                                      mContext.startActivity(browserIntentxiic5);
                                      break;
                                  case 6:
                                      Intent browserIntentxiic6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/L3LixkCN5tM"));
                                      mContext.startActivity(browserIntentxiic6);
                                      break;
                                  case 7:
                                      Intent browserIntentxiic7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=gyxgVsXMYq0&list=PL7jfMV2bTYmqnYac3pdt9uaDNCXvffayK"));
                                      mContext.startActivity(browserIntentxiic7);
                                      break;
                              }
                          }else if(Labsub==3) {
                              switch (position) {
                                  case 0:
                                      Intent browserIntentxiib0 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=N-nIHhncEao&list=PLdByiA-Hwam--z3TK_lEoWFs3TgCtRFUu&index=2&t=3s"));
                                      mContext.startActivity(browserIntentxiib0);
                                      break;
                                  case 1:
                                      Intent browserIntentxiib1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=eftNwlrQ7Fo&list=PLdByiA-Hwam--z3TK_lEoWFs3TgCtRFUu&index=6"));
                                      mContext.startActivity(browserIntentxiib1);
                                      break;
                                  case 2:
                                      Intent browserIntentxiib2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=o1yYy-V2CAs&list=PLdByiA-Hwam--z3TK_lEoWFs3TgCtRFUu&index=5"));
                                      mContext.startActivity(browserIntentxiib2);
                                      break;
                                  case 3:
                                      Intent browserIntentxiib3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://newtonclasses.net/study-the-effect-of-different-temperatures-3-different-ph-on-the-activity-of-salivary-amylase-on-starch/"));
                                      mContext.startActivity(browserIntentxiib3);
                                      break;
                                  case 4:
                                      Intent browserIntentxiib4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://newtonclasses.net/biology-practical-class-12/isolate-dna-from-available-plant-material-such-as-spinach-green-pea-seeds-papaya-etc/"));
                                      mContext.startActivity(browserIntentxiib4);
                                      break;
                                  case 5:
                                      Intent browserIntentxiib5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://newtonclasses.net/biology-practical-class-12-study-of-pollen-germination/"));
                                      mContext.startActivity(browserIntentxiib5);
                                      break;
                                  case 6:
                                      Intent browserIntentxiib6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LLyG3sh6N7A"));
                                      mContext.startActivity(browserIntentxiib6);
                                      break;
                                  case 7:
                                      Intent browserIntentxiib7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=01WNGp8CoK8"));
                                      mContext.startActivity(browserIntentxiib7);
                                      break;
                                  case 8:
                                      Intent browserIntentxiib8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LLyG3sh6N7A"));
                                      mContext.startActivity(browserIntentxiib8);
                                      break;
                                  case 9:
                                      Intent browserIntentxiib9 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=11s5Biyi9q4&list=PLB7eslSsgXLaaOy4G-Pp3lG48YY46UJIX&index=9"));
                                      mContext.startActivity(browserIntentxiib9);
                                      break;
                                  case 10:
                                      Intent browserIntentxiib10 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=91A5zs5Mqfo"));
                                      mContext.startActivity(browserIntentxiib10);
                                      break;
                                  case 11:
                                      Intent browserIntentxiib11 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://study.com/academy/lesson/plant-adaptations-hydrophytes-mesophytes-xerophytes.html"));
                                      mContext.startActivity(browserIntentxiib11);
                                      break;
                                  case 12:
                                      Intent browserIntentxiib12 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://study.com/academy/lesson/adaptation-of-plants-in-an-aquatic-habitat.html"));
                                      mContext.startActivity(browserIntentxiib12);
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
                                      Intent browserIntenticxp0 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=qruu-O4bNkQ"));
                                      mContext.startActivity(browserIntenticxp0);
                                      break;
                                  case 1:
                                      Intent browserIntenticxp1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=qvwczLJRLj0"));
                                      mContext.startActivity(browserIntenticxp1);
                                      break;
                                  case 2:
                                      Intent browserIntenticxp2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=FWMja9K8-vc"));
                                      mContext.startActivity(browserIntenticxp2);
                                      break;
                                  case 3:
                                      Intent browserIntenticxp3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=AElLVGW9kxQ"));
                                      mContext.startActivity(browserIntenticxp3);
                                      break;
                                  case 4:
                                      Intent browserIntenticxp4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6C3UXLwvf-M"));
                                      mContext.startActivity(browserIntenticxp4);
                                      break;
                                  case 5:
                                      Intent browserIntenticxp5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=-nuM-Ujd2lE"));
                                      mContext.startActivity(browserIntenticxp5);
                                      break;
                                  case 6:
                                      Intent browserIntenticxp6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=-nuM-Ujd2lE"));
                                      mContext.startActivity(browserIntenticxp6);
                                      break;
                                  case 7:
                                      Intent browserIntenticxp7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=0sQsPFfGIHM"));
                                      mContext.startActivity(browserIntenticxp7);
                                      break;
                              }
                          }
                          else if(Labsub==2){
                              switch (position){
                                  case 0:
                                      Intent browserIntenticxc0=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=O_Mpmgd4egU&list=PLwHRG35htDQVZseVeT-0mGOo-T7UG8112"));
                                      mContext.startActivity(browserIntenticxc0);
                                      break;
                                  case 1:
                                      Intent browserIntenticxc1=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=pzZK1sX1_1U"));
                                      mContext.startActivity(browserIntenticxc1);
                                      break;
                                  case 2:
                                      Intent browserIntenticxc2=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=8Nsm-ndeBCc"));
                                      mContext.startActivity(browserIntenticxc2);
                                      break;
                                  case 3:
                                      Intent browserIntenticxc3=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=VRJcn50b21A"));
                                      mContext.startActivity(browserIntenticxc3);
                                      break;
                              }
                          }else if(Labsub==3) {
                              switch (position) {
                                  case 0:
                                      Intent browserIntenticxb0 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=W5EmSBq3uuU"));
                                      mContext.startActivity(browserIntenticxb0);
                                      break;
                                  case 1:
                                      Intent browserIntenticxb1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=_mMnbO31g3U&list=PLdByiA-Hwam--z3TK_lEoWFs3TgCtRFUu&index=17"));
                                      mContext.startActivity(browserIntenticxb1);
                                      break;
                                  case 2:
                                      Intent browserIntenticxb2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=gXocZZDDPaw"));
                                      mContext.startActivity(browserIntenticxb2);
                                      break;
                                  case 3:
                                      Intent browserIntenticxb3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=58ZoyWXH0OA"));
                                      mContext.startActivity(browserIntenticxb3);
                                      break;
                                  case 4:
                                      Intent browserIntenticxb4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=805VoHIIQCs"));
                                      mContext.startActivity(browserIntenticxb4);
                                      break;
                                  case 5:
                                      Intent browserIntenticxb5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=NMVFvY3znBA"));
                                      mContext.startActivity(browserIntenticxb5);
                                      break;
                                  case 6:
                                      Intent browserIntenticxb6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=E4h4o8L1ARg"));
                                      mContext.startActivity(browserIntenticxb6);
                                      break;
                                  case 7:
                                      Intent browserIntenticxb7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=hs4nh7dx8yw"));
                                      mContext.startActivity(browserIntenticxb7);
                                      break;
                              }
                          }
                      }
                  }else {
                      if(Paper==3){
                          if(Labsub==1) {
                              switch (position) {

                                  case 0:
                                      Intent browserIntenticxiip0 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6nO9bQMI7UE"));
                                      mContext.startActivity(browserIntenticxiip0);
                                      break;
                                  case 1:
                                      Intent browserIntenticxiip1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=UcaIC28BOHw"));
                                      mContext.startActivity(browserIntenticxiip1);
                                      break;
                                  case 2:
                                      Intent browserIntenticxiip2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=wU-8SokqyCw"));
                                      mContext.startActivity(browserIntenticxiip2);
                                      break;
                                  case 3:
                                      Intent browserIntenticxiip3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6nO9bQMI7UE"));
                                      mContext.startActivity(browserIntenticxiip3);
                                      break;
                                  case 4:
                                      Intent browserIntenticxiip4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=j8N1Z6338UQ"));
                                      mContext.startActivity(browserIntenticxiip4);
                                      break;
                                  case 5:
                                      Intent browserIntenticxiip5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Rw2WE81iAFc"));
                                      mContext.startActivity(browserIntenticxiip5);
                                      break;
                                  case 6:
                                      Intent browserIntenticxiip6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=POamqkQX630"));
                                      mContext.startActivity(browserIntenticxiip6);
                                      break;
                                  case 7:
                                      Intent browserIntenticxiip7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=B-W0eVv6fLI"));
                                      mContext.startActivity(browserIntenticxiip7);
                                      break;
                                  case 8:
                                      Intent browserIntenticxiip8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=OU-VWe8kErM"));
                                      mContext.startActivity(browserIntenticxiip8);
                                      break;
                                  case 9:
                                      Intent browserIntenticxiip9 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=sGcJTeVzqug"));
                                      mContext.startActivity(browserIntenticxiip9);
                                      break;
                                  case 10:
                                      Intent browserIntenticxiip10 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=PCGPGmtd3Ow"));
                                      mContext.startActivity(browserIntenticxiip10);
                                      break;
                                  case 11:
                                      Intent browserIntenticxiip11 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=mWE1R5eHtz8"));
                                      mContext.startActivity(browserIntenticxiip11);
                                      break;
                                  case 12:
                                      Intent browserIntenticxiip12 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ZUBihmkuEic"));
                                      mContext.startActivity(browserIntenticxiip12);
                                      break;
                              }
                          }
                          else if(Labsub==2){
                              switch (position){
                                  case 0:
                                      Intent browserIntenticxiic0=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=HDUd4KqBKa8"));
                                      mContext.startActivity(browserIntenticxiic0);
                                      break;
                                  case 1:
                                      Intent browserIntenticxiic1=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=q6qISqX8Ljk"));
                                      mContext.startActivity(browserIntenticxiic1);
                                      break;
                                  case 2:
                                      Intent browserIntenticxiic2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.olabs.edu.in/?sub=73&brch=8&sim=141&cnt=715"));
                                      mContext.startActivity(browserIntenticxiic2);
                                      break;
                                  case 3:
                                      Intent browserIntenticxiic3=new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/d-I2JSlEKtA"));
                                      mContext.startActivity(browserIntenticxiic3);
                                      break;
                                  case 4:
                                      Intent browserIntenticxiic4=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=8Nsm-ndeBCc"));
                                      mContext.startActivity(browserIntenticxiic4);
                                      break;
                                  case 5:
                                      Intent browserIntenticxiic5=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=KXko8KS1tqk"));
                                      mContext.startActivity(browserIntenticxiic5);
                                      break;
                                  case 6:
                                      Intent browserIntenticxiic6=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=gyxgVsXMYq0&list=PL7jfMV2bTYmqnYac3pdt9uaDNCXvffayK"));
                                      mContext.startActivity(browserIntenticxiic6);
                                      break;
                              }
                          }else if(Labsub==3) {
                              switch (position) {
                                  case 0:
                                      Intent browserIntenticxiib0 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=GR9oNlNY-aI&list=PLoiqVdX-Y1IvUsBSMW4ie6rrsagrFcEoV"));
                                      mContext.startActivity(browserIntenticxiib0);
                                      break;
                                  case 1:
                                      Intent browserIntenticxiib1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=IJLOmIb6q80"));
                                      mContext.startActivity(browserIntenticxiib1);
                                      break;
                                  case 2:
                                      Intent browserIntenticxiib2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=POivZf4vc-c"));
                                      mContext.startActivity(browserIntenticxiib2);
                                      break;
                                  case 3:
                                      Intent browserIntenticxiib3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=lXF6yjdyIgY&list=PLoiqVdX-Y1IvUsBSMW4ie6rrsagrFcEoV&index=12"));
                                      mContext.startActivity(browserIntenticxiib3);
                                      break;
                                  case 4:
                                      Intent browserIntenticxiib4 = new Intent(Intent.ACTION_VIEW, Uri.parse("youtube.com/watch?v=JDCRilb1rD0&list=PLoiqVdX-Y1IvUsBSMW4ie6rrsagrFcEoV&index=17"));
                                      mContext.startActivity(browserIntenticxiib4);
                                      break;
                                  case 5:
                                      Intent browserIntenticxiib5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=kBlg4sI_VyM&list=PLoiqVdX-Y1IvUsBSMW4ie6rrsagrFcEoV&index=3"));
                                      mContext.startActivity(browserIntenticxiib5);
                                      break;
                                  case 6:
                                      Intent browserIntenticxiib6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=uqC0e7oK26U"));
                                      mContext.startActivity(browserIntenticxiib6);
                                      break;
                                  case 7:
                                      Intent browserIntenticxiib7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=022uXou9j_Q"));
                                      mContext.startActivity(browserIntenticxiib7);
                                      break;
                                  case 8:
                                      Intent browserIntenticxiib8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=e2zT2W-pyWo"));
                                      mContext.startActivity(browserIntenticxiib8);
                                      break;
                                  case 9:
                                      Intent browserIntenticxiib9 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6_uRLqm8uTA"));
                                      mContext.startActivity(browserIntenticxiib9);
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
        ImageView exam_img_id;
        TextView exam_dis;
        CardView cardView;




        public MyViewHolder(View itemView){
            super(itemView);

            tv_exam_title=(TextView) itemView.findViewById(R.id.exam_title);
            exam_img_id=(ImageView) itemView.findViewById(R.id.exam_img_id);
            exam_dis=(TextView) itemView.findViewById(R.id.exam_dis);
            cardView=(CardView) itemView.findViewById(R.id.cardview_id);



        }
    }

}
