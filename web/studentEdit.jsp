<%@ page import="com.xmx.pojo.Student" %>
<%@ page import="com.xmx.pojo.Grade" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xmx.pojo.Classes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" />
    <script src="https://www.layuicdn.com/layui-v2.5.6/layui.js"></script>



</head>

<body>
<%
    Student student=(Student) request.getAttribute("student");
%>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="adindex.jsp">首页</a></li>
        <li><a href="adstudent.jsp">学生管理</a></li>
        <li><a href="adstudent.jsp">学生信息</a></li>
        <li><a href="#">编辑</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>学生信息</span></div>
    <form action="studentServlet?tag=update" method="post">
        <ul class="forminfo">
            <li><label>学号</label><input name="stuNo" id="stuNo" type="text" class="dfinput" readonly  value="<%=student.getStuNo() %>" /></li>
            <li><label>姓名</label><input name="stuName" id="stuName" type="text" class="dfinput"  value="<%=student.getStuName()%>"/> </li>
            <li><label>密码</label><input name="pwd" id="pwd" type="password" class="dfinput" value="<%=student.getPwd()%>" /></li>
            <li><label>性别</label>
                <input type="radio" name="sex" value="男" <%=student.getSex().equals("男")?"checked":""%>><i style="font-size: 14px; font-style: normal;"> 男
                </i>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" name="sex" value="女" <%=student.getSex().equals("女")?"checked":""%>><i style="font-size: 14px; font-style: normal;"> 女
                </i>
            </li>
            <li><label>出生日期</label><input type="text" class="dfinput" name="born" id="born" placeholder="" value="<%=student.getBorn()%>">
            </li>
            <li><label>所属年级</label>
                <select class="select3 scinput1" name="grade">
                    <option>请选择</option>
                    <%
                        List<Grade> list = (List<Grade>) request.getAttribute("grades");
                        for(Grade grades : list){
                            if(student.getGrade().getgId()==grades.getgId()){
                                out.print("<option selected value='"+grades.getgId()+"'>"+grades.getgName()+"</option>");
                            }else {
                                out.print("<option value='"+grades.getgId()+"'>"+grades.getgName()+"</option>");
                            }
                        }
                    %>
                </select>
            </li>
            <li><label>所属班级</label>
                <select class="select3 scinput1" name="stuclass">
                    <option>请选择</option>
                    <%
                        List<Classes> list1 = (List<Classes>) request.getAttribute("classes");
                        for(Classes classes : list1){
                            if(student.getClasses().getClassId()==classes.getClassId()){
                                out.print("<option selected value='"+classes.getClassId()+"'>"+classes.getClassName()+"</option>");
                            }else {
                                out.print("<option value='"+classes.getClassId()+"'>"+classes.getClassName()+"</option>");
                            }
                        }
                    %>

                </select>
            </li>
            <li><label>联系电话</label><input name="phone" id="tel" type="text" class="dfinput"  value=<%=student.getPhone()%>></li>
            <li><label>地址</label><input name="address" id="address" type="text" class="dfinput" value=<%=student.getAddress()%>></li>
            <li><label>身份证</label><input name="idCard" id="idcard" type="text" class="dfinput" value=<%=student.getIdCard()%>></li>
            <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存" /></li>
        </ul>

    </form>
</div>

<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#born',
            type: 'date',
            trigger: 'click'
        });
    });

</script>
</body>

</html>