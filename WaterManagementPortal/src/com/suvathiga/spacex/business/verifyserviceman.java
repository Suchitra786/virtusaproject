package com.suvathiga.spacex.business;

import com.suvathiga.spacex.jframes.servicemanjframe;

import java.sql.*;

public class verifyserviceman {
    public void verify(String usermail,String userpass){
        System.out.println("verifying for serivice man id");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {

            Statement st = con.createStatement();


            ResultSet ser = st.executeQuery("select emailid,password,fname,lname,servicemanid from servicemans where emailid = '" + usermail + "';");
        if (ser.isBeforeFirst() == false) {
            System.out.println("Wrong credentials for serivice man also !");
        }
        while (ser.next()) {

            if (usermail.equals(ser.getString("emailid"))) {
                System.out.println("hhh");
                if (userpass.equals(ser.getString("password"))) {

                    System.out.println("Verification complete  and logged in");
                    System.out.println("opening signup page");
                    String fname = ser.getString("fname");
                    String lanem = ser.getString("lname");
                    System.out.println("opening serviceman ui");

                    new servicemanjframe().open(ser.getInt("servicemanid"));
                } else {
                    System.out.println("Wrong password !");
                }
            }
        }


    } catch (
    SQLException throwables) {
        throwables.printStackTrace();
    }
                System.out.println("exited");
    }
}
