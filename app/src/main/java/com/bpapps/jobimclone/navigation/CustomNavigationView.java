package com.bpapps.jobimclone.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class CustomNavigationView extends NavigationView {
    private double mUpperSpacing = 0.15;
    private double mBottomSpacing = 0.0;

    public CustomNavigationView(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomNavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomNavigationView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        setBackgroundColor(Color.TRANSPARENT);
        //   setInsetsColor(Color.GREEN);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            v.setBackground(new ColorDrawable(Color.WHITE));
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.clipPath(createClipPath());
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @SuppressLint("RtlHardcoded")
    private Path createClipPath() {
        Path path = new Path();
        DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) getLayoutParams();
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        boolean rtl;
        if (layoutParams.getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
            rtl = true;
        } else {
            rtl = false;
        }

        if (rtl) {
            path.moveTo((int) (width * mUpperSpacing), 0);
            path.lineTo(width, 0);
            path.lineTo(width, height);
            path.lineTo((int) (width * mBottomSpacing), height);
            path.close();
        } else {
            path.moveTo(0, 0);
            path.lineTo((int) (width * (1 - mUpperSpacing)), 0);
            path.lineTo((int) (width * (1 - mBottomSpacing)), height);
            path.lineTo(0, height);
            path.close();
        }

        return path;
    }
}

