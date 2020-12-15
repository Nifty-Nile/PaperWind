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

import de.hdodenhof.circleimageview.CircleImageView;

public class CollegePredictorMainAdapter extends RecyclerView.Adapter<CollegePredictorMainAdapter.viewholder> {

    private List<CollegePredictorMainHolder> listItem;

    public CollegePredictorMainAdapter(List<CollegePredictorMainHolder> listItem) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.collegepredictormainasset,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(listItem.get(position).getImage(),listItem.get(position).getName(),listItem.get(position).getBranch());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private TextView title;
        private CircleImageView categoryImage;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.categoryTitle);
            categoryImage=itemView.findViewById(R.id.categoryImage);

        }

        public void setData(String imageUrl, final String title, final int branch) {
            Glide.with(itemView.getContext()).load(imageUrl).into(categoryImage);
            this.title.setText(title);
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(itemView.getContext(),DemoActivity.class);
                    intent.putExtra("title",title);
                    intent.putExtra("branch",branch);
                    itemView.getContext().startActivity(intent);
                }
            });*/

        }
    }
}
