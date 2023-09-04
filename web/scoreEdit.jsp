<%@ page import="com.xmx.pojo.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" />
</head>

<body>
<%
    Course course=(Course) request.getAttribute("course");
    int no=(int) request.getAttribute("no");
    int addscore=(int) request.getAttribute("addscore");
%>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="adindex.jsp">首页</a></li>
        <li><a href="#">学生管理</a></li>
        <li><a href="adscore.jsp">成绩信息</a></li>
        <li><a href="#">编辑</a></li>
    </ul>
</div>
<form action="scoreServlet?tag=save" method="post">
    <div class="formbody">

        <div class="formtitle"><span>成绩信息</span></div>
        <form action="scoreServlet?tag=save" method="post">
            <ul class="forminfo">
                <li><label>学号</label><input name="stuNo" id="stuNo"  type="text" class="dfinput" value=<%=no%> readonly /></li>
                <li><label>课程名称</label>
                    <select class="select3 scinput1" name="course"  >
                        <%
                            List<Course> list=(List<Course>) request.getAttribute("Course");
                            for(Course course1 : list){
                                if(course.getcId()==course1.getcId()){
                                    out.print("<option selected value='"+course1.getcId()+"'>"+course.getcName()+"</option>");
                                }
                            }
                        %>
                    </select>
                </li>
                <li><label>分数</label><input name="score" id="score" type="text" class="dfinput"  value=<%=addscore%>></li>
                <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存" /></li>
            </ul>

        </form>
    </div>

</form>
</body>

</html>