package com.nbird.paperwind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    //Array
    public int[] slide_images={
            R.drawable.slide1,
            R.drawable.slide2,
            R.drawable.slide3,
    };

    public String[] slide_Headings={
      "PDF",
      "TEST",
      "STATISTIC"
    };

    public String[] slide_descs={
      "A statistic is a characteristic of a sample. Generally, a statistic is used to estimate the value of a population parameter. For instance, suppose we selected a random sample of 100 students from a school with 1000 students.",
      "A statistic is a characteristic of a sample. Generally, a statistic is used to estimate the value of a population parameter. For instance, suppose we selected a random sample of 100 students from a school with 1000 students.",
      "A statistic is a characteristic of a sample. Generally, a statistic is used to estimate the value of a population parameter. For instance, suppose we selected a random sample of 100 students from a school with 1000 students.",
    };
    @Override
    public int getCount() {
        return slide_Headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view== (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.slidelayout,container,false);

        ImageView slideImageView=(ImageView) view.findViewById(R.id.imageView);
        TextView slideHeading=(TextView) view.findViewById(R.id.textView);
        TextView slideDiscription=(TextView) view.findViewById(R.id.textView2);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_Headings[position]);
        slideDiscription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }


    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((ConstraintLayout)object);
    }
}
