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
      "STUDY MATERIAL",
      "ONLINE TESTS",
      "COLLEGE PREDICTOR"
    };

    public String[] slide_descs={
      "Access wide range of study material for Boards and Competitive exams ranging from Sample papers, Official previous year papers, Textbook solutions, formulae, labs and many more! ",
      "What's better than sharpening your skills in real-time exam environment? Practice a wide set of online exams and get to know your true potential!",
      "Worried about which college you'll get? Relax! we got your post exam stress covered. Presenting the ultimate Rank and College predictor which helps you to find you the college you really deserve.",
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
