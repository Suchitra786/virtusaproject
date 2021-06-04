package com.suvathiga.spacex.data;

public class Customer {
    private int id;
    private String fname;
    private String lname;
    private String emailid;
    private String password;
    private String userType;
    private  String addr1;
    private  String addr2;
    private String city;
    private String active;
    private String notification="";
    private boolean ispaid;
    private  String due;

    public String getDue() {
        return due;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public boolean isIspaid() {
        return ispaid;
    }

    public void setIspaid(boolean ispaid) {
        this.ispaid = ispaid;
    }

    public void updatenotification(String i){
      this.notification+=i;
  }
    public String getActive() {
        return active;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Customer(int id, String fname, String lname, String emailid, String password, String userType) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.emailid = emailid;
        this.password = password;
        this.userType = userType;
    }

    public Customer() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
}
