package com.example.Flying_Tiger;

import com.example.Flying_Tiger.Class.Recipient;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "submitChangeServlet", value = "/submitChange-Servlet")
public class SubmitChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Recipient.getMapper().getuow().commit();
        String script = "<script>location.href='manage_recipients.jsp'</script>";
        response.getWriter().println(script);
    }
}
