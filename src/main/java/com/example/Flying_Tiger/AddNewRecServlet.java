package com.example.Flying_Tiger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "AddNewRecServlet", value = "/AddNewRec-Servlet")
public class AddNewRecServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password=request.getParameter("password");
        String name=request.getParameter("name");
        String birthdatestr=request.getParameter("birthdate");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date birthdate= null;
        try {
            java.util.Date birthdatedata = simpleDateFormat.parse(birthdatestr);
            birthdate=new java.sql.Date(birthdatedata.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            Recipient.getMapper().insert(KeyTable.getKey("recipient"),
                    password,name,birthdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String script = "<script>location.href='src/ui-elements/manage_recipients.jsp'</script>";
        response.getWriter().println(script);
    }
}
