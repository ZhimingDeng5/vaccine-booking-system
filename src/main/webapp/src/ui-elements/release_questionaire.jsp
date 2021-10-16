<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.example.Flying_Tiger.*" %>
<%@ page import="com.example.Flying_Tiger.Class.HealthCareProvider" %>
<%@ page import="com.example.Flying_Tiger.Mapper.ExclusiveWriteLockManager" %>


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
    Long id=Long.parseLong(request.getParameter("id"));
    long qid= Long.parseLong((request.getParameter("qid")));
    ExclusiveWriteLockManager.getInstance().releaseLock(qid, id);
    String script = "<script>location.href='Edit_questionnaire.jsp?id="+id+"'</script>";
    response.getWriter().println(script);

%>
</body>
</html>
