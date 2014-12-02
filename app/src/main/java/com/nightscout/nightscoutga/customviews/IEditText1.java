package com.nightscout.nightscoutga.customviews;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

public class IEditText1 extends EditText {

	public IEditText1(Context context) {
		super(context);
		init(context);
	}
	public IEditText1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IEditText1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
	public void init(Context context)
	{
		setCustomFont(context,"HelveticaLight.ttf");
//		setTextColor(getResources().getColor(R.color.title_grey));
//		setHintTextColor(getResources().getColor(R.color.grey1));
		setTextSize(15);
		setMinimumHeight(40);
		setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
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
