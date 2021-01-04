<%@ page import="com.fjndljh.entity.Blog" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<font size="5" color="#4682b4"><b> 所有博客：</b></font>
<hr>
<%--    ${requestScope.blogList}--%>

<table border="1">
    <tr>
        <td><b>用户</b></td>
        <td><b>文章标题</b></td>
        <td><b>发表时间</b></td>
        <td><b>操作</b></td>
    </tr>
    <%
        List<Blog> blogList = (List<Blog>)session.getAttribute("blogList");
        for(Blog blog : blogList){
    %>
    <tr>
        <td><%=blog.getUserName()%></td>
        <td><%=blog.getTitle()%></td>
        <td><%=blog.getBlogDate()%></td>
<%--        对于所有博客，没有删除操作   --%>
        <td><a href=<%=request.getContextPath()+"/blog/view?id="+blog.getBlogID()%>>查看</a> </td>
    </tr>
    <%
        }
    %>
</table>
<br>
<a href="/login/res3?userName=${sessionScope.username}&password=${sessionScope.password}">我的博客</a>
</body>
</html>
