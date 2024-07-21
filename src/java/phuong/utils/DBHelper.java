/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hungd
 */
public class DBHelper {

   public static Connection getConnection() throws ClassNotFoundException, SQLException{
        //1. Load Driver (vi no  tinh )
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Create connect string . Syntax : protocol://ip:port ; databaseName = (ten database)
        String url = "jdbc:sqlserver://" // protocol
                + "localhost:1433;" //container
                + "databaseName=PHUONG";
        //3. getConnection from Driver Manager
        Connection con  = DriverManager.getConnection(url, "sa", "12345");
        //4. return connection
        return con;
   }
   
 
 
}
