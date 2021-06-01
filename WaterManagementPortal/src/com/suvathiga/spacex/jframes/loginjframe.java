package com.suvathiga.spacex.jframes;


import com.suvathiga.spacex.formclass.Loginform;

import javax.swing.*;

public class loginjframe {
    public void open(){
        JFrame fram=new JFrame("Login");
        fram.setContentPane(new Loginform().getMainpanel());
        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fram.pack();
        fram.setSize(400,400);
        fram.setVisible(true);
    }
}
