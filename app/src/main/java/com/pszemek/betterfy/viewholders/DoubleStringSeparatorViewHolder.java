package com.pszemek.betterfy.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.adapters.RecyclerOnPosClickListener;

/**
 * Created by Ciemek on 01/07/16.
 */
public class DoubleStringSeparatorViewHolder extends AbstractBaseViewHolder {

    public TextView textLeft;
    public TextView    textRight;

    public DoubleStringSeparatorViewHolder(View itemView) {
        super(itemView, null);
        textLeft = (TextView) itemView.findViewById(R.id.recycler_item_doublestring_separator_left);
        textRight = (TextView) itemView.findViewById(R.id.recycler_item_doublestring_separator_right);
    }
}

