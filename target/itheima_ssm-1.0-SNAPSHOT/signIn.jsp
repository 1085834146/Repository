<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <font size="5" color="#4682b4"><b>请注册： </b> </font>
    <hr>
    <form action="${pageContext.request.contextPath}/login/sign" method="post">
        <P><b>输入用户名:</b>
            <br>
            <Input type=text name="userName">
            <br>
            <b>输入密码:</b>
            <br>
            <input type="password" name="password">
            <br>
            <Input type=submit value="注册">
        </p>
    </form>
</body>
</html>
