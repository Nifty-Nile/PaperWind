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

public class EntranceExamPreviousRecyclerAdapter extends RecyclerView.Adapter<EntranceExamPreviousRecyclerAdapter.viewholder> {

    private List<EntranceExamPreviousRecylclerHolder> listItem;
    int position1;
    private InterstitialAd interstitialAd;

    public EntranceExamPreviousRecyclerAdapter(List<EntranceExamPreviousRecylclerHolder> listItem,int position1, InterstitialAd interstitialAd){
        this.listItem=listItem;
       this.position1=position1;
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



        public void setData(String imageurl, String name,final int set) {
            Glide.with(itemView.getContext()).load(imageurl).into(categoryImage);
            this.title.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interstitialAd.setAdListener(new AdListener(){
                        public void onAdClosed(){
                            super.onAdClosed();
                            interstitialAd.loadAd(new AdRequest.Builder().build());
                            Intent intent=new Intent(itemView.getContext(),EntranceExamPreviousPDFDisplayActivity.class);
                            intent.putExtra("position",position1);
                            intent.putExtra("set",set);
                            itemView.getContext().startActivity(intent);
                        }

                    });

                    if(interstitialAd.isLoaded()){
                        interstitialAd.show();
                        return;
                    }


                    Intent intent=new Intent(itemView.getContext(),EntranceExamPreviousPDFDisplayActivity.class);
                    intent.putExtra("position",position1);
                    intent.putExtra("set",set);
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}

