package com.suvathiga.spacex.formclass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class signupform {
    private JTextField Usertype;
    private JButton signupButton;
    private JTextField fname;
    private JTextField lname;
    private JTextField mailid;
    private JTextField password;
    private JTextField addr1;
    private JTextField addr2;
    private JTextField city;
    private JTextField state;
    private JTextField phoneno;
    private JTextField aadharid;
    private JTextField citiesavailable;
    private JPanel signupmainpanel;

    public signupform() {
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("New User : "+fname.getText()+lname.getText()+" Signed up !");
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {
                    Integer i=Integer.valueOf(Usertype.getText());
                    Statement mystat = con.createStatement();
                    String sql2="insert into userdetails"
                            +"(addr1,addr2,city,state,phoneno,aadharno)"
                            +" values('"+addr1.getText()+"','"+addr2.getText()+"','"+city.getText()+"','"+state.getText()+"','"+phoneno.getText()+"','"+aadharid.getText()+"')";
                    mystat.executeUpdate(sql2);
                    String  sql1 ="select userid from userdetails where aadharno='"+aadharid.getText()+"'";
                    ResultSet r= mystat.executeQuery(sql1);
                    int userid=0;
                    if(r.isBeforeFirst()) {
                        while (r.next())
                            userid = r.getInt("userid");
                    }

                    System.out.println("userid got "+userid);
                    //if( usertype i ==1   cutomer  else service man

                    if(i==1) {
                        String sql = "insert into users "
                                + "(id,fname,lname,emailid,password,usertype)"
                                + " values('"+userid+"','" + fname.getText() + "','" + lname.getText() + "','" + mailid.getText() + "','" + password.getText() + "','" + i + "')";

                        mystat.executeUpdate(sql);
                        System.out.println("Insert complete to users table");
                    }else{


                        String sql = "insert into servicemans"
                                + "(servicemanid,fname,lname,emailid,password,usertype,citiesavail)"
                                + " values('"+userid+"','" + fname.getText() + "','" + lname.getText() + "','" + mailid.getText() + "','" + password.getText() + "','" + i + "','"+citiesavailable.getText()+"')";

                        mystat.executeUpdate(sql);
                        System.out.println("Insert complete to sericeman table");
                    }





                    System.out.println("Insert complete to userdetails table");
                    System.out.println("Sign in successful");

                    JFrame sframe=new JFrame(" login");
                    sframe.setContentPane(new Loginform().getMainpanel());
                    sframe.pack();
                    sframe.setSize(600,600);
                    sframe.setVisible(true);


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }

    public JPanel getSignupmainpanel() {
        return signupmainpanel;
    }
}
