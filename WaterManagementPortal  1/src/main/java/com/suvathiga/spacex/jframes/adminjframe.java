package com.suvathiga.spacex.jframes;


import com.suvathiga.spacex.formclass.adminform;

import javax.swing.*;

public class adminjframe {



    public void open() {
        JFrame sframe = new JFrame(" admin");

        sframe.setContentPane(new adminform().getmainpanel());
        sframe.pack();
        sframe.setSize(600, 600);
        sframe.setVisible(true);
    }
}
