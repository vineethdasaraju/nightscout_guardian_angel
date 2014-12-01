package com.nightscout.nightscoutga.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jjoe64.graphview.CustomLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.nightscout.nightscoutga.Models.Glucose;
import com.nightscout.nightscoutga.R;

public class GraphsFragment extends Fragment {

    public GraphsFragment(){}

    public static GraphView graphView = null;
    public static GraphViewSeries exampleSeries;
    public static double[] sx = null;
    public static double[] sy = null;
    public static String[] hLabels = null;
    public static Glucose[] gdata = null;

    public static void updateGraphData(Glucose[] gd){
        gdata = gd;
    }

    public static GraphView.GraphViewData[] getHorizontalData(Glucose[] gd){
        GraphView.GraphViewData[] graphViewData = new GraphView.GraphViewData[gd.length];
        String[] hl = new String[gd.length];
        for(int i = 0; i < gd.length; i++) {

            //Entry entry = entries.get(i);
            //int pain = entry.getPain();
            if(gd[i] != null) {
                graphViewData[i] = new GraphView.GraphViewData(i, gd[i].getGlucoseValue());

                // Generate the horizontal labels
                //SimpleDateFormat sdf = new SimpleDateFormat("EEE");
                //String dayOfWeek = sdf.format(entry.getDate());
                hl[i] = gd[i].getGlucoseDate() + "" + gd[i].getGlucoseTime();
            }

        }
        hLabels = hl;
        return graphViewData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_graphs, container, false);

        //if(graphView == null) {
            graphView = new LineGraphView(
                    getActivity() // context
                    , "Glucose Levels" // heading
            );
        //}
        if(gdata != null) {
            exampleSeries = new GraphViewSeries("", null, getHorizontalData(gdata));
            graphView.addSeries(exampleSeries); // data
            //graphView.setHorizontalLabels(hLabels);
            graphView.setCustomLabelFormatter(new CustomLabelFormatter() {
                @Override
                public String formatLabel(double value, boolean isValueX) {
                    if (isValueX) {
                        // Assuming only 7 total values here
                        return hLabels[(int) value];
                    } else
                        return String.format("%.2f", value);
                }
            });
            graphView.getGraphViewStyle().setNumHorizontalLabels(4);
            //graphView.getNestedScrollAxes();
            graphView.getGraphViewStyle().setNumVerticalLabels(13);
            graphView.setManualYMaxBound(160);
            graphView.setManualYMinBound(40);
            graphView.setViewPort(0, 3);
            graphView.setScrollable(true);
            //graphView.setVerticalLabels(new String[]{"160", "140", "120", "100", "80", "60", "40"});
            Log.e("Hello", "Before");
            LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.graphicalview);
            layout.canScrollHorizontally(View.LAYOUT_DIRECTION_INHERIT);
            Log.e("Hello", layout + "After");
            layout.addView(graphView);
            Log.e("Hello", "After22");
        }
        else{

            exampleSeries = new GraphViewSeries(new GraphView.GraphViewData[]{
                    new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
            });
            graphView.addSeries(exampleSeries);
            LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.graphicalview);
            //Log.e("Hello", layout + "After");
            //layout.addView(graphView);
            //layout.removeAllViewsInLayout();
            layout.addView(graphView);
            //layout.invalidate();
        }

        /*}
        else {
            /*graphView = new LineGraphView(
                    getActivity() // context
                    , "Glucose Levels" // heading
            );*/
        //graphView.addSeries(exampleSeries);
        //LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.graphicalview);
        //Log.e("Hello", layout + "After");
        //layout.addView(graphView);
        //graphView.redrawAll();
        //graphUpdate(rootView);
        //}
        /*graphView = new LineGraphView(
                getActivity() // context
                , "GraphViewDemo" // heading
        );*/

        /*Glucose gd[] = new Glucose[5];
        gd[0] = new Glucose(23, 64, "29/11", "10:20");
        gd[1] = new Glucose(23, 72, "29/11", "10:30");
        gd[2] = new Glucose(23, 81, "29/11", "10:40");
        gd[3] = new Glucose(23, 60, "29/11", "10:50");
        gd[4] = new Glucose(23, 68, "29/11", "11:00");

        updateGraphData(gd);*/

        return rootView;
}

    @Override
    public void onResume() {
        super.onResume();
        graphView.redrawAll();
    }

    public static void graphUpdate(View rootView){
        //sx = x;
        //sy = y;
        graphView.removeAllSeries();
        //GraphView.GraphViewData[] xy = new GraphView.GraphViewData[x.length];
        //for(int i = 0; i < x.length; i++){
        //xy[i] = new GraphView.GraphViewData(x[i], y[i]);
        //}
        if(gdata != null) {
            exampleSeries = new GraphViewSeries("", null, getHorizontalData(gdata));
            graphView.addSeries(exampleSeries);
            graphView.setHorizontalLabels(hLabels);
            graphView.setVerticalLabels(new String[]{"160", "140", "120", "100", "80", "60", "40"});
        }
        else{
            exampleSeries = new GraphViewSeries(new GraphView.GraphViewData[]{
                    new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
                    , new GraphView.GraphViewData(0, 0d)
            });
            graphView.addSeries(exampleSeries);
        }
        Log.e("Hello", "Before");
        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.graphicalview);
        //layout.removeAllViews();
        Log.e("Hello", layout + "After");
        layout.addView(graphView);
        graphView.redrawAll();
        //layout.removeView(graphView);
        //layout.updateViewLayout(graphView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        Log.e("Hello", "After22");
        //graphView.redrawAll();
    }
}