<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.example.Flying_Tiger.*" %>


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
    Long hcpid=Long.parseLong(request.getParameter("id"));
    Long rid=Long.parseLong(request.getParameter("rid"));
    int edit= Integer.parseInt(request.getParameter("edit"));
    if(edit==0) {
        Recipient.getMapper().deletebooking(rid, hcpid);
    }
    else{
        Recipient recipient=Recipient.getMapper().find(rid);
        recipient.setInjected(true);
        Recipient.getMapper().update(recipient);
    }
    String script = "<script>location.href='booking_list.jsp?id="+hcpid+"'</script>";
    response.getWriter().println(script);
%>
</body>
</html>
