package com.nightscout.nightscoutga.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.nightscout.nightscoutga.R;

public class GraphsFragment extends Fragment {

    public GraphsFragment(){}

    public static GraphView graphView = null;
    public static GraphViewSeries exampleSeries;
    public static double[] sx = null;
    public static double[] sy = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_graphs, container, false);

        if(graphView == null) {
            graphView = new LineGraphView(
                    getActivity() // context
                    , "GraphViewDemo" // heading
            );
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
            graphView.addSeries(exampleSeries); // data
            Log.e("Hello", "Before");
            LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.graphicalview);
            Log.e("Hello", layout + "After");
            layout.addView(graphView);
            Log.e("Hello", "After22");
        }
        else{
            graphUpdate(sx, sy);
        }
        graphView = new LineGraphView(
                getActivity() // context
                , "GraphViewDemo" // heading
        );
        return rootView;
    }

    @Override
    public void onResume() {
        graphView.redrawAll();
    }

    public static void graphUpdate(double[] x, double[] y){
        sx = x;
        sy = y;
        graphView.removeAllSeries();
        GraphView.GraphViewData[] xy = new GraphView.GraphViewData[x.length];
        for(int i = 0; i < x.length; i++){
            xy[i] = new GraphView.GraphViewData(x[i], y[i]);
        }
        exampleSeries = new GraphViewSeries(xy);
        graphView.addSeries(exampleSeries);
        graphView.redrawAll();
    }
}