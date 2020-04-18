package com.bpapps.jobimclone.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class NavViewCustomConstrainLayout extends ConstraintLayout {

    private double mUpperSpacing = 0.2;
    private double mBottomSpacing = 0.1;


    public NavViewCustomConstrainLayout(Context context) {
        super(context);
    }

    public NavViewCustomConstrainLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NavViewCustomConstrainLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.clipPath(createClipPath());
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @SuppressLint("RtlHardcoded")
    private Path createClipPath() {
        Path path = new Path();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
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
