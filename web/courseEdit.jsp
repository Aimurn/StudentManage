<%@ page import="com.xmx.pojo.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xmx.pojo.Grade" %>
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
        <li><a href="adindex.jsp">首页</a></li>
        <li><a href="adcourse.jsp">课程管理</a></li>
        <li><a href="adcourse.jsp">课程信息</a></li>
        <li><a href="#">编辑</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>课程信息</span></div>
    <%
        Course course = (Course) request.getAttribute("course");
    %>
    <form action="courseServlet?tag=update" method="post" id="personalForm">
    <ul class="forminfo">
        <li><label>课程编号</label><input name="couId" id="couId" type="text" value="<%=course.getcId()%>" class="dfinput"
                                      readonly /></li>
        <li><label>课程名称</label><input name="couName" id="couName" value="<%=course.getcName()%>" type="text" class="dfinput" /></li>
        <li><label>课程图片</label>
            <img src="fileImages/<%=course.getcImage()%>" width="100px" height="100px">
            <input type="file" id="previewImg" style="position: absolute; bottom: 430px; left: 230px"
                   onchange="handleFileSelect(event)">
            <i style="padding-left: 2px"></i>
        </li>
        <li><label>课时</label><input name="chour" id="chour" value="<%=course.getHour()%>" type="text" class="dfinput" /></li>
        <li><label>所属年级</label>
            <select class="select3 scinput1" name="grade">
                <option>全部</option>
                <%
                    List<Grade> list = (List<Grade>) request.getAttribute("grades");
                    for (Grade grade : list) {
                        if (course.getGrade().getgId() == grade.getgId()) {
                            out.print("<option value='" + grade.getgId() + "' selected>" + grade.getgName() +
                                    "</option>");
                        } else {
                            out.print("<option value='" + grade.getgId() + "'>" + grade.getgName() +
                                    "</option>");
                        }
                    }
                %>
            </select>
        </li>
        <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存" /></li>
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
        xhr.open('POST', 'fileUploadServlet?tag=update&cId=<%=course.getcId()%>', true);
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