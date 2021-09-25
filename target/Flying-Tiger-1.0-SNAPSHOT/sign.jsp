<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:useBean id="dbConn" class="com.example.Flying_Tiger.DBConn"></jsp:useBean>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'sign.jsp' starting page</title>

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

    dbConn.openDB(); //打开数据库

    String sql="insert into users values('"+usr+"','"+pwd+"')";
    System.out.println("sql:"+sql);
    int n=dbConn.execUpdate(sql);    //第二步
    dbConn.closeDB();

    if(n!=0){
        request.getRequestDispatcher("succ.jsp").forward(request, response);
    }else{
        request.getRequestDispatcher("fail.jsp").forward(request, response);
    }
%>
</body>
</html>
