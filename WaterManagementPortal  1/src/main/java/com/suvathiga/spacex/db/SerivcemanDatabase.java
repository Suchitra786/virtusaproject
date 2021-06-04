package com.suvathiga.spacex.db;


import com.suvathiga.spacex.data.Serviceman;

import java.sql.ResultSet;
import java.sql.SQLException;



public class  SerivcemanDatabase {

    private Jdbcconnector jdbcconnector = new Jdbcconnector();

    public Serviceman getServiceman(String usermail, String userpass)  {
       Serviceman serviceman = new Serviceman();
        try {
            ResultSet resultSet = jdbcconnector.dbconnectexecute("select emailid,password,servicemanid from servicemans where emailid = '" + usermail + "' and password='"+userpass+"'");
            if (resultSet.isBeforeFirst()) {
                while(resultSet.next()) {
                    serviceman.setEmailid( resultSet.getString("emailid"));
                    serviceman.setPassword(resultSet.getString("password"));
                    serviceman.setServicemanid(resultSet.getInt("servicemanid"));
                     return serviceman;

                }

            }

        } catch (SQLException throwable) {
            return serviceman;
        }
        return null;
    }





}
