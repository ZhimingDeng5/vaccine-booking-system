package com.example.Flying_Tiger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddQuestionnaireServlet", value = "/AddQuestionnaire-Servlet")
public class AddQuestionnaireServlet extends HttpServlet {
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
        int minAge= Integer.parseInt(request.getParameter("minAge"));
        int maxAge= Integer.parseInt(request.getParameter("maxAge"));
        //Questionaire questionnaire=new Questionaire(id,type,hcpid,question1,question2,question3,question4,question5);
        try {
            Questionaire test=Questionaire.getMapper().find(hcpid,type);
            if (test!=null)
            {
                String script = "<script>alert('questionnaire of this vaccine type existed! Please edit that questionnaire!');" +
                        "location.href='src/ui-elements/Add_questionnaire.jsp?id="+hcpid+"'</script>";
                System.out.println(script);
                response.getWriter().println(script);
            }
            else {
                long id = KeyTable.getKey("questionaire");
                Questionaire.getMapper().insert(id, type, hcpid, minAge, maxAge, q1, q2, q3, q4, q5);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String script = "<script>location.href='src/ui-elements/Add_questionnaire.jsp?id="+hcpid+"'</script>";
        response.getWriter().println(script);
    }
}
