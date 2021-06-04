package com.suvathiga.spacex.db;

import com.suvathiga.spacex.data.Customer;
import com.suvathiga.spacex.data.Serviceman;
import com.suvathiga.spacex.data.Signup;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupDatabase {

    private static Jdbcconnector jdbcconnector=new Jdbcconnector();
    private Serviceman serviceman=new Serviceman();
    private Customer customer =new Customer();
    private static Signup signup=new Signup();


    public Signup addsignupdetails(String addr1, String addr2, String city, String state, String phoneno, String aadharid, String usertype, String fname, String lname, String mailid, String password, String citiesavail)  {
        try {
            System.out.println(addr1+addr2+city+state+phoneno+aadharid);
            jdbcconnector.dbconnectupdate("insert into userdetails"
                    + "(addr1,addr2,city,state,phoneno,aadharno)"
//                    +"values ("+addr1+","+addr2+","+city+","+state+","+phoneno+" "+aadharid+")");
                    + " values('" + addr1 + "','" + addr2 + "','" + city + "','" + state + "', '" + Integer.valueOf(phoneno) + "' ,'" + aadharid + "')");

        System.out.println("Sucessfully Added details to the user details");
        ResultSet re = null;

            re = jdbcconnector.dbconnectexecute("select userid from userdetails where aadharno='" + aadharid + "'");

        if (re.isBeforeFirst()) {
            while (re.next())
                signup.setUserid(re.getInt("userid"));
        }


int iusertype=Integer.valueOf(usertype);

        if(iusertype==1) {
            jdbcconnector.dbconnectupdate("insert into users "
                    + "(id,fname,lname,emailid,password,usertype)"
                    + " values('"+signup.getUserid()+"','" + fname + "','" + lname + "','" + mailid + "','" + password + "','" + iusertype + "')");
            System.out.println("Insert complete to users table");
            return signup;
        }else{

jdbcconnector.dbconnectupdate( "insert into servicemans"
                    + "(servicemanid,fname,lname,emailid,password,usertype,citiesavail)"
                    + " values('"+signup.getUserid()+"','" + fname + "','" + lname + "','" + mailid + "','" + password + "','" + iusertype + "','"+citiesavail+"')");
            System.out.println("Insert complete to sericeman table");
            return signup;
        }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return signup;
    }

    public ResultSet getuserdetails(int id ) throws SQLException {

           ResultSet  re = jdbcconnector.dbconnectexecute("select addr1,addr2,city,state,phoneno,aadharno from userdetails where userid='" + id + "'");
            return re;
    }
}
