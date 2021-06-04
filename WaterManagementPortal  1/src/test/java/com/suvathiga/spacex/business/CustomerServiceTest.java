package com.suvathiga.spacex.business;


import com.suvathiga.spacex.data.Customer;
import com.suvathiga.spacex.db.ApprovedRequestsdatabase;
import com.suvathiga.spacex.db.CustomerDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerDatabase mockCustomerDatabase;
    @Mock
    ApprovedRequestsdatabase mockapprovedrequesdatabase;


    @Test
    public void testCheckCustomer(){
            Customer customer=new Customer();

             String user="user";
             String pass="pass";
            customer.setEmailid(user);
            customer.setPassword(pass);

        Mockito.when(mockCustomerDatabase.getCustomer(user,pass))
                .thenReturn(customer);
        CustomerService customerService = new CustomerService(mockCustomerDatabase);
        boolean bool = customerService.checkcustomer(user,pass);
        Assertions.assertTrue(bool);


        Assertions.assertEquals(new Customer().getClass(),customerService.getCustomer().getClass());


        Mockito.when(mockapprovedrequesdatabase.getservicemanid(12, 1))
                .thenReturn(new String("11"));
         customer.setId(12);
         customerService = new CustomerService(mockapprovedrequesdatabase);
        Assertions.assertEquals(customer,customerService.pay(customer ));



    }

}
