package com.nightscout.nightscoutga.customviews;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class STextView extends TextView{

	public STextView(Context context) {
		super(context);
		init(context);
	}
	public STextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public STextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
	public void init(Context context)
	{
		setCustomFont(context,"Helvetica.ttf");
		setTextSize(15);
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
