package com.suvathiga.spacex.formclass;


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

    public JPanel getmainpanel() {
        return adminmainpanel;
    }

    public adminform() {


        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {
            Statement mystat = con.createStatement();

            String sql = "SELECT * FROM requests WHERE requestype=1  ";
            String sql1 = "SELECT * FROM requests WHERE requestype=3 ";
            String sql2 = "SELECT * FROM requests WHERE requestype!=1 and requestype!=3   ";
            ResultSet r = mystat.executeQuery(sql);
            if (r.isBeforeFirst())
                table1.setModel(DbUtils.resultSetToTableModel(r));

            ResultSet r1 = mystat.executeQuery(sql1);
            if (r1.isBeforeFirst())
                table2.setModel(DbUtils.resultSetToTableModel(r1));

            ResultSet r2 = mystat.executeQuery(sql2);
            if (r2.isBeforeFirst())
                table3.setModel(DbUtils.resultSetToTableModel(r2));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {
                    Statement mystat = con.createStatement();
                    String sql = "insert into approvedrequests(arequserid,requestype) SELECT requserid,requestype FROM requests WHERE requestype=1 ";
                    mystat.executeUpdate(sql);
                    String sql1 = "delete FROM requests WHERE requestype=1  ";
                    mystat.executeUpdate(sql1);
                    table1.setVisible(false);
                    System.out.println("Successfully approved the connection requests");

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {
                    Statement mystat = con.createStatement();
                    String sql = "insert into approvedrequests(arequserid,requestype) SELECT requserid,requestype FROM requests WHERE requestype=3 ";
                    mystat.executeUpdate(sql);
                    String sql1 = "delete FROM requests WHERE requestype=3  ";
                    mystat.executeUpdate(sql1);
                    table2.setVisible(false);
                    System.out.println("Successfully delected the unwanted  requests");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {
                    Statement mystat = con.createStatement();
                    String sql = "insert into approvedrequests(arequserid,requestype) SELECT requserid,requestype FROM requests WHERE requestype=2 ";
                    mystat.executeUpdate(sql);
                    String sql1 = "delete FROM requests WHERE requestype=2  ";
                    mystat.executeUpdate(sql1);
                    table3.setVisible(false);
                    System.out.println("Successfully approved the cancel requests");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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




