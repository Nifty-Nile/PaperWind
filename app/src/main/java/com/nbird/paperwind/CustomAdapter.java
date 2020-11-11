package com.nbird.paperwind;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomAdapter extends BaseExpandableListAdapter {

    private Context context;
    private HashMap<String, List<String>> childtitles;
    List<String> headertitles;

    public CustomAdapter(Context context,List<String> headertitles,HashMap<String,List<String>> childtitles){
        this.context=context;
        this.childtitles=childtitles;
        this.headertitles=headertitles;
    }

    @Override
    public int getGroupCount() {
        return this.headertitles.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.childtitles.get(this.headertitles.get(i)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headertitles.get(groupPosition);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.childtitles.get(this.headertitles.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle=(String) getGroup(groupPosition);

        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.group_header,null);
        }
            TextView listTitleTextView=(TextView) convertView.findViewById(R.id.title);
            listTitleTextView.setText(listTitle);
            return convertView;
        }




    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String expandedListText=(String) getChild(groupPosition,childPosition);

        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.child_header,null);
        }
            TextView expandedListTextView=(TextView) convertView.findViewById(R.id.childitem);
            expandedListTextView.setText(expandedListText);

             if(groupPosition==0){
                 convertView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if (childPosition == 0) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",1);
                             intent.putExtra("child",1);
                             context.startActivity(intent);
                         } else if (childPosition == 1) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",1);
                             intent.putExtra("child",2);
                             context.startActivity(intent);
                         }
                     }
                 });
             }else if(groupPosition==1){
                 convertView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if (childPosition == 0) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",2);
                             intent.putExtra("child",1);
                             context.startActivity(intent);
                         } else if (childPosition == 1) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",2);
                             intent.putExtra("child",2);
                             context.startActivity(intent);
                         }

                     }
                 });
             }else if(groupPosition==1){
                 convertView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if (childPosition == 0) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",2);
                             intent.putExtra("child",1);
                             context.startActivity(intent);
                         } else if (childPosition == 1) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",2);
                             intent.putExtra("child",2);
                             context.startActivity(intent);
                         }

                     }
                 });
             }else if(groupPosition==2){
                 convertView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if (childPosition == 0) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",3);
                             intent.putExtra("child",1);
                             context.startActivity(intent);
                         }

                     }
                 });
             }else if(groupPosition==3){
                 convertView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if (childPosition == 0) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",4);
                             intent.putExtra("child",1);
                             context.startActivity(intent);
                         } else if (childPosition == 1) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",4);
                             intent.putExtra("child",2);
                             context.startActivity(intent);
                         }

                     }
                 });
             }else if(groupPosition==4){
                 convertView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if (childPosition == 0) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",5);
                             intent.putExtra("child",1);
                             context.startActivity(intent);
                         } else if (childPosition == 1) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",5);
                             intent.putExtra("child",2);
                             context.startActivity(intent);
                         }else if (childPosition == 2) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",5);
                             intent.putExtra("child",3);
                             context.startActivity(intent);
                         } else if (childPosition == 3) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",5);
                             intent.putExtra("child",4);
                             context.startActivity(intent);
                         }

                     }
                 });
             }else if(groupPosition==5){
                 convertView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if (childPosition == 0) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",6);
                             intent.putExtra("child",1);
                             context.startActivity(intent);
                         } else if (childPosition == 1) {
                             Intent intent = new Intent(context, HelpMoney1Activity.class);
                             intent.putExtra("parent",6);
                             intent.putExtra("child",2);
                             context.startActivity(intent);
                         }

                     }
                 });
             }





            return convertView;



    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
