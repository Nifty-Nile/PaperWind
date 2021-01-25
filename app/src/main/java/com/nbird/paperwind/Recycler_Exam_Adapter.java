package com.nbird.paperwind;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

public class Recycler_Exam_Adapter extends RecyclerView.Adapter<Recycler_Exam_Adapter.viewholder> {

    private List<Recycler_Exam_Holder> listItem;
    int Exam,Std,Paper,Chapter;
    private InterstitialAd interstitialAd;
    public Recycler_Exam_Adapter(List<Recycler_Exam_Holder> listItem,int Exam,int Std,int Paper,int Chapter,InterstitialAd interstitialAd){
        this.listItem=listItem;
        this.Exam=Exam;
        this.Std=Std;
        this.Paper=Paper;
        this.Chapter=Chapter;
        this.interstitialAd=interstitialAd;
    }




    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customexampdfdisplay,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
         holder.setData(listItem.get(position).getImageurl(),listItem.get(position).getName(),listItem.get(position).getSet());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView categoryImage;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.categoryTitle);
            categoryImage=itemView.findViewById(R.id.categoryImage);
        }

        public void setData(String imageurl, String name, final int set) {
            Glide.with(itemView.getContext()).load(imageurl).into(categoryImage);
            this.title.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    interstitialAd.setAdListener(new AdListener(){
                        public void onAdClosed(){
                            super.onAdClosed();
                            interstitialAd.loadAd(new AdRequest.Builder().build());
                            Intent intent=new Intent(itemView.getContext(),Pdf_Display_Activity.class);
                            intent.putExtra("Exam",Exam);
                            intent.putExtra("Std",Std);
                            intent.putExtra("Paper",Paper);
                            intent.putExtra("Chapter",Chapter);
                            intent.putExtra("set",set);
                            itemView.getContext().startActivity(intent);
                        }

                    });

                    if(interstitialAd.isLoaded()){
                        interstitialAd.show();
                        return;
                    }


                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(itemView.getContext(),Pdf_Display_Activity.class);
                            intent.putExtra("Exam",Exam);
                            intent.putExtra("Std",Std);
                            intent.putExtra("Paper",Paper);
                            intent.putExtra("Chapter",Chapter);
                            intent.putExtra("set",set);
                            itemView.getContext().startActivity(intent);
                        }
                    });
                }
            });




        }
    }
}
