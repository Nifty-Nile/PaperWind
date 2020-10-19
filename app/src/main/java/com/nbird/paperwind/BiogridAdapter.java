package com.nbird.paperwind;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BiogridAdapter extends BaseAdapter {
    private int sets=0;



    public BiogridAdapter(int sets) {
        this.sets = sets;

    }

    @Override
    public int getCount() {
        return sets;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup parent) {
        View view1;
        if(view==null){
            view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.customgridbio,parent,false);
        }else{
            view1=view;
        }
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(parent.getContext(),TestMainDisplayActivityASAP.class);
                intent.putExtra("biosnum",i+1);
                parent.getContext().startActivity(intent);

            }
        });
        ((TextView)view1.findViewById(R.id.iconnumber)).setText(String.valueOf(i+1));
        return view1;
    }
}

