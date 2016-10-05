package com.mycompany.app.utils;



import com.mysql.jdbc.Driver;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by siege on 2015-12-07.
 */
public class DataBaseUtils {
    public static Connection  getConnection(ServletContext servletContext){
        Connection connection=null;
        Properties prop=new Properties();
        String url=null;
        String userName=null;
        String password=null;
        try {
            String path=servletContext.getRealPath("WEB-INF/config/database.properties");
            prop.load(new FileInputStream(path));
            url=(String)prop.get("url");
            userName=(String)prop.get("userName");
            password=(String)prop.get("password");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("load database info error");
            return null;
        }
        try {
            DriverManager.registerDriver(new Driver());
            connection=DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("can't connect to the database");
        }
        return connection;
    }

}
