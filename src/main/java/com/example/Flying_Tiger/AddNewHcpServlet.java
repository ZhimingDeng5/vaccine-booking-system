package com.example.Flying_Tiger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "AddNewHcpServlet", value = "/AddNewHcp-Servlet")
public class AddNewHcpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginServlet log= new LoginServlet();
        String password= log.toUserPwd(request.getParameter("password"));
        String name=request.getParameter("name");
        String type=request.getParameter("type");
        int postcode=Integer.parseInt(request.getParameter("postcode"));
        try {
            HealthCareProvider.getMapper().insert(KeyTable.getKey("healthcareprovider"),
                    password,type,name,postcode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String script = "<script>location.href='src/ui-elements/manage_providers.jsp'</script>";
        response.getWriter().println(script);
    }
}
