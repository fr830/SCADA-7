package com.mycompany.app.servlet.dalian1;



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
@WebServlet("/GetReportDataServlet_Dalian1")
public class GetReportDataServlet_Dalian1 extends HttpServlet {
    private final static String [] stringArr={
            "MH2",
            "AT",
            "Pinst_MPa",
            "Linst",
            "COSTH2",
            "AMH2",
            "Pend_MPa",
            "P0_MPa",
            "Ptarget_MPa",
            "Sjqj35",
            "WRITEdispenssor",
            "AMH2_Calculation35",
            "Pinst_bar",
            "Pend_bar",
            "P0_bar",
            "Ptarget_bar",
            "WRITEcompenssor",
            "Pin450",
            "Pout450",
            "Tout450",
            "Toil450",
            "Pin450_bar",
            "Pout450_bar",
            "BY11",
            "BY12",
            "BY13",
            "Pin900",
            "Pout900",
            "Tout900",
            "Toil900",
            "Pin900_bar",
            "Pout900_bar",
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
        String selectSql="select MH2," +
                "AT,"+
                "Pinst_MPa,"+
                "Linst,"+
                "COSTH2,"+
                "AMH2,"+
                "Pend_MPa,"+
                "P0_MPa,"+
                "Ptarget_MPa,"+
                "Sjqj35,"+
                "WRITEdispenssor,"+
                "AMH2_Calculation35,"+
                "Pinst_bar,"+
                "Pend_bar,"+
                "P0_bar,"+
                "Ptarget_bar,"+
                "WRITEcompenssor,"+
                "Pin450,"+
                "Pout450,"+
                "Tout450,"+
                "Toil450,"+
                "Pin450_bar,"+
                "Pout450_bar,"+
                "BY11,"+
                "BY12,"+
                "BY13,"+
                "Pin900,"+
                "Pout900,"+
                "Tout900,"+
                "Toil900,"+
                "Pin900_bar,"+
                "Pout900_bar,"+
                "SUBSTR(SHARPTIME,1) AS SHARPTIME  FROM TBL_DATA_DALIAN1_HOUR WHERE SUBSTR(SHARPTIME,1,10) BETWEEN '"+start_date+"' AND '"+end_date+"'"+searchTypeClause+" LIMIT "+(Integer.parseInt(currentPage)-1)*10+",10";
        String countSql="SELECT COUNT(*)  FROM TBL_DATA_DALIAN1_HOUR WHERE SUBSTR(SHARPTIME,1,10) BETWEEN '"+start_date+"' AND '"+end_date+"'"+searchTypeClause;
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
