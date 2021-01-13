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

public class FormulaAdapter extends RecyclerView.Adapter<FormulaAdapter.viewholder> {

    private List<FormulaHolder> listItem;


    int std,subject;


    public FormulaAdapter(List<FormulaHolder> listItem, int std, int subject) {
        this.listItem = listItem;
        this.std=std;
        this.subject=subject;
    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.formula_custom_ui,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(listItem.get(position).getUrl(),listItem.get(position).getTitle(),listItem.get(position).getSet());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private TextView title1;
        private ImageView categoryImage;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            title1=itemView.findViewById(R.id.title);
            categoryImage=itemView.findViewById(R.id.imageicon);
        }

        public void setData(String imageUrl, final String title, final int set) {
            Glide.with(itemView.getContext()).load(imageUrl).into(categoryImage);
            this.title1.setText(title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(itemView.getContext(),Formula_Pdf_Activity.class);
                    intent.putExtra("Subject100",subject);
                    intent.putExtra("Std100",std);
                    intent.putExtra("set",set);
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}

