package com.example.Flying_Tiger;

import com.example.Flying_Tiger.Class.Questionaire;
import com.example.Flying_Tiger.Mapper.ExclusiveWriteLockManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EditQuestionnaireServlet", value = "/EditQuestionnaire-Servlet")
public class ReleaseQuestionnaireServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long hcpid= Long.parseLong(request.getParameter("id"));
        long id= Long.parseLong((request.getParameter("qid")));
        ExclusiveWriteLockManager.getInstance().releaseLock(id, hcpid);
        String script = "<script>location.href='src/ui-elements/Edit_questionnaire.jsp?id="+hcpid+"'</script>";
        response.getWriter().println(script);
    }

}
