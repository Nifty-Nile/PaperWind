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

public class RefAdapter extends RecyclerView.Adapter<RefAdapter.viewholder> {

    private List<RefHolder> listItem;


    public RefAdapter(List<RefHolder> listItem){
        this.listItem=listItem;


    }




    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.collegepredictormainasset,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(listItem.get(position).getUrl(),listItem.get(position).getMail());
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

        public void setData(String imageurl, String mail) {
            Glide.with(itemView.getContext()).load(imageurl).into(categoryImage);
            this.title.setText(mail);


        }
    }
}

