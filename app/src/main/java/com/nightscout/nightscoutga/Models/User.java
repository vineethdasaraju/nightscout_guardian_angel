package com.nightscout.nightscoutga.Models;

/**
 * Created by User on 01/12/2014.
 */


public class User {

    public static User[] patientsList;

    private int userId;
    private String emailId;
    private String userName;
    private String fullName;
    private String password;
    private long phoneNum;
    private String address;
    private String regId;

    public static void updatePatientDetails(User[] patients){
        patientsList = patients;
    }

    /*public User(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }*/
    public User(String emailId, String password) {
        super();
        this.emailId = emailId;
        this.password = password;
    }
    public User(String emailId, String userName, String fullName,
                String password, Long phoneNum, String address) {
        super();
        this.emailId = emailId;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.phoneNum = phoneNum;
        this.address = address;
    }
    public User(String emailId, String userName, String fullName) {
        super();
        //this.userId = userId;
        this.emailId = emailId;
        this.userName = userName;
        this.fullName = fullName;
        //this.password = password;
        //this.phoneNum = phoneNum;
        //this.address = address;
        //this.regId = regId;
    }
    public User(int userId, String emailId, String userName, String fullName,
                String password, long phoneNum, String address, String regId) {
        super();
        this.userId = userId;
        this.emailId = emailId;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.phoneNum = phoneNum;
        this.address = address;
        this.regId = regId;
    }

    public User() {

    }

    public String getRegId() {
        return regId;
    }
    public void setRegId(String regId) {
        this.regId = regId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

}