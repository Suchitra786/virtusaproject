package com.suvathiga.spacex.formclass;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class servicemanform {
    private JTable table1;
    private JTextField noOfServiceRequestsTextField;
    private JTextField totalAmountCollectedTodayTextField;
    private JButton logoutButton;
    private JTextField amountCollectedTodayTextField;
    private JPanel serivcemainpanel;
    private JButton updatedone;
    private JTextField sid;
    private JTextField displaycamt;
    private JTextField displayscount;
    private int amt;
    private String sql1= "SELECT * FROM requests WHERE requestype=3  ";;
   ResultSet r;
    public servicemanform(int id) {

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {
            Statement mystat = con.createStatement();

             r = mystat.executeQuery(sql1);
            if (r.isBeforeFirst())
                table1.setModel(DbUtils.resultSetToTableModel(r));

            displayscount.setText((String.valueOf(table1.getRowCount())));
            displaycamt.setText("0");
            sid.setText(String.valueOf(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        updatedone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 List<Integer> requser=new ArrayList<>();
                List<Integer> requestcol=new ArrayList<>();


                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {
                    Statement mystat = con.createStatement();
                    String sql1 = "SELECT requestscol,requserid FROM requests WHERE requestype=3  ";
                    ResultSet r = mystat.executeQuery(sql1);
                    if(r.isBeforeFirst()) {


                        while (r.next()) {

                           requser.add( r.getInt("requserid"));
                            requestcol.add(r.getInt("requestscol"));

                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

//                table1.selectAll();
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root")) {
                    Statement mystat = con.createStatement();
                    int[] arr=table1.getSelectedRows();
                    amt+= arr.length*500;
                    int s=arr.length;
                    System.out.println(s);


                    for(int i=0;i<arr.length;i++){
                            String sql2 = "delete  FROM requests WHERE requestype=3 and requestscol='"+requestcol.get(i)+"' and requserid ='"+requser.get(i)+"'";
                            mystat.executeUpdate(sql2);


                    }
                    if(table1.getRowCount()==1) {
                        table1.setVisible(false);
                        displaycamt.setText(String.valueOf(amt+500));
                    }
                    r = mystat.executeQuery(sql1);
                    if (r.isBeforeFirst()){
                        table1.setModel(DbUtils.resultSetToTableModel(r));
                        displaycamt.setText(String.valueOf(amt));
                        }

                }
                 catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame sframe=new JFrame(" login");
                sframe.setContentPane(new Loginform().getMainpanel());
                sframe.pack();
                sframe.setSize(600,600);
                sframe.setVisible(true);

            }
        });
    }

    public JPanel getSerivcemainpanel() {
        return serivcemainpanel;
    }



}
