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

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.viewholder> {

    private List<PaymentReceiptHolder> listItem;


    public PaymentHistoryAdapter(List<PaymentReceiptHolder> listItem){
        this.listItem=listItem;


    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.paymenthistorylayout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(listItem.get(position).getPayment_id()
                ,listItem.get(position).getAmount()
                ,listItem.get(position).getMail()
                ,listItem.get(position).getNumber()
                ,listItem.get(position).getTime());
    }

    @Override
    public int getItemCount() {


        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        private TextView mail;
        private TextView number;
        private TextView paymentid;
        private TextView amount;
        private TextView dateandtime;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            mail=itemView.findViewById(R.id.mailtext);
            number=itemView.findViewById(R.id.numbertext);
            paymentid=itemView.findViewById(R.id.paymentid);
            amount=itemView.findViewById(R.id.amount123);
            dateandtime=itemView.findViewById(R.id.timeanddate);

        }

        public void setData(String paymentid121,String amount121,String mail121,String number121,String dateandtime121) {
            this.paymentid.setText(paymentid121);
            this.amount.setText("Rs  "+amount121);
            this.mail.setText(mail121);
            this.number.setText(number121);
            this.dateandtime.setText(dateandtime121);



        }
    }
}


