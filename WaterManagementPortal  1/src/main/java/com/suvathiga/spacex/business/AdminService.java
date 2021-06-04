package com.suvathiga.spacex.business;

import com.suvathiga.spacex.db.ApprovedRequestsdatabase;
import com.suvathiga.spacex.db.Requestsdatabase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminService {
    private Requestsdatabase requestsdatabase;
    private ApprovedRequestsdatabase approvedRequestsdatabase;
    private ResultSet re;

    public AdminService(Requestsdatabase requestsdatabase) {
        this.requestsdatabase = requestsdatabase;
    }

    public AdminService(ApprovedRequestsdatabase approvedRequestsdatabase) {
        this.approvedRequestsdatabase = approvedRequestsdatabase;
    }

    public AdminService(Requestsdatabase requestsdatabase, ApprovedRequestsdatabase approvedRequestsdatabase) {
        this.requestsdatabase = requestsdatabase;
        this.approvedRequestsdatabase = approvedRequestsdatabase;
    }

    public AdminService(){

    }


    public boolean isconndata(String type){
        requestsdatabase=new Requestsdatabase();
        re=requestsdatabase.getcon(type);

        try {
            if(re.isBeforeFirst())
                return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return false;

    }
    public ResultSet getnewcon(){
        return re;
    }

    public void  insertanddelete(String type){
        approvedRequestsdatabase.insert(type);
        requestsdatabase.delete(type);

    }





}
