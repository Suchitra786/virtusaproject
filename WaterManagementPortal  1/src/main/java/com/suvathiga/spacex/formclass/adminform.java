package com.suvathiga.spacex.formclass;


import com.suvathiga.spacex.business.AdminService;
import com.suvathiga.spacex.db.ApprovedRequestsdatabase;
import com.suvathiga.spacex.db.Jdbcconnector;

import com.suvathiga.spacex.db.Requestsdatabase;
import com.suvathiga.spacex.jframes.loginjframe;
import net.proteanit.sql.DbUtils;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

public class adminform {
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JPanel adminmainpanel;
    private JButton logoutButton;
    private Jdbcconnector con = new Jdbcconnector();


    public JPanel getmainpanel() {
        return adminmainpanel;
    }
    private ResultSet re;

private AdminService adminService=new AdminService(new Requestsdatabase(),new ApprovedRequestsdatabase());
    public adminform() {

        if(adminService.isconndata("1")) {
            table1.setModel(DbUtils.resultSetToTableModel(adminService.getnewcon()));
            table1.setVisible(true);
        }
        if(adminService.isconndata("3")) {
            table2.setModel(DbUtils.resultSetToTableModel(adminService.getnewcon()));
            table2.setVisible(true);
        }
        if(adminService.isconndata("2")) {
            table3.setModel(DbUtils.resultSetToTableModel(adminService.getnewcon()));
            table3.setVisible(true);
        }

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminService.insertanddelete("1");
                table1.setVisible(false);
                System.out.println("Successfully approved the connection requests");

            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminService.insertanddelete("3");
                table2.setVisible(false);
                System.out.println("Successfully approved the connection requests");
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminService.insertanddelete("2");
                table3.setVisible(false);
                System.out.println("Successfully approved the connection requests");

            }
        });


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              new loginjframe().open();
            }
        });
    }
}




