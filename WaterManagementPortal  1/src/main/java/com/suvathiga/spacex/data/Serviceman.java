package com.suvathiga.spacex.data;

import java.util.ArrayList;
import java.util.List;

public class Serviceman {
    private  int servicemanid;
    private String emailid;
    private String password;
    private String fname;
    private String lname;
    private String citiesavail;
    private List<Integer> requser=new ArrayList<>();
    private  List<Integer> requestcol=new ArrayList<>();
    public void addreuser(int i){
        requser.add(i);
    }
    public void addrequestcol(int i){
        requestcol.add(i);
    }
    public List<Integer> getreuserlist(){
        return requser;
    }
    public List<Integer> getrequestcollist(){
        return requestcol;
    }
    public int getreuser(int i){
        return requser.get(i);
    }
    public int getrequestcol(int i){
        return requestcol.get(i);
    }
    public Serviceman() {
    }

    public int getServicemanid() {
        return servicemanid;
    }

    public void setServicemanid(int servicemanid) {
        this.servicemanid = servicemanid;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCitiesavail() {
        return citiesavail;
    }

    public void setCitiesavail(String citiesavail) {
        this.citiesavail = citiesavail;
    }
}
