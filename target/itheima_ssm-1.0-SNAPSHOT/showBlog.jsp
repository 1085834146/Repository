<%@ page import="java.util.List" %>
<%@ page import="com.fjndljh.entity.Reply" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <font size="5">标题：<b> ${sessionScope.标题}</b> </font>
    <br>
作者：<b>${sessionScope.作者}</b>
    <hr>
    文章：${sessionScope.文章}
    <hr>
    时间：${sessionScope.时间}
    <hr><hr>
    <font size="5" color="#4682b4"><b>评论区:</b></font>
    <hr>
<%
    List<Reply> list = (List<Reply>)session.getAttribute("replyList");
    if(list != null){
        for(Reply reply : list){
%>
        <tr>
            <td><font color="#4682b4"> <b><%=reply.getUserName()%></b></font><%="评论:    "%></td>
            <td><font style="font-family: SimHei"> <%=reply.getReplyWords()%></font></td>
            <br>
            <td><font size="2"> <%="回复时间：" + reply.getReplyTime()%></font></td>
            <td><a href=<%=request.getContextPath()+"/reply/delete?id="+reply.getReplyID()%>>删除</a></td>
        </tr>
        <hr>
<%
        }
    }
%>

    <form action="${pageContext.request.contextPath}/reply/doReply" method="post">
        我也说一句：
        <br>
<%--        style="font-size:18px;width:400px; height:200px;"--%>
        <Input type=text name="words">
        <Input type=submit value="回复">
    </form>

</body>
</html>
