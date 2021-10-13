package com.example.Flying_Tiger;

import com.example.Flying_Tiger.Class.KeyTable;
import com.example.Flying_Tiger.Class.Timeslot;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

@WebServlet(name = "AddTimeslotServlet", value = "/AddTimeslot-Servlet")
public class AddTimeslotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Time time= Time.valueOf(request.getParameter("time"));
        Date date= Date.valueOf(request.getParameter("date"));
        Long id= KeyTable.getKey("timeslot");
        try {
            Timeslot.getMapper().insert(id,date,time);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
