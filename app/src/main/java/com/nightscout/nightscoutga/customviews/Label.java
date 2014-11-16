package com.nightscout.nightscoutga.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.nightscout.nightscoutga.R;

public class Label extends TextView {

    Context ctx = null;

    public Label(Context context) {
        super(context);
        this.ctx = context;
    }

    public Label(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public Label(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context, attrs, defStyle);
    }

    public void init(Context context, AttributeSet attrs, int defStyle) {

        this.ctx = context;
        // TODO Auto-generated constructor stub
        int tf = 0;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.Label);

        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.Label_typeface:
                    tf = a.getResourceId(attr, R.string.label_typeface_def);
                    setCustomFont(ctx, ctx.getResources().getString(tf));
                    break;

            }
        }
        a.recycle();

    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), asset);
            setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        } catch (Exception e) {

            return false;
        }
        setTypeface(tf);
        return true;
    }

}