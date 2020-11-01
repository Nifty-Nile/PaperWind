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

public class TestRecyclerAdapter extends RecyclerView.Adapter<TestRecyclerAdapter.viewholder> {

    private List<TestRecyclerHolder> listItem;
    int position,mode;

    public TestRecyclerAdapter(List<TestRecyclerHolder> listItem,int position,int mode){
        this.listItem=listItem;
        this.position=position;
        this.mode=mode;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_exams,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(listItem.get(position).getImage(),listItem.get(position).getTitle(),listItem.get(position).getDis(),listItem.get(position).getSet(),listItem.get(position).getPhy(),listItem.get(position).getChem(),listItem.get(position).getMaths(),listItem.get(position).getBio());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView categoryImage;
        private TextView dis;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.exam_title);
            dis=itemView.findViewById(R.id.exam_dis);
            categoryImage=itemView.findViewById(R.id.exam_img_id);
        }

        public void setData(String imageurl, String title, String dis,final int set,final int phy,final int chem,final int maths,final int bio) {
            Glide.with(itemView.getContext()).load(imageurl).into(categoryImage);
            this.title.setText(title);
            this.dis.setText(dis);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(itemView.getContext(),TestAgrementActivity.class);
                    intent.putExtra("position",position);
                    intent.putExtra("mode",mode);
                    intent.putExtra("set",set);
                    intent.putExtra("phy",phy);
                    intent.putExtra("chem",chem);
                    intent.putExtra("maths",maths);
                    intent.putExtra("bio",bio);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}

