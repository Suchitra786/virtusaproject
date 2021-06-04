package com.suvathiga.spacex.business;

import com.suvathiga.spacex.data.Customer;
import com.suvathiga.spacex.data.Signup;
import com.suvathiga.spacex.db.CustomerDatabase;
import com.suvathiga.spacex.db.SignupDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
public class SignupTest {

    @Mock

    SignupDatabase mocksignupDatabase;

    @Test
    public void testsignup(){

        Mockito.when(mocksignupDatabase.addsignupdetails("abc","ced","py","india","1234","das","1","as","qw","qw@gmail.com","123","py,my,ty"))
                .thenReturn(new Signup());

       SignupService signupService = new SignupService(mocksignupDatabase);
        boolean bool = signupService.adddetails("abc","ced","py","india","1234","das","1","as","qw","qw@gmail.com","123","py,my,ty");
        Assertions.assertTrue(bool);
    }

}
