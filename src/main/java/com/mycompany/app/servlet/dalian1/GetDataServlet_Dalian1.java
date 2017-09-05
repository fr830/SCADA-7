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
 * Created by siege on 2015-12-07.
 */

@WebServlet("/GetDataServlet_Dalian1")
public class GetDataServlet_Dalian1 extends HttpServlet {
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
            "Pout900_bar"
    };

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String callBack=request.getParameter("callback");
        StringBuilder stringBuilder;
        if (callBack!=null&&!"".equals(callBack)){
            stringBuilder=new StringBuilder(callBack+"({\"success\": true,\"data\":{");
        }else {
            stringBuilder=new StringBuilder("{\"success\": true,\"data\":{");
        }
        //StringBuilder stringBuilder=new StringBuilder("{\"success\": true,\"data\":{");
        String dataString="";
        String sql="select " +
                "MH2,"+
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
                "UPTIME  from tbl_data_dalian1 WHERE UPTIME=(SELECT MAX( UPTIME ) FROM tbl_data_dalian1)  ";
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

            if (callBack!=null&&!"".equals(callBack)){
                dataString =dataString+")";
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





