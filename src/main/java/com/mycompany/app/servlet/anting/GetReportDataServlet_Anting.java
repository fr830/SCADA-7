package com.mycompany.app.servlet.anting;



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
@WebServlet("/GetReportDataServlet_Anting")
public class GetReportDataServlet_Anting extends HttpServlet {
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
            "AI020",
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
        String selectSql="select ROUND(ATD_102,3) AS ATD_102," +
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
                "AI020,SUBSTR(UPTIME,1) AS SHARPTIME  FROM TBL_DATA_ANTING WHERE SUBSTR(UPTIME,1,10) BETWEEN '"+start_date+"' AND '"+end_date+"'"+searchTypeClause+" LIMIT "+(Integer.parseInt(currentPage)-1)*10+",10";
        String countSql="SELECT COUNT(*)  FROM TBL_DATA_ANTING WHERE SUBSTR(UPTIME,1,10) BETWEEN '"+start_date+"' AND '"+end_date+"'"+searchTypeClause;
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
