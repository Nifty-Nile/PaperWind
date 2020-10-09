package com.nbird.paperwind;

import android.content.Context;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class RankMenuAdapter extends RecyclerView.Adapter<RankMenuAdapter.viewholder> {

    private final Context mContext;
    private List<RankMenuHolder> listItem;


    public RankMenuAdapter(Context mContext,List<RankMenuHolder> listItem){
        this.mContext=mContext;
        this.listItem=listItem;



    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rankpredictorcollegemenu,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, final int position) {
        holder.setData(listItem.get(position).getPic(),listItem.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(),RankPredictorInputActivity.class);
                intent.putExtra("RankEE",position);
                holder.itemView.getContext().startActivity(intent);
            }
        });
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
            title=itemView.findViewById(R.id.title);
            categoryImage=(ImageView) itemView.findViewById(R.id.imageicon);
        }

        public void setData(int pic, String title) {
            Glide.with(itemView.getContext()).load(pic).into(categoryImage);
            this.title.setText(title);


        }
    }
}

