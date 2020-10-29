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

import java.util.List;

public class LatestExamUpdatesAdapter extends RecyclerView.Adapter<LatestExamUpdatesAdapter.viewholder> {

    private List<LatestExamUpdatesHolder> listItem;


    public LatestExamUpdatesAdapter(List<LatestExamUpdatesHolder> listItem){
        this.listItem=listItem;


    }




    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.latestnewscustomshow,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(listItem.get(position).getExampic(),listItem.get(position).getDis(),listItem.get(position).getHead(),listItem.get(position).getOptionalpic());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView examImage;
        private TextView discribe;
        private ImageView optionImage;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.exam_title);
            examImage=itemView.findViewById(R.id.exam_img_id);
            discribe=itemView.findViewById(R.id.exam_dis);
            optionImage=itemView.findViewById(R.id.image1);

        }

        public void setData(String exampic, String dis, String title,String optionalImage) {
            Glide.with(itemView.getContext()).load(exampic).into(examImage);
            this.discribe.setText(dis);
            Glide.with(itemView.getContext()).load(optionalImage).into(optionImage);
            this.title.setText(title);

        }
    }
}

