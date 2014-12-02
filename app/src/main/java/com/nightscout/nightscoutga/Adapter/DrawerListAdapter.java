package com.nightscout.nightscoutga.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightscout.nightscoutga.R;
import com.nightscout.nightscoutga.UI.Fragments.DrawerListItem;

import java.util.ArrayList;

public class DrawerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DrawerListItem> drawerListItems;
    View root;

    public DrawerListAdapter(Context context, ArrayList<DrawerListItem> drawerListItems){
        this.context = context;
        this.drawerListItems = drawerListItems;
    }

    @Override
    public int getCount() {
        return drawerListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return drawerListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            root = mInflater.inflate(R.layout.drawer_listview_item, null);

        } else {
            root = convertView;
        }

        ImageView image = (ImageView) root.findViewById(R.id.drawer_item_icon);
        TextView title = (TextView) root.findViewById(R.id.drawer_item_title);

        image.setImageResource(drawerListItems.get(position).getIcon());
        title.setText(drawerListItems.get(position).getTitle());

        return root;
    }
}
