package com.suvathiga.spacex.db;

import com.suvathiga.spacex.data.Customer;
import com.suvathiga.spacex.data.Serviceman;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApprovedRequestsdatabase {
private String serid="";
    private String sqlinsert="insert into approvedrequests(arequserid,requestype) SELECT requserid,requestype FROM requests WHERE requestype=";
private ResultSet re;
    private  Jdbcconnector jdbcconnector=new Jdbcconnector();
    public String getservicemanid(int id,int type) {
        try {
        re = jdbcconnector.dbconnectexecute("select  servicemanid  from approvedrequests where requestype='"+type+"' and arequserid='" + id + "'");
            if (re.isBeforeFirst())
                while (re.next()) {
                    serid = re.getString("servicemanid");
                    System.out.println("in "+serid);
                     return serid;
                }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        System.out.println("out "+serid);
        return "";
    }

    public void insert(String type) {

        try {
           jdbcconnector.dbconnectupdate(sqlinsert+ type);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void insert(int type,int resquserid,int servicemanid) {

        try {
            jdbcconnector.dbconnectupdate("insert into approvedrequests(requestype,arequserid,servicemanid)  values ('"+type+"', '"+resquserid+"', '"+String.valueOf(servicemanid)+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
