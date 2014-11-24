package com.nightscout.nightscoutga.UI.Fragments;

public class DrawerListItem {

    private String title;
    private int icon;

    public DrawerListItem() {
    }

    public DrawerListItem(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return this.title;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
    
