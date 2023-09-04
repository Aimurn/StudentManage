<%@ page import="com.xmx.pojo.Grade" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<%
    Grade grade=(Grade) request.getAttribute("grade");
%>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="adindex.jsp">首页</a></li>
        <li><a href="adgrade.jsp">年级管理</a></li>
        <li><a href="adgrade.jsp">年级信息</a></li>
        <li><a href="#">编辑</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>编辑年级</span></div>
    <form action="gradeServlet?tag=save" method="post">
        <ul class="forminfo">
            <li><label>年级编号</label><input name="gid" id="gid" type="text" class="dfinput" value=<%=grade.getgId()%> readonly /></li>
            <li><label>年级名称</label><input name="gname" id="gname" type="text" class="dfinput" value=<%=grade.getgName()%> /></li>
            <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存" /></li>
        </ul>
    </form>
</div>


</body>

</html>