package com.suvathiga.spacex.business;

import com.suvathiga.spacex.jframes.customerjframe;

import java.sql.*;

public class verifycustomer {
    public void verify(String usermail, String userpass) {
        System.out.println("Verifying for Customer");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {

            Statement st = con.createStatement();


            ResultSet cus = st.executeQuery("select emailid,password,fname,lname,id from users where emailid = '" + usermail + "'");
            String emailid = "";
            if (cus.isBeforeFirst()) {

                while (cus.next()) {
                    emailid = cus.getString("emailid");
                    if (usermail.equals(emailid)) {

                        if (userpass.equals(cus.getString("password"))) {

                            System.out.println("Verification complete  and logged in");
                            System.out.println("opening customer ui");
                            String fname1 = cus.getString("fname");
                            String lname1 = cus.getString("lname");
                            int userid = cus.getInt("id");

                            Statement st2 = con.createStatement();

                            new customerjframe().open(fname1, lname1, emailid, userid);

                        } else {
                            System.out.println("Wrong password !");
                        }
                    }
                }


            } else {
                System.out.println("Wrong credentials for customer");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
