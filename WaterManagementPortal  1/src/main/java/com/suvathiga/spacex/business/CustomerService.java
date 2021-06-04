package com.suvathiga.spacex.business;


import com.suvathiga.spacex.data.Customer;
import com.suvathiga.spacex.db.*;
import com.suvathiga.spacex.jframes.customerjframe;
import java.sql.*;

public class CustomerService {

    private CustomerDatabase customerDatabase ;
    private Customer customer;
    private ApprovedRequestsdatabase approvedRequestsdatabase;
    private Requestsdatabase requestsdatabase;
    private SignupDatabase signupDatabase;

    public CustomerService(CustomerDatabase customerDatabase){

        this.customerDatabase = customerDatabase;
    }
    public CustomerService(ApprovedRequestsdatabase approvedRequestsdatabase,Requestsdatabase requestsdatabase){
        this.approvedRequestsdatabase=approvedRequestsdatabase;
        this.requestsdatabase=requestsdatabase;
    }


    public  void insertreq(Customer customer, int type, String des){
        requestsdatabase.insert(customer,type,des);
        System.out.println("     requested succesfully");
        System.out.println("     request sent by " + " " + customer.getFname()+" "+customer.getLname());

    }
    public boolean checkcustomer(String usermail, String userpass){
        customer =  customerDatabase.getCustomer(usermail,userpass);
        if(customer == null){
            System.out.println("wrong credentials for customer");
            return false;
        }
        System.out.println("Verification complete for customer");
        return true;
    }
    public  Customer getCustomer(){
       signupDatabase= new SignupDatabase();
        try {
            customerDatabase.merge(customer,signupDatabase.getuserdetails(customer.getId()));
        } catch (SQLException throwables) {


        }
        return customer;
    }

    public CustomerService(ApprovedRequestsdatabase approvedRequestsdatabase) {
        this.approvedRequestsdatabase = approvedRequestsdatabase;
    }

    public Customer isactive(Customer customer, int type){
         if(customer.isIspaid()) {
               customer.setActive("Active");
           }else{
               System.out.println("You didnt payed the bill or you didnt have a connection");
             customer.setActive("Not Active");
           }


       return customer;
    }



public Customer pay(Customer customer){
        try{
    if(approvedRequestsdatabase.getservicemanid(customer.getId(),1).equals("nill") ||Integer.valueOf(approvedRequestsdatabase.getservicemanid(customer.getId(),1))<100 ) {
        System.out.println("Bill payed by " + customer.getFname() + " " + customer.getLname());
        customer.setDue("No due");
        customer.setIspaid(true);
    }else{
        System.out.println("You didnt have a connection or not yet approved");
    }
    return customer;


}catch(Exception e){
        return customer;
        }

}
public Customer getnotification(Customer customer){
        try{
        if(approvedRequestsdatabase.getservicemanid(customer.getId(),1).equals("nill") ||Integer.valueOf(approvedRequestsdatabase.getservicemanid(customer.getId(),1))<100)
          customer.updatenotification("New connection request approved  ");
        if(approvedRequestsdatabase.getservicemanid(customer.getId(),2).equals("nill") || Integer.valueOf(approvedRequestsdatabase.getservicemanid(customer.getId(),2))<100)
            customer.updatenotification("Cancel connection request approved  ");
    if(approvedRequestsdatabase.getservicemanid(customer.getId(),3).equals("nill") || Integer.valueOf(approvedRequestsdatabase.getservicemanid(customer.getId(),3))<100)
        customer.updatenotification("Service request is approved   ");
        return customer;

}catch(Exception e){
        return customer;
        }
}
public boolean checkreq(int id,int type){
     if(requestsdatabase.chechreq(id,type)==0)
         return false;
     else
         return true;
}

public boolean checkappreq(int id,int type){
        try {
            if (approvedRequestsdatabase.getservicemanid(id, type).equals("nill") || Integer.valueOf(approvedRequestsdatabase.getservicemanid(id, type)) < 100) {
                System.out.println("apptru");
                return true;
            } else {
                System.out.println("appfal");
                return false;
            }
        }catch(Exception e){
            return false;
        }
}




}





