package com.nightscout.nightscoutga.Background;

/**
 * Created by shivam on 11/28/2014.
 */
public class ResponseModel {

    private int responseCode;
    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

}
