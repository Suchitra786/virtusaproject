package com.suvathiga.spacex.db;

import com.suvathiga.spacex.data.Customer;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDatabase {
private  Customer customer =new Customer();
    private Jdbcconnector jdbcconnector = new Jdbcconnector();

    public Customer getCustomer(String usermail, String userpass)  {

        try {
            ResultSet resultSet = jdbcconnector.dbconnectexecute("select fname,lname,id from users where emailid = '" + usermail + "'and  password='"+userpass+"'");
            if (resultSet.isBeforeFirst()) {
                while(resultSet.next()) {
                            customer.setEmailid(usermail);
                            customer.setPassword(userpass);
                            customer.setFname(resultSet.getString("fname"));
                            customer.setLname(resultSet.getString("lname"));
                            customer.setId(resultSet.getInt("id"));
                            return customer;
                        }
                    }
        } catch (SQLException throwable) {
           System.out.println("Exception in customerdatabase");
        }
        return null;
    }

public Customer merge(Customer customer,ResultSet re){
    try {
        if(re.isBeforeFirst()) {
            while (re.next()) {
              customer.setAddr1(re.getString("addr1"));
               customer.setAddr2( re.getString("addr2"));
                customer.setCity(re.getString("city"));
                return customer;
            }
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return  customer;
}



}
