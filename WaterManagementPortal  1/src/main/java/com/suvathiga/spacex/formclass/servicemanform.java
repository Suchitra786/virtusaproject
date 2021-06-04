package com.suvathiga.spacex.formclass;

import com.suvathiga.spacex.business.ServicemanService;
import com.suvathiga.spacex.data.Serviceman;
import com.suvathiga.spacex.db.Jdbcconnector;
import com.suvathiga.spacex.jframes.loginjframe;
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
    private JTextField displayamt;
    private int amt;
    private ServicemanService servicemanService=new ServicemanService();
    private String sql1= "SELECT * FROM requests WHERE requestype=3  ";;
   ResultSet r;
   private Jdbcconnector jdbcconnector=new Jdbcconnector();
    public servicemanform(int id) {

        if(servicemanService.isservicedata("3")) {
            table1.setModel(DbUtils.resultSetToTableModel(servicemanService.getseriveddata("3")));
            table1.setVisible(true);
            sid.setText(String.valueOf(id));
        }


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new loginjframe().open();
            }
        });
        updatedone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int[] arr=table1.getSelectedRows();
                System.out.println(arr[0]);
                System.out.println(table1.getSelectedRow());
                amt+= arr.length*500;
                Serviceman serviceman=servicemanService.getServicemandata("3");
                serviceman.setServicemanid(id);
                servicemanService.updateapprovereqdatabase(serviceman,3);
                servicemanService.deleterequestdatabase("3",serviceman,arr);
                displayamt.setText(String.valueOf(amt));
                if(servicemanService.isservicedata("3")) {
                    table1.setModel(DbUtils.resultSetToTableModel(servicemanService.getseriveddata("3")));

                }
                if(table1.getRowCount()<0){
                    table1.setVisible(false);
                }
//                if(table1.getRowCount()<=0){
//                    table1.setVisible(false);
//                }
//                if(table1.getRowCount()==1){
//                    table1.setVisible(false);
//                }


            }
        });

    }

    public JPanel getSerivcemainpanel() {
        return serivcemainpanel;
    }



}
