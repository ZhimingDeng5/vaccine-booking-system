package com.example.Flying_Tiger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usr= request.getParameter("username");
        String pwd= request.getParameter("password");
        String option=request.getParameter("type");
        boolean v=false;
        long id;
        String script="";
        switch (option){
            case "1":
                id=Long.parseLong(usr);
                Administrator admin= null;
                try {
                    admin = Administrator.getMapper().find(id);
                    if (admin!=null&&admin.getPassword().equals(pwd))
                        v=true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(v)
                    script="<script>location.href='src/ui-elements/Admin_Dashboard.jsp'</script>";
                else {
                    script = "<script>alert('userid or password incorrect!');location.href='src/ui-elements/auth-signin.jsp'</script>";
                }
                break;
            case "3":
                id=Long.parseLong(usr);
                HealthCareProvider hcp= null;
                try {
                    hcp = HealthCareProvider.getMapper().find(id);
                    if (hcp!=null&&hcp.getPassword().equals(pwd))
                        v=true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(v)
                    script = "<script>location.href='src/ui-elements/Provider_profile.jsp?id="+id+"'</script>";
                else {
                    script = "<script>alert('userid or password incorrect!');location.href='src/ui-elements/auth-signin.jsp'</script>";
                }
                break;
            case "2":
                id=Long.parseLong(usr);
                Recipient rcp= null;
                try {
                    rcp = Recipient.getMapper().find(id);
                    if (rcp!=null&&rcp.getPassword().equals(pwd))
                        v=true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(v)
                    script = "<script>location.href='src/ui-elements/my_booking.jsp?id="+id+"'</script>";
                else {
                    script = "<script>alert('userid or password incorrect!');location.href='src/ui-elements/auth-signin.jsp'</script>";
                }
                break;
        }
        response.getWriter().println(script);
    }
}