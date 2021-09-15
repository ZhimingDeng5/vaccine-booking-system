<%@page import="java.sql.ResultSet"%>
<%@page import="com.example.Flying_Tiger.DBConn"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

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
    DBConn dbc=new DBConn();

    dbc.openDB();
    String sql="select * from users";
    ResultSet rs= dbc.execQuery(sql);
    boolean v=false;
    while(rs.next()){
        if(rs.getString("username").equals(usr)&&rs.getString("password").compareTo(pwd)==0){
            v=true;
            break;
        }
    }
    rs.close();
    dbc.closeDB();

    if(v){
        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }
    else{
        request.getRequestDispatcher("faillogin.jsp").forward(request, response);
    }


%>
</body>
</html>
