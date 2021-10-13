<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.example.Flying_Tiger.*" %>
<%@ page import="com.example.Flying_Tiger.Class.Administrator" %>
<%@ page import="com.example.Flying_Tiger.Class.Recipient" %>
<%@ page import="com.example.Flying_Tiger.Class.HealthCareProvider" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>My JSP 'v.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>

<body>
<%
    String usr= request.getParameter("username");
    String pwd= request.getParameter("password");
    String option=request.getParameter("type");
    boolean v=false;
    Long id;
    String script="";
    switch (option){
        case "1":
            id=Long.parseLong(usr);
            Administrator admin= Administrator.getMapper().find(id);
            if (admin!=null&&admin.getPassword().equals(pwd))
                v=true;
            if(v)
                script="<script>location.href='Admin_Dashboard.jsp'</script>";
            else {
                script = "<script>alert('userid or password incorrect!');location.href='auth-signin.jsp'</script>";
                response.getWriter().println(script);
            }
            break;
        case "3":
            id=Long.parseLong(usr);
            HealthCareProvider hcp=HealthCareProvider.getMapper().find(id);
            if (hcp!=null&&hcp.getPassword().equals(pwd))
                v=true;
            if(v)
                script = "<script>location.href='Provider_profile.jsp?id="+id+"'</script>";
            else {
                script = "<script>alert('userid or password incorrect!');location.href='auth-signin.jsp'</script>";

            }
            break;
        case "2":
            id=Long.parseLong(usr);
            Recipient rcp= Recipient.getMapper().find(id);
            if (rcp!=null&&rcp.getPassword().equals(pwd))
                v=true;
            if(v)
                script = "<script>location.href='my_booking.jsp?id="+id+"'</script>";
            else {
                script = "<script>alert('userid or password incorrect!');location.href='auth-signin.jsp'</script>";
                response.getWriter().println(script);
            }
            break;
    }
    response.getWriter().println(script);
%>
</body>
</html>
