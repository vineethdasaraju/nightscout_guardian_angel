package com.nightscout.nightscoutga.UI;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;

import com.jjoe64.graphview.GraphViewDataInterface;
import com.nightscout.nightscoutga.R;

//import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

//import android.app.Activity;
//import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

//import java.util.ArrayList;
//import java.util.List;


public class Graph extends Activity {

    public static GraphView graphView = null;
    public static GraphViewSeries exampleSeries;
    public static double[] sx = null;
    public static double[] sy = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph2);
        if(graphView == null) {
            graphView = new LineGraphView(
                    this // context
                    , "GraphViewDemo" // heading
            );
            exampleSeries = new GraphViewSeries(new GraphViewData[]{
                    new GraphViewData(0, 0d)
                    , new GraphViewData(0, 0d)
                    , new GraphViewData(0, 0d)
                    , new GraphViewData(0, 0d)
                    , new GraphViewData(0, 0d)
                    , new GraphViewData(0, 0d)
                    , new GraphViewData(0, 0d)
                    , new GraphViewData(0, 0d)
                    , new GraphViewData(0, 0d)
            });
            graphView.addSeries(exampleSeries); // data
            Log.e("Hello", "Before");
            LinearLayout layout = (LinearLayout) findViewById(R.id.graphicalview);
            Log.e("Hello", layout + "After");
            layout.addView(graphView);
            Log.e("Hello", "After22");
        }
        else{
            graphUpdate(sx, sy);
        }
        graphView = new LineGraphView(
                this // context
                , "GraphViewDemo" // heading
        );
    }

    protected void onResume(){
        super.onResume();
        //graphView.removeAllSeries();
        /*exampleSeries = new GraphViewSeries(new GraphViewData[] {
                new GraphViewData(1, 2.0d)
                , new GraphViewData(3, 1.5d)
                , new GraphViewData(4, 2.5d)
                , new GraphViewData(5, 1.0d)
                , new GraphViewData(6, 2.0d)
                , new GraphViewData(7, 3.0d)
                , new GraphViewData(8, 2.5d)
                , new GraphViewData(9, 1.5d)
                , new GraphViewData(10, 4.0d)
        });*/
        graphView.redrawAll();
    }

    public static void graphUpdate(double[] x, double[] y){
        sx = x;
        sy = y;
        graphView.removeAllSeries();
        GraphViewData[] xy = new GraphViewData[x.length];
        for(int i = 0; i < x.length; i++){
            xy[i] = new GraphViewData(x[i], y[i]);
        }
        exampleSeries = new GraphViewSeries(xy);
        graphView.addSeries(exampleSeries);
        graphView.redrawAll();
    }

}