<%//Browser fallback force refresh
    response.setHeader("Cache-Control","no-store");
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma","no-cache");
%>
<%
    session.invalidate();
    String script="";
    script = "<script>alert('logout successfully!');location.href='auth-signin.jsp'</script>";

    response.getWriter().println(script);

%>
<%--
  Created by IntelliJ IDEA.
  User: yigaoli
  Date: 2021/9/24
  Time: 8:49 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>logout</title>
</head>
<body>



</body>
</html>
