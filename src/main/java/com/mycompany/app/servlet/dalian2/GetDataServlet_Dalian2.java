package com.mycompany.app.servlet.dalian2;



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
 * Created by siege on 2015-12-07.
 */

@WebServlet("/GetDataServlet_Dalian2")
public class GetDataServlet_Dalian2 extends HttpServlet {
    private final static String [] stringArr={
            "HE109_T",
            "CP10A_T",
            "CP10B_T",
            "AI014",
            "AI013",
            "COMPRESSOR_P",
            "CP10A_P",
            "CP10B_P",
            "T852_P",
            "T853_P",
            "AI016",
            "AI015",
            "ATD_102",
            "ATD_101",
            "ATD_104",
            "ATD_105",
            "ATD_103",
            "ATD_106",
            "AI011",
            "AI05",
            "AI29",
            "AI30",
            "AI08",
            "AI028",
            "AI01",
            "AI020"
    };

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder=new StringBuilder("{\"success\": true,\"data\":{");
        String dataString="";
        String sql="select ROUND(ATD_102,3) AS ATD_102," +
                "ROUND(ATD_101,3) AS ATD_101," +
                "COMPRESSOR_P," +
                "HE109_T, " +
                "CP10A_P, " +
                "ATD_104, " +
                "CP10B_P, " +
                "ROUND(ATD_105,3) AS ATD_105," +
                "CP10A_T, " +
                "CP10B_T, " +
                "T852_P, " +
                "T853_P, " +
                "ROUND(ATD_103,3) AS ATD_103, " +
                "AI016, " +
                "AI014, " +
                "AI013, " +
                "AI011, " +
                "ROUND(AI015,3) AS AI015, " +
                "ROUND(AI05,3) AS AI05, " +
                "AI29, " +
                "AI30, " +
                "AI08, " +
                "AI028, " +
                "ROUND(ATD_106,3) AS ATD_106," +
                "AI01, " +
                "AI020,UPTIME  from tbl_data_anting WHERE UPTIME=(SELECT MAX( UPTIME ) FROM TBL_DATA_ANTING)  ";
        Connection connection= DataBaseUtils.getConnection(this.getServletContext());
        Statement statement= null;
        ResultSet resultSet=null;
        try {
            statement = connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                for (int i=0;i<stringArr.length;i++){
                    stringBuilder.append("\""+stringArr[i]+"\":"+"\""+resultSet.getFloat(stringArr[i])+"\""+",");
                }
                dataString=stringBuilder.substring(0,stringBuilder.length()-1)+"}}";
                break;
            }
            response.setContentType("text/html;charset=UTF-8");
            response.getOutputStream().write(dataString.getBytes("UTF-8"));
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





