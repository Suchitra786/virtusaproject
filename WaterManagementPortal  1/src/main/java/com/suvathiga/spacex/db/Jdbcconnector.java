package com.suvathiga.spacex.db;

import java.sql.*;

public class Jdbcconnector {
    Statement mystat;
    Connection con;
    public Statement getconnect() throws SQLException {
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/wmp", "root", "root");
        return  con.createStatement();
    }

    public void dbconnectupdate(String sql) throws SQLException {
        mystat=getconnect();
        mystat.executeUpdate(sql);
     }
     public ResultSet dbconnectexecute(String sql) throws  SQLException{
        mystat=getconnect();
        ResultSet re=mystat.executeQuery(sql);

        return re;
     }







}
