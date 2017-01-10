package com.mycompany.app.servlet.yunfu;



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

@WebServlet("/GetDataServlet_Yunfu")
public class GetDataServlet_Yunfu extends HttpServlet {
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
        String sql="select MH2_A," +
                "AT_A," +
                "Plinest_A," +
                "Llinest_A, " +
                "PRISE_A, " +
                "ALLMH2_A, " +
                "Pend_A, " +
                "P0_A," +
                "Ptarget_A, " +
                "MH2_B, " +
                "AT_B, " +
                "Plinest_B, " +
                "Llinest_B, " +
                "PRISE_B, " +
                "ALLMH2_B, " +
                "Pend_B, " +
                "P0_B, " +
                "Ptarget_B, " +
                "Pout_H2_1_1, " +
                "Pin_H2_1_1, " +
                "Tout_H2_1_1, " +
                "Pout_H2_2_1, " +
                "Pin_H2_2_1, " +
                "Tout_H2_2_1," +
                "P_H_tank_2_1, " +
                "P_M_TANK_2_1, " +
                "P_L_TANK_2_1, " +
                "UPTIME  from tbl_data_yunfu WHERE UPTIME=(SELECT MAX( UPTIME ) FROM TBL_DATA_ANTING)  ";
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





