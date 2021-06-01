package com.suvathiga.spacex.jframes;



import com.suvathiga.spacex.formclass.customerform;

import javax.swing.*;

public class customerjframe {
    public void open(String fname1,String lname1,String emailid,int userid){
        JFrame sframe = new JFrame(" customerUi");
        sframe.setContentPane(new customerform(fname1, lname1, emailid,userid).getCustomermainpanel());
        sframe.pack();
        sframe.setSize(600, 600);
        sframe.setVisible(true);
    }
}
