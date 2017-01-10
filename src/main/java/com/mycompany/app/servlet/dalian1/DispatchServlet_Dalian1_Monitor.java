package com.mycompany.app.servlet.dalian1;

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
@WebServlet("/dalian1")
public class DispatchServlet_Dalian1_Monitor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("WEB-INF/pages/dalian1.jsp");
        requestDispatcher.forward(request,response);
    }
}
