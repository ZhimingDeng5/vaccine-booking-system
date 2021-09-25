package com.example.Flying_Tiger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "UpdateRepServlet", value = "/UpdateRep-Servlet")
public class UpdateRepServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id= Long.parseLong(request.getParameter("id"));
        String password=request.getParameter("password");
        String name=request.getParameter("name");
        String birthdatestr=request.getParameter("birthdate");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try{
            Recipient recipient=Recipient.getMapper().find(id);
            recipient.setPassword(password);
            recipient.setName(name);
            java.util.Date birthdatedata = null;
            try {
                birthdatedata = simpleDateFormat.parse(birthdatestr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date birthdate=new java.sql.Date(birthdatedata.getTime());
            recipient.setBirth(birthdate);
            Recipient.getMapper().getuow().registerDirty(recipient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String script = "<script>location.href='src/ui-elements/manage_recipients.jsp'</script>";
        response.getWriter().println(script);
    }

}
