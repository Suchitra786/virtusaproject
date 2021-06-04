package com.suvathiga.spacex.business;


import com.suvathiga.spacex.data.Signup;

import com.suvathiga.spacex.db.SignupDatabase;




public class SignupService{

    private SignupDatabase signupdatabase = null;

    private Signup signup;

    public SignupService(SignupDatabase signupDatabase){

     this.signupdatabase=signupDatabase;
    }



    public Boolean adddetails(String addr1 , String addr2, String city, String state, String phoneno, String aadharid, String usertype,String fname,String lname,String mailid,String password,String citiesavail)  {

         signup= signupdatabase.addsignupdetails(addr1 ,addr2,city, state, phoneno, aadharid,usertype,fname,lname,mailid,password,citiesavail);
            if(signup !=null)
                return true
                        ;
        return false;
    }

    public  Signup getSignupid(){
        return signup;
    }



}
