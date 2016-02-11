package com.acmutd.hackutd;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by feefa on 2/3/2016.
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String navTitles[];
    private int navIcons[];

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;

        TextView textView;
        ImageView imageView;


        public ViewHolder(View itemView,int ViewType) {
            super(itemView);


            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

            if(ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);// Creating ImageView object with the id of ImageView from item_row.xml
                Holderid = 1;                                               // setting holder id as 1 as the object being populated are of type item row
            }
        }


    }

    NavigationAdapter(String[] navigationTitles, int[] navigationIcons) {
        navTitles = navigationTitles;
        navIcons = navigationIcons;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_item,parent, false); //Inflating the layout

            return new ViewHolder(v,viewType);

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_header, parent,false); //Inflating the layout

            return new ViewHolder(v,viewType);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder.Holderid == TYPE_ITEM) {
            holder.textView.setText(navTitles[position - 1]);
            holder.imageView.setImageResource(navIcons[position -1]);
        }
    }

    @Override
    public int getItemCount() {
        return navTitles.length+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}
