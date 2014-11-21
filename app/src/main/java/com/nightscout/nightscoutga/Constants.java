package com.nightscout.nightscoutga;

/**
 * Created by User on 19/11/2014.
 */
public class Constants {

    public final static float MG_DL_TO_MMOL_L = 0.05556f;

    public enum TREND_ARROW_VALUES {
        NONE(0),
        DOUBLE_UP(1,"\u21C8", "DoubleUp"),
        SINGLE_UP(2,"\u2191", "SingleUp"),
        UP_45(3,"\u2197", "FortyFiveUp"),
        FLAT(4,"\u2192", "Flat"),
        DOWN_45(5,"\u2198", "FortyFiveDown"),
        SINGLE_DOWN(6,"\u2193", "SingleDown"),
        DOUBLE_DOWN(7,"\u21CA", "DoubleDown"),
        NOT_COMPUTABLE(8),
        OUT_OF_RANGE(9);

        private String arrowSymbol;
        private String trendName;
        private int myID;

        TREND_ARROW_VALUES(int id, String a, String t) {
            myID=id;
            arrowSymbol = a;
            trendName = t;
        }

        TREND_ARROW_VALUES(int id) {
            this(id,null, null);
        }

        public String Symbol() {
            if (arrowSymbol == null) {
                return "\u2194";
            } else {
                return arrowSymbol;
            }
        }

        public String friendlyTrendName() {
            if (trendName == null) {
                return this.name().replace("_", " ");
            } else {
                return this.trendName;
            }
        }

        public int getID(){
            return myID;
        }

    }

    public enum SPECIALBGVALUES_MGDL {
        NONE("??0", 0),
        SENSORNOTACTIVE("?SN", 1),
        MINIMALLYEGVAB("??2", 2),
        NOANTENNA("?NA", 3),
        SENSOROUTOFCAL("?NC", 5),
        COUNTSAB("?CD", 6),
        ABSOLUTEAB("?AD", 9),
        POWERAB("???", 10),
        RFBADSTATUS("?RF", 12);


        private String name;
        private int val;
        private SPECIALBGVALUES_MGDL(String s, int i){
            name=s;
            val=i;
        }

        public int getValue(){
            return val;
        }

        public String toString(){
            return name;
        }

        public static SPECIALBGVALUES_MGDL getEGVSpecialValue(int val){
            for (SPECIALBGVALUES_MGDL e: values()){
                if (e.getValue()==val)
                    return e;
            }
            return null;
        }

        public static boolean isSpecialValue(int val){
            for (SPECIALBGVALUES_MGDL e: values()){
                if (e.getValue()==val)
                    return true;
            }
            return false;
        }

    }

}
