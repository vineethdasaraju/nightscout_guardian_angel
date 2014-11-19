package com.viewpagerindicator;



import java.lang.reflect.Field;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

public class CustomViewPager extends ViewPager {

private boolean enabled;
Fragment[] frags = null;
public Handler handler = null;
public Runnable runnable = null;

public CustomViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.enabled = false;
    
    handler = new Handler();
    runnable = new Runnable() {
        public void run() {
        	handler.postDelayed(runnable, autoSlide());
        	
        }
    };
}



public void setFragments(Fragment[] frags)
{
	 this.frags = frags;
}

@Override
public boolean onTouchEvent(MotionEvent event) {
	try {
        Field mScroller;
        mScroller = ViewPager.class.getDeclaredField("mScroller");
        mScroller.setAccessible(true);  
        ScrollerCustomDurationSwipe scrollerSwipe = new ScrollerCustomDurationSwipe(this.getContext());
        // scroller.setFixedDuration(5000);
        mScroller.set(this, scrollerSwipe);
    } catch (NoSuchFieldException e) {
    } catch (IllegalArgumentException e) {
    } catch (IllegalAccessException e) {
    }
    if (this.enabled) {
    	
    	if(event.ACTION_DOWN == event.getAction()||event.getAction()==event.ACTION_HOVER_ENTER || event.getAction()==event.ACTION_MOVE)
    	{
    		if (handler!= null) {
    	        handler.removeCallbacks(runnable);
    	    }

    		
    	}
    	else
    	{
    		handler.postDelayed(runnable, 6000);
    	}
    	
        return super.onTouchEvent(event);
        
    }

    return false;
}

String getDirection(MotionEvent event)
{
	float x1=0, x2=0, y1=0, y2=0, dx, dy;
	String direction = "";
	switch(event.getAction()) {
	    case(MotionEvent.ACTION_DOWN):
	        x1 = event.getX();
	        y1 = event.getY();
	        break;
	    case(MotionEvent.ACTION_UP): {
	        x2 = event.getX();
	        y2 = event.getY();
	        dx = x2-x1;
	            dy = y2-y1;

	            // Use dx and dy to determine the direction
	        if(Math.abs(dx) > Math.abs(dy)) {
	            if(dx>0)
	            	{
	            		//right
	            		direction = "right";
	            	}
	            else
					{
	            		//left
	            		direction = "left";
					}
	        } else {
	            if(dy>0) direction = "down";
	            else direction = "up";
	        }
	    }
	    case(MotionEvent.ACTION_SCROLL): {
	        x2 = event.getX();
	        y2 = event.getY();
	        dx = x2-x1;
	            dy = y2-y1;

	            // Use dx and dy to determine the direction
	        if(Math.abs(dx) > Math.abs(dy)) {
	            if(dx>0)
	            	{
	            		//right
	            		direction = "right";
	            	}
	            else
					{
	            		//left
	            		direction = "left";
					}
	        } else {
	            if(dy>0) direction = "down";
	            else direction = "up";
	        }
	    }
	}
	
	return direction;
}
@Override
public boolean onInterceptTouchEvent(MotionEvent e) {
	
	
    if (this.enabled) {
    	
		
        return super.onInterceptTouchEvent(e);
    }

    return false;
}

public int autoSlide()
{
	int count = this.getCurrentItem();
	int time = 4000;
	boolean flag = true;
	try {
        Field mScroller;
        mScroller = ViewPager.class.getDeclaredField("mScroller");
        mScroller.setAccessible(true); 
        Interpolator sInterpolator = new AccelerateInterpolator();
        ScrollerCustomDuration scroller = new ScrollerCustomDuration(this.getContext(), sInterpolator);
        // scroller.setFixedDuration(5000);
        mScroller.set(this, scroller);
    } catch (NoSuchFieldException e) {
    } catch (IllegalArgumentException e) {
    } catch (IllegalAccessException e) {
    }
    if(count>=7)
    {
    	count = 0;
    	flag = false;
    	time = 6000;
    }
    count++;
    this.setCurrentItem(count, flag);
    
    
    return time;
    
}
public void setPagingEnabled(boolean enabled) {
    this.enabled = enabled;
} 

}