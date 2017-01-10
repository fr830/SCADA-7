package com.mycompany.app.servlet.dalian2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by siege on 2016-11-21.
 */
@WebServlet("/amount_data_dalian2")
public class DispatchServlet_Dalian2_Amount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("WEB-INF/pages/amount_data_dalian2.jsp");
        requestDispatcher.forward(request,response);
    }
}
