<%@ page import="com.xmx.pojo.User" %>
<%@ page import="com.xmx.service.impl.UserServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" />
</head>

<body>

    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="adindex.jsp">首页</a></li>
            <li><a href="adpersonal.jsp">个人资料</a></li>
        </ul>
    </div>

    <%
        User user = (User) session.getAttribute("user");
        int no = user.getId();
        User user1 = new UserServiceImpl().findByUserId(no);
    %>

    <div class="formbody">

        <div class="formtitle"><span>基本信息</span></div>

        <form action="userServlet" method="post" id="personalForm">
        <ul class="forminfo">
            <li><label>用户ID</label>
                <input name="uId" id="uId" type="text" value="<%=user1.getId()%>" class="dfinput" readonly/></li>
            <li><label>用户名</label><input name="user" id="user" type="text" value="<%=user1.getUsername()%>"
                                         class="dfinput" /></li>
            <li><label>密码</label><input name="pwd" id="pwd" type="password" value="<%=user1.getPwd()%>"
                                        class="dfinput" /></li>
            <li><label>头像</label>
                <img id="previewImg" src="fileImages/<%=user1.getImage()%>" width="100px" height="100px">
                <input type="file" id="upImg" style="position: absolute; bottom: 385px; left: 230px"
                       onchange="handleFileSelect(event)">
                <i style="padding-left: 2px"></i>
            </li>
            <li><label>管理员类型</label>
                <input name="type" type="radio" value="1" <% if (user.getType() == 1) { %> checked
                        <% } else { %> disabled <% } %>/><i style="font-size: 14px; font-style: normal;"> 超级管理员</i>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="type" type="radio" value="0" <% if (user.getType() == 0) { %> checked
                    <% } else { %> disabled <% } %>/><i style="font-size: 14px; font-style: normal;"> 普通管理员</i></li>
            <li><label>&nbsp;</label><input name="btn" id="btn" type="submit" class="btn" value="确认保存" /></li>
            <span style="color: red; margin-left: 100px; font-size: 16px">${msg}</span>
        </ul>
    </form>
    </div>

<script>
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