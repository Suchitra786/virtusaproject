package com.suvathiga.spacex.formclass;

import com.suvathiga.spacex.business.CustomerService;
import com.suvathiga.spacex.business.ServicemanService;
import com.suvathiga.spacex.data.Customer;
import com.suvathiga.spacex.data.Serviceman;
import com.suvathiga.spacex.db.CustomerDatabase;
import com.suvathiga.spacex.db.SerivcemanDatabase;
import com.suvathiga.spacex.jframes.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Loginform {

    private JTextField userField;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton signupHereButton;
    private JPanel Mainpanel;
    private CustomerService customerService;
    private ServicemanService servicemanService;


    public JPanel getMainpanel() {
        return Mainpanel;
    }

    public Loginform() {
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String usermail = userField.getText();
                String userpass = passwordField.getText();
                if (isAdmin(usermail, userpass)) {
                    new adminjframe().open();
                } else {
                    customerService = new CustomerService(new CustomerDatabase());
                    if (customerService.checkcustomer(usermail, userpass)) {
                        Customer customer = customerService.getCustomer();
                        new customerjframe().open(customer);
                    } else {
                        servicemanService = new ServicemanService(new SerivcemanDatabase());
                        if (servicemanService.checkServiceman(usermail, userpass)) {
                            Serviceman serviceman = servicemanService.getServiceman();
                            new servicemanjframe().open(serviceman.getServicemanid());
                        }

                    }

                }
            }
        });
        signupHereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new signupjframe().open();
            }
        });

        }




    public static boolean isAdmin(String usermail, String userpass){
      return usermail.equals("wmpadmin") && userpass.equals("wmpadmin");
    }


    public static void main(String[] args) {
        // write your necode h
     new loginjframe().open();

    }
}
