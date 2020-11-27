package com.nbird.paperwind;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TestRecyclerAdapter extends RecyclerView.Adapter<TestRecyclerAdapter.viewholder> {

    private List<TestRecyclerHolder> listItem;
    int position,mode;
    Context context;
    int value,safe;

    public TestRecyclerAdapter(Context context,List<TestRecyclerHolder> listItem, int position, int mode, int value ,int safe){
        this.listItem=listItem;
        this.position=position;
        this.mode=mode;
        this.context=context;
        this.value=value;
        this.safe=safe;

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
                public void onClick(final View view) {



                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference();

                    FirebaseAuth fAuth = FirebaseAuth.getInstance();
                    final List<TestPaperPurshasedHolder> list123 = new ArrayList<>();
                    myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("ExamPaperPurchased").orderByChild("position").equalTo(String.valueOf(position)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                String pos123 = dataSnapshot1.child("position").getValue(String.class);
                                String set123 = dataSnapshot1.child("set").getValue(String.class);
                                int setint=Integer.parseInt(set123);

                                if(setint==set){
                                    safe=1;
                                    Intent intent=new Intent(itemView.getContext(),TestAgrementActivity.class);
                                    intent.putExtra("position",position);
                                    intent.putExtra("mode",mode);
                                    intent.putExtra("set",set);
                                    intent.putExtra("phy",phy);
                                    intent.putExtra("chem",chem);
                                    intent.putExtra("maths",maths);
                                    intent.putExtra("bio",bio);
                                    intent.putExtra("value",value);
                                    intent.putExtra("safe",safe);

                                    itemView.getContext().startActivity(intent);
                                    return;
                                }
                            }


                               if(value>=20){
                                   AlertDialog.Builder builder=new AlertDialog.Builder(context,R.style.AlertDialogTheme);
                                   View view1= LayoutInflater.from(context).inflate(R.layout.alert_dialog,(ConstraintLayout) view.findViewById(R.id.layoutDialogContainer));
                                   builder.setView(view1);
                                   ((TextView) view1.findViewById(R.id.textTitle)).setText("Use 20 Paper Notes");
                                   ((TextView) view1.findViewById(R.id.textMessage)).setText("Pay 20 Paper Notes To Get Life Time Full Access To College Predictor");
                                   ((Button) view1.findViewById(R.id.buttonNo)).setText("Cancel");
                                   ((Button) view1.findViewById(R.id.buttonYes)).setText("Pay 20 Paper Notes!");
                                   ((ImageView) view1.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_timer_24);

                                   final AlertDialog alertDialog=builder.create();

                                   view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
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
                                           intent.putExtra("value",value);
                                           itemView.getContext().startActivity(intent);
                                           alertDialog.dismiss();
                                       }
                                   });
                                   view1.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           alertDialog.dismiss();
                                       }
                                   });

                                   if(alertDialog.getWindow()!=null){
                                       alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                                   }
                                   alertDialog.show();
                               }else {
                                   AlertDialog.Builder builder=new AlertDialog.Builder(context,R.style.AlertDialogTheme);
                                   View view1= LayoutInflater.from(context).inflate(R.layout.alert_dialog,(ConstraintLayout) view.findViewById(R.id.layoutDialogContainer));
                                   builder.setView(view1);
                                   ((TextView) view1.findViewById(R.id.textTitle)).setText("Use 20 Paper Notes");
                                   ((TextView) view1.findViewById(R.id.textMessage)).setText("You don't have enough Paper Notes to get this Paper.");
                                   ((Button) view1.findViewById(R.id.buttonNo)).setText("Cancel");
                                   ((Button) view1.findViewById(R.id.buttonYes)).setText("Get 20 Paper Notes!");
                                   ((ImageView) view1.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_timer_24);

                                   final AlertDialog alertDialog=builder.create();

                                   view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent intent=new Intent(itemView.getContext(),MoneyActivity.class);
                                           itemView.getContext().startActivity(intent);
                                           alertDialog.dismiss();
                                       }
                                   });
                                   view1.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           alertDialog.dismiss();
                                       }
                                   });

                                   if(alertDialog.getWindow()!=null){
                                       alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                                   }
                                   alertDialog.show();
                               }
                           }





                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(context,"Data Can't be Loaded", Toast.LENGTH_SHORT).show();

                        }
                    });









                }
            });
        }
    }
}

