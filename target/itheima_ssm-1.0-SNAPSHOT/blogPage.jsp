<%@ page import="java.util.List" %>
<%@ page import="com.fjndljh.entity.Blog" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <font size="5" color="#4682b4"><b> ${sessionScope.username}的博客：</b></font>
    <hr>
<%--    ${requestScope.blogList}--%>

    <table border="1">
    <tr>
        <td><b>用户</b></td>
        <td><b>文章标题</b></td>
        <td><b>发表时间</b></td>
        <td colspan="2"><b>操作</b></td>
    </tr>
    <%
        List<Blog> blogList = (List<Blog>)session.getAttribute("blogList");
        for(Blog blog : blogList){
    %>
        <tr>
            <td><%=blog.getUserName()%></td>
            <td><%=blog.getTitle()%></td>
            <td><%=blog.getBlogDate()%></td>
            <td><a href=<%=request.getContextPath()+"/blog/delete1?id="+blog.getBlogID()%>>删除</a></td>
            <td><a href=<%=request.getContextPath()+"/blog/view?id="+blog.getBlogID()%>>查看</a> </td>
        </tr>
    <%
        }
    %>
    </table>

</body>
</html>
