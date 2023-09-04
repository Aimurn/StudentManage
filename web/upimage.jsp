<%--
  Created by IntelliJ IDEA.
  User: Aimurn
  Date: 2023/7/3
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/blueimp-file-upload/10.7.0/js/jquery.fileupload.min.js"></script>
    <script src="js/jquery.js"></script>
</head>
<body>
<form action="fileUploadServlet" id="uploadForm" enctype="multipart/form-data">
    <li>
        <label>头像</label>
        <img id="previewImg" src="" width="100px" height="100px">
        <input type="file" id="upImg" style="position: absolute; bottom: 430px; left: 230px" onchange="handleFileSelect(event)">
        <i style="padding-left: 2px"></i>
    </li>
    <li>
        <input type="button" value="上传" onclick="uploadFile()">
    </li>
</form>
<script>
    function handleFileSelect(event) {
        var fileInput = event.target;
        var file = fileInput.files[0]; // 获取选择的文件（假设只选择了一个文件）

        // 显示预览图像
        var imgElement = document.getElementById('previewImg');
        var reader = new FileReader();
        reader.onload = function(e) {
            imgElement.src = e.target.result;
        };
        reader.readAsDataURL(file);

        // 延迟一定时间后执行上传操作
        setTimeout(function() {
            uploadFile(file);
        }, 500); // 0.5秒后执行上传操作（单位为毫秒）
    }

    function uploadFile(file) {
        var form = document.getElementById('uploadForm');
        var formData = new FormData(form);
        formData.append('file', file); // 将选择的文件添加到FormData对象中

        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'fileUploadServlet', true);
        xhr.onreadystatechange = function() {
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
