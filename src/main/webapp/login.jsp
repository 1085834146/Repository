
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <font size="5" color="#4682b4"><b>登陆 </b> </font>
    <hr>
    <P><font size="4"> <b>输入用户名:</b></font></p>
    <%--   使用post，这样用户信息不会出现在url中 --%>
    <form action="${pageContext.request.contextPath}/login/res2" method="post">
            用户名: <Input type=text name="userName">
            <br>
              密码: <input type="password" name="password">
            <br>
            <Input type=submit value="登录">
    </form>
    <form action="${pageContext.request.contextPath}/signIn.jsp" method="post">
        <Input type=submit value="去注册">
    </form>
</body>
</html>
