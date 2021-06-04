package com.suvathiga.spacex.business;


import com.suvathiga.spacex.data.Serviceman;

import com.suvathiga.spacex.db.ApprovedRequestsdatabase;
import com.suvathiga.spacex.db.Requestsdatabase;
import com.suvathiga.spacex.db.SerivcemanDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class ServicemanService {

    private SerivcemanDatabase serivcemanDatabase = null;
    private Serviceman serviceman;
    private Requestsdatabase requestsdatabase=new Requestsdatabase();
    private ApprovedRequestsdatabase approvedRequestsdatabase=new ApprovedRequestsdatabase();

    public  ServicemanService(SerivcemanDatabase serivcemanDatabase){
        this.serivcemanDatabase=serivcemanDatabase;
    }
    public ServicemanService(){
    }

    public ServicemanService(ApprovedRequestsdatabase approvedRequestsdatabase) {
        this.approvedRequestsdatabase = approvedRequestsdatabase;
    }

    public ServicemanService(Requestsdatabase requestsdatabase) {
        this.requestsdatabase = requestsdatabase;
    }

    public ServicemanService(Requestsdatabase requestsdatabase, ApprovedRequestsdatabase approvedRequestsdatabase) {
        this.requestsdatabase = requestsdatabase;
        this.approvedRequestsdatabase = approvedRequestsdatabase;
    }

    public boolean checkServiceman(String usermail , String userpass){

        serviceman =  serivcemanDatabase.getServiceman(usermail,userpass);
        if(serviceman == null){
            System.out.println("Wrong credentials for service man also");
            return false;
        }
        System.out.println("Verification complete for seriviceman");
        return true;

    }

    public  Serviceman getServiceman(){
        return serviceman;
    }

    public boolean isservicedata(String type){
        ResultSet re=requestsdatabase.getcon(type);

        try {
            if(re.isFirst()) {
                System.out.println("1");
                return true;
            }if(re.isBeforeFirst()) {
                while (re.next()) {
                    System.out.println("2");
                    return true;
                }
                System.out.println("3");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        System.out.println("4");
        return false;

    }
    public ResultSet getseriveddata(String type){
        return requestsdatabase.getcon(type) ;
    }


    public void  insertanddelete(String type){
        approvedRequestsdatabase.insert(type);
        requestsdatabase.delete(type);

    }

   public void updateapprovereqdatabase(Serviceman serviceman1,int type){
        List<Integer> arr= serviceman1.getreuserlist();
        for(Integer i:arr)
        approvedRequestsdatabase.insert(type,i,serviceman1.getServicemanid());
    }
    public boolean isconndata(String type){
      ResultSet  re=requestsdatabase.getcon(type);

        try {
            if(re.isBeforeFirst())
                return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return false;

    }
    public Serviceman getServicemandata(String type){
       ResultSet re;
       Serviceman serviceman=new Serviceman();
        if(isconndata(type)) {
            re = requestsdatabase.getcon(type);

            try {
                if(re.isBeforeFirst()) {
                    while (re.next()) {
                        serviceman.addreuser( re.getInt("requserid"));
                        serviceman.addrequestcol(re.getInt("requestscol"));
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return serviceman;
    }
   public  void deleterequestdatabase(String type,Serviceman serviceman,int[] arr){
        requestsdatabase.delete(type,serviceman,arr);
   }

}
