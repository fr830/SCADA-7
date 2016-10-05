package com.mycompany.app.servlet;

import com.mycompany.app.utils.DataBaseUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by siege on 2015-12-23.
 */
@WebServlet("/GetStatisticServlet")
public class GetStatisticServlet extends HttpServlet {

    private final static String [] stringArr={"AI29","AI30"};
    private final static String [] stringArr1={"A����","B����"};

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder=new StringBuilder("{\"success\": true,\"data\":[");
        String dataString="";
        String sql="SELECT  AI29,AI30 FROM TBL_DATA_ANTING WHERE IMPORTTIME=(SELECT MAX(IMPORTTIME) FROM TBL_DATA_ANTING)";
        Connection connection= DataBaseUtils.getConnection(this.getServletContext());
        Statement statement= null;
        ResultSet resultSet=null;
        try {
            statement = connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                for (int i=0;i<stringArr.length;i++){
                    stringBuilder.append("{\"name\":\""+stringArr1[i]+"\","+"\"value\":\""+resultSet.getFloat(stringArr[i])+"\""+"},");
                }
                dataString=stringBuilder.substring(0,stringBuilder.length()-1)+"]}";
                break;
            }
            System.out.println(dataString);
            response.setContentType("text/html;charset=UTF-8");
            response.getOutputStream().write(dataString.getBytes("UTF-8"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
