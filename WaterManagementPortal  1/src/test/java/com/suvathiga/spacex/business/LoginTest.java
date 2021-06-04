package com.suvathiga.spacex.business;

import com.suvathiga.spacex.formclass.Loginform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LoginTest {

    @Test
    public void testIsAdminCheck(){
       boolean result =  Loginform.isAdmin("wmpadmin", "wmpadmin");
       Assertions.assertTrue(result);
    }





}
