package com.example.Flying_Tiger;

import com.example.Flying_Tiger.Class.HealthCareProvider;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateHcpServlet", value = "/UpdateHcp-Servlet")
public class UpdateHcpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id= Long.parseLong(request.getParameter("hcpId"));
        LoginServlet log= new LoginServlet();
        String password= log.toUserPwd(request.getParameter("password"));
        String name=request.getParameter("name");
        String type=request.getParameter("type");
        int postcode=Integer.parseInt(request.getParameter("post"));
        try {
            HealthCareProvider temphcp=HealthCareProvider.getMapper().find(id);
            temphcp.setName(name);
            temphcp.setPost(postcode);
            temphcp.setPassword(password);
            temphcp.setType(type);
            HealthCareProvider.getMapper().update(temphcp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String script = "<script>location.href='src/ui-elements/manage_providers.jsp'</script>";
        response.getWriter().println(script);
    }
}
