package com.example.Flying_Tiger;

import com.example.Flying_Tiger.Class.HealthCareProvider;
import com.example.Flying_Tiger.Class.KeyTable;
import com.example.Flying_Tiger.Mapper.VaccineMapper;
import com.example.Flying_Tiger.Class.Vaccine;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "UpdatevcTypeServlet", value = "/UpdatevcType-Servlet")
public class UpdatevcTypeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id= Long.parseLong(request.getParameter("vcId"));
        String type=request.getParameter("type");
        Vaccine vaccine = new Vaccine(id, type);
        vaccine.setType(type);
        String script = "<script>location.href='src/ui-elements/manage_vaccines.jsp'</script>";
        response.getWriter().println(script);
    }
}
