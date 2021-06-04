package com.suvathiga.spacex.db;

import com.suvathiga.spacex.data.Customer;
import com.suvathiga.spacex.data.Serviceman;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Requestsdatabase {

    private String sqldelete="delete FROM requests WHERE requestype=";
    private String sqldisplay="SELECT * FROM requests WHERE requestype=";
      private  Jdbcconnector jdbcconnector=new Jdbcconnector();
    private  ResultSet re;
    private Customer customer;


    public ResultSet getcon(String type) {

        try {
            re = jdbcconnector.dbconnectexecute(sqldisplay + type);
            return re;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return re;
    }

public void delete(String type){
    try {
       jdbcconnector.dbconnectupdate(sqldelete + type);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }


}
    public void delete(String type, Serviceman serviceman,int[] arr){


        try {
            for(int i=0;i< arr.length;i++)
            jdbcconnector.dbconnectupdate(sqldelete +" '"+type+"' and requserid = '"+serviceman.getreuser(i)+"' and requestscol = '"+serviceman.getrequestcol(i)+"'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    public void insert(Customer customer,int type,String des){
        String sql1 = "insert into requests "
                + "(requserid,requestype,fname,lname,addr1,addr2,city,description)"
                + "values('" + customer.getId() + "','" + type + "','" + customer.getFname() + "','" +customer.getLname()+ "','" + customer.getAddr1() + "','" + customer.getAddr2() + "','" +customer.getCity()+ "','" + des + "')";
        try {
            jdbcconnector.dbconnectupdate(sql1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public int chechreq(int id,int type) {
        int requestscol=0;
        try {
            re = jdbcconnector.dbconnectexecute("select  requestscol from requests where requestype='"+type+"' and requserid='" + id + "'");
            if (re.isBeforeFirst())
                while (re.next()) {

                    requestscol = re.getInt("requestscol");
                    System.out.println(requestscol+"in condi");
                    return requestscol;
                }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        System.out.println(requestscol+" out condi ");
        return requestscol;
    }

}
