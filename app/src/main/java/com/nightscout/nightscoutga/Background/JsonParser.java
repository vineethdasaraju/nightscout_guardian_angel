package com.nightscout.nightscoutga.Background;

/**
 * Created by shivam on 11/28/2014.
 */

import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;

        import com.nightscout.nightscoutga.Background.ResponseModel;
/**
 * Created by shivam on 11/27/2014.
 */
public class JsonParser {

    public List<ResponseModel> parser(String content) throws Exception{
        List<ResponseModel> responselist = null;
        ResponseModel rm;
        int code = 0;
        try {
            //JSONArray ar = new JSONArray(content);  //Array object cannot handle single json object
       /*List<ResponseModel>*/ responselist = new ArrayList<ResponseModel>();

            //for (int i = 0; i < ar.length(); i++) {

            JSONObject jso = new JSONObject(content); //ar.getJSONObject(i);
            /*ResponseModel*/ rm = new ResponseModel();

            rm.setResponseCode(jso.getInt("responseCode"));
            rm.setResponseMessage(jso.getString("responseMessage"));
            //code = rm.getResponseCode();

            responselist.add(rm);



            //return responselist;
        } catch (JSONException e) {
            e.printStackTrace();

        }/*finally {
        if(code != 0){
            return code;}
            else return 0;
    }*/
        return responselist;
    }

}
