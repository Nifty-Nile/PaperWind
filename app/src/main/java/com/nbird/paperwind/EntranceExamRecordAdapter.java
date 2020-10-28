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

public class EntranceExamRecordAdapter extends RecyclerView.Adapter<EntranceExamRecordAdapter.viewholder> {

    private List<EntranceExamRecorHolder> listItem;


    public EntranceExamRecordAdapter(List<EntranceExamRecorHolder> listItem){
        this.listItem=listItem;


    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.examrecorddesign,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(listItem.get(position).getTotalmarks()
                ,listItem.get(position).getNameofexam()
                ,listItem.get(position).getModes()
                ,listItem.get(position).getSetno()
                ,listItem.get(position).getPhymarks()
                ,listItem.get(position).getChemmarks()
                ,listItem.get(position).getMathsmarks()
                ,listItem.get(position).getExampic()
                ,listItem.get(position).getFullmarksexam());
    }

    @Override
    public int getItemCount() {


        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        private TextView totalmarks;
        private ImageView pic;
        private TextView phymarks;
        private TextView chemmarks;
        private TextView mathsmarks;
        private TextView entrancename;
        private TextView mode;
        private TextView setno;
        private TextView fullmarksexam1;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            totalmarks=itemView.findViewById(R.id.totalmarks);
            pic=itemView.findViewById(R.id.exam_img_id);
            phymarks=itemView.findViewById(R.id.phymarks);
            chemmarks=itemView.findViewById(R.id.chemmarks);
            mathsmarks=itemView.findViewById(R.id.mathsmarks);
            entrancename=itemView.findViewById(R.id.entranceexamname);
            mode=itemView.findViewById(R.id.Mode);
            setno=itemView.findViewById(R.id.setno);
            fullmarksexam1=itemView.findViewById(R.id.fullmarksexam123);


        }

        public void setData(String totalmarks,String entrancename,String mode,String setno,String phymarks,String chemmarks,String mathsmarks,String imageurl,String fullmarksexam5) {
            Glide.with(itemView.getContext()).load(imageurl).into(pic);
            this.totalmarks.setText("Your Marks : "+totalmarks);
            this.phymarks.setText("Physics : "+phymarks);
            this.chemmarks.setText("Chemistry : "+chemmarks);
            this.mathsmarks.setText("Mathematics : "+mathsmarks);
            this.entrancename.setText(entrancename);
            this.mode.setText("Mode : "+mode);
            this.setno.setText("Paper Set No. : "+setno);
            this.fullmarksexam1.setText(fullmarksexam5);



        }
    }
}

