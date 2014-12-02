package com.nightscout.nightscoutga.util;

import android.location.Address;

import com.google.android.gms.maps.model.LatLng;

public class Constants {

    public final static String HTTP_PUT				=	"PUT";
    public final static String HTTP_POST			=	"POST";
    public final static String HTTP_DEL				=	"DELETE";
    public final static String HTTP_GET 			=	"GET";

    public static String uName;

    public static final String serverAddress = "http://192.168.0.24:8080";
    public static String ProjectID = "755643285059";
//    public static final String serverAddress = "http://ec2-54-173-219-144.compute-1.amazonaws.com:8080";
    public static final String apiPrefix = serverAddress + "/nightscoutpro";
    public static final String apiRegister2 = apiPrefix + "/ga/register2";
    public static final String apiLogin = apiPrefix + "/ga/login";
    public static final String apiInvite = apiPrefix + "/ga/invite";
    public static final String ChangePassword = apiPrefix + "/ga/invite";
    public static final String updateProfile = apiPrefix + "/updateProfile";
    public static final String getEmergencyPatientDetails = apiPrefix + "/get_emergency_patient";
    public static final String acknowledgeEmergency = apiPrefix + "/acknowledge_guardian/" + Constants.userid;

    public static final String BG_SERVICE_CONTENT_TYPE_OCTET_STREAM = 	"binary/octet-stream";
    public static final String BG_SERVICE_CONTENT_TYPE_JSON 		= 	"application/json";
    public final static String NEW_LINE 		= "\n";

    public final static float MG_DL_TO_MMOL_L = 0.05556f;
    public static boolean isDotCom = false;

//    GCM
    public static final String MSG_KEY = "title";

//    Registration
    public static LatLng latlng;
    public static Address lastKnownAddress;
    public static LatLng userLatLng = null;

    // Keys for storing user details
    public static String KEY_userid = "userid";
    public static String KEY_username = "username";
    public static String KEY_phoneNumber = "phoneNumber";
    public static String KEY_fullname = "fullname";
    public static String KEY_userEmail = "userEmail";
    public static String KEY_fbPage = "fbPage";
    public static String KEY_userAddress = "userAddress";
    public static String KEY_userLat = "userLat";
    public static String KEY_userLng = "userLng";

    // User Details
    public static String userid = "26";
    public static String fullname = "";
    public static String username = "null";
    public static String phoneNumber = "null";
    public static String userEmail = "null";
    public static String fbPage = "null";
    public static String userAddress = "null";
    public static String userLat = "";
    public static String userLng = "";


    public static String emergencyPatientName = "";
    public static String emergencyPatientPhone = "";

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
