package com.pszemek.betterfy.adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.misc.MainActivityItem;

/**
 * Created by Ciemek on 28/06/16.
 */
public class MainAdapter extends BaseAdapter<MainActivityItem> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_mainmenu, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MainActivityItem item = getItem(position);
        ((MainViewHolder) holder).image.setImageResource(item.resIdImage);
        ((MainViewHolder) holder).button.setText(item.resIdButton);
        ((MainViewHolder) holder).button.setTextColor(item.resIdButtonColour);
        ((MainViewHolder) holder).text.setText(item.resIdText);
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        ImageView   image;
        Button      button;
        TextView    text;

        public MainViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.recycler_item_mainmenu_image);
            button = (Button)itemView.findViewById(R.id.recycler_item_mainmenu_button);
            text = (TextView)itemView.findViewById(R.id.recycler_item_mainmenu_textview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerOnPosClickListener.onPosClick(v, getLayoutPosition());
                }
            });
        }
    }
}
