<%@ page import="com.xmx.pojo.Grade" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xmx.pojo.Classes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<%
    //    Student student=(Student) request.getAttribute("student");
    Classes classes=(Classes) request.getAttribute("classes");
%>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="adindex.jsp">首页</a></li>
        <li><a href="adclass.jsp">班级管理</a></li>
        <li><a href="adclass.jsp">班级信息</a></li>
        <li><a href="#">编辑</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>编辑班级</span></div>
    <form action="classesServlet?tag=update" method="post">
        <ul class="forminfo">
            <li><label>班级编号</label><input name="cid" id="cid" type="text" class="dfinput" readonly value=<%=classes.getClassId()%> /></li>
            <li><label>班级名称</label><input name="cname" id="cname" type="text" class="dfinput" value=<%=classes.getClassName()%> /></li>
            <li><label>所属年级</label>
                <select class="select3 scinput1" name="grade">
                    <%
                        List<Grade> list = (List<Grade>) request.getAttribute("grades");
                        for(Grade grades : list){
                            if(classes.getGrade().getgId()==grades.getgId()){
                                out.print("<option selected value='"+grades.getgId()+"'>"+grades.getgName()+"</option>");
                            }else {
                                out.print("<option value='"+grades.getgId()+"'>"+grades.getgName()+"</option>");
                            }
                        }
                    %>
                </select>
            </li>
            <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存" /></li>
        </ul>
    </form>
</div>


</body>

</html>