package com.example.Flying_Tiger;

import com.example.Flying_Tiger.Class.Questionaire;
import com.example.Flying_Tiger.Mapper.ExclusiveWriteLockManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EditQuestionnaireServlet", value = "/EditQuestionnaire-Servlet")
public class EditQuestionnaireServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long hcpid= Long.parseLong(request.getParameter("id"));
        String type=request.getParameter("type");
        String q1=request.getParameter("question1");
        String q2=request.getParameter("question2");
        String q3=request.getParameter("question3");
        String q4=request.getParameter("question4");
        String q5=request.getParameter("question5");
        long id= Long.parseLong((request.getParameter("qid")));
        int minAge= Integer.parseInt(request.getParameter("minAge"));
        int maxAge= Integer.parseInt(request.getParameter("maxAge"));
        Questionaire questionnaire=new Questionaire(id,type,hcpid,minAge,maxAge,q1,q2,q3,q4,q5);
        try {
            Questionaire.getMapper().update(questionnaire);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ExclusiveWriteLockManager.getInstance().releaseLock(id, hcpid);
        String script = "<script>location.href='src/ui-elements/Edit_questionnaire.jsp?id="+hcpid+"'</script>";
        response.getWriter().println(script);
    }

}
