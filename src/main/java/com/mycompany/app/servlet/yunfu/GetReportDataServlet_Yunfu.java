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
 * Created by siege on 2016-06-19.
 */
@WebServlet("/GetReportDataServlet_Yunfu")
public class GetReportDataServlet_Yunfu extends HttpServlet {
    private final static String [] stringArr={
            "MH2_A",
            "AT_A",
            "Plinest_A",
            "Llinest_A",
            "PRISE_A",
            "ALLMH2_A",
            "Pend_A",
            "P0_A",
            "Ptarget_A",
            "MH2_B",
            "AT_B",
            "Plinest_B",
            "Llinest_B",
            "PRISE_B",
            "ALLMH2_B",
            "Pend_B",
            "P0_B",
            "Ptarget_B",
            "Pout_H2_1_1",
            "Pin_H2_1_1",
            "Tout_H2_1_1",
            "Pout_H2_2_1",
            "Pin_H2_2_1",
            "Tout_H2_2_1",
            "P_H_tank_2_1",
            "P_M_TANK_2_1",
            "P_L_TANK_2_1",
            "SHARPTIME"
    };

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage=request.getParameter("currentPage");
        String start_date=request.getParameter("start_date");
        String end_date=request.getParameter("end_date");
        String searchType=request.getParameter("searchType");
        StringBuilder stringBuilder=new StringBuilder("{\"success\": true,");
        int pageCount=0;
        String searchTypeClause="";
        if ("hour".equals(searchType)){
            searchTypeClause=" AND SUBSTR(SHARPTIME,15,2)='00' ";
        }
        String selectSql="select MH2_A," +
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
                "SUBSTR(SHARPTIME,1) AS SHARPTIME  FROM TBL_DATA_YUNFU_HOUR WHERE SUBSTR(SHARPTIME,1,10) BETWEEN '"+start_date+"' AND '"+end_date+"'"+searchTypeClause+" LIMIT "+(Integer.parseInt(currentPage)-1)*10+",10";
        String countSql="SELECT COUNT(*)  FROM TBL_DATA_YUNFU_HOUR WHERE SUBSTR(SHARPTIME,1,10) BETWEEN '"+start_date+"' AND '"+end_date+"'"+searchTypeClause;
        Connection connection= DataBaseUtils.getConnection(this.getServletContext());
        Statement statement= null;
        ResultSet resultSet=null;
        try {
            statement = connection.createStatement();
            resultSet=statement.executeQuery(countSql);
            while (resultSet.next()){
                pageCount=resultSet.getInt("COUNT(*)");
            }
            stringBuilder.append("\"pageCount\":"+(int)Math.ceil(pageCount/10d)+",\"data\":[");
            resultSet=statement.executeQuery(selectSql);
            while (resultSet.next()){
                stringBuilder.append("{");
                for (int i=0;i<stringArr.length-1;i++){
                    stringBuilder.append("\""+stringArr[i]+"\":"+"\""+resultSet.getFloat(stringArr[i])+"\""+",");
                }
                stringBuilder.append("\"SHARPTIME\":"+"\""+resultSet.getString("SHARPTIME")+"\""+"},");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append("]}");
            response.setContentType("text/html;charset=UTF-8");
            response.getOutputStream().write(stringBuilder.toString().getBytes("UTF-8"));
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
