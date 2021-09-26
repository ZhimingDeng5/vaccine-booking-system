package com.example.Flying_Tiger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AnswerJudgeServlet", value = "/AnswerJudge-Servlet")
public class AnswerJudgeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean permit=true;
        long recid= Long.parseLong(request.getParameter("recid"));
        long hcpid= Long.parseLong(request.getParameter("hcpid"));
        long tid= Long.parseLong(request.getParameter("tid"));
        long vacid=Long.parseLong(request.getParameter("vacid"));
        for (int i=1;i<=5;i++)
        {
            String para="Q"+i;
            String Answer=request.getParameter(para);
            if(Answer.equals("Yes"))
            {
                permit=false;
                break;
            }
        }
        if(!permit)
        {
            String script = "<script>alert('your health factors imply that this type of vaccine is not suitable for you!');" +
                    "location.href='src/ui-elements/booking.jsp?id="+recid+
                    "&hcpid=&date='</script>";
            response.getWriter().println(script);
        }
        else
        {
            try {
                Recipient.getMapper().book(recid,hcpid,tid,vacid);
                String script = "<script>location.href='src/ui-elements/my_booking.jsp?id="+recid+ "'</script>";
                response.getWriter().println(script);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
