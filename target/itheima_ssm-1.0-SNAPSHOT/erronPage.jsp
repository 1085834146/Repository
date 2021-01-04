
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>用户不存在或密码错误!<br>
        <form action="${pageContext.request.contextPath}/login.jsp"><!--默认Get方法-->
            <Input type=submit value="返回登陆界面">
            <br>
        </form>
        <form action="${pageContext.request.contextPath}/signIn.jsp">
            <Input type=submit value="注册">
            <br>
        </form>
    </p>
</body>
</html>
