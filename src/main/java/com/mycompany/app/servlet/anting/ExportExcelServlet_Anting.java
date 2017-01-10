package com.mycompany.app.servlet.anting;

import com.mycompany.app.utils.DataBaseUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by siege on 2016-06-20.
 */
@WebServlet("/ExportExcelServlet_Anting")
public class ExportExcelServlet_Anting extends HttpServlet {
    static Map<Integer,String> map=new HashMap<Integer,String>();
    static  String[] excelTitle={
            "时间",
            "入口压力(MPa)","A出口压力(MPa)",
            "B出口压力(MPa)","储气罐压力A(MPa)",
            "储气罐压力B(MPa)","回水温度(°C)",
            "A出口温度(°C)","B出口温度(°C)",
            "A总量(KG)","B总量(KG)",
            "气体压力A(MPa)","气体压力B(MPa)",
            "气体温度A(°C)","气体温度B(°C)"
    };
    static {
        map.put(0,"UPTIME");
        map.put(1,"COMPRESSOR_P");
        map.put(2,"CP10A_P");
        map.put(3,"CP10B_P");
        map.put(4,"T852_P");
        map.put(5,"T853_P");
        map.put(6,"HE109_T");
        map.put(7,"CP10A_T");
        map.put(8,"CP10B_T");
        map.put(9,"AI29");
        map.put(10,"AI30");
        map.put(11,"AI015");
        map.put(12,"AI016");
        map.put(13,"AI013");
        map.put(14,"AI014");

    }


    public void produceExcel(HttpServletRequest request, HttpServletResponse response, ResultSet resultSet) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        sheet.autoSizeColumn(0);
        //sheet.setColumnWidth((short) 0, (short) (35.7));
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        Font font=workbook.createFont();
        font.setFontName("宋体");
       // font.setFontHeightInPoints((short) 14);
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        try {
            HSSFRow row0 = sheet.createRow(0);
            HSSFCell cell0;
            for (int i = 0; i < excelTitle.length; i++) {
                cell0=row0.createCell(i);
                cell0.setCellStyle(cellStyle);
                cell0.setCellValue(excelTitle[i]);
            }
            int i=1;
            HSSFRow row;
            while (resultSet.next()){
                row= sheet.createRow(i);
                for (int j = 0; j <map.size() ; j++) {
                    HSSFCell cell=row.createCell(j);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(resultSet.getString(map.get(j)));
                }
                i++;
            }
            String filename = "安亭站数据报表.xls";
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" +URLEncoder.encode(filename,"UTF-8"));
             OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final static String[] stringArr = {
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
            "UPTIME"
    };

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String start_date = request.getParameter("start_date");
        String end_date = request.getParameter("end_date");
        String searchType=request.getParameter("searchType");
        int pageCount = 0;
        String searchTypeClause="";
        if ("hour".equals(searchType)){
            searchTypeClause=" AND SUBSTR(UPTIME,15,2)='00' ";
        }
        String selectSql = "select ROUND(ATD_102,3) AS ATD_102," +
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
                "AI020,SUBSTR(UPTIME,1) AS UPTIME  FROM TBL_DATA_ANTING_HOUR WHERE SUBSTR(UPTIME,1,10) BETWEEN '" + start_date + "' AND '" + end_date + "'"+searchTypeClause;
        //String countSql = "SELECT COUNT(*)  FROM TBL_DATA_ANTING WHERE SUBSTR(UPTIME,1,10) BETWEEN '" + start_date + "' AND '" + end_date + "'";
        Connection connection = DataBaseUtils.getConnection(this.getServletContext());
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            /*resultSet = statement.executeQuery(countSql);
            while (resultSet.next()) {
                pageCount = resultSet.getInt("COUNT(*)");
            }*/
            resultSet = statement.executeQuery(selectSql);
            produceExcel(request, response,  resultSet);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}