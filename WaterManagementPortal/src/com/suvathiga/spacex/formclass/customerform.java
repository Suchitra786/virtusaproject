package com.suvathiga.spacex.formclass;


import com.suvathiga.spacex.jframes.loginjframe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class customerform {
    private JButton downloadBillsButton;
    private JButton downloadRecieptsButton;
    private JButton serreqsendbutton;
    private JTextPane displaycustname;
    private JButton newconnreq;
    private JButton payBillButton;
    private JTextArea cancelreqdescrip;
    private JButton conncancelreq;
    private JPanel customermainpanel;
    private JTextField servicereqdecription;
    private JTextField cancelconndescrip;
    private JLabel displaytitle;
    private JLabel displayemail;
    private JLabel displaynotification;
    private JLabel displaystatus;
    private JLabel displaydue;
    private JLabel descrip;
    private JButton logoutbut;
    private  int swithcnew;
    private  String isactive;
    private boolean ispaid;
    private boolean isserreq;
    private  boolean iscanreq;

    public JPanel getCustomermainpanel() {
        return customermainpanel;
    }

    public customerform(String fname, String lname, String emailid, int id) {

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {
            Statement mystat = con.createStatement();
            ResultSet re = mystat.executeQuery("select  servicemanid  from approvedrequests where requestype=1 and arequserid=( select id from users where emailid= '" + emailid + "')");

        String sermanid = "";
        while (re.next()) {
            sermanid = re.getString("servicemanid");

        }
        if (sermanid != "") {
            isactive = "Active";
            displaydue.setText("Bill due : 500");
        } else {
            isactive = "Not Active";
        }
            String notification="";
            String sql = "SELECT * FROM approvedrequests WHERE requestype=1 and  arequserid='"+id+"'";
            ResultSet r = mystat.executeQuery(sql);
            if(r.isBeforeFirst())
                while(r.next())
                    notification+="Your Request for new Connection is Approved   ";
            String sql1 = "SELECT * FROM approvedrequests WHERE requestype=2 and  arequserid='"+id+"' ";
            ResultSet r1 = mystat.executeQuery(sql1);
            if(r1.isBeforeFirst())
                while(r1.next())
                    notification+="Necessary actions are took for your service requests    ";
            String sql2 = "SELECT * FROM approvedrequests WHERE requestype=3 and  arequserid='"+id+"'";
            ResultSet r2 = mystat.executeQuery(sql2);
            if(r2.isBeforeFirst())
                while(r2.next())
                    notification+="Your Request for cancel connection is Approved    ";
                displaynotification.setText(notification);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        displaytitle.setText("Welcome back ! "+fname+" "+lname);
        displayemail.setText("Emailid : "+emailid);

        displaystatus.setText(isactive);
       displaydue.setText("No due");
        payBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isactive=="Active") {
                    System.out.println("Bill payed by " + fname + " " + lname);
                    displaydue.setText("No due");
                    ispaid = true;
                }else{
                    System.out.println("You didnt have a connection");
                }
            }
        });

        conncancelreq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(iscanreq==false) {
                    System.out.println("Trying to  cancel connection ");
                    int requestype = 2;

                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {
                        Statement mystat = con.createStatement();
                        String sql1 = "select userid,addr1,addr2,city from userdetails where userid=(select id from users where fname='" + fname + "'and lname='" + lname + "')";
                        ResultSet r = mystat.executeQuery(sql1);
                        String addr1 = "", addr2 = "", city = "";
                        int userid = 0;
                        if (r.isBeforeFirst()) {
                            while (r.next()) {
                                addr1 = r.getString("addr1");
                                addr2 = r.getString("addr2");
                                city = r.getString("city");
                                userid = r.getInt("userid");

                            }


                            String sql2 = "insert into requests "
                                    + "(requserid,requestype,fname,lname,addr1,addr2,city,description)"
                                    + "values('" + userid + "','" + requestype + "','" + fname + "','" + lname + "','" + addr1 + "','" + addr2 + "','" + city + "','" + cancelconndescrip.getText() + "')";

                            mystat.executeUpdate(sql2);
                            System.out.println("Cancel Connection requested succesfully");
                            conncancelreq.setText("Requested");
                            System.out.println("Cancel request sent by " + fname + " " + lname);
                            System.out.println("Description : " + cancelconndescrip.getText());

                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    iscanreq=true;
                }else{
                    System.out.println("You already sent a cancel request");
                }

            }
        });


        newconnreq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(isactive== "Not Active")
                {
                System.out.println("Creating new connection request");
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {
                    Statement mystat = con.createStatement();

                    int requestype = 1;

                    String sql1 = "select userid,addr1,addr2,city from userdetails where userid=(select id from users where fname='" + fname + "'and lname='" + lname + "')";
                    ResultSet r = mystat.executeQuery(sql1);
                    String addr1 = "", addr2 = "", city = "";
                    int userid = 0;
                    if (r.isBeforeFirst()) {
                        while (r.next()) {
                            addr1 = r.getString("addr1");
                            addr2 = r.getString("addr2");
                            city = r.getString("city");
                            userid = r.getInt("userid");

                        }
                        System.out.println("New Connection request recieved from ");


                        String sql2 = "insert into requests "
                                + "(requserid,requestype,fname,lname,addr1,addr2,city)"
                                + "values('" + userid + "','" + requestype + "','" + fname + "','" + lname + "','" + addr1 + "','" + addr2 + "','" + city + "')";

                        mystat.executeUpdate(sql2);
                        System.out.println("New Connection requested succesfully");
                        newconnreq.setText("Requested");
                        swithcnew = 1;

                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else{
                System.out.println("you already have a connection");
            }
            }

            });
        downloadBillsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( isactive=="Active") {
                    System.out.println("Water Management Portal");
                    System.out.println( "New Connection : Minimum charges: 300 ");
                    System.out.println("                 Service charges: 200 ");
                    System.out.println("                   Total charges: 500 ");
                }else {
                    System.out.println("You didnt have a connection ");
                }


            }
        });
        downloadRecieptsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isactive=="Active") {
                    if (ispaid == true) {
                        System.out.println("Water Management Portal");
                        System.out.println("New Connection : Minimum charges: 300 ");
                        System.out.println("                 Service charges: 200 ");
                        System.out.println("                   Total charges: 500 ");
                        System.out.println("Bill payed and verified  ");
                    } else {
                        System.out.println("Still you didnt payed the bill");
                    }
                }else{
                    System.out.println("You didnt have a connection ");
                }
            }
        });
        serreqsendbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(isserreq==false) {
                int requestype = 3;
                System.out.println("Trying to send Service request");
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {
                    Statement mystat = con.createStatement();
                    String sql1 = "select userid,addr1,addr2,city from userdetails where userid=(select id from users where fname='" + fname + "'and lname='" + lname + "')";
                    ResultSet r = mystat.executeQuery(sql1);
                    String addr1 = "", addr2 = "", city = "";
                    int userid = 0;
                    if (r.isBeforeFirst()) {
                        while (r.next()) {
                            addr1 = r.getString("addr1");
                            addr2 = r.getString("addr2");
                            city = r.getString("city");
                            userid = r.getInt("userid");

                        }
                        System.out.println("New Connection request recieved from ");


                        String sql2 = "insert into requests "
                                + "(requserid,requestype,fname,lname,addr1,addr2,city,description)"
                                + "values('" + userid + "','" + requestype + "','" + fname + "','" + lname + "','" + addr1 + "','" + addr2 + "','" + city + "','" + servicereqdecription.getText() + "')";

                        mystat.executeUpdate(sql2);

                        serreqsendbutton.setText("Requested");
                        System.out.println("Serivce request requested succesfully");
                        System.out.println("Service request sent by " + fname + " " + lname);
                        System.out.println("Description : " + servicereqdecription.getText());

                    }
                    isserreq=true;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else{
                System.out.println("You already sent a service request");
            }
            }
        });
        logoutbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new loginjframe().open();

            }
        });
    }


}
