package com.suvathiga.spacex.jframes;


import com.suvathiga.spacex.formclass.servicemanform;

import javax.swing.*;

public class servicemanjframe {

    public void open(int id) {
        JFrame sframe = new JFrame("SerUi");
        sframe.setContentPane(new servicemanform(id).getSerivcemainpanel());
        sframe.pack();
        sframe.setSize(600, 600);
        sframe.setVisible(true);
    }

}
