package com.suvathiga.spacex.formclass;

import com.suvathiga.spacex.business.verifycustomer;
import com.suvathiga.spacex.business.verifyserviceman;
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

    public JPanel getMainpanel() {
        return Mainpanel;
    }

    public Loginform() {
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String usermail = userField.getText();
                String userpass = passwordField.getText();
                 if(userField.getText().equals("wmpadmin") && passwordField.getText().equals("wmpadmin") ){
                     new adminjframe().open();
                 }
                 else
                 {
                     new verifycustomer().verify(usermail,userpass);
                 }
                new verifyserviceman().verify(usermail,userpass);
             }
        });
        signupHereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new signupjframe().open();

            }
        });


    }

    public static void main(String[] args) {
        // write your necode h
     new loginjframe().open();

    }
}
