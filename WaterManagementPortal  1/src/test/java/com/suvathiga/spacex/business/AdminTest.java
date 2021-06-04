package com.suvathiga.spacex.business;

import com.suvathiga.spacex.db.ApprovedRequestsdatabase;
import com.suvathiga.spacex.db.CustomerDatabase;
import com.suvathiga.spacex.db.Jdbcconnector;
import com.suvathiga.spacex.db.Requestsdatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
public class AdminTest {


    @Mock
    Requestsdatabase mockrequestdatabase;

@Mock
AdminService adminService;


    @Test
    public void admintest() throws SQLException {


       adminService=new AdminService();

        Assertions.assertFalse(adminService.isconndata("29"));

       adminService=new AdminService(mockrequestdatabase);

        Assertions.assertTrue(adminService.isconndata("1"));



    }
}
