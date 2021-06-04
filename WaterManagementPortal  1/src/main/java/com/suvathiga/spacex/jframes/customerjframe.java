package com.suvathiga.spacex.jframes;



import com.suvathiga.spacex.data.Customer;
import com.suvathiga.spacex.formclass.customerform;

import javax.swing.*;

public class customerjframe {
    public void open(Customer customer){
        JFrame sframe = new JFrame(" customerUi");
        sframe.setContentPane(new customerform(customer).getCustomermainpanel());
        sframe.pack();
        sframe.setSize(600, 600);
        sframe.setVisible(true);
    }
}
