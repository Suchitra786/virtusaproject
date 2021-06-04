package com.suvathiga.spacex.formclass;


import com.suvathiga.spacex.business.CustomerService;
import com.suvathiga.spacex.data.Customer;
import com.suvathiga.spacex.db.ApprovedRequestsdatabase;
import com.suvathiga.spacex.db.CustomerDatabase;
import com.suvathiga.spacex.db.Jdbcconnector;
import com.suvathiga.spacex.db.Requestsdatabase;
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
   private String lname;
    private String fname;
    private boolean isserreq;
    private  boolean iscanreq;
    private Customer customer1;
    private CustomerService customerService1;

   private CustomerService customerService;
    private Jdbcconnector jdbcconnector;
    public JPanel getCustomermainpanel() {
        return customermainpanel;
    }

    public customerform(Customer customer) {
        customerService=new CustomerService(new ApprovedRequestsdatabase(),new Requestsdatabase());
        this.customerService1=customerService;
       this.customer1=customer;
        customer=customerService.isactive(customer,1);
        displaystatus.setText(customer.getActive());
        isactive=customer.getActive();
        customer=customerService.getnotification(customer);
        displaynotification.setText(customer.getNotification());
        displaytitle.setText("Welcome back ! "+customer.getFname()+" "+customer.getLname());
        displayemail.setText("Emailid : "+customer.getEmailid());

       fname=customer.getLname();
       lname=customer.getFname();
        payBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ispaid=true;
              customer1=customerService1.pay(customer1);
              displaydue.setText("no due");
              customer1= customerService1.isactive(customer1,1);
              displaystatus.setText(customer1.getActive());
            }
        });

        conncancelreq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int type = 2;
                customerService = new CustomerService(new ApprovedRequestsdatabase(), new Requestsdatabase());
                System.out.println("Trying to see if the cancel request is already requested");
                System.out.println("appout");

                if (!customerService.checkappreq(customer1.getId(), type)) {
                    System.out.println("appin");
                    if (!customerService.checkreq(customer1.getId(), type)) {
                        System.out.println("Trying to  cancel connection   :");
                        customerService.insertreq(customer1, type, cancelconndescrip.getText());
                        conncancelreq.setText("Requested");
                        System.out.println("Description : " + cancelconndescrip.getText());
                    }else {
                        System.out.println("You requested already");
                    }
                } else {
                    System.out.println("You request approved already");

                }

            }

    });


        newconnreq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int type = 1;
                System.out.println("Trying to see if the new connection is already requested");


                    if (!customerService.checkreq(customer1.getId(), type)) {
                        System.out.println("appin");
                            if (!customerService.checkappreq(customer1.getId(), type)) {
                            System.out.println("Trying to  request new connection   : ");
                            String des="new connection";
                            customerService.insertreq(customer1, type, des);
                            newconnreq.setText("Requested");
                        } else {
                            System.out.println(" you  request have been approved already for new connection");

                        }
                    } else {
                        System.out.println("You already requested");
                    }

            }
            });
        downloadBillsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( customer1.getActive().equals("Active")) {
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
                if(customer1.getActive().equals("Active")) {
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
                int type = 3;
                customerService = new CustomerService(new ApprovedRequestsdatabase(),new Requestsdatabase());
                System.out.println("Trying to see if it is already requested the serivice");
                System.out.println("appout");
                if (!customerService.checkreq(customer1.getId(), type)) {
                    System.out.println("appin");
                    if(!customerService.checkappreq(customer1.getId(),type)) {
                        System.out.println("Trying to send service request   :");
                        customerService.insertreq(customer1, type, servicereqdecription.getText());
                        serreqsendbutton.setText("Requested");
                        System.out.println("Description : " + servicereqdecription.getText());
                    }else{

                        System.out.println("Your request for service have been approved already");
                    }
                }else{
                    System.out.println("You have already requested a service");

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
