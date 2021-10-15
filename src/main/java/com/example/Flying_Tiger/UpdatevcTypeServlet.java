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
        Long id= Long.parseLong(request.getParameter("id"));
        String type=request.getParameter("type");

        try {
            Vaccine vaccine = Vaccine.getMapper().find(id);
            vaccine.setType(type);
            Vaccine.getMapper().update(vaccine);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String script = "<script>location.href='src/ui-elements/manage_vaccines.jsp'</script>";
        response.getWriter().println(script);
    }
}
