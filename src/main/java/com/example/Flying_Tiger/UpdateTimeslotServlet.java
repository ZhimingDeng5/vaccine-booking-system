package com.example.Flying_Tiger;



import com.example.Flying_Tiger.Class.Timeslot;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

@WebServlet(name = "UpdateTimeslotServlet", value = "/UpdateTimeslot-Servlet")
public class UpdateTimeslotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String edit=request.getParameter("edit");
        int numLeft= Integer.parseInt(request.getParameter("numLeft"));
        long hcpid= Long.parseLong(request.getParameter("id"));
        Date date= Date.valueOf(request.getParameter("date"));
        String timestr=request.getParameter("time");
        //add optimistic lock

        if(timestr.length()<8) {
            timestr += ":00";
        }
        Time time= Time.valueOf(timestr);
        if(edit.equals("yes")) {
            int version= Integer.parseInt(request.getParameter("version"));
            long tid = Long.parseLong(request.getParameter("tid"));
            try {
                Timeslot temp=Timeslot.getMapper().find(tid);
                temp.SetTime(time);
                temp.SetDate(date);
                Timeslot.getMapper().update(temp);
                String alert=Timeslot.getMapper().editTimeslotAssociation(hcpid,tid,numLeft,version);
                String script = "<script>alert('" +alert+"');"+
                        "location.href='src/ui-elements/my_timeslot.jsp?id=" +
                        hcpid+"'</script>";
                response.getWriter().println(script);
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        else {
            try {
                long tid=Timeslot.getMapper().getIDByDateTime(date,time);
                if(tid!=0)
                {
                    if(Timeslot.getMapper().getNumLeft(hcpid,tid)>=0)
                    {
                        String script = "<script>alert('timeslot existed! You should edit " +
                                "that timeslot If you want to add more seats for this time!');" +
                                "location.href='src/ui-elements/my_timeslot.jsp?id=" +
                                hcpid+"'</script>";
                        response.getWriter().println(script);
                    }
                    else
                    {
                      Timeslot.getMapper().insertTimeslotAssociation(hcpid,tid,numLeft);
                    }
                }
                else
                {
                    Timeslot.getMapper().insert(date,time);
                    tid=Timeslot.getMapper().getIDByDateTime(date,time);
                    Timeslot.getMapper().insertTimeslotAssociation(hcpid,tid,numLeft);
                }
                String script = "<script>alert('Add timeslot successfully');" +
                        "location.href='src/ui-elements/my_timeslot.jsp?id=" +
                        hcpid+"'</script>";
                response.getWriter().println(script);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
