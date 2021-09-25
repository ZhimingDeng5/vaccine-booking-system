package com.example.Flying_Tiger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.security.MessageDigest;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usr= request.getParameter("username");
        String pwd= toUserPwd(request.getParameter("password"));
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
                if(v) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", option);
                    script = "<script>location.href='src/ui-elements/Admin_Dashboard.jsp'</script>";
                }
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
                if(v) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", option);
                    script = "<script>location.href='src/ui-elements/Provider_profile.jsp?id=" + id + "'</script>";
                }
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
                if(v) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", option);
                    script = "<script>location.href='src/ui-elements/my_booking.jsp?id=" + id + "'</script>";
                }
                else {
                    script = "<script>alert('userid or password incorrect!');location.href='src/ui-elements/auth-signin.jsp'</script>";
                }
                break;
        }
        response.getWriter().println(script);
    }
    private String toUserPwd(final String password) {
        try {
            if (password == null) {
                return null;
            }

            final MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            final byte[] digests = messageDigest.digest(password.getBytes());

            final StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < digests.length; i++) {
                int halfbyte = (digests[i] >>> 4) & 0x0F;
                for (int j = 0; j <= 1; j++) {
                    stringBuilder.append(
                            ((0 <= halfbyte) && (halfbyte <= 9))
                                    ? (char) ('0' + halfbyte)
                                    : (char) ('a' + (halfbyte - 10)));
                    halfbyte = digests[i] & 0x0F;
                }
            }

            return stringBuilder.toString();
        } catch (final Throwable throwable) {
            //this.log.error("error converting password", throwable);
            return null;
        }
    }

}