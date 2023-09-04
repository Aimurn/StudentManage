<%@ page import="com.xmx.pojo.Student" %>
<%@ page import="com.xmx.dao.impl.StudentDaoImpl" %>
<%@ page import="com.xmx.service.impl.StudentServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" />
    <script src="https://www.layuicdn.com/layui-v2.5.6/layui.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/blueimp-file-upload/10.7.0/js/jquery.fileupload.min.js"></script>
    <script src="js/jquery.js"></script>
</head>

<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="stuindex.jsp">首页</a></li>
        <li><a href="stupersonal.jsp">个人资料</a></li>
    </ul>
</div>

<div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>

    <%
        Student student = (Student) session.getAttribute("user");
        int no = student.getStuNo();
        Student student1 = new StudentServiceImpl().findById(no);

    %>

    <form action="stuUserServlet" method="post" id="personalForm">
        <ul class="forminfo">
            <li><label>学号</label>
                <input name="stuNo" id="stuNo" type="text" value="<%=student1.getStuNo()%>" class="dfinput"
                       readonly/></li>
            <li><label>姓名</label>
                <input name="stuName" id="stuName" type="text" value="<%=student1.getStuName()%>" class="dfinput"
                       readonly/></li>

            <li>
                <label>头像</label>
                <img id="previewImg"
                     src="fileImages/<%=student1.getStuImg()%>" width="100px" height="100px">
                <input type="file" id="upImg" style="position: absolute; bottom: 430px; left: 230px" onchange="handleFileSelect(event)">
                <i style="padding-left: 2px"></i>
            </li>

            <li><label>密码</label>
                <input name="pwd" id="pwd" type="password" value="<%=student1.getPwd()%>" class="dfinput" /></li>
            <li><label>性别</label>
                <input type="radio" name="sex" value="男" <% if (student1.getSex().equals("男")) { %> checked
                    <% } else { %> disabled <% } %>>
                <i style="font-size: 14px; font-style: normal;"> 男
                </i>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" name="sex" value="女" <% if (student1.getSex().equals("女")) { %> checked
                    <% } else { %> disabled <% } %>>
                <i style="font-size: 14px; font-style: normal;"> 女
                </i>
            </li>
            <li><label>出生日期</label>
                <input type="text" class="dfinput" name="born" id="born" value="<%=student1.getBorn()%>">
            </li>
            <li><label>所属年级</label>
                <input name="stugrade" id="stugrade" value="<%=student1.getGrade().getgName()%>" type="text"
                       class="dfinput" readonly />
            </li>
            <li><label>所属班级</label>
                <input name="stuClass" id="stuClass" value="<%=student1.getClasses().getClassName()%>" type="text"
                       class="dfinput" readonly />
            </li>
            <li><label>联系电话</label>
                <input name="tel" id="tel" value="<%=student1.getPhone()%>" type="text" class="dfinput" /></li>
            <li><label>地址</label>
                <input name="address" id="address" type="text" value="<%=student1.getAddress()%>"
                       class="dfinput" /></li>
            <li><label>身份证</label>
                <input name="idcard" id="idcard" type="text" value="<%=student1.getIdCard()%>" class="dfinput" />
            </li>
            <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存" /></li>
            <span style="color: red; margin-left: 100px; font-size: 16px">${msg}</span>
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



    function handleFileSelect(event) {
        var fileInput = event.target;
        var file = fileInput.files[0]; // 获取选择的文件（假设只选择了一个文件）

        // 显示预览图像
        var imgElement = document.getElementById('previewImg');
        var reader = new FileReader();
        reader.onload = function (e) {
            imgElement.src = e.target.result;
        };
        reader.readAsDataURL(file);

        // 延迟一定时间后执行上传操作
        setTimeout(function () {
            uploadFile(file);
        }, 500); // 0.5秒后执行上传操作（单位为毫秒）
    }

    function uploadFile(file) {
        var form = document.getElementById('personalForm');
        var formData = new FormData(form);
        formData.append('file', file); // 将选择的文件添加到FormData对象中

        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'fileUploadServlet', true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // 上传成功后的操作
                console.log('文件上传成功！');
            }
        };
        xhr.send(formData);
    }
</script>
</body>

</html>
