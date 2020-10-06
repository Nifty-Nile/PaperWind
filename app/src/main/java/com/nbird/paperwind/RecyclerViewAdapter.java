package com.nbird.paperwind;

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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
private Context mContext;
private List<Exam> mData;

public RecyclerViewAdapter(Context mContext,List<Exam> mData){
    this.mContext=mContext;
    this.mData=mData;
}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.cardview_item_exams,parent,false);

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
            Intent intent=new Intent(mContext,BoardYearActivity.class);
            intent.putExtra("Title",mData.get(position).getTitle());
            intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
            mContext.startActivity(intent);
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
