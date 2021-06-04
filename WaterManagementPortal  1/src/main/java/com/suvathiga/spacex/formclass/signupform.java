package com.suvathiga.spacex.formclass;

import com.suvathiga.spacex.business.SignupService;
import com.suvathiga.spacex.db.CustomerDatabase;
import com.suvathiga.spacex.db.SerivcemanDatabase;
import com.suvathiga.spacex.db.SignupDatabase;
import com.suvathiga.spacex.jframes.loginjframe;
import com.suvathiga.spacex.jframes.signupjframe;

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
    private  SignupService signupService;

    public signupform() {
        try {
            signupButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    signupService = new SignupService(new SignupDatabase());
                    if (signupService.adddetails(addr1.getText(), addr2.getText(), city.getText(), state.getText(), phoneno.getText(), aadharid.getText(), Usertype.getText(), fname.getText(), lname.getText(), mailid.getText(), password.getText(), citiesavailable.getText())) {
                        System.out.println("Sign in successful");
                        System.out.println("New User : " + fname.getText() + lname.getText() + " Signed up !");
                        new loginjframe().open();
                    }


                }
            });
        } catch(Exception e){

        }

    }

    public JPanel getSignupmainpanel() {
        return signupmainpanel;
    }
}
