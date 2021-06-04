package com.suvathiga.spacex.business;

import com.suvathiga.spacex.data.Serviceman;

import com.suvathiga.spacex.db.SerivcemanDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ServicmanTest {

    @Mock
    SerivcemanDatabase mockservicemanDatabase;

    @Test
    public void testCheckSeriveman(){

        Mockito.when(mockservicemanDatabase.getServiceman("user", "pass"))
                .thenReturn(new Serviceman());

         ServicemanService servicemanService = new ServicemanService(mockservicemanDatabase);
        boolean bool =servicemanService.checkServiceman("user","pass");
        Assertions.assertTrue(bool);


        ServicemanService servicemanService1=new ServicemanService();
        Assertions.assertFalse(servicemanService1.isservicedata("10"));


    }



}
