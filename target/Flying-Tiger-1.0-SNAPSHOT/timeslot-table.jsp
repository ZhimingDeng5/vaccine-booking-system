<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2021/9/17
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.Set" %>
<%@ page import="com.example.Flying_Tiger.Timeslot"%>
<html>
<head>
    <title>Timeslot Table</title>
</head>
<body>
<%
    Set<Timeslot> timeslottable=Timeslot.getall();
%>
<table >
    <tr>
    <th>id</th>
    <th>date</th>
    <th>time</th>
    <th>hcpid</th>
        <th></th>
    </tr>
    <%
        for (Timeslot timeslot:timeslottable) {
    %>
    <tr>
        <td><%=timeslot.getTimeslotID() %></td>
        <td><%=timeslot.getDate() %></td>
        <td><%=timeslot.getTime() %></td>
        <td><%=timeslot.getHcpID() %></td>
        <!-- 调用数据库判断默认选项
        <td><input type="radio" name="sex" value="boy" <%=1>0 ? "checked" : null%> > </td>!-->
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
