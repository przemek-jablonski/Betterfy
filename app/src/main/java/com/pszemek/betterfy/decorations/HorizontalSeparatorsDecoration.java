package com.pszemek.betterfy.decorations;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Ciemek on 19/06/16.
 */
public class HorizontalSeparatorsDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable separator;
    private int padding = 15;


    public HorizontalSeparatorsDecoration(Context context) {
        final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
        separator = styledAttributes.getDrawable(0);
        styledAttributes.recycle();
    }

    public HorizontalSeparatorsDecoration(Context context, int separatorDrawableId) {
        separator = ContextCompat.getDrawable(context, separatorDrawableId);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        left += padding;
        int right = parent.getWidth() - parent.getPaddingRight();
        right -= padding;

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + separator.getIntrinsicHeight();

            separator.setBounds(left, top, right, bottom);
            separator.draw(c);

        }

    }
}
