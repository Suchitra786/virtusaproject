package com.suvathiga.spacex.jframes;

import com.suvathiga.spacex.formclass.signupform;


import javax.swing.*;

public class signupjframe {

    public void open(){
        System.out.println("opening signup page");
        JFrame sframe=new JFrame(" signupform");
        sframe.setContentPane(new signupform().getSignupmainpanel());
        sframe.pack();
        sframe.setSize(600,600);
        sframe.setVisible(true);
    }
}
