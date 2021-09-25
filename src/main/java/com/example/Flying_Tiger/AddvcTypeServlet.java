package com.example.Flying_Tiger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddvcTypeServlet", value = "/AddvcType-Servlet")
public class AddvcTypeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        VaccineMapper.getInstance().insert(KeyTable.getKey("vaccine"),type);
        String script = "<script>location.href='src/ui-elements/manage_vaccines.jsp'</script>";
        response.getWriter().println(script);
    }
}
