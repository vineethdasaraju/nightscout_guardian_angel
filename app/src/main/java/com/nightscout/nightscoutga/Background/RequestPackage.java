package com.nightscout.nightscoutga.Background;

/**
 * Created by shivam on 11/28/2014.
 */

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shivam on 11/26/2014.
 */
public class RequestPackage {
    private String uri;
    private String method = "GET";


    private Map<String, String> map = new HashMap<String, String>();


    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public Map<String, String> getMap() {
        return map;
    }
    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setMap(String key, String value){
        map.put(key, value);
    }

    public String getEncodedParams(){
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            String value = null;
            try {
                value = URLEncoder.encode(map.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (sb.length() > 0){
                sb.append("&");
            }
            sb.append(key + "=" +value);
        }
        return sb.toString();
    }


}


