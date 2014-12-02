package com.nightscout.nightscoutga.Models;

public class Glucose {

    int patientId;
    String date;
    String time;
    int glucose;

    /*public Glucose(int g, Date d){
        this.date = DateFormat.getDateInstance().format(date);
        this.glucose = g;
        this.time = DateFormat.getTimeInstance().format(date);
        this.patientId = Integer.parseInt(Constants.userid);
    }*/

    public Glucose(int pid, int g, String d, String time){
        this.patientId = pid;
        this.date = d;
        this.glucose = g;
        this.time = time;
        //this.patientId = Integer.parseInt(Constants.userid);
    }

    public String getGlucoseDate(){
        return date;
    }

    public String getGlucoseTime(){
        return time;
    }

    public int getGlucoseValue(){
        return glucose;
    }

}